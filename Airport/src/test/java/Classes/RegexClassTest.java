/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import junit.framework.TestCase;

/**
 *
 * @author victo
 */
public class RegexClassTest extends TestCase {
    
    public RegexClassTest(String testName) {
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
     * Test of isAValidatedDate method, of class RegexClass.
     */
    public void testIsAValidatedDate() {
        System.out.println("isAValidatedDate");
        String date = "09/09/2021 00:00";
        boolean expResult = true;
        boolean result = RegexClass.isAValidatedDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result != expResult){
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of isAValidatedDouble method, of class RegexClass.
     */
    public void testIsAValidatedDouble() {
        System.out.println("isAValidatedDouble");
        double randomDouble = Math.random()*Double.MAX_VALUE;        
        String capacity = Double.toString(randomDouble);
        
        boolean expResult = true;
        boolean result = RegexClass.isAValidatedDouble(capacity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result != expResult){
            fail("The test case is a prototype.");
        }
    }
}
