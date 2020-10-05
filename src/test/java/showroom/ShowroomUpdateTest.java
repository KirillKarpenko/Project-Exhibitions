package showroom;

import controller.command.implementation.AdminCreateShowroomButtonCommand;
import controller.command.implementation.AdminUpdateShowroomButtonCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowroomUpdateTest {

    AdminUpdateShowroomButtonCommand command = new AdminUpdateShowroomButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @Before
    public void setup() {
        Mockito.when(request.getParameter("showroom")).thenReturn("3,Temp Showroom,6,AgroExpo,OTHER,2020-11-10 00:00:00.0,2020-11-17 00:00:00.0,24.99");
        Mockito.when(request.getParameter("showroom_name")).thenReturn("Updated Temp Showroom");
    }

    @Test
    public void test() {
        String updateShowroom = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/admin/showrooms?showroomPage=1", updateShowroom);
    }

}
