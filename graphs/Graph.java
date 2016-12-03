import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Graph {
    private int numOfVertices;
    private int numOfEdges;

    public Graph() {
        numOfVertices = 0;
        numOfEdges = 0;
    }

    public Graph(int v) {
        numOfVertices = v;
        numOfEdges = 0;
    }

    public int getNumOfVertices() {
        return numOfVertices;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public int addVertex() {
        implementAddVertex();
        ++numOfVertices;
        return (numOfVertices - 1);
    } 

    public abstract void implementAddVertex();

    public void addEdge(int w, int v) throws IndexOutOfBoundsException {
        if(w < numOfVertices && v < numOfVertices) {
            implementAddEdge(w, v);
            ++numOfEdges;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public abstract void implementAddEdge(int w, int v);

    public abstract List<Integer> getOutNeighbours(int v);

    public abstract List<Integer> getInNeighbours(int v);

    public abstract List<Integer> getDistance2(int v);

    public List<Integer> degreeSequence() {
        List<Integer> degreeSequence = new ArrayList<>();
        for (int i = 0; i < getNumOfVertices(); i++) {
            int counter = getOutNeighbours(i).size() + getInNeighbours(i).size();
            degreeSequence.add(counter);
        }
        Collections.sort(degreeSequence, Collections.reverseOrder());
        return degreeSequence;
    }

    @Override
    public String toString() {
        String str = "Graph has " + numOfVertices + " vertices, " + numOfEdges + " edges, ";
        return str;
    }
}

