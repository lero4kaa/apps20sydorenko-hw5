package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author andrii
 */
public class StreamAppTest {
    
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testAverage() {
        double expResult = 1.0;
        double result = intStream.average();
        assertEquals(expResult, result, 0.01);
    }

    @Test
    public void testSum() {
        double expResult = 5;
        double result = intStream.sum();
        assertEquals(expResult, result, 0.01);
    }

    @Test
    public void testCount() {
        double expResult = 5;
        double result = intStream.count();
        assertEquals(expResult, result, 0.01);
    }

    @Test
    public void testMax() {
        Integer expResult = 3;
        Integer result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testMin() {
        Integer expResult = -1;
        Integer result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);        
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);        
    }
    
}
