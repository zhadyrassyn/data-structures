import java.util.List;
import java.util.ArrayList;

public class MazeNode {
    private Coordinate coordinate;
    private char displayChar;
    private List<MazeNode> neighbours;

    public static final char EMPTY = '-';
    public static final char START = 'S';
    public static final char GOAL = 'G';
    public static final char PATH = 'o';

    public MazeNode(Coordinate coordinate) {
        this.coordinate = coordinate;        
        this.neighbours = new ArrayList<MazeNode>();
        this.displayChar = EMPTY;
    }

    public char getDisplayChar() {
        return this.displayChar;
    }

    public void setDisplayChar(char c) {
        this.displayChar = c;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void addNeighbour(MazeNode neighbour) {
        this.neighbours.add(neighbour);
    }

    public List<MazeNode> getNeighbours() {
        return this.neighbours;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Row: " + this.coordinate.row);
        sb.append(", Col: " + this.coordinate.col);
        sb.append(", Neighbours: [");
        for(MazeNode node : this.neighbours) {
            sb.append("(" + node.getCoordinate().row + ", " + node.getCoordinate().col+"), ");
        }
        sb.append("]");
        return sb.toString();

    }
    
}

