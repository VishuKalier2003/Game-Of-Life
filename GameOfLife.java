/* The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0)... 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
* Any live cell with fewer than two live neighbors dies as if caused by under-population...
* Any live cell with two or three live neighbors lives on to the next generation...
* Any live cell with more than three live neighbors dies, as if by over-population...
* Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction...
* The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously... 
Given the current state of the m x n grid board, return the next state...
* Eg 1 : Input = [0, 1, 0]                          Output = [0, 0, 0]
*                [0, 0, 1]                                   [1, 0, 1]
*                [1, 1, 1]                                   [0, 1, 1]
*                [0, 0, 0]                                   [0, 1, 0]
* Eg 2 : Input = [1, 1]                             Output = [1, 1]
*                [1, 0]                                      [1, 1]
*/
import java.util.*;
public class GameOfLife
{
    public int[][] LifeMatrixNextState(int board[][])
    {
        int Answer[][] = new int[board.length][board[0].length];   // Creating the Output matrix...
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                Answer[i][j] = 0;    // Setting the value to Zero (the base value)...
                System.out.print(board[i][j]+",");
                NeighborsSearch(board, Answer, i, j);   // Calling the Neighbors Search...
            }
            System.out.println();
        }
        System.out.println("The Next state of Board of Game of Life : ");
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
                System.out.print(Answer[i][j]+",");     // Printing the Output board...
            System.out.println();
        }
        return Answer;    // Returning the Output matrix...
    }
    public void NeighborsSearch(int board[][], int answer[][], int x, int y)
    {
        int live = 0;   // Variable to store the number of adjacent live cells...
        if((x - 1 != -1) && (board[x - 1][y] == 1))
            live++;     // Checking the Life state of Left cell...
        if((x - 1 != -1) && (y - 1 != -1) && (board[x - 1][y - 1] == 1))
            live++;     // Checking the Life state of Top Left cell..
        if((x - 1 != -1) && (y + 1 != board[0].length) && (board[x - 1][y + 1] == 1))
            live++;     // Checking the Life state of Bottom Left cell...
        if((y - 1 != -1) && (board[x][y - 1] == 1))
            live++;     // Checking the Life state of Top cell...
        if((x + 1 != board.length) && (y + 1 != board[0].length) && (board[x + 1][y + 1] == 1))
            live++;     // Checking the Life state of Bottom Right cell...
        if((x + 1 != board.length) && (y - 1 != -1) && (board[x + 1][y - 1] == 1))
            live++;     // Checking the Life state of Top Right cell...
        if((x + 1 != board.length) && (board[x + 1][y] == 1))
            live++;     // Checking the Life state of Right cell...
        if((y + 1 != board[0].length) && (board[x][y + 1] == 1))
            live++;     // Checking the Life state of Bottom cell...
        if((live < 2) && (board[x][y] == 1))
            answer[x][y] = 0;     // Under population death...
        else if(((live == 2) || (live == 3)) && (board[x][y] == 1))
            answer[x][y] = 1;     // Equilibrium Life condition...
        else if((live > 3) && (board[x][y] == 1))
            answer[x][y] = 0;     // Over population death...
        else if((live == 3) && (board[x][y] == 0))
            answer[x][y] = 1;     // Reproduction condition...
        return;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int m, n;
        System.out.print("Enter length (number of rows) of matrix : ");
        m = sc.nextInt();
        System.out.print("Enter breadth (number of columns) of matrix : ");
        n = sc.nextInt();
        int matrix[][] = new int[m][n];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                System.out.print("Enter data of "+(i+1)+" row and "+(j+1)+" column : ");
                matrix[i][j] = sc.nextInt();
            }
        }
        GameOfLife gameoflife = new GameOfLife();     // Object creation...
        matrix = gameoflife.LifeMatrixNextState(matrix);      // Function calling...
        sc.close();
    }
}


// Time Complexity  - O(N x N) time...
// Space Complexity - O(N x N) space...

/* DEDUCTIONS :- 
 * 1. The Answer matrix is created and filled with all dead cells (here 0)...
 * 2. All the eight adjacent neighbors is checked in an Iterative fashion foe every cell, then on the basis of the provided conditions the Life or Death of the current cell is determined...
*/