
package com.coen448;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegressionTest {
    Robot robot;

    @BeforeEach
    public void setup() {
        robot = new Robot();
    }

    @Test
    void testCase1() {
        robot.init(10);
        robot.moveTo(4);
        robot.setPen_down(true);
        robot.turnRight();
        robot.moveTo(8);
        robot.turnLeft();
        robot.moveTo(4);
        robot.turnLeft();
        robot.moveTo(6);
        robot.turnLeft();
        robot.moveTo(7);
        robot.turnRight();
        robot.moveTo(1);
        robot.printFloor();
        robot.printInfo();

        List<String> testHistory = new ArrayList<>();
        testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");
        testHistory.add("M " + 4 + " - Move " + 4 + " spaces");
        testHistory.add("D - Pen Down");
        testHistory.add("R - Turn Right");
        testHistory.add("M " + 8 + " - Move " + 8 + " spaces");
        testHistory.add("L - Turn Left");
        testHistory.add("M " + 4 + " - Move " + 4 + " spaces");
        testHistory.add("L - Turn Left");
        testHistory.add("M " + 6 + " - Move " + 6 + " spaces");
        testHistory.add("L - Turn Left");
        testHistory.add("M " + 7 + " - Move " + 7 + " spaces");
        testHistory.add("R - Turn Right");
        testHistory.add("M " + 1 + " - Move " + 1 + " spaces");
        testHistory.add("P - Print Floor");
        testHistory.add("C - Print Information about Robot");
        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
        }

//beyond EST and test turn right, and movement, pen down
    @Test
    void testCase2() {

        robot.init(10);
        robot.moveTo(2);
        robot.setPen_down(true);
        robot.turnRight();
        robot.moveTo(19);

        robot.printFloor();
        robot.printInfo();

        List<String> testHistory = new ArrayList<>();
        testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");
        testHistory.add("M " + 2 + " - Move " + 2 + " spaces");
        testHistory.add("D - Pen Down");
        testHistory.add("R - Turn Right");
        testHistory.add("M " + 19 + " - Move " + 19 + " spaces");

        testHistory.add("P - Print Floor");
        testHistory.add("C - Print Information about Robot");
        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
    }

//beyond west and test left ,movement, pen down
    @Test
    void testCase3() {
        robot.init(10);
        robot.moveTo(3);
        robot.setPen_down(true);
        robot.turnLeft();
        robot.moveTo(19);

        robot.printFloor();
        robot.printInfo();

        List<String> testHistory = new ArrayList<>();
        testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");
        testHistory.add("M " + 3 + " - Move " + 3 + " spaces");
        testHistory.add("D - Pen Down");
        testHistory.add("L - Turn Left");
        testHistory.add("M " + 19 + " - Move " + 19 + " spaces");
        testHistory.add("P - Print Floor");
        testHistory.add("C - Print Information about Robot");
        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
    }

    //beyond north, test movement , pen down
    @Test
    void testCase4() {
        robot.init(10);
        robot.moveTo(4);
        robot.setPen_down(true);
        robot.moveTo(12);

        robot.printFloor();
        robot.printInfo();

        List<String> testHistory = new ArrayList<>();
        testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");
        testHistory.add("M " + 4 + " - Move " + 4 + " spaces");
        testHistory.add("D - Pen Down");

        testHistory.add("M " + 12 + " - Move " + 12 + " spaces");
        testHistory.add("P - Print Floor");
        testHistory.add("C - Print Information about Robot");
        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
    }
    //beyond south, test movement and pen down
    @Test
    void testCase5() {
        robot.init(10);
        robot.moveTo(5);
        robot.setPen_down(true);
        robot.turnLeft();
        robot.turnLeft();
        robot.moveTo(12);
        robot.printFloor();
        robot.printInfo();

        List<String> testHistory = new ArrayList<>();
        testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");
        testHistory.add("M " + 5 + " - Move " + 5 + " spaces");
        testHistory.add("D - Pen Down");
        testHistory.add("L - Turn Left");
        testHistory.add("L - Turn Left");
        testHistory.add("M " + 12 + " - Move " + 12 + " spaces");
        testHistory.add("P - Print Floor");
        testHistory.add("C - Print Information about Robot");
        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
    }

     // test pen up and down
    @Test
    void testCase6() {
        robot.setPen_down(true);
        robot.setPen_down(false);


        List<String> testHistory = new ArrayList<>();
        //testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");
        testHistory.add("D - Pen Down");

        testHistory.add("U - Pen Up");

        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
    }

    //test turn right
    @Test
    void testCase7() {
        //robot.init(10);

        robot.turnLeft();


        List<String> testHistory = new ArrayList<>();
       // testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");
//        testHistory.add("P - Print Floor");
//        testHistory.add("C - Print Information about Robot");
        testHistory.add("L - Turn Left");
//        testHistory.add("P - Print Floor");
//        testHistory.add("C - Print Information about Robot");
        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
    }


    //test turn right
    @Test
    void testCase8() {
      //  robot.init(10);

        robot.turnRight();
//        robot.printFloor();
//        robot.printInfo();

        List<String> testHistory = new ArrayList<>();
      //testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");
       // testHistory.add("P - Print Floor");
       // testHistory.add("C - Print Information about Robot");
        testHistory.add("R - Turn Right");
//        testHistory.add("P - Print Floor");
//        testHistory.add("C - Print Information about Robot");
        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
    }

     //test print information without initial
    @Test
    void testCase9() {
        robot.printInfo();

        List<String> testHistory = new ArrayList<>();

        testHistory.add("C - Print Information about Robot");
        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
    }

    //when print floor,need initial
    @Test
    void testCase10() {
        robot.init(10);

        robot.printFloor();
        //robot.printInfo();

        List<String> testHistory = new ArrayList<>();
        testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");
        testHistory.add("P - Print Floor");
        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
    }


    //test initial without delete
    @Test
    void testCase11() {
        robot.init(10);


        List<String> testHistory = new ArrayList<>();
        testHistory.add("I " + 10 + " - Initialize " + 10 + " by " + 10 + " floor");

        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());
        robot.deleteHistory();
    }

    //test delete function
    @Test
    void testCase12() {
        robot.deleteHistory();
        List<String> testHistory = new ArrayList<>();
        testHistory.add("H - Show Command History");
        String historyString = "Command History:\n" + String.join("\n", testHistory);

        assertEquals(historyString, robot.getHistory());

    }

}
