package Classes;

import Entities.EFlight;
import java.util.List;
import junit.framework.TestCase;

public class EmailTest extends TestCase {
    
    public EmailTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of sendReportbyId method, of class Email.
     */
    public void testSendReportbyId() {
        System.out.println("sendReportbyId");
        int idFlight = 0;
        String mail = "airtraffic.report@gmail.com";
        List<EFlight> flightList = null;
        Email instance = new Email();
        instance.sendReportbyId(idFlight, mail, flightList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendReportbyDate method, of class Email.
     */
    public void testSendReportbyDate() throws Exception {
        System.out.println("sendReportbyDate");
        String dateFlight = "";
        String mail = "";
        List<EFlight> flightList = null;
        Email instance = new Email();
        instance.sendReportbyDate(dateFlight, mail, flightList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFile method, of class Email.
     */
    public void testCreateFile() {
        System.out.println("createFile");
        List<EFlight> flightList = null;
        Email instance = new Email();
        instance.createFile(flightList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SendMail method, of class Email.
     */
    public void testSendMail() {
        System.out.println("SendMail");
        String mail = "";
        Email instance = new Email();
        instance.SendMail(mail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
