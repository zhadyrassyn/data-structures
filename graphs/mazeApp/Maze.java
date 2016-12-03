import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Queue;
import java.util.ListIterator;

public class Maze {
    public MazeNode[][] cells;
    private int width;
    private int height;

    private static final int DEFAULT_WIDTH = 10;
    private static final int DEFAULT_HEIGHT = 10;

    public Maze() {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.cells = new MazeNode[width][height];
    }

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new MazeNode[width][height];
    }

    public void initialize(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new MazeNode[width][height];
    }

    public void addMazeNode(Coordinate coordinate) {
        this.cells[coordinate.row][coordinate.col] = new MazeNode(coordinate);
    }
    
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void printMaze() {
        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                MazeNode currNode = this.cells[row][col];
                System.out.print(currNode == null ? "*" : currNode.getDisplayChar());
            }
            System.out.println();
        }
    }

    public void setPath(List<MazeNode> path) {
        int index = 0;
        for(MazeNode node : path) {
            if(index == 0) {
                node.setDisplayChar(MazeNode.START);
            } else if(index == path.size() - 1) {
                node.setDisplayChar(MazeNode.GOAL);
            } else {
                node.setDisplayChar(MazeNode.PATH);
            }
            ++index;
        }
    }

    public void clearPath() {
        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                if(this.cells[row][col] != null) {
                    this.cells[row][col].setDisplayChar(MazeNode.EMPTY);
                }
            } 
        }
    }

    public void linkEdges() {
        for(int row = 0; row < this.height; row++) {
            for(int col = 0; col < this.width; col++) {
                if(this.cells[row][col] != null) {
                    //top
                    if(row - 1 >= 0 && this.cells[row-1][col] != null) {
                        this.cells[row][col].addNeighbour(this.cells[row-1][col]);
                    }
                    //left
                    if(col - 1 >= 0 && this.cells[row][col-1] != null) {
                        this.cells[row][col].addNeighbour(this.cells[row][col-1]);
                    }
                    //bottom
                    if(row + 1 < height && this.cells[row+1][col] != null) {
                        this.cells[row][col].addNeighbour(this.cells[row+1][col]);
                    }
                    //right
                    if(col + 1 < width && this.cells[row][col+1] != null) {
                        this.cells[row][col].addNeighbour(this.cells[row][col+1]);
                    }
                }
            }
        }     
    }

    public LinkedList<MazeNode> dfs(Coordinate startPos, Coordinate goalPos) {
        MazeNode start = this.cells[startPos.row][startPos.col];
        MazeNode goal = this.cells[goalPos.row][goalPos.col];
        
        if(start == null || goal == null) {
            System.out.println("start position or goal position is null!");
            return new LinkedList<MazeNode>();
        }

        HashMap<MazeNode, MazeNode> parentMap = new HashMap<MazeNode, MazeNode>();
        boolean found = dfsSearch(start, goal, parentMap);
        
        if(!found) {
            System.out.println("No such path exists!");
            return new LinkedList<MazeNode>();
        } 

        return constructPath(start, goal, parentMap);

    }

    public LinkedList<MazeNode> constructPath(MazeNode start, MazeNode goal, HashMap<MazeNode, MazeNode> parentMap) {
        LinkedList<MazeNode> path = new LinkedList<MazeNode>();
        MazeNode curr = goal;
        while(curr != start) {
            path.addFirst(curr);
            curr = parentMap.get(curr);
        }
        path.addFirst(start);
        return path;
    }

    public boolean dfsSearch(MazeNode start, MazeNode goal, HashMap<MazeNode, MazeNode> parentMap) {
        HashSet<MazeNode> visited = new HashSet<MazeNode>();
        Stack<MazeNode> toExplore = new Stack<MazeNode>();
        boolean found = false;

        toExplore.push(start);
        
        while(!toExplore.empty()) {
            MazeNode curr = toExplore.pop();
            if(curr == goal) {
                found = true;
                break;
            } 

            List<MazeNode> neighbours = curr.getNeighbours();
            for(int i = neighbours.size() - 1; i >= 0; i--) {
                MazeNode next = neighbours.get(i);
                if(!visited.contains(next)) {
                    toExplore.push(next);
                    visited.add(next);
                    parentMap.put(next, curr);
                }
            }
        }
    
		return found;
    }

    public List<MazeNode> bfs(Coordinate startPos, Coordinate goalPos) {
        MazeNode start = this.cells[startPos.row][startPos.col];
        MazeNode goal = this.cells[goalPos.row][goalPos.col];

        if(start == null || goal == null) {
            System.out.println("Start position or goal position does not exist");
            return new LinkedList<MazeNode>();
        }

        HashMap<MazeNode, MazeNode> parentMap = new HashMap<MazeNode, MazeNode>();
        boolean found = bfsSearch(start, goal, parentMap);

        if(!found) {
            System.out.println("Not path exists!");
            return new LinkedList<MazeNode>();
        }

        return constructPath(start, goal, parentMap);

    }

    public boolean bfsSearch(MazeNode start, MazeNode goal, HashMap<MazeNode, MazeNode> parentMap) {
        HashSet<MazeNode> visited = new HashSet<MazeNode>();
        Queue<MazeNode> queue = new LinkedList<MazeNode>();
        boolean found = false;
        
        visited.add(start);
        queue.add(start);

        while(!queue.isEmpty()) {
            MazeNode curr = queue.remove();    
            if(curr == goal) {
                found = true;
                break;
            }

            for(MazeNode node: curr.getNeighbours()) {
                if(!visited.contains(node)) {
                    visited.add(node);
                    queue.add(node);
                    parentMap.put(node, curr);
                }
            }
        }

        return found;
    }

}
