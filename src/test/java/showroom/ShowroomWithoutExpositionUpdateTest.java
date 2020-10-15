package showroom;

import controller.command.implementation.AdminCreateShowroomButtonCommand;
import controller.command.implementation.AdminUpdateShowroomButtonCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowroomWithoutExpositionUpdateTest {

    AdminUpdateShowroomButtonCommand command = new AdminUpdateShowroomButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession session = Mockito.mock(HttpSession.class);

    @Before
    public void setup() {
        Mockito.when(request.getParameter("showroom")).thenReturn("4,Temp Showroom (no exposition),0,null,null,null,null,0");
        Mockito.when(request.getParameter("showroom_name")).thenReturn("Updated Temp Showroom (no exp)");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getSession().getAttribute("lang")).thenReturn("en");
    }

    @Test
    public void test() {
        String createShowroom = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/admin/showrooms?showroomPage=1", createShowroom);
    }

}
