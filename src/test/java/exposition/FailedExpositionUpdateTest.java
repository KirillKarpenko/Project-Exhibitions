package exposition;

import controller.command.implementation.AdminUpdateExpositionButtonCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FailedExpositionUpdateTest {

    AdminUpdateExpositionButtonCommand command = new AdminUpdateExpositionButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession httpSession = Mockito.mock(HttpSession.class);

    @Before
    public void setup() {
        Mockito.when(request.getParameter("exposition")).thenReturn("7,Updated Temp Exposition,OTHER,2020-10-01 00:00:00.0,2020-10-02 00:00:00.0,0.99");
        Mockito.when(request.getParameter("exposition_name")).thenReturn("AgroExpo");
        Mockito.when(request.getSession()).thenReturn(httpSession);
    }

    @Test
    public void test() {
        String updateExposition = command.execute(request, response);
        Assert.assertEquals("/jsp/admin/update_exposition.jsp", updateExposition);
    }

}
