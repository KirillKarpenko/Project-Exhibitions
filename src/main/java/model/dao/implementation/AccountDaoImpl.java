package model.dao.implementation;

import model.dao.AccountDao;
import model.entity.Account;
import org.apache.log4j.Logger;
import util.QueryManager;

import java.sql.*;

public class AccountDaoImpl implements AccountDao {

    private final Connection connection;
    private static final Logger log = Logger.getLogger(AccountDaoImpl.class);

    public AccountDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Account account) {
        String create = QueryManager.getString("account.insert");

        try (PreparedStatement preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, account.getLogin());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getFirstName());
            preparedStatement.setString(4, account.getLastName());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    account.setId(resultSet.getInt(1));
                    account.setRole(Account.Role.USER);
                }

                else
                    throw new SQLException("User with such login already exists!");
            }

            connection.commit();
            log.info("Account created");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Account creation rollback");
            } catch (SQLException ex) {
                log.error("Error: ", ex);
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public Account findByLogin(String login) {
        String findByLogin = QueryManager.getString("account.findByLogin");

        try (PreparedStatement preparedStatement = connection.prepareStatement(findByLogin)) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return getAccountFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Account findByLoginAndPassword(String login, String password) {
        String findByLoginAndPassword = QueryManager.getString("account.findByLoginAndPassword");

        try (PreparedStatement preparedStatement = connection.prepareStatement(findByLoginAndPassword)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next())
                    return getAccountFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Account account) {
        String update = QueryManager.getString("account.update");

        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, account.getLogin());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getFirstName());
            preparedStatement.setString(4, account.getLastName());
            preparedStatement.setInt(5, account.getId());

            if (preparedStatement.executeUpdate() == 0)
                throw new SQLException("There is nothing to update in account!");

            connection.commit();
            log.info("Account updated");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Account update rollback");
            } catch (SQLException ex) {
                log.error("Error: ", ex);
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String deleteById = QueryManager.getString("account.deleteById");

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteById)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() == 0)
                throw new SQLException("User with such id doesn't exist!");

            connection.commit();
            log.info("Account deleted");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Account deletion rollback");
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

    private Account getAccountFromResultSet(ResultSet resultSet) {
        Account account = new Account();

        try {
            account.setId(resultSet.getInt(1));
            account.setLogin(resultSet.getString(2));
            account.setPassword(resultSet.getString(3));
            account.setFirstName(resultSet.getString(4));
            account.setLastName(resultSet.getString(5));
            account.setRole(Account.Role.valueOf(resultSet.getString(6).toUpperCase()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

}
