package com.udacity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by asser on 6/6/16.
 */

public class GameTest {

    private Game game;

    @Before
    public void setUp(){
        game = new Game();
    }
    //TODO
    /*CODE REVIEW NOTE:
    * Original code had inconsistent environment for "Game.checkGameWinner".
    * In game's environment players were taking turns by invoking "Game.playAt", which decreased "Game.freeSpots" from 9 to 0.
    *   If game would end with tie in this environment, "Game.freeSpots" would be 0.
    * Unlike original tests, which were sideloading game's grid, which meant, that:
    *   "Game.freeSpots" was 9 even, when there was different number of free spots ex. 0.
    *   What I did was:
    *       * Change how sideloading works
    *           (now tests are sideloading grid to "Game.grid" rather than directly to "Game.checkGameWinner").
    *       * Recalculate "Game.freeSpots" because of sideloading.
    * Thank you for listening me and have a nice day ;)
    */
    /**
     * Set game's grid.
     * This method sets correctly game's grid (including setting correctly "game.freeSpots" variable).
     * @param grid grid to be set in game.grid
     */
    public void setGrid(char[][] grid){
      game.grid = grid;
        for (char[] mark : grid) {
            for (char entry : mark) {
                if(entry != '-') game.freeSpots--;
            }
        }
    }
    @Test
    public void horizontal_x_win_case1() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid = {   {'-', 'o', 'x'},
                            {'o', 'x', 'x'},
                            {'-', 'o', 'x'}};
        setGrid(grid);
        assertTrue("x horizontal win fail", game.checkGameWinner().equalsIgnoreCase("x wins"));
    }

    @Test
    public void horizontal_x_win_case2() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'x', '-', '-'},
                            {'x', 'x', 'o'},
                            {'x', 'o', 'o'}};
        setGrid(grid2);
        assertTrue("x horizontal win fail", game.checkGameWinner().equalsIgnoreCase("x wins"));
    }
    @Test
    public void horizontal_x_win_case3() {
        // Note that horizontal here is vertical in UI and vise versa
        char [][] grid3 = { {'-','x','-'},
                            {'o','x','o'},
                            {'x','x','o'}};
        setGrid(grid3);
        assertTrue( "x horizontal win fail", game.checkGameWinner().equalsIgnoreCase("x wins"));
    }

    @Test
    public void horizontal_o_win_case1() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid = {   {'-', 'o', 'x'},
                            {'x', 'o', '-'},
                            {'-', 'o', 'x'}};
        setGrid(grid);
        assertTrue("o horizontal win fail", game.checkGameWinner().equalsIgnoreCase("o wins"));
    }

    @Test
    public void horizontal_o_win_case2() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid = {  {'x', 'x', 'o'},
                            {'-', 'x', 'o'},
                            {'x', 'o', 'o'}};
        setGrid(grid);
        assertTrue("o horizontal win fail", game.checkGameWinner().equalsIgnoreCase("o wins"));
    }
    @Test
    public void horizontal_o_win_case3() {
        // Note that horizontal here is vertical in UI and vise versa
        char [][] grid = { {'o','x','-'},
                            {'o','-','x'},
                            {'o','x','-'}};
        setGrid(grid);
        assertTrue("o horizontal win fail", game.checkGameWinner().equalsIgnoreCase("o wins"));
    }


    @Test
    public void vertical_x_win_case1() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid = {   {'-', 'o', 'x'},
                            {'x', 'x', 'x'},
                            {'-', 'o', 'o'}};
        setGrid(grid);
        assertTrue("x vertical win fail", game.checkGameWinner().equalsIgnoreCase("x wins"));
    }

    @Test
    public void vertical_x_win_case2() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid = {  {'x', 'o', 'o'},
                            {'-', '-', 'o'},
                            {'x', 'x', 'x'}};
        setGrid(grid);
        assertTrue("x vertical win fail", game.checkGameWinner().equalsIgnoreCase("x wins"));
    }
    @Test
    public void vertical_x_win_case3() {
        // Note that horizontal here is vertical in UI and vise versa
        char [][] grid3 = { {'x','x','x'},
                            {'o','-','o'},
                            {'x','-','o'}};
        setGrid(grid3);
        assertTrue("x vertical win fail", game.checkGameWinner().equalsIgnoreCase("x wins"));
    }

    @Test
    public void vertical_o_win_case1() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid = {   {'o', 'o', 'o'},
                            {'x', 'x', '-'},
                            {'x', 'o', 'x'}};
        setGrid(grid);
        assertTrue("o vertical win fail", game.checkGameWinner().equalsIgnoreCase("o wins"));
    }

    @Test
    public void vertical_o_win_case2() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'x', 'x', 'o'},
                            {'o', 'o', 'o'},
                            {'x', 'o', 'x'}};
        setGrid(grid2);
        assertTrue("o vertical win fail", game.checkGameWinner().equalsIgnoreCase("o wins"));
    }
    @Test
    public void vertical_o_win_case3() {
        // Note that horizontal here is vertical in UI and vise versa
        char [][] grid3 = { {'x','x','-'},
                            {'x','-','x'},
                            {'o','o','o'}};
        setGrid(grid3);
        assertTrue("o vertical win fail", game.checkGameWinner().equalsIgnoreCase("o wins"));
    }


    @Test
    public void diagonal_x_win_case1() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'x', 'o', 'o'},
                            {'-', 'x', 'o'},
                            {'x', '-', 'x'}};
        setGrid(grid2);
        assertTrue( "x diagonal win fail", game.checkGameWinner().equalsIgnoreCase("x wins"));
    }
    @Test
    public void diagonal_x_win_case2() {
        // Note that horizontal here is vertical in UI and vise versa
        char [][] grid3 = { {'x','-','x'},
                            {'-','x','o'},
                            {'x','o','o'}};
        setGrid(grid3);
        assertTrue("x diagonal win fail", game.checkGameWinner().equalsIgnoreCase("x wins"));
    }

    @Test
    public void diagonal_o_win_case1() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid = {   {'x', '-', 'o'},
                            {'x', 'o', 'x'},
                            {'o', 'o', 'x'}};
        setGrid(grid);
        assertTrue("o diagonal win fail", game.checkGameWinner().equalsIgnoreCase("o wins"));
    }

    @Test
    public void diagonal_o_win_case2() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid = {  {'o', 'x', '-'},
                            {'x', 'o', 'x'},
                            {'x', 'o', 'o'}};
        setGrid(grid);
        assertTrue("o diagonal win fail", game.checkGameWinner().equalsIgnoreCase("o wins"));
    }

    @Test
    public void tie_game_case1() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid = {  {'o', 'x', 'o'},
                            {'x', 'o', 'x'},
                            {'x', 'o', 'x'}};
        setGrid(grid);
        assertTrue("tie game failed", game.checkGameWinner().equalsIgnoreCase("tie"));
    }

    @Test
    public void tie_game_case2() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'o', 'x', 'o'},
                            {'o', 'x', 'x'},
                            {'x', 'o', 'x'}};
        setGrid(grid2);
        assertTrue("tie game failed", game.checkGameWinner().equalsIgnoreCase("tie"));
    }

    @Test
    public void tie_game_case3() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'x', 'o', 'o'},
                            {'o', 'x', 'x'},
                            {'x', 'o', 'o'}};
        setGrid(grid2);
        assertTrue( "tie game failed", game.checkGameWinner().equalsIgnoreCase("tie"));
    }


    @Test
    public void no_winner_case1() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'o', 'x', '-'},
                            {'o', 'x', 'x'},
                            {'x', 'o', 'x'}};
        setGrid(grid2);
        assertTrue("game no supposed to end", game.checkGameWinner().equalsIgnoreCase("none"));
    }

    @Test
    public void no_winner_case2() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'o', 'x', '-'},
                            {'o', 'x', 'x'},
                            {'-', 'o', 'x'}};
        setGrid(grid2);
        assertTrue("game no supposed to end", game.checkGameWinner().equalsIgnoreCase("none"));
    }

    @Test
    public void no_winner_case3() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'-', '-', '-'},
                            {'-', 'x', 'o'},
                            {'-', '-', 'x'}};
        setGrid(grid2);
        assertTrue("game no supposed to end", game.checkGameWinner().equalsIgnoreCase("none"));
    }

    @Test
    public void no_winner_case4() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'-', '-', '-'},
                            {'-', 'x', '-'},
                            {'-', '-', '-'}};
        setGrid(grid2);
        assertTrue("game no supposed to end", game.checkGameWinner().equalsIgnoreCase("none"));
    }
    @Test
    public void five_empty_tiles() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'-', '-', '-'},
                {'-', 'x', '-'},
                {'o', 'x', 'o'}};
        setGrid(grid2);
        assertTrue("game.freeSpots should be 5 instead of "+ game.freeSpots, game.freeSpots == 5);
    }
    @Test
    public void zero_empty_tiles() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'x', 'o', 'x'},
                {'o', 'x', 'o'},
                {'o', 'x', 'o'}};
        setGrid(grid2);
        assertTrue("game.freeSpots should be 0 instead of "+ game.freeSpots, game.freeSpots == 0);
    }
    @Test
    public void nine_empty_tiles() {
        // Note that horizontal here is vertical in UI and vise versa
        char[][] grid2 = {  {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}};
        setGrid(grid2);
        assertTrue("game.freeSpots should be 9 instead of "+ game.freeSpots, game.freeSpots == 9);
    }
}
