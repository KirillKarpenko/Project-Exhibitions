package showroom;

import controller.command.implementation.AdminDeleteExpositionButtonCommand;
import controller.command.implementation.AdminDeleteShowroomButtonCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowroomDeleteTest {

    AdminDeleteShowroomButtonCommand command = new AdminDeleteShowroomButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @Before
    public void setup() {
        Mockito.when(request.getParameter("showroom_id")).thenReturn("1");
    }

    @Test
    public void test() {
        String deleteShowroom = command.execute(request, response);
        Assert.assertEquals("/exhibitions/admin/showrooms?showroomPage=1", deleteShowroom);
    }

}
