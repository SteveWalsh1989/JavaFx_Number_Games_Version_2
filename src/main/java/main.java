
//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;


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
 * Tab 1 : Game 1 : Guessing game
 *
 *      User has 6 guesses to guess a random number between 1 - 4 to win a prize
 *      User can use the enter key, or guess key to submit their input in the text field
 *      User can reset the game which sets a new winning number
 *      User quit the game: alert window pops up to confirm before closing
 *
 *      Winner will win a 4 star prize if guesses user correctly
 *
 *
 * ------------------------------------------------------------------------------------------------
 * Tab 2 : Game 2 : Lotto Game
 *
 *
 *      Creates array of 6 random numbers from the selection.
 *      User can select 6 numbers from those on screen that are added to array.
 *      User presses submit to allow program to compare
 *      Compares numbers and tells user if they matched the numbers
 *      and how many they matched
 *      User can try again with the same lotto numbers
 *      User can press reset to give new lotto numbers
 *
 *      Winner can get 4, 5 or 6 star prizes depending on how many numbers they match
 *
 *
 * ------------------------------------------------------------------------------------------------
 * Tab 3 : Prizes
 *
 *      Reads prizes and star values  from file
 *      Uses TreeLists to store and retrieve prizes
 *      TreeLists methods were imported from Jar file
 *      Tab is only available to winning players
 *      Displays prizes available to user
 *      User can select prize
 *      Displays prize value to user
 *      Allows to enter name
 *
 *
 *------------------------------------------------------------------------------------------------
 * Tab 4 : Winners
 *
 *      Displays option to view winners in ascending / descending order
 *      Option to delete winner from list using textfield and winner number shown
 *      Displays Winner Index, name and prize to user
 *
 * ------------------------------------------------------------------------------------------------
 */

//--------------------------------------------------
// CLASS
//--------------------------------------------------
public class main extends Application {



    /* ----------------------------------------------------
        Game 1 :  Variables
       ---------------------------------------------------- */
    private static int   numGuesses = 6;      // total number of guesses for user which is 6
    private static boolean isWinner = false;  // assume user is not a winner initially, true if user guesses correctly
    private static int winningNumber;         // store number needed to win game
    private static int userGuess;             // stores user guess from textfield
    private static Label hintLower;           // set hint for higher label
    private static Label hintHigher;          // set hint for lower  label


    /* ----------------------------------------------------
        Game 2 :  Variables
    ---------------------------------------------------- */
    private static int g2_prize = 0;
    private static int numberLimit = 6;                          // limits number of winning numbers and user guesses
    private static int numberGuess = 0;                          // stores how many numbers user guessed
    private static ArrayList<Integer> userGuess2;                // array to store usersGuess of lotto numbers
    private static int[] winningNumbers  = new int[numberLimit]; // array to store 6 winning numbers randomly generated
    private static Button[] lottoButtons = new Button[50];       // array to hold all numeric buttons

    private static Label label_WinningNum_title;   //
    private static Label label_WinningNum_1;       //
    private static Label label_WinningNum_2;       //
    private static Label label_WinningNum_3;       //  create labels to show the winning numbers
    private static Label label_WinningNum_4;       //
    private static Label label_WinningNum_5;       //
    private static Label label_WinningNum_6;       //

    private static Label label_UserGuess_title;    //
    private static Label label_UserGuess_1;        //
    private static Label label_UserGuess_2;        //
    private static Label label_UserGuess_3;        // create labels to show the users guessed numbers
    private static Label label_UserGuess_4;        //
    private static Label label_UserGuess_5;        //
    private static Label label_UserGuess_6;        //

    private static Button g2_Start;                //
    private static Button g2_Quit;                 //
    private static Button g2_TryAgain;             // create buttons for start, quit, reset and submit
    private static Button g2_Submit;               //
    private static Button g2_Reset;                //

    private static TabPane myTab;                  // Tab pane
    private Tab tab1;                              // Tab for guessing game
    private Tab tab2;                              // Tab for lotto game
    private Tab tab3;                              // Tab for prizes
    private static Tab tab4;                       // tab for winners

     /* ----------------------------------------------------
        Tab 3:  prize page variables
    ---------------------------------------------------- */

    private HBox hBox_Ask_Winner;               // hBox to ask winner name

    private VBox vBox_Tab3;                     // main vbox
    private VBox vBox_Tab3_Top;                 // top vbox
    private VBox vBox_Tab3_bottom;              // bottom vbox

    private static Label label_Prize_Title;     //  showing prize title
    private static Label label_You_Won;         //  showing they won
    private static Label label_Ask_Winner;      //  ask winner name
    private static Label label_Congratulations; //  congratulations label after select prize

    private static TextField storeWinnerName;   // store winner name

    /* ----------------------------------------------------
        Tab 4:  Winner page variables
    ---------------------------------------------------- */
    private VBox vBox_tab4;                                        // main structure of winner tab
    private VBox vBox_WinnersDetails;                              // holds winners details

    private HBox winnerTitle;                                      // holds title pf page
    private HBox displayWinnerOptions;                             // show buttons for different display types
    private HBox deleteWinner;                                     // store button and field to delete winner from list

    private static Label label_winners_title;                      // title of page
    private static Label label_Winners;                            // and display prizes
    private static Label label_delete_winner;                      // prompt

    private static Button winner_displayAscending;                 // display winners in ascending order
    private static Button winner_displayDescending;                // display winners in descending order
    private static Button deletewinner;                            // delete winner

    private static TextField storeWinnerIndex;                     // store winner name to be deleted


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        /* ----------------------------------------------------
            read in array list of winners from file
            ---------------------------------------------------- */

        winnerList.readWinners();


         /* ----------------------------------------------------
             Default screen layout
            ---------------------------------------------------- */

        myTab = new TabPane();             // create tab pane

         /* ----------------------------------------------------
             Game 1 : GUI Components
            ---------------------------------------------------- */

        winningNumber = generateWinningNumber();   // generate and store the winning number
        BorderPane root = new BorderPane();        // Create a BorderPane with a Text node in each of the five regions
        GridPane game_1_grid = new GridPane();     // grid pane

        // Buttons
        Button g1_start = new Button("Start");    // start button
        Button g1_reset = new Button("Reset");    // Reset button
        Button g1_quit  = new Button("Quit");     // Quit button
        Button g1_guess = new Button("Submit");   // Guess button
        Button g1_clear = new Button("Clear");    // Clear button


        HBox game_1_Labels_Structure = new HBox();

        HBox game_1_Number_InputStructure = new HBox();

        HBox game_1_Buttons_Structure  = new HBox();

        VBox game_1_mainStructure = new VBox();



