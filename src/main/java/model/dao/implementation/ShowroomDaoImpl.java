package model.dao.implementation;

import model.dao.ShowroomDao;
import model.entity.Exposition;
import model.entity.Showroom;
import org.apache.log4j.Logger;
import util.QueryManager;
import util.ThreadLocalWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowroomDaoImpl implements ShowroomDao {

    private final Connection connection;
    private static final Logger log = Logger.getLogger(ShowroomDaoImpl.class);

    public ShowroomDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Showroom showroom) {
        String create = QueryManager.getString("showroom.insert");

        try (PreparedStatement preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, showroom.getName());
            preparedStatement.setInt(2, showroom.getExposition().getId());
            preparedStatement.setString(3, ThreadLocalWrapper.getLocale().toString());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next())
                    showroom.setId(resultSet.getInt(1));

                else
                    throw new SQLException("Showroom with such name already exists!");
            }

            connection.commit();
            log.info("Showroom created");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Showroom creation rollback");
            } catch (SQLException ex) {
                log.error("Error: ", ex);
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void createWithoutExposition(Showroom showroom) {
        String createWithoutExposition = QueryManager.getString("showroom.insertWithoutExposition");

        try (PreparedStatement preparedStatement = connection.prepareStatement(createWithoutExposition, Statement.RETURN_GENERATED_KEYS
        )) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, showroom.getName());
            preparedStatement.setString(2, ThreadLocalWrapper.getLocale().toString());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next())
                    showroom.setId(resultSet.getInt(1));

                else
                    throw new SQLException("Showroom with such name already exists!");
            }

            connection.commit();
            log.info("Showroom without exposition created");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Showroom without exposition creation rollback");
            } catch (SQLException ex) {
                log.error("Error: ", ex);
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public Showroom findByName(String name) {
        String findByName = QueryManager.getString("showroom.findByName");

        try (PreparedStatement preparedStatement = connection.prepareStatement(findByName)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, ThreadLocalWrapper.getLocale().toString());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return getShowroomFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Showroom> findByPage(int start, int total) {
        String findByPage = QueryManager.getString("showroom.findByPage");
        List<Showroom> showrooms = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(findByPage)) {
            preparedStatement.setString(1, ThreadLocalWrapper.getLocale().toString());
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    showrooms.add(getShowroomFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return showrooms;
    }

    @Override
    public int amount() {
        String amount = QueryManager.getString("showroom.amount");

        try (PreparedStatement preparedStatement = connection.prepareStatement(amount)) {
            preparedStatement.setString(1, ThreadLocalWrapper.getLocale().toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void updateAndSetExpositionNull(Showroom showroom) {
        String updateSetExpositionNull = QueryManager.getString("showroom.updateSetExpositionNull");

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSetExpositionNull)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, showroom.getName());
            preparedStatement.setString(2, ThreadLocalWrapper.getLocale().toString());
            preparedStatement.setInt(3, showroom.getId());

            if (preparedStatement.executeUpdate() == 0)
                throw new SQLException("There is nothing to update in showroom!");

            connection.commit();
            log.info("Showroom updated, exposition set to null");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Showroom update and exposition setting to null rollback");
            } catch (SQLException ex) {
                log.error("Error: ", ex);
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void update(Showroom showroom) {
        String update = QueryManager.getString("showroom.update");

        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, showroom.getName());
            preparedStatement.setInt(2, showroom.getExposition().getId());
            preparedStatement.setString(3, ThreadLocalWrapper.getLocale().toString());
            preparedStatement.setInt(4, showroom.getId());

            if (preparedStatement.executeUpdate() == 0)
                throw new SQLException("There is nothing to update in showroom!");

            connection.commit();
            log.info("Showroom updated");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Showroom update rollback");
            } catch (SQLException ex) {
                log.error("Error: ", ex);
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String deleteById = QueryManager.getString("showroom.deleteById");

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteById)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() == 0)
                throw new SQLException("Showroom with such id doesn't exist!");

            connection.commit();
            log.info("Showroom deleted");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Showroom deletion rollback");
            } catch (SQLException ex) {
                log.error("Error: ", ex);
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Error: ", e);
            e.printStackTrace();
        }
    }

    private Showroom getShowroomFromResultSet(ResultSet resultSet) {
        Showroom showroom = new Showroom();

        try {
            showroom.setId(resultSet.getInt("showroom.id"));
            showroom.setName(resultSet.getString("showroom.name"));
            Exposition exposition = new Exposition();
            exposition.setId(resultSet.getInt("exposition.id"));
            String name = resultSet.getString("exposition.name");

            if (!resultSet.wasNull())
                exposition.setName(name);

            String category = resultSet.getString("exposition.category");

            if (!resultSet.wasNull())
                exposition.setCategory(Exposition.Category.valueOf(category.toUpperCase()));

            Timestamp startDate = resultSet.getTimestamp("exposition.start_date");

            if (!resultSet.wasNull())
                exposition.setStartDate(startDate);

            Timestamp endDate = resultSet.getTimestamp("exposition.end_date");

            if (!resultSet.wasNull())
                exposition.setEndDate(endDate);

            double price = resultSet.getDouble("exposition.price");

            if (!resultSet.wasNull())
                exposition.setPrice(price);

            showroom.setExposition(exposition);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return showroom;
    }

}
