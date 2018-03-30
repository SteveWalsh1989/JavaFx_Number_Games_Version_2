import java.io.*;
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
public class winnerList implements Serializable{

   public static  ArrayList<winner> winnersList = new ArrayList<>();  // create arrayList to store winners

    static String fileName = "src/docs/winnerList.bin";



    /**
     * readWinners
     *
     * reads details of winner objects to file using serialisation
     * and stores in arrayList winnersList
     *
     * @throws FileNotFoundException
     */
    public static void readWinners()throws Exception{

    try {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));     // create new input stream to the file

       winnersList = (ArrayList) is.readObject();                                        // store winner object in arrayList

        is.close();                                                                      // close input stream

    } catch (IOException e) {                                                            // catch exception if file not found
    e.printStackTrace();
    } catch (ClassNotFoundException e) {                                                 // catch exception if class not found
        e.printStackTrace();
    }

    for ( int  i = 0; i < winnersList.size(); i++ ) {       // ***** TEST : prints winners list to console *****\\

        System.out.println("Winner: " + i + "\tName: " + winnersList.get(i).getName() + "\tPrize: " + winnersList.get(i).getPrize());
     }

    }

    /**
     * persistWinnerListToFile
     *
     * Saves details of winner objects to file using serialisation
     *
     * @throws FileNotFoundException
     */
    public static void persistWinnerListToFile() throws IOException {

        try {
            ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream(fileName)); // open output stream to convert data

            os.writeObject(winnersList);


        } catch (IOException e) {                                                            // catch exception if file not found
            e.printStackTrace();
        }
    }

}
