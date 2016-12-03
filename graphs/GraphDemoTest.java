public class GraphDemoTest {
    public static void main(String[] args) {
        GraphAdjMatrix adjMatrix = new GraphAdjMatrix(6);
        adjMatrix.addEdge(0, 1);
        adjMatrix.addEdge(1, 2);
        adjMatrix.addEdge(2, 4);
        adjMatrix.addEdge(4, 1);
        adjMatrix.addEdge(5, 1);
        adjMatrix.addEdge(4, 3);
        adjMatrix.addEdge(5, 3);
        adjMatrix.addEdge(5, 0);
        adjMatrix.addEdge(3, 3);
        adjMatrix.addEdge(3, 2);
        for(int i = 0; i < adjMatrix.getNumOfVertices(); i++) {
            System.out.println(i + " " + adjMatrix.getInNeighbours(i));
        }
    }
}
