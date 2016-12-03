import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class GraphAdjList extends Graph {
    private static final int DEFAULT_NUM_OF_VERTICES = 5;
    private Map<Integer, ArrayList<Integer>> adjList;

    public GraphAdjList() {
        super();
        adjList = new HashMap<Integer, ArrayList<Integer>>();
    }

    public GraphAdjList(int numOfVertices) {
        super(numOfVertices);
        adjList = new HashMap<Integer, ArrayList<Integer>>();
    }

    public void implementAddEdge(int v, int w) {
        if(adjList.containsKey(v) && adjList.containsKey(w)) {
            adjList.get(v).add(w);
        } else {
            System.out.println("Some vertex does not exist!");
        }
    }

    public void implementAddVertex() {
        adjList.put(getNumOfVertices(), new ArrayList<Integer>());
    }

    public List<Integer> getInNeighbours(int v) {
        List<Integer> list = new ArrayList<Integer>();
        for(int vertex : adjList.keySet()) {
            if(getOutNeighbours(vertex).contains(v)) {
                list.add(vertex);
            }
        }
        return list;
    }

    public List<Integer> getOutNeighbours(int v) {
        if(adjList.containsKey(v)) {
            return adjList.get(v);
        } else {
            System.out.println("Vertex does not exist!");
            return new ArrayList<Integer>();
        }
        
    }

    public List<Integer> getDistance2(int v) {
        List<Integer> distance2List = new ArrayList<Integer>();
        if(adjList.containsKey(v)) {
            for(int vertex : getOutNeighbours(v)) {
                distance2List.addAll(getOutNeighbours(vertex));
            }
        } else {
            System.out.println("This vertex does not exist!");
        }
        return distance2List;
    }
}
