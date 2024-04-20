// Programmer: Cyrus McCollim
// Date: April 17, 2024
// Purpose: Determines whether a maze is possible to complete.
// Input: File containing a map of a maze.
//        "#" - Wall, "." - Unexplored, "o" - Start, "*" - End
// Output: Whether the maze can be completed, and the paths searched.

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

public class Main {
    public static String[][] maze;
    public static Room startingRoom;

    public static void main(String[] args){
        System.out.print("\nWhat file contains the map of the maze? ");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();

        if (importMaze(fileName)){
            // Shows the user the un-searched maze while grabbing the location of the starting point.
            System.out.println("\nHere is the maze!");
            printMaze();

            // Outputs whether a path exists,
            boolean pathExists = searchMaze(startingRoom);
            System.out.println("\nA path to complete the maze does " + ((pathExists)? "exist" : "not exist") + "!");

            System.out.println("\nHere are the paths explored:");
            printMaze();
        }

        System.out.println("\nGoodbye!");
    }

    public static boolean searchMaze(Room current){
        // Holds the indexes of adjacent squares to the current room.
        int[][] adjacentPositions = {{current.getX() - 1, current.getY()},
                                     {current.getX() + 1, current.getY()},
                                     {current.getX(), current.getY() - 1},
                                     {current.getX(), current.getY() + 1}};

        // Traverses all four adjacent squares to the current room.
        for (int[] p : adjacentPositions){
            try {
                // If the adjacent square is an unexplored room, create the next node and search recursively for more rooms.
                String nextSquare = maze[p[1]][p[0]];
                if (nextSquare.equals(".")) { // If unexplored room...
                    // Marks the current room as explored.
                    maze[p[1]][p[0]] = "x";

                    Room next = new Room(p[0], p[1]);
                    current.getPaths().add(next);

                    if (searchMaze(next)) {
                        return true;
                    }
                } else if (nextSquare.equals("*")){ // If the end of the maze...
                    return true;
                }
            } catch (Exception ignored) { } // Prevents index out of bounds on array look-ups.
        }

        return false;
    }

    public static boolean importMaze(String fileName){
        try {
            // Adds all lines of the file to a list of lines containing squares on the maze map.
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<String> lines = reader.lines().toList();
            reader.close();

            // Initializes the maze with the appropriate dimensions.
            String[] mazeDimensions = lines.getFirst().split(" ");
            maze = new String[Integer.parseInt(mazeDimensions[1])][Integer.parseInt(mazeDimensions[0])];

            // Traverses the maze and populates each square in the array.
            for (int r = 0; r < maze.length; r++) {
                for (int c = 0; c < maze[0].length; c++) {
                    String squareContent = lines.get(r + 1).split("")[c];

                    if (squareContent.equals("o")) {
                        startingRoom = new Room(c, r);
                    }

                    maze[r][c] = squareContent;
                }
            }
            return true;
        } catch (Exception e){
            System.out.print("Failed to load maze.");
            return false;
        }
    }

    public static void printMaze(){
        // Shows the user the maze.
        for (String[] x : maze) {
            for (String y : x) {
                // Prints using a special unicode character for a cool visual.
                switch (y){
                    case ".":
                        System.out.print("⬛" + " "); /*Black Square*/
                        break;
                    case "*":
                        System.out.print("\uD83D\uDFE1" + " "); /*Golden Circle*/
                        break;
                    case "x":
                        System.out.print("\uD83D\uDD34" + " "); /*Red Circle*/
                        break;
                    case "o":
                        System.out.print("\uD83D\uDD35" + " "); /*Blue Circle*/
                        break;
                    default:
                        System.out.print("⬜" + " "); /*White Square*/
                        break;
                }
            }
            System.out.println();
        }
    }
}
