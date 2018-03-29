
import static org.junit.Assert.*;

/**
 * Created by Steve on 29/03/2018.
 *
 *
 * Tests element of Game 2: lotto numbers
 *
 */
public class game2Test {

    @org.junit.Test
    public void testGenerateRandomNumberArray() throws Exception {
        int numberLimit = 6;
        int[] winningNumbers;
        winningNumbers = main.generateRandomNumberArray(numberLimit);
        assertEquals(6,winningNumbers.length );
    }
}



