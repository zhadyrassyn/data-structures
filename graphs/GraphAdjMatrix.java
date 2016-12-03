import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.ArrayList;

public class GraphAdjMatrix extends Graph {
    private final static int defaultNumOfVertices = 5; 
    private int[][] adjMatrix;

    public GraphAdjMatrix() {
        super(defaultNumOfVertices);
        adjMatrix = new int[defaultNumOfVertices][defaultNumOfVertices];
    }

    public GraphAdjMatrix(int v) {
        super(v);
        adjMatrix = new int[v][v];
    }

    public void implementAddVertex() {
        int v = getNumOfVertices();
        if(v >= adjMatrix.length) {
            int[][] newAdjMatrix = new int[v*2][v*2];
            for(int i = 0; i < adjMatrix.length; i++) {
                for(int j = 0; j < adjMatrix.length; j++) {
                    newAdjMatrix[i][j] = adjMatrix[i][j];
                }
            }
            adjMatrix = newAdjMatrix;
        }
    }

    public void implementAddEdge(int v, int w) {
        adjMatrix[v][w] = 1;    
    }

    public List<Integer> getOutNeighbours(int v) throws IndexOutOfBoundsException {
        int n = getNumOfVertices();
        if(v >= n) {
            throw new IndexOutOfBoundsException();
        }
        LinkedList<Integer> ll = new LinkedList<Integer>();
        for(int j = 0; j < n; j++) {
            if(adjMatrix[v][j] != 0) {
                ll.add(j);
            }
        }
        return ll;
    }

    public List<Integer> getInNeighbours(int v) throws IndexOutOfBoundsException {
        int n = getNumOfVertices();
        if(v >= n) {
            throw new IndexOutOfBoundsException();
        }
        LinkedList<Integer> ll = new LinkedList<Integer>();
        for(int i = 0; i < n; i++) {
            if(adjMatrix[i][v] != 0) {
                ll.add(i);
            }
        }
        return ll;
    }

    public List<Integer> getDistance2(int v) {
        List<Integer> res = new ArrayList<>();
		int N = getNumOfVertices();
		for(int i = 0; i < N; i++) {
			int counter = 0;
			for(int j = 0; j < N; j++) {
				counter += (adjMatrix[v][j] * adjMatrix[j][i]);
			}
			if(counter != 0) {
				while(counter-- > 0) {
					res.add(i);
				}
			}
			
		}
		return res;
    } 

}

