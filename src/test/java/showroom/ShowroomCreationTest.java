package showroom;

import controller.command.implementation.AdminCreateExpositionButtonCommand;
import controller.command.implementation.AdminCreateShowroomButtonCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowroomCreationTest {

    AdminCreateShowroomButtonCommand command = new AdminCreateShowroomButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @Before
    public void setup() {
        Mockito.when(request.getParameter("showroom_name")).thenReturn("Temp Showroom");
        Mockito.when(request.getParameter("exposition_name")).thenReturn("AgroExpo");
    }

    @Test
    public void test() {
        String createShowroom = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/admin/showrooms?showroomPage=1", createShowroom);
    }

}
