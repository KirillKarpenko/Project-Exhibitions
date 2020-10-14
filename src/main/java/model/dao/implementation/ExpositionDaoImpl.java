package model.dao.implementation;

import model.dao.ExpositionDao;
import model.entity.Exposition;
import org.apache.log4j.Logger;
import util.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpositionDaoImpl implements ExpositionDao {

    private final Connection connection;
    private static final Logger log = Logger.getLogger(ExpositionDaoImpl.class);

    public ExpositionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Exposition exposition) {
        String create = QueryManager.getString("exposition.insert");

        try (PreparedStatement preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, exposition.getName());
            preparedStatement.setString(2, exposition.getCategory().name());
            preparedStatement.setTimestamp(3, exposition.getStartDate());
            preparedStatement.setTimestamp(4, exposition.getEndDate());
            preparedStatement.setDouble(5, exposition.getPrice());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next())
                    exposition.setId(resultSet.getInt(1));

                else
                    throw new SQLException("Exposition with such name already exists!");
            }

            connection.commit();
            log.info("Exposition created");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Exposition creation rollback");
            } catch (SQLException ex) {
                log.error("Error: ", ex);
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public Exposition findByName(String name) {
        String findByName = QueryManager.getString("exposition.findByName");

        try (PreparedStatement preparedStatement = connection.prepareStatement(findByName)) {
            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return getExpositionFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Exposition> findByPage(int start, int total) {
        String findByPage = QueryManager.getString("exposition.findByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(findByPage)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public List<String> findExpositionsNames() {
        String findExpositionsNames = QueryManager.getString("exposition.findExpositionsNames");
        List<String> expositionsNames = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findExpositionsNames)) {

            while (resultSet.next())
                expositionsNames.add(resultSet.getString(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositionsNames;
    }

    @Override
    public int amount() {
        String amount = QueryManager.getString("exposition.amount");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(amount)) {
            if (resultSet.next())
                return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Exposition> filterByDateByPage(Timestamp startDate, Timestamp endDate, int start, int total) {
        String filterByDateByPage = QueryManager.getString("exposition.filterByDateByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(filterByDateByPage)) {
            preparedStatement.setTimestamp(1, startDate);
            preparedStatement.setTimestamp(2, endDate);
            preparedStatement.setInt(3, start);
            preparedStatement.setInt(4, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public int dateAmount(Timestamp startDate, Timestamp endDate) {
        String dateAmount = QueryManager.getString("exposition.dateAmount");

        try (PreparedStatement preparedStatement = connection.prepareStatement(dateAmount)) {
            preparedStatement.setTimestamp(1, startDate);
            preparedStatement.setTimestamp(2, endDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Exposition> sortByPriceAscByPage(int start, int total) {
        String sortByPriceAscByPage = QueryManager.getString("exposition.sortByPriceAscByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sortByPriceAscByPage)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public List<Exposition> sortByPriceDescByPage(int start, int total) {
        String sortByPriceDescByPage = QueryManager.getString("exposition.sortByPriceDescByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sortByPriceDescByPage)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public List<Exposition> filterByCategoryByPage(Exposition.Category category, int start, int total) {
        String filterByCategoryByPage = QueryManager.getString("exposition.filterByCategoryByPage");

        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(filterByCategoryByPage)) {
            preparedStatement.setString(1, category.name());
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public int categoryAmount(Exposition.Category category) {
        String categoryAmount = QueryManager.getString("exposition.categoryAmount");

        try (PreparedStatement preparedStatement = connection.prepareStatement(categoryAmount)) {
            preparedStatement.setString(1, category.name());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Exposition> sortByPriceAscAndFilterByCategoryByPage(Exposition.Category category, int start, int total) {
        String query = QueryManager.getString("exposition.sortByPriceAscAndFilterByCategoryByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.name());
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public List<Exposition> sortByPriceDescAndFilterByCategoryByPage(Exposition.Category category, int start, int total) {
        String query = QueryManager.getString("exposition.sortByPriceDescAndFilterByCategoryByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.name());
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public List<Exposition> sortByPriceAscAndFilterByDateByPage(Timestamp startDate, Timestamp endDate, int start, int total) {
        String query = QueryManager.getString("exposition.sortByPriceAscAndFilterByDateByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, startDate);
            preparedStatement.setTimestamp(2, endDate);
            preparedStatement.setInt(3, start);
            preparedStatement.setInt(4, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public List<Exposition> sortByPriceDescAndFilterByDateByPage(Timestamp startDate, Timestamp endDate, int start, int total) {
        String query = QueryManager.getString("exposition.sortByPriceDescAndFilterByDateByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, startDate);
            preparedStatement.setTimestamp(2, endDate);
            preparedStatement.setInt(3, start);
            preparedStatement.setInt(4, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public List<Exposition> filterByDateAndCategoryByPage(Timestamp startDate, Timestamp endDate, Exposition.Category category, int start, int total) {
        String query = QueryManager.getString("exposition.filterByDateAndCategoryByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, startDate);
            preparedStatement.setTimestamp(2, endDate);
            preparedStatement.setString(3, category.name());
            preparedStatement.setInt(4, start);
            preparedStatement.setInt(5, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public List<Exposition> sortByPriceAscAndFilterByCategoryAndDateByPage(Timestamp startDate, Timestamp endDate, Exposition.Category category, int start, int total) {
        String query = QueryManager.getString("exposition.sortByPriceAscAndFilterByCategoryAndDateByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, startDate);
            preparedStatement.setTimestamp(2, endDate);
            preparedStatement.setString(3, category.name());
            preparedStatement.setInt(4, start);
            preparedStatement.setInt(5, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public List<Exposition> sortByPriceDescAndFilterByCategoryAndDateByPage(Timestamp startDate, Timestamp endDate, Exposition.Category category, int start, int total) {
        String query = QueryManager.getString("exposition.sortByPriceDescAndFilterByCategoryAndDateByPage");
        List<Exposition> expositions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, startDate);
            preparedStatement.setTimestamp(2, endDate);
            preparedStatement.setString(3, category.name());
            preparedStatement.setInt(4, start);
            preparedStatement.setInt(5, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next())
                    expositions.add(getExpositionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expositions;
    }

    @Override
    public int categoryAndDateAmount(Timestamp startDate, Timestamp endDate, Exposition.Category category) {
        String categoryAndDateAmount = QueryManager.getString("exposition.categoryAndDateAmount");

        try (PreparedStatement preparedStatement = connection.prepareStatement(categoryAndDateAmount)) {
            preparedStatement.setTimestamp(1, startDate);
            preparedStatement.setTimestamp(2, endDate);
            preparedStatement.setString(3, category.name());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void update(Exposition exposition) {
        String update = QueryManager.getString("exposition.update");

        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, exposition.getName());
            preparedStatement.setString(2, exposition.getCategory().name());
            preparedStatement.setTimestamp(3, exposition.getStartDate());
            preparedStatement.setTimestamp(4, exposition.getEndDate());
            preparedStatement.setDouble(5, exposition.getPrice());
            preparedStatement.setInt(6, exposition.getId());

            if (preparedStatement.executeUpdate() == 0)
                throw new SQLException("There is nothing to update in exposition!");

            connection.commit();
            log.info("Exposition updated");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Exposition update rollback");
            } catch (SQLException ex) {
                log.error("Error: ", ex);
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String deleteById = QueryManager.getString("exposition.deleteById");

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteById)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() == 0)
                throw new SQLException("Exposition with such id doesn't exist!");

            connection.commit();
            log.info("Exposition deleted");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Exposition deletion rollback");
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

    private Exposition getExpositionFromResultSet(ResultSet resultSet) {
        Exposition exposition = new Exposition();

        try {
            exposition.setId(resultSet.getInt(1));
            exposition.setName(resultSet.getString(2));
            exposition.setCategory(Exposition.Category.valueOf(resultSet.getString(3).toUpperCase()));
            exposition.setStartDate(resultSet.getTimestamp(4));
            exposition.setEndDate(resultSet.getTimestamp(5));
            exposition.setPrice(resultSet.getDouble(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exposition;
    }

}
