package register;

import controller.command.implementation.AdminUpdateShowroomButtonCommand;
import controller.command.implementation.UserDeleteAccountButtonCommand;
import model.entity.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteAccountTest {

    UserDeleteAccountButtonCommand command = new UserDeleteAccountButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession httpSession = Mockito.mock(HttpSession.class);

    @Before
    public void setup() {
        Account account = new Account();
        account.setId(1);
        account.setLogin("Kirill");
        account.setPassword("Kirill12");
        account.setFirstName("Kirill");
        account.setLastName("Karpenko");
        account.setRole(Account.Role.ADMIN);
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(request.getSession().getAttribute("account")).thenReturn(account);
    }

    @Test
    public void test() {
        String deleteAccount = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/index?expositionPage=1", deleteAccount);
    }

}
