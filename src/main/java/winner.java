import java.io.Serializable;

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
 *      Last Update:  28/3/18
 *
 * ------------------------------------------------------------------------------------------------
 *
 *  Winner object to store winner name and prize
 *
 *
 *  : Stores prizeWon : String value showing name of the prize won
 *  : Stores starValue: star value required to win this prize
 *
 * ------------------------------------------------------------------------------------------------
 **/
public class winner implements Serializable{

    private String name;
    private String prize;


    /**
     * Default constructor
     */
    public winner(){}

    /**
     * overloaded constructor
     *
     * Sets value of winner name and prize when creating winner object
     *
     * @param winnerName   winner name
     * @param winnerPrize  winner prize
     */
    public winner(String winnerName, String winnerPrize) {

        this.name  = winnerName;
        this.prize = winnerPrize;

    }

    /* ----------------------
            Getters
       ----------------------- */

    /**
     * getName
     *
     * @return name : returns winners name
     */
    public String getName(){

        return this.name;
    }
    /**
     * getPrize
     *
     * @return Prize : returns winners Prize
     */
    public String getPrize(){

        return this.prize;
    }

     /* ----------------------
            Setters
       ----------------------- */
    /**
     * setName
     *
     * @param winnerName : name of winner
     */
    public void setName(String winnerName){

    this.name = winnerName;
    }
    /**
     * setPrize
     *
     * @param winnerPrize : prize of winner
     */
    public void setPrize(String winnerPrize) {
        this.prize = winnerPrize;

    }

    /**
     * printDetails
     *
     * Prints details of winner to console
     */
    public void printDetails(){

        System.out.println("Winner's name: " + this.name + "\t\tPrize: " +this.prize);
    }








}
