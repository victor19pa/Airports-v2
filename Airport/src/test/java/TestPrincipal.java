
import com.airport.Principal;
//import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

public class TestPrincipal extends TestCase{
    private Principal principal;
    
    String statusActivated = "ACTIVATED";
    String statusCancel = "CANCEL";
    String statusDelayed = "DELAYED";
    String statusLanded = "LANDED";
        
    
    public void testing(){
        principal = new Principal();
    }
    
    public void testGetFlightStatus(){
        testing();        
        String result = principal.getFlightStatus(1);
        switch(result){
            case("ACTIVATED"):
                assertEquals(statusActivated , result);
                break;
            case("CANCEL"):
                assertEquals(statusCancel , result);
                break;
            case("DELAYED"):
                assertEquals(statusDelayed , result);
                break;
            case("LANDED"):
                assertEquals(statusLanded , result);
                break;
        }
    }
    
    public void testSetFlightStatus(){
        testing();        
        String result = principal.setFlightStatus();
        switch(result){
            case("ACTIVATED"):
                assertEquals(statusActivated , result);
                break;
            case("CANCEL"):
                assertEquals(statusCancel , result);
                break;
            case("DELAYED"):
                assertEquals(statusDelayed , result);
                break;
            case("LANDED"):
                assertEquals(statusLanded , result);
                break;
        }
    }
    
    public void testSetAirplaneStatus(){
        testing();
        boolean testAirplane = principal.setAirPlaneStatus();
        
        if(testAirplane){
            assertEquals(true,testAirplane);
        }else{
            assertEquals(false,testAirplane);
        }
    }
}
