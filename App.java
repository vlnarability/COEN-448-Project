import java.util.*;

public class App {

    static Robot robot = new Robot();

    public static void main(String[] args) throws Exception {
        boolean exit = false;
        Scanner input = new Scanner(System.in);
        while(!exit){
            
            System.out.println("Enter Command");

            String next_cmd = input.nextLine().toUpperCase();
            String cmd = null;
            int cmd_int = -1;

            if ((next_cmd.length() > 1) && next_cmd.matches("[A-Z]\\s+-?\\d+")){
                String[] split_cmd = next_cmd.split("\\s+");
                cmd = split_cmd[0];
                if (split_cmd[1].charAt(0) != '-'){
                    try {
                        cmd_int = Integer.parseInt(split_cmd[1]);
                    }
                    catch (NumberFormatException e){
                        System.out.println("Invalid integer");
                    }
                }
            } else if (next_cmd.length() == 1){
                cmd = next_cmd;
            }
            
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
                    robot.init(cmd_int);
                    break;              
                default:
                    System.out.println("Command not recognized");
                    break;
            }

        }

        input.close();
        System.out.println("Goodbye");
    }
}
