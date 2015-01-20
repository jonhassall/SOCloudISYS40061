import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonathan Hassall
 */
public class SimpleClassTest {
    
    public SimpleClassTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addTwoNumbers method, of class SimpleClass.
     */
    @Test
    public void testAddTwoNumbers() {
        System.out.println("addTwoNumbers");
        
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                int arg1 = i;
                int arg2 = j;
                SimpleClass instance = new SimpleClass();
                int expResult = i + j;
                int result = instance.addTwoNumbers(arg1, arg2);
//                System.out.println("* SimpleClassTest: addTwoNumbers(" + arg1 + ", " + arg2 + ")");
                assertEquals(expResult, result);
            }
        }
//        int arg1 = 2;
//        int arg2 = 2;
//        SimpleClass instance = new SimpleClass();
//        int expResult = 4;
//        int result = instance.addTwoNumbers(arg1, arg2);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
