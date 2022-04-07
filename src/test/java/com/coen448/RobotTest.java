
package com.coen448;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import com.coen448.Robot.Direction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RobotTest {

    Robot robot;

    @BeforeEach
    public void setup() {
        robot = new Robot();
    }
 
    @Test
    @DisplayName("Verify Functionality of History")
    void checkHistory() {
        robot.init(10);
        robot.moveTo(4);
        robot.setPen_down(true);

        List<String> testHistory = new ArrayList<>();
        testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");
        testHistory.add("M " + 4 + " - Move " + 4 + " spaces");
        testHistory.add("D - Pen Down");
        testHistory.add("H - Show Command History");

        String historyString = "Command History:\n"+String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());

    }

    @Test
    @DisplayName("Should create initial information: i 10")
    void shouldCreateInit() {
        robot.init(10);
        Assertions.assertFalse(robot.getPen_down()); // check pen initial state: false
        assertEquals(Direction.NORTH, robot.facing_dir);
        assertEquals(Arrays.toString(new int[] { 0, 0 }), Arrays.toString(robot.location));
        assertArrayEquals(new int[10][10], robot.floor);
        assertTrue(robot.initialized);
    }

    @Test
    @DisplayName("Should check pen status")
    void shouldCheckPen() {
        robot.init(10);
        assertFalse(robot.getPen_down()); // pen is up

    }

    @Test
    @DisplayName("should turn left ,then change direction from initial")
    void checkTurnLeft() {
        assertEquals(Direction.NORTH, robot.getFacing_dir()); // initial direction to north
        robot.turnLeft();
        assertEquals(Direction.WEST, robot.getFacing_dir());
        robot.turnLeft();
        assertEquals(Direction.SOUTH, robot.getFacing_dir());
        robot.turnLeft();
        assertEquals(Direction.EAST, robot.getFacing_dir());
        robot.turnLeft();
        robot.turnLeft();
        assertEquals(Direction.WEST, robot.getFacing_dir());
        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();
        assertEquals(Direction.NORTH, robot.getFacing_dir());

    }

    @Test
    @DisplayName("should turn right ,then change direction from initial")
    void checkTurnRight() {
        assertEquals(Direction.NORTH, robot.getFacing_dir());
        robot.turnRight();
        assertEquals(Direction.EAST, robot.getFacing_dir());
        robot.turnRight();
        assertEquals(Direction.SOUTH, robot.getFacing_dir());
        robot.turnRight();
        assertEquals(Direction.WEST, robot.getFacing_dir());
        robot.turnRight();
        robot.turnRight();
        assertEquals(Direction.EAST, robot.getFacing_dir());
        robot.turnRight();
        robot.turnRight();
        robot.turnRight();
        assertEquals(Direction.NORTH, robot.getFacing_dir());
    }

    @Test
    @DisplayName("should check movement, 2d array work with row first, so it will shows as y,x")
    void checkMovePenUp() {
        robot.init(10);
        robot.moveTo(5);

        if (Direction.NORTH == robot.getFacing_dir()) {
            assertEquals(Arrays.toString(new int[] { 5, 0 }), Arrays.toString(robot.getLocation())); // int[](y,x)
        }
        

        robot.turnRight();
        robot.moveTo(2);
        if (Direction.EAST == robot.getFacing_dir()) {
            assertEquals(Arrays.toString(new int[] { 5, 2 }), Arrays.toString(robot.getLocation()));

        }
        

        robot.moveTo(15);
        if (Direction.EAST == robot.getFacing_dir()) {
            assertEquals(Arrays.toString(new int[] { 5, 2 }), Arrays.toString(robot.getLocation()));
        }
        
        robot.turnRight();
        robot.moveTo(2);
        if (Direction.SOUTH == robot.getFacing_dir()) {
            assertEquals(Arrays.toString(new int[] { 3, 2 }), Arrays.toString(robot.getLocation()));

        }
    }

    @Test
    @DisplayName("should check movement, 2d array work with row first, so it will shows as y,x")
    void checkMovePenDown() {
        robot.init(10);

        int[] old_location = robot.getLocation(); // row = 0, column = 1
        int[] new_location = robot.getLocation(); // row = 0, column = 1
        int[][] floor = robot.getFloor();

        robot.setPen_down(true);
        robot.moveTo(5);

        if (Direction.NORTH == robot.getFacing_dir()) {
            assertEquals(Arrays.toString(new int[] { 5, 0 }), Arrays.toString(robot.getLocation())); // int[](y,x)
            for (int i = old_location[0]; i >= new_location[0]; i--)
                            assertEquals(1, floor[i][old_location[1]]);
        }
        

        robot.turnRight();
        robot.moveTo(2);
        if (Direction.EAST == robot.getFacing_dir()) {
            assertEquals(Arrays.toString(new int[] { 5, 2 }), Arrays.toString(robot.getLocation()));
            for (int i = old_location[0]; i >= new_location[0]; i--)
            assertEquals(1, floor[i][old_location[1]]);
        }
        

        robot.moveTo(15);
        if (Direction.EAST == robot.getFacing_dir()) {
            assertEquals(Arrays.toString(new int[] { 5, 2 }), Arrays.toString(robot.getLocation()));
            for (int i = old_location[0]; i >= new_location[0]; i--)
                            assertEquals(1, floor[i][old_location[1]]);
        }
        
        robot.turnRight();
        robot.moveTo(2);
        if (Direction.SOUTH == robot.getFacing_dir()) {
            assertEquals(Arrays.toString(new int[] { 3, 2 }), Arrays.toString(robot.getLocation()));
            for (int i = old_location[0]; i >= new_location[0]; i--)
                            assertEquals(1, floor[i][old_location[1]]);

        }
    }

    @Test
    @DisplayName("should check pen down, so the status is true")
    void checkPenDown() {
        robot.init(10);
        assertTrue(!robot.getPen_down());

    }

    @Test
    @DisplayName("Test if print information command works as intended")
    void checkPrintInfo() {

        robot.init(10);
        assertEquals("Position: 0, 0 - Pen: Up - Facing: NORTH", robot.getInfo());

        robot.moveTo(1);
        assertEquals("Position: 0, 1 - Pen: Up - Facing: NORTH", robot.getInfo());

        robot.turnRight();
        assertEquals("Position: 0, 1 - Pen: Up - Facing: EAST", robot.getInfo());

        robot.moveTo(2);
        robot.setPen_down(true);
        assertEquals("Position: 2, 1 - Pen: Down - Facing: EAST", robot.getInfo());

        robot.turnLeft();
        robot.moveTo(20);
        assertEquals("Position: 2, 1 - Pen: Down - Facing: NORTH", robot.getInfo()); // check board
    }

    @Test
    @DisplayName("Test if print floor command works as intended")
    void checkPrintFloor() {
        robot.init(10);

        String clean_result = "";
        int[][] clean_floor = new int[10][10];
        // Loop through flipped array to print with 0,0 at bottom right
        for (int i = clean_floor.length - 1; i >= 0; i--) {
            for (int j = 0; j < clean_floor[i].length; j++) {
                if (j == 0)
                    clean_result += Robot.text_purple + " (" + i + ") " + Robot.text_reset;
                if (clean_floor[i][j] == 1)
                    clean_result += " * ";
                else
                    clean_result += " - ";
            }
            clean_result += "\n";
        }

        clean_result += "     ";
        for (int i = 0; i < clean_floor.length; i++)
            clean_result += Robot.text_purple + "(" + i + ")" + Robot.text_reset;

        assertEquals(clean_result, robot.getPrintedFloor());
        robot.setPen_down(true);
        robot.moveTo(1);

        clean_result = "";
        clean_floor[0][0] = 1;
        clean_floor[1][0] = 1;
        // Loop through flipped array to print with 0,0 at bottom right
        for (int i = clean_floor.length - 1; i >= 0; i--) {
            for (int j = 0; j < clean_floor[i].length; j++) {
                if (j == 0)
                    clean_result += Robot.text_purple + " (" + i + ") " + Robot.text_reset;
                if (clean_floor[i][j] == 1)
                    clean_result += " * ";
                else
                    clean_result += " - ";
            }
            clean_result += "\n";
        }

        clean_result += "     ";
        for (int i = 0; i < clean_floor.length; i++)
            clean_result += Robot.text_purple + "(" + i + ")" + Robot.text_reset;

        assertEquals(clean_result, robot.getPrintedFloor());
    }

    /*
     * @Test
     * 
     * @DisplayName("check path coverage1")
     * void checkPathCoverage1(){
     * robot.init(-1);
     * 
     * }
     */
    @Test
    @DisplayName("check path coverage1-2-3-4-5")
    void checkPathCoverage2() {
        robot.init(10);
        assertEquals(Direction.NORTH, robot.getFacing_dir());
        robot.turnRight();
        robot.moveTo(4);
        if (Direction.EAST == robot.getFacing_dir()) {
            assertEquals(Arrays.toString(new int[] { 0, 4 }), Arrays.toString(robot.getLocation()));

        }
        ;

    }

    @Test
    @DisplayName("check path coverage1-2-3-4")
    void checkPathCoverage3() {
        robot.init(10);
        assertEquals(Direction.NORTH, robot.getFacing_dir());
        robot.turnRight();
        robot.moveTo(5);
        if (Direction.EAST == robot.getFacing_dir()) {
            assertEquals(Arrays.toString(new int[] { 0, 5 }), Arrays.toString(robot.getLocation()));

        }
        ;

    }

    @AfterEach
    void tearDown(){
        robot.deleteHistory();
        robot = null;
    }
}