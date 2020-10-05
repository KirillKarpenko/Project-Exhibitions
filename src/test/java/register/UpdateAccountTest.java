package register;

import controller.command.implementation.RegisterCommand;
import controller.command.implementation.UserUpdateAccountButtonCommand;
import controller.command.implementation.UserUpdateAccountPageCommand;
import model.entity.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateAccountTest {

    UserUpdateAccountButtonCommand command = new UserUpdateAccountButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession httpSession = Mockito.mock(HttpSession.class);

    @Before
    public void setup() {
        Account account = new Account();
        account.setId(2);
        account.setLogin("TempUser");
        account.setPassword("TempUser1");
        account.setFirstName("Temp");
        account.setLastName("User");
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(request.getSession().getAttribute("account")).thenReturn(account);
        Mockito.when(request.getParameter("login")).thenReturn("Kirill");
        Mockito.when(request.getParameter("password")).thenReturn("Kirill12");
        Mockito.when(request.getParameter("first_name")).thenReturn("Kirill");
        Mockito.when(request.getParameter("last_name")).thenReturn("Karpenko");
    }

    @Test
    public void process() {
        String updateAccount = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/user/account_info", updateAccount);
    }

}
