package com.coen448;

import java.util.*;

public class Robot {
    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST;
    }

    public static final String text_purple = "\u001B[35m";
    public static final String text_reset = "\u001B[0m";

    boolean pen_down = false; // False = Up True = Down
    boolean initialized = false;
    Direction facing_dir = Direction.NORTH;
    int[] location = { 0, 0 };

    int[][] floor;

    static List<String> history = new ArrayList<>();

    // Initialize the system with default values and user input floor size
    public void init(int size) {
        this.floor = new int[size][size];
        this.location = new int[2];
        this.facing_dir = Direction.NORTH;
        this.pen_down = false;
        this.initialized = true;

        addEvent("I", size);
    }

    //Returns a string of the history of all commands
    public String getHistory(){
        addEvent("H", -1);
        return "Command History:\n"+String.join("\n", history);
    }

    //Add to history
    public void addEvent(String cmd, int cmd_int){
        switch (cmd) {
            case "U": // Pen Up
                history.add("U - Pen Up");
                break;
            case "D": // Pen Down
                history.add("D - Pen Down");
                break;
            case "R": // Turn Right
                history.add("R - Turn Right");
                break;
            case "L": // Turn Left
                history.add("L - Turn Left");
                break;
            case "M": // Move Forward
                history.add("M " + cmd_int + " - Move " + cmd_int + " spaces");
                break;
            case "P": // Print Floor
                history.add("P - Print Floor");
                break;
            case "C": // Print Pen Information
                history.add("C - Print Information about Robot");
                break;
            case "Q": // Quit
                history.add("Q - Quit");
                break;
            case "H": // History
                history.add("H - Show Command History");
                break;
            case "I": // Initialize
                history.add("I " + cmd_int + " - Initialize " + cmd_int + " by " + cmd_int + " floor");
                break;
            default:
                history.add(cmd + " - Unknown Command");
                break;
        }
    }

    //Prints the History
    public void displayHistory(){
        System.out.println(getHistory());
    }

    //Deletes the History
    public void deleteHistory(){
        history.clear();   
    }

    // Return Array of floor
    public int[][] getFloor() {
        return this.floor.clone();
    }

    // Return state of initialization
    public boolean getInitState() {
        return this.initialized;
    }

    // Print the floor with labeled indices
    public void printFloor() {
        System.out.println(getPrintedFloor());

        addEvent("P", -1);
    }

    // Return the floor with labeled indices
    public String getPrintedFloor() {
        String result = "";
        // Loop through flipped array to print with 0,0 at bottom right
        for (int i = floor.length - 1; i >= 0; i--) {
            for (int j = 0; j < floor[i].length; j++) {
                if (j == 0)
                    result += text_purple + " (" + i + ") " + text_reset;
                if (floor[i][j] == 1)
                    result += " * ";
                else
                    result += " - ";
            }
            result += "\n";
        }

        result += "     ";
        for (int i = 0; i < floor.length; i++)
            result += text_purple + "(" + i + ")" + text_reset;

        return result;
    }

    // Print all information related to the system
    public void printInfo() {
        System.out.println(getInfo());

        addEvent("C", -1);
    }

    // Returns all information related to the system
    public String getInfo() {
        String pen = "";

        if (getPen_down()) {
            pen = "Down";
        } else
            pen = "Up";

        String result = "";

        result += String.format("Position: %1$d, %2$d - Pen: %3$s - Facing: %4$s", getLocation()[1], getLocation()[0],
                pen, getFacing_dir());
        return result;
    }

    // Returns state of Pen
    public boolean getPen_down() {
        return this.pen_down;
    }

    // Sets state of Pen
    public void setPen_down(boolean pen_down) {
        this.pen_down = pen_down;

        if (pen_down)
            addEvent("D", -1);
        else
            addEvent("U", -1);
    }

    // Returns current direction of robot
    public Direction getFacing_dir() {
        return this.facing_dir;
    }

    // Sets direction of robot
    public void setFacing_dir(Direction facing_dir) {
        this.facing_dir = facing_dir;
    }

    // Turns robot left
    public void turnLeft() {
        switch (getFacing_dir()) {
            case NORTH:
                setFacing_dir(Direction.WEST);
                break;
            case EAST:
                setFacing_dir(Direction.NORTH);
                break;
            case SOUTH:
                setFacing_dir(Direction.EAST);
                break;
            case WEST:
                setFacing_dir(Direction.SOUTH);
                break;
        }

        addEvent("L", -1);
    }

    // Turns robot Right
    public void turnRight() {
        switch (getFacing_dir()) {
            case NORTH:
                setFacing_dir(Direction.EAST);
                break;
            case EAST:
                setFacing_dir(Direction.SOUTH);
                break;
            case SOUTH:
                setFacing_dir(Direction.WEST);
                break;
            case WEST:
                setFacing_dir(Direction.NORTH);
                break;
        }

        addEvent("R", -1);
    }

    // Returns the current coordinates of the robot
    public int[] getLocation() {
        return this.location.clone();
    }

    // Sets the coordinates of the robot
    public void setLocation(int[] location) {
        this.location = location;
    }

    // Moves the robot according to user input movement spaces
    public void moveTo(int movement) {
        int[] old_location = getLocation(); // row = 0, column = 1
        int[] new_location = getLocation(); // row = 0, column = 1

        // Changes how the robot moves depending on the current direction of the robot
        // Each case has similar code, but changes the row and column per the direction
        // First checks if the robot is already at the edge and cannot move
        // Next checks if robot can complete every movement space, else the robot does not move
        // Lastly, checks if the pen is down and updates the floor if true
        switch (getFacing_dir()) {
            case NORTH:
                if ((old_location[0] + movement) > floor[0].length - 1) {
                    System.out.println("Cannot move that far in this direction");
                } else {
                    new_location[0] += movement;

                    if (getPen_down()) {
                        for (int i = old_location[0]; i <= new_location[0]; i++)
                            floor[i][old_location[1]] = 1;
                    }
                }

                break;
            case EAST:
                if ((old_location[1] + movement) > floor[1].length - 1) {
                    System.out.println("Cannot move that far in this direction");
                } else {
                    new_location[1] += movement;

                    if (getPen_down()) {
                        for (int i = old_location[1]; i <= new_location[1]; i++)
                            floor[old_location[0]][i] = 1;
                    }
                }

                break;
            case SOUTH:
                if ((old_location[0] - movement) < 0) {
                    System.out.println("Cannot move that far in this direction");
                } else {
                    new_location[0] -= movement;

                    if (getPen_down()) {
                        for (int i = old_location[0]; i >= new_location[0]; i--)
                            floor[i][old_location[1]] = 1;
                    }
                }

                break;
            case WEST:
                if ((old_location[1] - movement) < 0) {
                    System.out.println("Cannot move that far in this direction");
                } else {
                    new_location[1] -= movement;

                    if (getPen_down()) {
                        for (int i = old_location[1]; i >= new_location[1]; i--)
                            floor[old_location[0]][i] = 1;
                    }
                }

                break;
        }

        setLocation(new_location);

        addEvent("M", movement);
    }

}
