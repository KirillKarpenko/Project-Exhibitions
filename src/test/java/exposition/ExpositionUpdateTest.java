package exposition;

import controller.command.implementation.AdminCreateExpositionButtonCommand;
import controller.command.implementation.AdminUpdateExpositionButtonCommand;
import model.entity.Exposition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExpositionUpdateTest {

    AdminUpdateExpositionButtonCommand command = new AdminUpdateExpositionButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @Before
    public void setup() {
        Mockito.when(request.getParameter("exposition")).thenReturn("7,Temp Exposition,OTHER,2020-10-01 00:00:00.0,2020-10-02 00:00:00.0,0.99");
        Mockito.when(request.getParameter("exposition_name")).thenReturn("Updated Temp Exposition");
    }

    @Test
    public void test() {
        String updateExposition = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/admin/expositions?expositionPage=1", updateExposition);
    }

}
