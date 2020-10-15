package register;

import controller.command.implementation.RegisterCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class FailedRegisterTest {

    RegisterCommand command = new RegisterCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession httpSession = Mockito.mock(HttpSession.class);

    @Before
    public void setup() {
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(request.getParameter("login")).thenReturn("TempUser");
        Mockito.when(request.getParameter("password")).thenReturn("TempUser");
        Mockito.when(request.getParameter("first_name")).thenReturn("Temp");
        Mockito.when(request.getParameter("last_name")).thenReturn("User");
    }

    @Test
    public void process() {
        String registerAccount = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/register", registerAccount);
    }

}
