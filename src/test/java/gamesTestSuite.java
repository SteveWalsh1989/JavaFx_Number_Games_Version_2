import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;


/**
 * Created by Steve on 29/03/2018.
 *
 *
 * Junit Test Suite to run all tests within
 * the game1Test and game2Test classes
 *
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({game1Test.class, game2Test.class})

public class gamesTestSuite {

    public static void main(String[] args){

        Result result = JUnitCore.runClasses(gamesTestSuite.class);

        for(Failure failure : result.getFailures()){

            System.out.println(failure.toString());

        }
            System.out.println(result.wasSuccessful());


    }








}
