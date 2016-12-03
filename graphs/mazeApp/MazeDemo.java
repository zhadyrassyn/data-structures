import java.util.List;

public class MazeDemo {
    public static void main(String[] args) {
        Maze maze = new Maze();
        MazeLoader.loadMaze(maze, "maze.txt");
        System.out.print("\n");
        /*
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.println(maze.cells[i][j]);
            }
        }
        */
        Coordinate start = new Coordinate(3, 3);
        Coordinate goal = new Coordinate(2, 0);
        System.out.println("DFS search");
        List<MazeNode> path = maze.dfs(start, goal);
        maze.setPath(path);
        maze.printMaze();

        maze.clearPath();

        System.out.println("\nBFS search");
        maze.setPath(maze.bfs(start, goal));
        maze.printMaze();

    }
}
