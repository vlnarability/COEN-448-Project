package com.coen448;

import java.util.Scanner;

public class App {

    static Robot robot = new Robot();

    public static void main(String[] args) throws Exception {
        boolean exit = false;
        Scanner input = new Scanner(System.in);

        //Print Command List
        System.out.println("Available Commands:\n [U|u] - Pen Up \n [D|d] - Pen Down \n [L|l] - Turn Left \n [R|r] - Turn Right \n [M|m s] - Move Forward \'s\' spaces (Non-Negative) \n [P|p] - Print Floor \n [C|c] - Print Robot Information \n [Q|q] - Stop Program \n [I|i n] - Initialize system to size \'n by n\' (Non-Negative) ");


        while(!exit){
            
            System.out.println("Enter Command");

            //Get user input
            String next_cmd = input.nextLine().toUpperCase();
            String cmd = null;
            int cmd_int = -1;

            //Ensure input follows default input requirement
            if ((next_cmd.length() > 1) && next_cmd.matches("[A-Z]\\s+-?\\d+")){
                //Split input into command and integer
                String[] split_cmd = next_cmd.split("\\s+");
                cmd = split_cmd[0];
                //Ensure integer is valid
                if (split_cmd[1].charAt(0) != '-'){
                    try {
                        cmd_int = Integer.parseInt(split_cmd[1]);
                    }
                    catch (NumberFormatException e){
                        System.out.println("Invalid integer! Must be Non-Negative");
                        continue;
                    }
                }
            } else if (next_cmd.length() == 1){
                cmd = next_cmd;
            } else {
                System.out.println("Command failed");
                continue;
            }

            //Check if program was initialized, else do not accept other commands
            if ((!robot.initialized) && ((!cmd.equals("I")) && (!cmd.equals("Q")))){
                System.out.println("Please initialize system first!");
                continue;
            }

            //Switch statement to control the program with user commands
            switch(cmd){
                case "U": //Pen Up
                    robot.setPen_down(false);
                    break;
                case "D": //Pen Down
                    robot.setPen_down(true);
                    break;
                case "R": //Turn Right
                    robot.turnRight();
                    break;
                case "L": //Turn Left
                    robot.turnLeft();
                    break;
                case "M": //Move Forward
                    robot.moveTo(cmd_int);
                    break;                  
                case "P": //Print Floor
                    robot.printFloor();
                    break;
                case "C": //Print Pen Information
                    robot.printInfo();
                    break;
                case "Q": //Quit
                    exit = true;
                    break;
                case "I": //Initialize
                    if (cmd_int == -1){
                        System.out.println("In order to initialize, a non negative integer is required"); 
                        break;
                    } else{
                        robot.init(cmd_int);
                        break;
                    }     
                default:
                    System.out.println("Command not recognized");
                    break;
            }

        }

        input.close();
        System.out.println("Goodbye");
    }
}