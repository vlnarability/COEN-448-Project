import java.util.*;

public class Robot {
    
    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST;
    }

    boolean pen_down = false; //False = Up True = Down
    Direction facing_dir = Direction.NORTH;
    int[] location = {0,0};

    List<List<Integer>> floor;
    
    public void init(int size){
        this.floor = new ArrayList<List<Integer>>(size);
        this.location = new int[2];
        this.facing_dir = Direction.NORTH;
        this.pen_down = false;

    }

    public void printFloor(){
        String result = "";
        for(int i = 0; i < floor.size(); i++){
            for(int j = 0; j < floor.get(i).size(); j++){
                if (floor.get(i).get(j) == 1)
                    result += "*";
                else
                    result += " ";
            }
            result += "\n";
        }
        System.out.println(result);
    }

    public void printInfo(){
        String pen = "";

        if (getPen_down()){
            pen = "Down";
        } else
            pen = "Up";
        
        String result = "";

        result += String.format("Position: %1$d, %2$d - Pen: %3$s - Facing: %4$s", getLocation()[0], getLocation()[1], pen, getFacing_dir());
        System.out.println(result);
    }

    // TODO
    public void changeFloor(int[] old_location, int[] new_location){
        
    }

    public boolean isPen_down() {
        return this.pen_down;
    }

    public boolean getPen_down() {
        return this.pen_down;
    }

    public void setPen_down(boolean pen_down) {
        this.pen_down = pen_down;
    }

    public Direction getFacing_dir() {
        return this.facing_dir;
    }

    public void setFacing_dir(Direction facing_dir) {
        this.facing_dir = facing_dir;
    }
    

    public void turnLeft(){
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
    }

    public void turnRight(){
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
    }

    public int[] getLocation() {
        return this.location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public void moveTo(int movement){ //Need to check if at edge
        int[] new_location = getLocation(); //row = 0, column = 1

        switch (getFacing_dir()) { //Need to change floor array
            case NORTH:
                new_location[0]+=movement;
                break;
            case EAST:
                new_location[1]+=movement;
                break;
            case SOUTH:
                new_location[0]-=movement;
                break;
            case WEST:
                new_location[1]-=movement;
                break;
        }

        if (getPen_down())
            changeFloor(getLocation(), new_location);
        setLocation(new_location);
    }

}
