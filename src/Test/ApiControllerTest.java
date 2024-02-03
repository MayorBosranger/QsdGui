package Test;

import Main.ApiController;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.*;

public class ApiControllerTest {
    @Test
    public void Test_connection_setup(){
        ApiController controller = new ApiController();

        HttpURLConnection connection = controller.startConnection();
        try{
            connection.getOutputStream();
        }catch(IOException e){
            assert(false);
        }
        assert(true);

        }
    }


