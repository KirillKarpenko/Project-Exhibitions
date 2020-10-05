package exposition;

import controller.command.implementation.AdminDeleteExpositionButtonCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExpositionDeleteTest {

    AdminDeleteExpositionButtonCommand command = new AdminDeleteExpositionButtonCommand();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @Before
    public void setup() {
        Mockito.when(request.getParameter("exposition_id")).thenReturn("1");
    }

    @Test
    public void test() {
        String deleteExposition = command.execute(request, response);
        Assert.assertEquals("redirect: /exhibitions/admin/expositions?expositionPage=1", deleteExposition);
    }

}
