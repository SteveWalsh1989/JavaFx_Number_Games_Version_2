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
 * Tree data structure to store prizes
 *
 *
 *
 *
 * ------------------------------------------------------------------------------------------------
 **/

//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//--------------------------------------------------
// CLASS
//--------------------------------------------------
class PrizeData {


    static BinaryTree fourStarTree;
    static BinaryTree fiveStarTree;
    static BinaryTree sixStarTree;


    static void storePrizes() throws Exception {

        fourStarTree = new BinaryTree();                                          // initialize to store 4 star prizes

        fiveStarTree = new BinaryTree();                                          // initialize to store 4 star prizes

        sixStarTree  = new BinaryTree();                                          // initialize to store 4 star prizes

        String fileName = "src/docs/prizes.txt";                             // The name of the file to open.

        String line;                                                              // This will reference one line at a time

        try {                                                                     // catch file missing error

            FileReader fileReader = new FileReader(fileName);                     // FileReader reads text files in the default encoding.

            BufferedReader bufferedReader = new BufferedReader(fileReader);       //  wrap FileReader in BufferedReader.

            while ((line = bufferedReader.readLine()) != null) {                  // keep going whilst there are mor lines in file

                String[]  individualPrize = line.split(":");                // split by colon to create array of

                if(individualPrize[1].compareTo("4") == 0) {          // Scenario 1:  if value is 4 star

                    fourStarTree.addNode(individualPrize[2] , individualPrize[0]);// store key and prize in 4 star tree

                } else if (individualPrize[1].compareTo("5") == 0 ) { // Scenario 2: if value is 5 star

                    fiveStarTree.addNode(individualPrize[2] , individualPrize[0]);// store key and prize in 5 star tree

                } else if (individualPrize[1].compareTo("6") == 0) {  // Scenario 3: if value is 5 star

                    sixStarTree.addNode(individualPrize[2] , individualPrize[0]); // store key and prize in 6 star tree
                }
            }
            bufferedReader.close();                                               //  close file
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");         // print error about opening file
        } catch (IOException ex) {
            System.out.println("Error reading file '"  + fileName + "'");         // print error about reading file
        }
    }
}


