package booking;

import controller.command.implementation.AdminCreateExpositionButtonCommand;
import controller.command.implementation.AdminUpdatePaidInformationButtonCommand;
import controller.command.implementation.UserSendOrderButtonCommand;
import model.entity.Account;
import model.entity.Booking;
import model.entity.Exposition;
import model.entity.Ticket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CreateBookingTest {

    UserSendOrderButtonCommand command = new UserSendOrderButtonCommand();
    AdminUpdatePaidInformationButtonCommand adminCommand = new AdminUpdatePaidInformationButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession httpSession = Mockito.mock(HttpSession.class);

    @Before
    public void setup() {
        Account account = new Account();
        account.setId(1);
        account.setLogin("Kibzya");
        account.setPassword("Kibzya12");
        account.setFirstName("Kirill");
        account.setLastName("Karpenko");
        account.setRole(Account.Role.ADMIN);
        Exposition exposition = new Exposition();
        exposition.setId(2);
        exposition.setName("ComicCon");
        exposition.setCategory(Exposition.Category.ENTERTAINMENT);
        exposition.setStartDate(Timestamp.valueOf("2020-10-09 00:00:00.0"));
        exposition.setEndDate(Timestamp.valueOf("2020-10-16 00:00:00.0"));
        exposition.setPrice(99.99);
        Ticket ticket = new Ticket();
        ticket.setExposition(exposition);
        ticket.setQuantity(1);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(request.getSession().getAttribute("account")).thenReturn(account);
        Mockito.when(request.getSession().getAttribute("cart")).thenReturn(tickets);
        Mockito.when(request.getParameter("total")).thenReturn("99.99");
        Mockito.when(request.getParameter("booking_id")).thenReturn("1");
    }
    
    @Test
    public void test() {
        String createBooking = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/user/", createBooking);
        String updatePaidInformation = adminCommand.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/admin", updatePaidInformation);
    }

}
