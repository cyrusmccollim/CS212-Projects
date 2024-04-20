import java.util.*;

public class Room {
    private final int x;
    private final int y;
    private final List<Room> paths; // Holds all adjacent rooms that have not been previously visited.

    // Constructor
    public Room(int x, int y) {
        this.x = x;
        this.y = y;
        paths = new ArrayList<>();
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public List<Room> getPaths() {
        return paths;
    }
}
