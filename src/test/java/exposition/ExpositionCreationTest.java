package exposition;

import controller.command.implementation.AdminCreateExpositionButtonCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExpositionCreationTest {

    AdminCreateExpositionButtonCommand command = new AdminCreateExpositionButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @Before
    public void setup() {
        Mockito.when(request.getParameter("exposition_name")).thenReturn("Temp Exposition");
        Mockito.when(request.getParameter("start_date")).thenReturn("2020-10-01");
        Mockito.when(request.getParameter("end_date")).thenReturn("2020-10-02");
        Mockito.when(request.getParameter("price")).thenReturn("0.99");
        Mockito.when(request.getParameter("category")).thenReturn("OTHER");
    }

    @Test
    public void test() {
        String createExposition = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/admin/expositions?expositionPage=1", createExposition);
    }

}
