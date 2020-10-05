package exposition;

import controller.command.implementation.AdminCreateExpositionButtonCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FailedExpositionCreationTest {

    AdminCreateExpositionButtonCommand command = new AdminCreateExpositionButtonCommand();
    HttpSession httpSession = Mockito.mock(HttpSession.class);
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @Before
    public void setup() {
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(request.getParameter("exposition_name")).thenReturn("AgroExpo");
        Mockito.when(request.getParameter("start_date")).thenReturn("2020-10-01");
        Mockito.when(request.getParameter("end_date")).thenReturn("2020-10-02");
        Mockito.when(request.getParameter("price")).thenReturn("0.99");
        Mockito.when(request.getParameter("category")).thenReturn("OTHER");
    }

    @Test
    public void test() {
        String createExposition = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/admin/create_exposition", createExposition);
    }

}
