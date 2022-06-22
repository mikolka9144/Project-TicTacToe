package com.udacity;

import java.util.Arrays;

/**
 * Created by udacity 2016
 * The Main class containing game logic and backend 2D array
 */
public class Game {

    private char turn; // who's turn is it, 'x' or 'o' ? x always starts
    private boolean twoPlayer; // true if this is a 2 player game, false if AI playing
    protected char [][] grid; // a 2D array of chars representing the game grid
    protected int freeSpots; // counts the number of empty spots remaining on the board (starts from 9  and counts down)
    private static GameUI gui;

    /**
     * Create a new single player game
     *
     */
    public Game() {
        newGame(false);
    }

    /**
     * Create a new game by clearing the 2D grid and restarting the freeSpots counter and setting the turn to x
     * @param twoPlayer: true if this is a 2 player game, false if playing against the computer
     */
    public void newGame(boolean twoPlayer){
        //sets a game to one or two player
        this.twoPlayer = twoPlayer;

        // initialize all chars in 3x3 game grid to '-'
        grid = new char[3][3];
        //fill all empty slots with -
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                grid[i][j] = '-';
            }
        }
        //start with 9 free spots and decrement by one every time a spot is taken
        freeSpots = 9;
        //x always starts
        turn = 'x';
    }


    /**
     * Gets the char value at that particular position in the grid array
     * @param i the x index of the 2D array grid
     * @param j the y index of the 2D array grid
     * @return the char value at the position (i,j):
     *          'x' if x has played here
     *          'o' if o has played here
     *          '-' if no one has played here
     *          '!' if i or j is out of bounds
     */
    public char gridAt(int i, int j){
        if(i>=3||j>=3||i<0||j<0)
            return '!';
        return grid[i][j];
    }

    /**
     * Places current player's char at position (i,j)
     * Uses the variable turn to decide what char to use
     * @param i the x index of the 2D array grid
     * @param j the y index of the 2D array grid
     * @return boolean: true if play was successful, false if invalid play
     */
    public boolean playAt(int i, int j){
        //check for index boundries
        if(i>=3||j>=3||i<0||j<0)
            return false;
        //check if this position is available
        if(grid[i][j] != '-'){
            return false; //bail out if not available
        }
        //update grid with new play based on who's turn it is
        grid[i][j] = turn;
        //update free spots
        freeSpots--;
        return true;
    }


    /**
     * Override
     * @return string format for 2D array values
     */
    public String toString(){
        return Arrays.deepToString(this.grid);
    }

    /**
     * Performs the winner chack and displayes a message if game is over
     * @return true if game is over to start a new game
     */
    public boolean doChecks() {
        //check if there's a winner or tie ?
        String winnerMessage = checkGameWinner();
        if (!winnerMessage.equals("None")) {
            gui.gameOver(winnerMessage);
            newGame(false);
            return true;
        }
        return false;
    }

    /**
     * Allows computer to play in a single player game or switch turns for 2 player game
     */
    public void nextTurn(){
        //check if single player game, then let computer play turn
        if(!twoPlayer){
            if(freeSpots == 0){
                return ; //bail out if no more free spots
            }
            int ai_i, ai_j;
            do {
                //randomly pick a position (ai_i,ai_j)
                ai_i = (int) (Math.random() * 3);
                ai_j = (int) (Math.random() * 3);
            }while(grid[ai_i][ai_j] != '-'); //keep trying if this spot was taken
            //update grid with new play, computer is always o
            grid[ai_i][ai_j] = 'o';
            //update free spots
            freeSpots--;
        }
        else{
            //change turns
            if(turn == 'x'){
                turn = 'o';
            }
            else{
                turn = 'x';
            }
        }
        return;
    }


    /**
     * Checks if the game has ended either because a player has won, or if the game has ended as a tie.
     * If game hasn't ended the return string will be "None",
     * If the game ends as tie, the return string will be "Tie",
     * If the game ends because there's a winner, it will return "X wins" or "O wins" accordingly
     * @return String indicating the outcome of the game: "X wins", "O wins", "Tie" or "None"
     */
    /* * @param grid 2D array of characters representing the game board
     *                       'x' if x has played here
     *      *          'o' if o has played here
     *      *          '-' if no one has played here
     *      *          '!' if i or j is out of bounds
     * */
    public String checkGameWinner(){
        //check horizontal lines
        for (int x = 0; x < 3; x++) {
            String line_char = IsLine(grid,x,0,x,1,x,2);
            if(!line_char.equals("-")) return line_char+" wins";
        }
        //check vertical lines
        for (int y = 0; y < 3; y++) {
            String line_char = IsLine(grid,0,y,1,y,2,y);
            if(!line_char.equals("-")) return line_char+" wins";
        }
        //check crosses
        String line_char = IsLine(grid,0,0,1,1,2,2);
        if(!line_char.equals("-")) return line_char+" wins";

        String line_char2 = IsLine(grid,0,2,1,1,2,0);
        if(!line_char2.equals("-")) return line_char2+" wins";
        if(freeSpots == 0) return "Tie";
        return "None";
    }

    /**
     * Checks if tiles (x1,y1),(x2,y2),(x3,y3) contain the same tile type
     * and if so, returns it.
     * @param grid game's grid
     * @return tile type (as upperCase), that appeared in all 3 tiles (returns '-' if there is no such tile)
     */
    private String IsLine(char[][] grid,int x1,int y1,int x2,int y2,int x3,int y3){
        boolean IsLineCreated = grid[x1][y1] == grid[x2][y2] && grid[x2][y2] == grid[x3][y3];
        if(IsLineCreated){
            char lineType = grid[x1][y1];
            switch (lineType){
                case 'x':
                    return "X";
                case 'o':
                    return "O";
            }
        }
        return "-";
    }
    /**
     * Main function
     * @param args command line arguments
     */
    public static void main(String args[]){
        Game game = new Game();
        gui = new GameUI(game);
    }

}
