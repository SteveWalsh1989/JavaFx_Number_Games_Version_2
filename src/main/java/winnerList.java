import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * ------------------------------------------------------------------------------------------------
 * Details :
 *
 *      Module: Object Orientated programming
 *
 *      Assignment 2: Maven, using imported Jar and TreeLists
 *
 *      Steve Walsh R00151053
 *
 *      Last Update:  1/4/18
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
public class winnerList extends ArrayList<winner> implements Serializable{

   public static  ArrayList<winner> winnersList = new ArrayList<>();  // create arrayList to store winners

    static String fileName = "src/docs/winnerList.bin";



    /**
     * readWinners
     *
     * reads details of winner objects to file using serialisation
     * and stores in arrayList winnersList
     *
     * @throws FileNotFoundException   catch error
     */
    public static void readWinners()throws Exception{

    try {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));     // create new input stream to the file

        setWinnersList((ArrayList) is.readObject());                                     // store winner object in arrayList

        is.close();                                                                      // close input stream

    } catch (IOException | ClassNotFoundException e  ) {                                   // catch exception if file not found
    e.printStackTrace();
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

            os.writeObject(getWinnersList());


        } catch (IOException e) {                                                            // catch exception if file not found
            e.printStackTrace();
        }
    }

    public static ArrayList<winner> getWinnersList() {
        return winnersList;
    }

    public static void setWinnersList(ArrayList<winner> winnersList) {
        winnerList.winnersList = winnersList;
    }


    @Override
    public Stream<winner> stream() {
        return null;
    }
}
