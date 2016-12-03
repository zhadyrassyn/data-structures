import java.io.BufferedReader;
import java.io.FileReader;

public class MazeLoader {
    public static void loadMaze(Maze maze, String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = null;
            int width = 0;
            int height = 0;

            //Read dimension of maze
            if((line = br.readLine()) != null) {
                String[] dimensions = line.split("\\s");
                width = Integer.parseInt(dimensions[0]);
                height = Integer.parseInt(dimensions[1]);
                //maze = new Maze(width, height);
                maze.initialize(width, height);
                System.out.println(maze.getWidth() + " " + maze.getHeight());
            }

            //load cells
            int row = 0;
            int col = 0;
            while((line = br.readLine()) != null && !line.trim().equals("")) {
                for(char c : line.toCharArray()) {
                    if(c != '*') {
                        maze.addMazeNode(new Coordinate(row, col));
                    }
                    ++col;
                }
                while(col < width) {
                    maze.addMazeNode(new Coordinate(row, col));
                    ++col;
                }
                col = 0;
                ++row;
            }
   
        } catch(Exception e) {
            System.out.println("Error while loading!");        
        }
        maze.linkEdges();
        maze.printMaze();
    }
}
