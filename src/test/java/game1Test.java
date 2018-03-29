import static org.junit.Assert.assertEquals;

/**
 * Created by Steve on 29/03/2018.
 */
public class game1Test {


    @org.junit.Test
    public void testStoreUserInput(){

            int userGuess = 6;
            int winningNumber =6;

        main.compareNumbers(userGuess, winningNumber);

        assertEquals(userGuess,winningNumber );
    }





}
