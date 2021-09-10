
import Classes.RegexClass;
import junit.framework.TestCase;

public class TestRegexClass extends TestCase {
    
    private RegexClass regexClass;
    
    public void testing(){
        regexClass = new RegexClass();
    }
    
    public void testValidateDate(){
        testing();  
        //cambiar el primer valor a true y en el 2do poner el patron correcto
        
        assertEquals(true, RegexClass.isAValidatedDate("09/09/2021 00:00"));
    }
    
    public void testValidateDouble(){
        testing();
        double randomDouble = Math.random()*Double.MAX_VALUE;
        assertEquals(true, RegexClass.isAValidatedDouble(Double.toString(randomDouble)));
    }
}