        // labels
        Label num_remaining_guess_label = new Label("You have " + numGuesses + " guesses remaining");
        hintLower = new Label("\t\tLower!!");    // set hint for higher label
        hintLower.setTextFill(Color.web("#3366ff"));  // set color to blue

        hintHigher = new Label("\t\tHigher!!");  // set hint for lower  label
        hintHigher.setTextFill(Color.web("#a300cc")); // set color to purple



        /* ----------------------------------------------------
                   Primary Stage Layout
           ---------------------------------------------------- */

        // Primary stage and scene
        primaryStage.setWidth(600);                             // set width of primary stage
        primaryStage.setHeight(500);                            // set width of primary stage
        primaryStage.setResizable(false);                       // prevents resizing of window
        Scene scene1 = new Scene(root, 400, 400); // Create the Scene
        primaryStage.setScene(scene1);                          // Add the scene to the Stage
        primaryStage.setTitle("Assignment");                    // Set the title of the Stage
        primaryStage.show();                                    // Display the Stage



        //----------------------------------
        //      Tab 1: Number Guessing Game
        //----------------------------------
        tab1 = new Tab();                      // create tab

        tab1.setText("Number Guessing Game");  // set text of tab

        tab1.setContent(game_1_mainStructure);  // set V box : will add button to this later that will start the game

        tab1.setClosable(false);               // prevent user from being able to close the tab

        myTab.getTabs().add(tab1);             // add the tab to the Tab pane

        root.setTop(myTab);                    // adding the tab to the Border pane root
        //----------------------------------
        //      Tab 2: Lotto Game
        //----------------------------------

        tab2 = new Tab();                  // create second lab for game 2

        tab2.setText("Lotto Cure");        // set text of tab 2

        myTab.getTabs().add(tab2);         // add the tab to the Tab pane

        VBox vBox_game2 = new VBox();      // create new vBox for the UserGuess and winningLottoNumbers labels

        tab2.setClosable(false);           // prevent user from being able to close the tab

        tab2.setContent(vBox_game2);       // set V box : will add button to this later that will start the game

        root.setTop(myTab);                // adding the tab to the Border pane root
        //-----------------------------
        //      Tab 3: Prizes
        //-----------------------------

        tab3 = new Tab();                                                       // create new tab for prizes

        tab3.setText("Prizes");                                                 // set text of tab 3

        label_Ask_Winner = new Label("Enter your name: ");                 // label to ask winners name

        label_Ask_Winner.setVisible(false);                                     // hide name input label

        label_Congratulations =  new Label("Congratulations You Are A Winner");

        storeWinnerName = new TextField();                            // create new text input field

        storeWinnerName.setPromptText("John Smith");                            // adding text prompt to text input field

        storeWinnerName.setVisible(false);                                      // hide text field for name input

        tab3.setClosable(false);                                                // prevent user from being able to close the tab

        vBox_Tab3_Top = new VBox();                                             // top structure

        hBox_Ask_Winner = new HBox();                                           // stores label and text input

        vBox_Tab3_Top.getChildren().addAll(hBox_Ask_Winner );                   // add label and hbox

        hBox_Ask_Winner.getChildren().addAll(label_Ask_Winner, storeWinnerName);// add label and textinput

        vBox_Tab3_bottom = new VBox();                                          // create new vBox for the UserGuess and winningLottoNumbers labels

        vBox_Tab3 = new VBox();                                                 // main vbox

        vBox_Tab3.getChildren().addAll(vBox_Tab3_Top, vBox_Tab3_bottom);        // add smaller boxes to main vbox

        tab3.setContent(vBox_Tab3);                                             // Adding to content to prize tab

        root.setTop(myTab);                                                     // adding the tab to the Border pane root




        //-----------------------------
        //      Tab 4: Winners
        //-----------------------------

        tab4 = new Tab();                                                       // create new tab for prizes

        tab4.setText("Winners");                                                // set text of tab 3

        tab4.setClosable(false);                                                // prevent user from being able to close the tab

        vBox_tab4 = new VBox();                                                 // vbox to display winner details

        label_winners_title = new Label("Winners List");                   // create label to hold winner page title text

        winnerTitle = new HBox();                                               // create new hBox to store title

        winnerTitle.getChildren().add(label_winners_title);                     // add label to heading hBox

        displayWinnerOptions = new HBox();                                      // hbox to store display options

        winner_displayAscending = new Button("Order by Name");             // option 1 : Order by Name

        winner_displayDescending = new Button("Order by Prize");           // option 2 : Order by Prize

        displayWinnerOptions.getChildren().addAll(winner_displayAscending,winner_displayDescending);

        deleteWinner = new HBox();

        label_delete_winner = new Label("Delete winner by entering index");              // prompt user to enter number of winner to delete

        storeWinnerIndex = new TextField();                                                   // create new textfield to store index

        storeWinnerIndex.setPromptText("EG: 1");                                              // set prompt of text field with example

        deletewinner = new Button("Delete");                                             // Delete button

        deleteWinner.getChildren().addAll(label_delete_winner,storeWinnerIndex,deletewinner); // add label and textfield to hbox

        vBox_WinnersDetails = new VBox();                                                     // store winners details

        ScrollPane scrollPane_WinnersDetails = new ScrollPane(vBox_WinnersDetails);           // Create a ScrollPane to add winners details too

