package showroom;

import controller.command.implementation.AdminUpdateShowroomButtonCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FailedShowroomUpdateTest {

    AdminUpdateShowroomButtonCommand command = new AdminUpdateShowroomButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession httpSession = Mockito.mock(HttpSession.class);

    @Before
    public void setup() {
        Mockito.when(request.getParameter("showroom")).thenReturn("2,Showroom №2,2,ComicCon,ENTERTAINMENT,2020-10-09 00:00:00.0,2020-10-16 00:00:00.0,99.99");
        Mockito.when(request.getParameter("showroom_name")).thenReturn("Showroom №1");
        Mockito.when(request.getSession()).thenReturn(httpSession);
    }

    @Test
    public void test() {
        String updateShowroom = command.execute(request, response);
        Assert.assertEquals("/jsp/admin/update_showroom.jsp", updateShowroom);
    }

}
