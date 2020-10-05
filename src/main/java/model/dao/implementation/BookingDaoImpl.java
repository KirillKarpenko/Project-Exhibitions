package model.dao.implementation;

import model.dao.BookingDao;
import model.entity.Account;
import model.entity.Booking;
import model.entity.Exposition;
import model.entity.Ticket;
import org.apache.log4j.Logger;
import util.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao {

    private final Connection connection;
    private static final Logger log = Logger.getLogger(BookingDaoImpl.class);

    public BookingDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Booking booking) {
        String create = QueryManager.getString("booking.insert");

        try (PreparedStatement preparedStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setDouble(1, booking.getTotal());
            preparedStatement.setInt(2, booking.getAccount().getId());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    booking.setId(resultSet.getInt(1));
                    booking.setPaid(false);
                    createTicketList(booking);
                }

                else
                    throw new SQLException("Pay for previous booking before new one!");
            }

            connection.commit();
            log.info("Booking created");
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

    private void createTicketList(Booking booking) {
        String create = QueryManager.getString("ticket.insert");

        if (booking.getTickets().isEmpty())
            throw new RuntimeException("Ticket list is empty!");

        for (Ticket ticket : booking.getTickets()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(create)) {
                preparedStatement.setInt(1, booking.getId());
                preparedStatement.setInt(2, ticket.getExposition().getId());
                preparedStatement.setInt(3, ticket.getQuantity());

                if (preparedStatement.executeUpdate() == 0)
                    throw new SQLException("Booking already exists!");

                log.info("Tickets created");
            } catch (SQLException e) {
                log.error("Error: ", e);

                try {
                    connection.rollback();
                    log.info("Ticket creation rollback");
                } catch (SQLException ex) {
                    log.error("Error: ", ex);
                    ex.printStackTrace();
                }

                e.printStackTrace();
            }
        }
    }

    @Override
    public Booking findUnpaidBookingByAccountId(int id) {
        String findUnpaidBookingByAccount = QueryManager.getString("booking.findUnpaidBookingByAccountId");

        try (PreparedStatement preparedStatement = connection.prepareStatement(findUnpaidBookingByAccount)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Booking booking = getBookingFromResultSet(resultSet);
                    booking.setTickets(getTicketsFromResultSet(resultSet));
                    return booking;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Booking findUnpaidById(int id) {
        String findUnpaidBookingByPage = QueryManager.getString("booking.findUnpaidBookingById");

        try (PreparedStatement preparedStatement = connection.prepareStatement(findUnpaidBookingByPage)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Booking booking = getBookingFromResultSet(resultSet);
                    booking.setTickets(getTicketsFromResultSet(resultSet));
                    return booking;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Booking> findAccountBookingsByPage(int id, int start, int total) {
        String findAccountBookingsByPage = QueryManager.getString("booking.findAccountBookingsByPage");
        List<Booking> bookings = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(findAccountBookingsByPage)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, total);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Booking booking = getBookingFromResultSet(resultSet);
                    booking.setTickets(getTicketsFromResultSet(resultSet));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    @Override
    public int accountAmount(int id) {
        String accountAmount = QueryManager.getString("booking.accountAmount");

        try (PreparedStatement preparedStatement = connection.prepareStatement(accountAmount)) {
            preparedStatement.setInt(1, id);

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
    public void updatePaidInformationById(int id) {
        String updatePaidInformationById = QueryManager.getString("booking.updatePaidInformationById");

        try (PreparedStatement preparedStatement = connection.prepareStatement(updatePaidInformationById)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() == 0)
                throw new SQLException("There is nothing to update in booking!");

            connection.commit();
            log.info("Paid information updated");
        } catch (SQLException e) {
            log.error("Error: ", e);

            try {
                connection.rollback();
                log.info("Paid information update rollback");
            } catch (SQLException ex) {
                log.error("Error: ", ex);
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void update(Booking booking) {
        String update = QueryManager.getString("booking.update");

        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            connection.setAutoCommit(false);
            preparedStatement.setDouble(1, booking.getTotal());
            preparedStatement.setBoolean(2, booking.isPaid());
            preparedStatement.setInt(3, booking.getId());

            if (preparedStatement.executeUpdate() == 0)
                throw new SQLException("There is nothing to update in booking!");

            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String deleteById = QueryManager.getString("booking.deleteById");

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteById)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() == 0)
                throw new SQLException("Booking with such id doesn't exist!");

            connection.commit();
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException ex) {
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

    private Booking getBookingFromResultSet(ResultSet resultSet) {
        Booking booking = new Booking();

        try {
            booking.setId(resultSet.getInt("booking.id"));
            booking.setTotal(resultSet.getDouble("booking.total"));
            Account account = new Account();
            account.setId(resultSet.getInt("account.id"));
            account.setLogin(resultSet.getString("account.login"));
            account.setPassword(resultSet.getString("account.password"));
            account.setFirstName(resultSet.getString("account.first_name"));
            account.setLastName(resultSet.getString("account.last_name"));
            account.setRole(Account.Role.valueOf(resultSet.getString("account.role").toUpperCase()));
            booking.setAccount(account);
            booking.setPaid(resultSet.getBoolean("booking.paid"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;
    }

    private List<Ticket> getTicketsFromResultSet(ResultSet resultSet) {
        List<Ticket> tickets = new ArrayList<>();

        try {
            do {
                Ticket ticket = new Ticket();
                Exposition exposition = new Exposition();
                exposition.setId(resultSet.getInt("exposition.id"));
                exposition.setName(resultSet.getString("exposition.name"));
                exposition.setCategory(Exposition.Category.valueOf(resultSet.getString("exposition.category").toUpperCase()));
                exposition.setStartDate(resultSet.getTimestamp("exposition.start_date"));
                exposition.setEndDate(resultSet.getTimestamp("exposition.end_date"));
                exposition.setPrice(resultSet.getDouble("exposition.price"));
                ticket.setExposition(exposition);
                ticket.setQuantity(resultSet.getInt("ticket.quantity"));
                tickets.add(ticket);
            } while (resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

}