        scrollPane_WinnersDetails.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);        // always have the vertical scroll bar shown

        scrollPane_WinnersDetails.setMaxHeight(230);                                          // set a max height

        vBox_tab4.getChildren().addAll(winnerTitle,displayWinnerOptions,deleteWinner,scrollPane_WinnersDetails); // add all structures to main vbox

        tab4.setContent(vBox_tab4);                                                           // set V box

        root.setTop(myTab);                                                                   // adding the tab to the Border pane root

        myTab.getTabs().add(tab4);                                                            // add the winner tab to the Tab pane



         /*
         ----------------------------------------------------
             Game 1: Number Guessing Game Layout
         ----------------------------------------------------
             */

        //-----------------------------
        //      TextField
        //-----------------------------
        TextField numberInput = new TextField();                                  // create new text input field
        numberInput.setPromptText("Guess a number 1-100");                        // adding text prompt to text input field



        //-----------------------------
        //   Layout
        //-----------------------------


        game_1_Labels_Structure.setSpacing(25);
        game_1_Labels_Structure.getChildren().addAll(hintLower, num_remaining_guess_label,hintHigher );

        game_1_Number_InputStructure.setSpacing(25);
        game_1_Number_InputStructure.setPadding(new Insets(0,0,0,60));

        game_1_Number_InputStructure.getChildren().addAll(g1_clear,numberInput,g1_guess);

        game_1_Buttons_Structure.setSpacing(25);
        game_1_Buttons_Structure.setPadding(new Insets(0,0,0,80));

        game_1_Buttons_Structure.getChildren().addAll(g1_start,g1_reset,g1_quit );

        game_1_mainStructure.setSpacing(40);
        game_1_mainStructure.setAlignment(Pos.CENTER);
        game_1_mainStructure.setPadding(new Insets(0,0,0,70));
        game_1_mainStructure.getChildren().addAll(game_1_Labels_Structure,game_1_Number_InputStructure,game_1_Buttons_Structure );



        //------------------------------------------
        //   Components visibility at program start
        //------------------------------------------
        hintHigher.setVisible(false);                                                     // before game start hints button hidden
        hintLower.setVisible(false);                                                      // before game start hints button hidden
        numberInput.setVisible(false);                                                    // before game start have text input hidden
        num_remaining_guess_label.setVisible(false);                                      // before game start have label hidden
        g1_reset.setVisible(false);                                                       // before game start reset button hidden
        g1_clear.setVisible(false);                                                       // before game start clear button hidden
        g1_guess.setVisible(false);                                                       // before game start guess button hidden
        g1_quit.setVisible(false);                                                        // before game start quit button hidden

        /*
        ----------------------------------------------------
             Game 1: Button Actions
        ----------------------------------------------------
        */

        //---------------------//
        //     Start Button    //
        //---------------------//
        g1_start.setOnAction(e -> beginGame(numberInput, num_remaining_guess_label, g1_reset,        // start Button: begins game
                g1_quit, g1_start, g1_guess, g1_clear, g1_quit, game_1_mainStructure));

        //---------------------//
        //     Quit Button     //
        //---------------------//
        g1_quit.setOnAction(e -> alertBox(primaryStage));                                            // Quit Button: closes primaryStage

        //---------------------//
        //     Reset Button    //
        //---------------------//
        g1_reset.setOnAction(e -> reset(numberInput, num_remaining_guess_label,                      // Reset Button: resets game
                g1_reset, g1_start, g1_clear, g1_guess,g1_quit               // before game start quit button hidden
        ));

        //---------------------//
        //     guess Button    //
        //---------------------//
        g1_guess.setOnAction(e -> guess(numberInput, num_remaining_guess_label));                    // guess Button: submits input in text field

        //     clear Button    //
        //---------------------//
        g1_clear.setOnAction(e -> clear(numberInput));                                               // clear Button: deletes number from input field

        //---------------------//
        //     Input Field     //
        //---------------------//
        numberInput.setOnAction((ActionEvent e) -> {

            userGuess = storeInput(numberInput);           // get users guess from input box

            compareNumbers(userGuess, winningNumber);      // check if guess is correct ;

            checkWinner(num_remaining_guess_label);        // checks winner

        });

            /*
            ----------------------------------------------------
             Game 2 Lotto Game
            ----------------------------------------------------
             */
        winningNumbers = generateRandomNumberArray(numberLimit); //  generates array of unique random numbers to store winning lotto numbers

        userGuess2 = new ArrayList<>();

        //-----------------------------
        //  Game 2 :  GUI Components
        //-----------------------------

        // Labels
        label_WinningNum_title = new Label("WinningNumbers:   ");
        label_WinningNum_1 = new Label(Integer.toString(winningNumbers[0])); //
        label_WinningNum_2 = new Label(Integer.toString(winningNumbers[1])); //
        label_WinningNum_3 = new Label(Integer.toString(winningNumbers[2])); // initialize labels for winning numbers
        label_WinningNum_4 = new Label(Integer.toString(winningNumbers[3])); //
        label_WinningNum_5 = new Label(Integer.toString(winningNumbers[4])); //
        label_WinningNum_6 = new Label(Integer.toString(winningNumbers[5])); //

        label_WinningNum_title.setVisible(false);                            //
        label_WinningNum_1.setVisible(false);                                //
        label_WinningNum_2.setVisible(false);                                //
        label_WinningNum_3.setVisible(false);                                //
        label_WinningNum_4.setVisible(false);                                // Hide Winning number labels at beginning of game
        label_WinningNum_5.setVisible(false);                                //
        label_WinningNum_6.setVisible(false);                                //

        label_UserGuess_title = new Label("Your Guesses: \t");
        label_UserGuess_1 = new Label("userGuess 1");                   //
        label_UserGuess_2 = new Label("userGuess 2");                   //
        label_UserGuess_3 = new Label("userGuess 3");                   //
        label_UserGuess_4 = new Label("userGuess 4");                   // initialize labels for user Guess
        label_UserGuess_5 = new Label("userGuess 5");                   //
        label_UserGuess_6 = new Label("userGuess 6");                   //

        label_UserGuess_title.setVisible(false);                             //
        label_UserGuess_1.setVisible(false);                                 //
        label_UserGuess_2.setVisible(false);                                 //
        label_UserGuess_3.setVisible(false);                                 // Hide userGuess labels at beginning of game
        label_UserGuess_4.setVisible(false);                                 //
        label_UserGuess_5.setVisible(false);                                 //
        label_UserGuess_6.setVisible(false);                                 //

        // Buttons
        g2_Start     = new Button("    Start    ");                     // initialize start button
        g2_Quit      = new Button("    Quit     ");                     // initialize Quit button
        g2_TryAgain  = new Button("  Try Again  ");                     // initialize try again button
        g2_Reset     = new Button("Reset Numbers");                     // initialize reset button
        g2_Submit    = new Button("   Submit    ");                     // initialize Submit button

        g2_Submit.setDisable(true);                                          // Disable submit button on game start
        g2_TryAgain.setDisable(true);                                        // Disable try again button on game start
        g2_Reset.setDisable(true);                                           // Disable reset button on game start

            /*
            ----------------------------------------------------
             Game 2 : Layout
            ----------------------------------------------------
             */
        //-----------------------------
        //      User Guess H Box
        //-----------------------------

        HBox hBox_User_Numbers = new HBox();                                             // adding hBox for user numbers

        hBox_User_Numbers.getChildren().addAll(label_UserGuess_title, label_UserGuess_1, label_UserGuess_2, label_UserGuess_3,  // add sample labels
                label_UserGuess_4, label_UserGuess_5, label_UserGuess_6);
        hBox_User_Numbers.setSpacing(20);                                                // sets spacing of the user guess hbox

        hBox_User_Numbers.setPadding(new Insets(10, 10, 10, 10)); // set padding of the user guess hbox


        //-----------------------------
        //      Winning Number H Box
        //-----------------------------
        HBox hBox_Winning_Numbers = new HBox();                                              // adding hBox for winning numbers

        hBox_Winning_Numbers.getChildren().addAll(label_WinningNum_title, label_WinningNum_1, label_WinningNum_2, label_WinningNum_3, label_WinningNum_4, label_WinningNum_5, label_WinningNum_6);                                                    // adding labels to h Box

        hBox_Winning_Numbers.setSpacing(20);                                                 // sets spacing of the winning number hbox

        hBox_Winning_Numbers.setPadding(new Insets(10, 10, 10, 10)); // set padding of the winning number hbox

        //-----------------------------
        //      Action Buttons H Box
        //-----------------------------

        HBox lotto_Game_Buttons = new HBox();                                             // adding hBox for buttons

        lotto_Game_Buttons.getChildren().addAll(g2_Submit, g2_Reset, g2_TryAgain, g2_Start, g2_Quit); // H Box : Main Option Buttons

        lotto_Game_Buttons.setSpacing(30);                                                // sets spacing of the buttons hbox

        lotto_Game_Buttons.setPadding(new Insets(15, 15, 15, 15)); // sets padding of the buttons hbox

        //-----------------------------
        //     Lotto Numbers FlowPane
        //-----------------------------
        FlowPane lotto_Numbers_FlowPane = new FlowPane();                       // create new flowPane to store lotto numbers

        lotto_Numbers_FlowPane.setHgap(11);                                     // set horizontal gap between panes in flowPane

        lotto_Numbers_FlowPane.setVgap(11);                                     // set vertical gap between panes in flowPane

        for (int i = 0; i < lottoButtons.length; i++) {                         // loop through all 45 lotto numbers to add buttons for each

            lottoButtons[i] = new Button(String.valueOf(i));                    // setting value of each button number

            lottoButtons[i].setPadding(new Insets(12, 12, 12, 12)); // set padding around each button

            lottoButtons[i].setMinHeight(50);                                   // set minimum height for buttons

            lottoButtons[i].setMinWidth(50);                                    // set minimum width for buttons

            lotto_Numbers_FlowPane.getChildren().addAll(lottoButtons[i]);       // add button to flowPane

            lottoButtons[i].setDisable(true);                                   // disabled buttons on game start

            int finalI = i;                                                     // second index variable

            lottoButtons[i].setOnAction(e -> storeGuess(lottoButtons[finalI])); // stores user guess
        }
        //-----------------------------
        //    Main Screen V Box
        //-----------------------------

        // Main  VBox
        vBox_game2.getChildren().addAll(hBox_User_Numbers, hBox_Winning_Numbers, lotto_Game_Buttons, lotto_Numbers_FlowPane); // adds all h boxes and flowPane to main VBox

             /*
            ----------------------------------------------------
             Game 2: Button Actions
            ----------------------------------------------------
             */
        //---------------------//
        //     Start Button    //
        //---------------------//
        g2_Start.setOnAction(e -> beginGame2());                    // starts the game by allowing users to select numbers

        //---------------------//
        //     Quit Button     //
        //---------------------//
        g2_Quit.setOnAction(e -> alertBox(primaryStage));           // shows pop up box to confirm user wishes to close application

        //---------------------//
        //     Try Again Button    //
        //---------------------//
        g2_TryAgain.setOnAction(e -> g2_reset(hBox_User_Numbers));  // start game over with same numbers);// resets the game by resetting all values and layout



        //---------------------//
        //  Reset Button   //
        //---------------------//
        g2_Reset.setOnAction(e -> {

            newLottoNumbers();                            // resets the winning lotto numbers

            g2_reset(hBox_User_Numbers);                  // resets the game

        });
        //---------------------//
        //     submit Button   //
        //---------------------//
        g2_Submit.setOnAction(e -> {                           // submits number for check to see if user won

            lottoCompareNumbers();                             // compares the userGuess with the WinningNumbers

            isWinner(hBox_User_Numbers);                       // check if user won a prize
        });

     /*
       -----------------------
         Prize Screen Layout
       -----------------------
     */
        //-----------------------------
        //     GUI Components
        //-----------------------------

        label_Prize_Title = new Label("SELECT AN OPTION TO REVEAL YOUR PRIZE: "); // title label for prize page

        label_You_Won     = new Label("Congratulations, you have won a: ");       // label to show what user won

        label_You_Won.setVisible(false);                                               // hide the label until user selects prize.


        //-----------------------------
        //     Ask Winner name HBox
        //-----------------------------
        label_Ask_Winner.setAlignment(Pos.CENTER);                                      // center align main label

        hBox_Ask_Winner.setAlignment(Pos.CENTER);                                       // center align

        hBox_Ask_Winner.setSpacing(20);                                                 // sets spacing

        hBox_Ask_Winner.setPadding(new Insets(25, 20, 25, 20));  // set padding

        //-----------------------------
        //     Delete Winner
        //-----------------------------
        label_delete_winner.setPadding(new Insets(0, 20, 0, 0)); // set padding

        //-----------------------------
        //     Prize VBox
        //-----------------------------

        vBox_Tab3_bottom.getChildren().addAll(label_Prize_Title,label_You_Won) ;        // title and youWon added to the top of the VBox

        vBox_Tab3_bottom.setAlignment(Pos.CENTER);                                      // positions vBox in the center

        vBox_Tab3_bottom.setSpacing(20);                                                // set spacing between nodes in VBox

     /*
       -----------------------
         Winner Screen Layout
       -----------------------
     */
        winnerTitle.setAlignment(Pos.CENTER);                                               // position title to center

        winnerTitle.setPadding(new Insets(15,0,15,0));              // set padding

        displayWinnerOptions.setAlignment(Pos.CENTER);                                      // position display options to center

        displayWinnerOptions.setPadding(new Insets(15,0,15,0));     // set padding


        winner_displayAscending.setMinWidth(100);                                           // set width

        winner_displayDescending.setMinWidth(100);                                          // set width

        winner_displayAscending.setMinHeight(40);                                           // set height

        winner_displayDescending.setMinHeight(40);                                          // set width

        winner_displayAscending.setPadding(new Insets(0,30,0,30));   // set padding

        winner_displayDescending.setPadding(new Insets(0,30,0,30));  // set padding

        deleteWinner.setAlignment(Pos.CENTER);                                              // position delete HBox to center

        deleteWinner.setPadding(new Insets(15,0,15,0));              // set padding

        storeWinnerIndex.setMaxWidth(50);                                                   // set width

        deletewinner.setMinWidth(50);
        deletewinner.setPadding(new Insets(5,20,5,20));

        vBox_WinnersDetails.setAlignment(Pos.CENTER_LEFT);                                  // position winner details to center
        vBox_WinnersDetails.setPadding(new Insets(5,20,5,160));


           /*
            ----------------------------------------------------
             Winner tab : Button Actions
            ----------------------------------------------------
             */

        //------------------------//
        // Ascending Order Button //
        //------------------------//
        winner_displayAscending.setOnAction(e -> {                           // Displays winners details ascending

            try {
                reorderWinnersName();                                        // reorder winnersList by name alphabetically
            } catch (CloneNotSupportedException e1) {
                e1.printStackTrace();
            }

            displayWinnerDetails();                                          // displays winners details
        });

        //-------------------------//
        // Descending Order Button //
        //-------------------------//
        winner_displayDescending.setOnAction(e -> {                          // Displays winners details Descending

            reorderWinnersPrize();                                           // reorder winnersList by prize alphabetically

            displayWinnerDetails();                                          // displays winners details
        });


        //-------------------------//
        // Delete Button //
        //-------------------------//
        deletewinner.setOnAction(e -> {                                     // deletes winner based on index in textfield

            try {
                removeWinner();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });




    } // close primary stage

    /*
     ----------------------------------------------------

          Methods

     ----------------------------------------------------
     */
      /* ----------------------------------------------------
             Game 1 Methods
         ----------------------------------------------------  */
    /**
     * Start : beginGame
     *
     * Starts the game by showing:
     *
     * Reset button
     * Text input
     * Number of remaining
     *
     * @param num          - textfield
     * @param label        - remaining guesses left
     * @param reset_button - reset button
     * @param quit_button  - quit button
     * @param start_button - start button
     * @param guess_button - guess button
     * @param clear_button - clear button
     * @param game_1_Structure         - grid layout
     */
    private  void beginGame(TextField num, Label label, Button reset_button, Button quit_button, Button start_button,
                            Button guess_button, Button clear_button,Button g1_quit,  VBox game_1_Structure) {

        num.setVisible(true);                               // at start have text input hidden
        label.setVisible(true);                             // at start have label  hidden
        reset_button.setVisible(true);                      // at start reset button  hidden
        guess_button.setVisible(true);                      // at start reset button  shown
        clear_button.setVisible(true);                      // at start clear button  shown
        start_button.setVisible(false);                     // at start start button  hidden
        g1_quit.setVisible(true);                           // before game start quit button hidden

        try {
            PrizeData.storePrizes();                        // load up prizes from file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * generateWinningNumber
     *
     * generate random number between 1 - 100 and store as winningNumber
     *
     * @return random - random number generated to be the winning number for the game
     */
    private int generateWinningNumber() {

        Random rand = new Random();                       // create new random object

        int random = rand.nextInt(100)+1 ;         // set random variable tobe random number between 0 - 100

        System.out.println("Winning Number: " + random);  // Testing: prints the winning number to console

        return random;                                    // return random number
    }

    /**
     * storeInput : validation class
     *
     * Stores the user input
     *
     * @param input - input field
     * @return userInput - returns the user input if valid
     */
    private static int storeInput(TextField input) {

        try {
            int userInput = Integer.parseInt(input.getText());                  // Converts the string input form user guess into an int and stores as guess

            input.clear();                                                      // clear keyboard nextLine for enter key

            return userInput;                                                   // returns guess to program

        } catch (NumberFormatException e) {

            input.setPromptText("Error: Guess number between 1-100");           // adding text prompt to text input field

            input.clear();                                                      // clear keyboard nextLine for enter key
        }
        return -1;
    }

    /**
     * storeInput : validation class
     *
     * Stores the user input
     *
     * @param input - input field
     * @return userInput - returns the user input if valid
     */
    private static String storeName(TextField input) {

        String name = (input.getText());                    // Converts the string input form user guess into an int and stores as guess

        input.clear();                                      // clear keyboard nextLine for enter key

        myTab.getTabs().add(tab4);                              // add the winner tab to the Tab pane

        return name;                                            // returns guess to program


    }

    /**
     * compareNumbers
     *
     * compares the userGuess and winningNumber
     *
     * @param userNum    - user guess
     * @param winningNum - number needed to win game
     */
    public static void compareNumbers(int userNum, int winningNum) {

        if (userNum < winningNum) {            // compare if user number is lower than winning number

            hintHigher.setVisible(true);       // show higher hint label

            hintLower.setVisible(false);       // hide lower hint label

        } else if (userNum > winningNum) {     // compare if user number is higher than winning number

            hintLower.setVisible(true);        // show lower hint label

            hintHigher.setVisible(false);      // hide higher hint label

        } else if (userNum == winningNum) {    // compare if user number is equal to the winning number

            isWinner = true;                   // set return variable to true as user won the game
        }
    }
    /**
     * checkWinner
     *
     * checks winner of game
     *
     * @param num_remaining_guess_label - remaining guesses left
     */
    private void checkWinner(Label num_remaining_guess_label) {

        if (!isWinner && numGuesses > 1) {                                                 // if didn't win and has guesses remaining

            numGuesses = numGuesses - 1;                                                   // decrease number of guesses

            num_remaining_guess_label.setText("You have " + numGuesses + " remaining!");   // tell player how many guesses remaining

        } else if (numGuesses == 1) {                                                      // if didn't win and has no guesses remaining

            num_remaining_guess_label.setText("Game Over: You have No more guesses!");     // change label showing lost game
            num_remaining_guess_label.setTextFill(Color.web("#ff3333"));                   // change label color to red
            hintHigher.setVisible(false);                                                  // hide hint
            hintLower.setVisible(false);                                                   // hide hint

        } else if (isWinner) {

            num_remaining_guess_label.setText("Winner: " + (7 - numGuesses) + " guesses taken"); // change label to show won game

            num_remaining_guess_label.setTextFill(Color.web("#00cc66"));                         // change label color to green

            myTab.getTabs().add(tab3);                                                           // displays prize tab

            displayPrizesTree(PrizeData.fourStarTree.root);                                      // displays prizes from tree

            hintHigher.setVisible(false);                                                        // hide hint

            hintLower.setVisible(false);                                                         // hide hint

        }
    }
    /**
     * Reset
     *
     * Reset button
     *
     * resets the game to beginning by resetting all values and buttons
     *
     * @param numberInput               - textfield
     * @param num_remaining_guess_label - remaining guesses left
     * @param reset                     - reset button
     * @param start                     - start button
     * @param clear                     - clear button
     * @param guess                     - guess button
     */
    private void reset(TextField numberInput, Label num_remaining_guess_label, Button reset,
                       Button start, Button clear, Button guess, Button quit) {

        numGuesses = 6;                                                                //  reset number of guesses
        isWinner = false;                                                              //  assumes new player is not already winner
        hintHigher.setVisible(false);                                                  //  game start hints button hidden
        hintLower.setVisible(false);                                                   //  game start hints button hidden
        numberInput.setVisible(false);                                                 //  game start have text input hidden
        num_remaining_guess_label.setVisible(false);                                   //  game start have label hidden
        reset.setVisible(false);                                                       //  game start start button shows
        start.setVisible(true);                                                        //  game start clear button hidden
        quit.setVisible(false);                                                        // before game start quit button hidden
        clear.setVisible(false);                                                       //  guess button hidden
        guess.setVisible(false);                                                       //  reset start button
        num_remaining_guess_label.setText("You have " + numGuesses + " remaining!");   //  reset label text
        num_remaining_guess_label.setTextFill(Color.web("#000000"));                   //  reset label color
        winningNumber = generateWinningNumber();                                       // generate and store the winning number
        vBox_Tab3_bottom.getChildren().clear();                                        // remove Vbox children
        label_Prize_Title.setVisible(true);
    }

    /**
     * Quit
     *
     * Quits the game by closing the window
     *
     * @param primaryStage - the main window of the application
     */
    private void quit(Stage primaryStage) {

        primaryStage.close();   // closes game window

    }
    /**
     * clear
     *
     * clears input from textfield
     *
     * @param inputField - textField to be cleared
     */
    private void clear(TextField inputField) {

        inputField.clear();   // clear keyboard nextLine for enter key
    }
    /**
     * guess
     *
     * guess button
     *
     * stores input from Textfield when guess button is pressed
     * Validates input to be off type integer
     * Clears testField if type is incorrect
     *
     * @param inputField                - TextField for user input
     * @param num_remaining_guess_label - label of remaining guesses
     */
    private void guess(TextField inputField, Label num_remaining_guess_label) {

        try{
            userGuess = Integer.parseInt(inputField.getText()); // stores the next integer added to the textfield as the user guess

        }catch(NumberFormatException e){                        // catch if input is not the correct type of integer

            inputField.clear();                               // clear input field of incorrect type entered
        }

        compareNumbers(userGuess, winningNumber);               // check if guess is correct

        checkWinner(num_remaining_guess_label);                 // checks winner

        inputField.clear();                                     // clear keyboard nextLine for enter key
    }

    /**
     * alertBox
     *
     * shows alert box to confirm user choice
     *
     * @param primaryStage - program main stage
     */
    private void alertBox(Stage primaryStage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);           // create alert box

        alert.setTitle("Alert");                                         // set title

        alert.setHeaderText("Your game will not be saved if you quit");  // add header

        alert.setContentText("Are you ok with this?");                   // set dialog

        Optional<ButtonType> result = alert.showAndWait();               // makes user select option before can return

        if (result.get() == ButtonType.OK) {                             // if statement for if user selects yes or now

            quit(primaryStage);                                          // quit the game
        } else {
            alert.close();                                               // quit the alert box
        }
    }

      /* ----------------------------------------------------
             Game 2 Methods
         ----------------------------------------------------  */

    /**
     * generateRandomNumberArray
     *
     * generates an integer array and stores unique random numbers within them
     *
     * @param size : size of array you wish to fill
     * @return numberArray   : returns integer array
     */
    public static int[] generateRandomNumberArray(int size) {

        int[] numberArray = new int[size];                            // creates new array to store the winning numbers

        for (int index1 = 0; index1 < size; index1++) {               // loop through array to store the numbers

            numberArray[index1] = (int) (Math.random() * 49);         // generate random number between 0 - 45

            for (int index2 = 0; index2 < index1; index2++) {         // check if random has already been selected by comparing with other elements in array
                if (numberArray[index1] == numberArray[index2]) {     // if not unique it gives element new number

                    numberArray[index2] = (int) (Math.random() * 45);
                }
            }
        }
        System.out.print("Lotto Winning Numbers: ");                  //******  testing: prints winning numbers to console *******//
        for (int aNumberArray : numberArray) {
            System.out.print(" " + aNumberArray);
        }
        System.out.println("");

        return numberArray;                                           // returns winning number array
    }
    /**
     * Start : beginGame2
     * <p>
     * Starts the game by making the lotto numbers selectable
     */
    private void beginGame2() {

        for (Button lottoButton : lottoButtons) {        // loop through lotto number array

            lottoButton.setDisable(false);               // make all lotto number buttons selectable
        }
        g2_TryAgain.setDisable(false);                   // show reset button

        g2_Reset.setDisable(false);                      // show new number button

        g2_Start.setDisable(true);                       // disable start button
        try {
            PrizeData.storePrizes();                    // load up prizes
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Reset
     * resets the game
     *
     * @param hBox_User_Numbers : box used for styling
     */
    private void g2_reset(HBox hBox_User_Numbers) {

        g2_Submit.setDisable(true);                      // Disable submit button on game start

        g2_TryAgain.setDisable(true);                    // Disable reset button on game start

        g2_Reset.setDisable(true);                       // Disable new number button on game start

        g2_Start.setDisable(false);                      // disable start button

        g2_Submit.setStyle(null);                        // reset color of submit button

        hBox_User_Numbers.setStyle(null);                // reset background color

        label_Prize_Title.setVisible(true);

        vBox_Tab3_bottom.getChildren().clear();          // remove Vbox children from winner tab

        myTab.getTabs().remove(tab3);                    // hides prize tab

        userGuess2.clear();                              // clears the user guess array

        for (Button lottoButton : lottoButtons) {        // loop through all 45 lotto numbers to add buttons for each

            lottoButton.setDisable(true);                // disable numeric buttons
        }
        label_UserGuess_1.setVisible(false);             //
        label_UserGuess_2.setVisible(false);             //
        label_UserGuess_3.setVisible(false);             //
        label_UserGuess_4.setVisible(false);             // Hide user guess labels
        label_UserGuess_5.setVisible(false);             //
        label_UserGuess_6.setVisible(false);             //
        label_UserGuess_title.setVisible(false);         //

        label_WinningNum_1.setVisible(false);            //
        label_WinningNum_2.setVisible(false);            //
        label_WinningNum_3.setVisible(false);            //
        label_WinningNum_4.setVisible(false);            // hide winning number labels
        label_WinningNum_5.setVisible(false);            //
        label_WinningNum_6.setVisible(false);            //
        label_WinningNum_title.setVisible(false);        //

        g2_prize = 0;                                    // reset prize

        numberGuess = 0;                                 // reset number of guesses
    }
    /**
     * newNumbers
     * <p>
     * resets the winning lotto numbers
     */
    private void newLottoNumbers() {

        generateRandomNumberArray(numberLimit);          // reset winning lotto numbers
    }

    /**
     * storeGuess
     * <p>
     * if user selects a numeric button it converts its text value to int and stores in the userGuess Array
     *
     * @param lottoButton - numeric button selected
     */
    private void storeGuess(Button lottoButton) {

        int result = Integer.parseInt(lottoButton.getText());            // convert button clicks text value to integer and store as result

        lottoButton.setDisable(true);                                    // disables button once selected

        label_UserGuess_title.setVisible(true);                          // displays the user guess title

        if (numberGuess < numberLimit) {                                 // checks if number guess is less than the limit of 6

            userGuess2.add(result);                                      // adds the user guess to the userGuess arrayList

            numberGuess++;                                               // increase number of guesses

            switch (numberGuess)  {                                      // sets label of user guess and makes label visible
                case 1:
                    label_UserGuess_1.setText(Integer.toString(result)); // sets label of user guess and makes label 1 visible
                    label_UserGuess_1.setVisible(true);
                    break;
                case 2:
                    label_UserGuess_2.setText(Integer.toString(result)); // sets label of user guess and makes label 2 visible
                    label_UserGuess_2.setVisible(true);
                    break;
                case 3:
                    label_UserGuess_3.setText(Integer.toString(result)); // sets label of user guess and makes label 3 visible
                    label_UserGuess_3.setVisible(true);
                    break;
                case 4:
                    label_UserGuess_4.setText(Integer.toString(result)); // sets label of user guess and makes label 4 visible
                    label_UserGuess_4.setVisible(true);
                    break;
                case 5:
                    label_UserGuess_5.setText(Integer.toString(result)); // sets label of user guess and makes label 5 visible
                    label_UserGuess_5.setVisible(true);
                    break;
                case 6:
                    label_UserGuess_6.setText(Integer.toString(result)); // sets label of user guess and makes label 6 visible
                    label_UserGuess_6.setVisible(true);
                    break;
                default:
            }
            if (numberGuess == numberLimit) {                                // check if the user guess array is full

                for (Button lottoButton1 : lottoButtons) {                   // loop through all 45 lotto numbers to add buttons for each

                    lottoButton1.setDisable(true);                           // disable numeric buttons

                    g2_Submit.setDisable(false);                             // enable submit button

                    g2_Submit.setStyle("-fx-background-color: #b3ecff;");    // change color of submit button to indicate it needs to be pressed
                }
            }
        }
    }

    /**
     *  lottoCompareNumbers
     *
     *  Compares the userGuess array and winningNumber Array
     *
     *
     */
    private void lottoCompareNumbers(){

        for(int index = 0; index < numberLimit; index++ ){            // loops for numberLimit times ( 6 )

            sequentialSearch(winningNumbers, userGuess2.get(index));  // runs sequentialSearch with each userGuess and the winningNumbers array
        }
    }
    /**
     * Method to check if a value is within an array
     *
     * Used to compare userGuess and winningNumbers to determine if user won a prize
     * Increments g2_prize for each match found
     *
     *@param array   - array to search
     *@param value   - value to check if in second array
     */
    private static void sequentialSearch(int[] array, int value) {

        int index = 0;                                             // create and initialise index

        boolean found = false;                                     // create and initialise found boolean

        while (!found && index < array.length) {                   // keeps looping whilst number not found and until end of array

            if (array[index] == value) {                           // if  values are the same

                found = true;                                      // allows to exit loop

                g2_prize++;                                        // increment prize variable
            }
            index++;                                               // increment index for loop
        }
    }
    /**
     *  isWinner
     *
     *  Displays to the user they won
     *
     *  Displays appropriate prize to user
     *
     */
    private void isWinner(HBox hBox_User_Numbers){

        if( g2_prize == 4) {                                                    // user matched 4 numbers correctly

            displayWinningNumbers();                                            // displays winning numbers

            myTab.getTabs().add(tab3);                                          // displays prize tab

            displayPrizesTree(PrizeData.fourStarTree.root);                     // displays prizes from tree

        } else if( g2_prize == 5 ) {                                            // user matched 5 numbers correctly

            displayWinningNumbers();                                            // displays winning numbers

            myTab.getTabs().add(tab3);                                          // displays prize tab

            displayPrizesTree(PrizeData.fiveStarTree.root);                     // displays prizes from tree

        } else if ( g2_prize == 6) {                                            // user matched 6 numbers correctly

            displayWinningNumbers();                                            // displays winning numbers

            myTab.getTabs().add(tab3);                                          // displays prize tab

            displayPrizesTree(PrizeData.sixStarTree.root);                      // displays prizes from tree

        } else {                                                                // else user did not win

            NotWinner(hBox_User_Numbers);                                       // run not Winner
        }
        if(g2_prize >= 4) {              // if user guessed 4 or more numbers correctly : Won Game

            newLottoNumbers();           // generates new winning Numbers

            hBox_User_Numbers.setStyle("-fx-background-color: #80ffbf;"); // change background color to green

        }
    }

    /**
     *  displayWinningNumbers
     *
     *  displays winning number
     */
    private void displayWinningNumbers() {

        label_WinningNum_1.setVisible(true);    //
        label_WinningNum_2.setVisible(true);    //
        label_WinningNum_3.setVisible(true);    //
        label_WinningNum_4.setVisible(true);    //
        label_WinningNum_5.setVisible(true);    // Hide Winning number labels at beginning of game
        label_WinningNum_6.setVisible(true);    //
        label_WinningNum_title.setVisible(true);//
    }

    /**
     *  notWinner
     *
     *  Displays to the user they did not win by changing background color
     *  Prints to console that user did not win the game
     *
     */
    private void NotWinner(HBox hBox_User_Numbers) {

        hBox_User_Numbers.setStyle("-fx-background-color: #ff6666;"); // change background color to red

    }

 /* ----------------------------------------------------
            Prize Tab Method
    ----------------------------------------------------  */
    /**
     *  displayPrizesTree
     *
     *
     *  displays prizes to user based on the starValue assigned
     *
     *@param node : takes in node of the tree to search
     */
    private void displayPrizesTree(Node node) {

        if (node != null) {                                     // loops whilst nodes are not null

            displayPrizesTree(node.leftChild );                 // Traverse the left node

            Button prizeButton = new Button();                  // creates new button for prize

            prizeButton.setMinWidth(100);                       // sets minimum width of prize button

            prizeButton.setMinHeight(45);                       // sets minimum height of prize button

            prizeButton.setText(node.key);                      // changes text of button to be the key

            vBox_Tab3_bottom.getChildren().add(prizeButton);    // adds button to VBox for prizes

            prizeButton.setOnAction(e ->{                       // when clicked

                        label_You_Won.setVisible(true);                 // display the you won label

                        label_Ask_Winner.setVisible(true);              // display label asking winner name

                        storeWinnerName.setVisible(true);               // hide text field for name input

                        vBox_Tab3_bottom.getChildren().clear();         // remove Vbox children

                        vBox_Tab3_bottom.getChildren().addAll(label_You_Won,prizeButton);

                        prizeButton.setText(node.prize);                // changes text to be actual prize

                        storeWinner(node.prize);                        // store winners details

                        prizeButton.setStyle("-fx-background-color: #80ffbf;"); // change background color of prize choosen to green
                    }
            );
            displayPrizesTree(node.rightChild);                // Traverse the right node


        }
    }

    /**
     * storeWinnerName
     *
     * Adds Winner name to the Winners array list
     *
     */
    public void storeWinner(String prize){

        storeWinnerName.setOnAction((ActionEvent e) -> {                         // When user enters name in textfield

            String name = storeName(storeWinnerName);                            // store name of winner from textfield

            winner w1 = new winner(name,prize );                                 // create new winner

            resetPrizeTab();                                                     // resets the prize tab

            Label winnerDetailsSaved = new Label("Winner's details saved"); // show details saved

            vBox_Tab3_bottom.getChildren().add(winnerDetailsSaved);              // show details saved

            winnerList.getWinnersList().add(w1) ;                                // add winner to the winners arrayList
            try {
                winnerList.persistWinnerListToFile();                            // save winners details to file
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * resetPrizeTab
     *
     * resets labels and fields of prize tab
     *
     */
    public void resetPrizeTab(){

        vBox_Tab3_bottom.getChildren().clear();          // remove Vbox children

        label_You_Won.setVisible(false);                 // hide the you won label

        label_Ask_Winner.setVisible(false);              // hide label asking winner name

        storeWinnerName.setVisible(false);               // hide text field for name input
    }
 /* ----------------------------------------------------
          Winner tab Methods
    ----------------------------------------------------  */

    /**
     * displayWinnerAscendingOrder
     *
     * displays winners in ascending order
     */
    public void reorderWinnersName() throws CloneNotSupportedException {
        int n = winnerList.getWinnersList().size();                                             // get size of winners list

        winner temp = new winner();                                                             // create temporary winner object

        for (int i = 0; i < n; i++) {                                                           // loop through array

            for (int j = 1; j < (n - i); j++) {                                                 // secondary loop

                int decVal1 = winnerList.getWinnersList().get(j - 1).getName().charAt(0);       // convert first char of initial item name to decimal value
                int decVal2 = winnerList.getWinnersList().get(j).getName().charAt(0);           // convert first char of next item name to decimal value

                if (decVal1   > decVal2) {                                                                  // if initial value is greater than second
                    temp.setName(winnerList.winnersList.get(j - 1).getName());                              //
                    temp.setPrize(winnerList.winnersList.get(j - 1).getPrize());                            // bubble sorts through the names
                    winnerList.winnersList.get(j - 1).setName(winnerList.winnersList.get(j).getName());     //
                    winnerList.winnersList.get(j - 1).setPrize(winnerList.winnersList.get(j).getPrize());   //
                    System.out.print("");                                                                   //
                    winnerList.winnersList.get(j).setName(temp.getName());                                  //
                    winnerList.winnersList.get(j).setPrize(temp.getPrize());                                //

                }
            }
        }
    }
    /**
     * displayWinnerDesscendingOrder
     *
     * displays winners in descending order
     */
    public  void reorderWinnersPrize(){

        int n = winnerList.getWinnersList().size();                                             // get size of winners list

        winner temp = new winner();                                                             // create temporary winner object

        for (int i = 0; i < n; i++) {                                                           // loop through array

            for (int j = 1; j < (n - i); j++) {                                                 // secondary loop

                int decVal1 = winnerList.getWinnersList().get(j - 1).getPrize().charAt(0);      // convert first char of initial item name to decimal value
                int decVal2 = winnerList.getWinnersList().get(j).getPrize().charAt(0);          // convert first char of next item name to decimal value

                if (decVal1   > decVal2) {                                                                  // if initial value is greater than second
                    temp.setName(winnerList.winnersList.get(j - 1).getName());                              //
                    temp.setPrize(winnerList.winnersList.get(j - 1).getPrize());                            // bubble sorts through the names
                    winnerList.winnersList.get(j - 1).setName(winnerList.winnersList.get(j).getName());     //
                    winnerList.winnersList.get(j - 1).setPrize(winnerList.winnersList.get(j).getPrize());   //
                    winnerList.winnersList.get(j).setName(temp.getName());                                  //
                    winnerList.winnersList.get(j).setPrize(temp.getPrize());                                //

                }
            }
        }
    }

    public void displayWinnerDetails(){

        vBox_WinnersDetails.getChildren().clear();                          // clears vbox

        for(int i = 0; i < winnerList.getWinnersList().size(); i++ ) {      // loop through all elements of winner arrayList

            Label DisplayWinner = new Label();                              // creates new button for prize

            DisplayWinner.setMinWidth(100);                                 // sets minimum width of prize button

            DisplayWinner.setMinHeight(45);                                 // sets minimum height of prize button

            DisplayWinner.setText(i+1 + "\t" + winnerList.getWinnersList().get(i).getName()
                    +  "\t\t" + winnerList.getWinnersList().get(i).getPrize()); // changes text of button to be name of winner and index+1

            vBox_WinnersDetails.getChildren().add(DisplayWinner);           // adds label to VBox for winners

        }
    }

    /**
     * removeWinner
     *
     * Deletes winner from winnersList
     *
     */
    public void removeWinner() throws IOException {

        int winnerIndex=-1;

        try{
            winnerIndex = Integer.parseInt(storeWinnerIndex.getText());                 // stores the next integer added to the textfield as the user guess

        }catch(NumberFormatException e){                                                // catch if input is not the correct type of integer

            storeWinnerIndex.clear();                                                   // clear input field of incorrect type entered
        }
        winnerList.getWinnersList().remove((winnerIndex - 1));                      // removes winner at index : NOT WORKING

        storeWinnerIndex.clear();                                                       // clear textfield

        displayWinnerDetails();                                                         // show new list

        winnerList.persistWinnerListToFile();                                           // save new list to file
    }


    /* -------------------------------------------------------------------
         Method to create winner clones to check how it impacts memory
       -------------------------------------------------------------------  */

    /**
     * cloneWinners
     *
     * Use visual VM or other to examine your project’s memory.
     * Create a loop that creates lots and lots of dummy
     * winner objects add them to some collection and then take
     * a snapshot of the heap again to see the effect on the Heap,
     * or wait for the out of memory error. Let it run until you
     * get outofmemory error
     *
     */
    public void cloneWinners() {
        ArrayList<winner> cloneArray = new ArrayList<>();                               // arraylist to hold the winners

        while (true) {                                                                  // infinite loop

            winner w1 = new winner("CloneName", "ClonePrize");   // create new winner

            cloneArray.add(w1);                                                         // add to list
        }
    }

} // close class
