import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * ------------------------------------------------------------------------------------------------
 * Details :
 *
 * Module: Object Orientated programming
 *
 * Assignment 2: Maven, using imported Jar and TreeLists
 *
 * Steve Walsh R00151053
 *
 * Last Update:  29/3/18
 *
 * ------------------------------------------------------------------------------------------------
 *
 * ArrayList to store winner objects
 *
 * Reads in winners from file
 * Persists new winners to file
 *
 * ------------------------------------------------------------------------------------------------
 **/
public class winnerList {

   public static  ArrayList<winner> winnersList = new ArrayList<>();  // create arrayList to store winners

    public void readWinners()throws Exception{

        String fileName = "src/docs/winnerList.bin";                                  // create file to store winners to





    }


    public static void persistWinnerListToFile() throws FileNotFoundException {

        String fileName = "src/docs/winnerList.bin";                                  // create file to store winners to

        try {
            ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream(fileName)); // open output stream to convert data

            for ( int i = 0; i < winnersList.size(); i++ ){                                  // loop through all winners

                os.writeObject(winnersList.get(i));                                          // write each to file
            }
        } catch (IOException e) {                                                            // catch exception if file not found
            e.printStackTrace();
        }
    }

}
