package com.coen448;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Arrays;

import com.coen448.Robot.Direction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RobotTest {

     Robot robot= new Robot();
    
     
     @BeforeAll
     public static void setupAll() {
         System.out.println("Should Print Before All Tests");
     }

     @Test
     @DisplayName("Should create initial information: i 10")
    void shouldCreateInit(){
         robot.init(10);    
         Assertions.assertFalse(robot.getPen_down());   // check pen inital state: false 
         assertEquals(Direction.NORTH,robot.facing_dir); 
         assertEquals(Arrays.toString(new int[]{0,0}), Arrays.toString(robot.location));
         assertArrayEquals(new int[10][10],robot.floor);
         assertTrue(robot.initialized);
     }

     @Test
     @DisplayName("Should check pen status")
     public void shouldcheckPen(){
         robot.init(10);
         assertFalse(robot.getPen_down()); //pen is up
         
     }

     @Test
     @DisplayName("should turn left ,then change direction from inital")
     void checkturnLeft(){
         assertEquals(Direction.NORTH, robot.getFacing_dir()); //inital direction to north
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
     @DisplayName("should turn right ,then change direction from inital")
     void checkturnRight(){
         assertEquals(Direction.NORTH,robot.getFacing_dir()); 
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
     void checkMove(){
         robot.init(10);
         robot.moveTo(5);
        
         if(Direction.NORTH==robot.getFacing_dir()){
         assertEquals(Arrays.toString(new int[]{5,0}),Arrays.toString(robot.getLocation())); // int[](y,x)
         };      

         robot.turnRight();
         robot.moveTo(2);
         if(Direction.EAST==robot.getFacing_dir()){
            assertEquals(Arrays.toString(new int[]{5,2}),Arrays.toString(robot.getLocation()));

            };  

         robot.moveTo(15);
         if(Direction.EAST==robot.getFacing_dir()){
            assertEquals(Arrays.toString(new int[]{5,9}),Arrays.toString(robot.getLocation()));
            };      
     }

     @Test
     @DisplayName("should check pen down, so the status is true")
     void checkPendown(){
         robot.init(10);
         assertTrue(!robot.getPen_down());

     }
    

}
