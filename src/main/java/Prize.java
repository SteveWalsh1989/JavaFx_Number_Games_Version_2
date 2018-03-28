

/**
 * ------------------------------------------------------------------------------------------------
 * Details :
 *
 *      Module: Object Orientated programming
 *
 *      Assignment 1: JavaFx
 *
 *      Steve Walsh R00151053
 *
 *      Last Update:  20/2/18
 *
 * ------------------------------------------------------------------------------------------------
 *
 *  Prize object to store prize details
 *
 *
 *  : Stores prizeWon : String value showing name of the prize won
 *  : Stores starValue: star value required to win this prize
 *
 * ------------------------------------------------------------------------------------------------
 **/

//--------------------------------------------------
// CLASS
//--------------------------------------------------
class Prize {

    private String starValue;           // store value of prize

    private String prizeWon ;           // create variables to store name user sees, and actual prize


    // constructor
    public Prize( String prize, String starValue) {

        this.starValue = starValue;    // sets star value

        this.prizeWon = prize;         // sets actual  prize

    }

     /* ----------------------
            Getter
       ----------------------- */

    /**
     * getStarValue
     *
     * returns the starValue of the prize
     *
     * @return this.starValue :  starValue of the prize
     */
    public String getStarValue(){

        return this.starValue; // return starValue of prize object

    }
    /**
     * getName
     *
     * returns the name of the prize
     *
     * @return this.prizeWon :  name of the prize
     */
    public String getPrizeWon(){

        return this.prizeWon; // return the name of prizeWon of prize object

    }


}