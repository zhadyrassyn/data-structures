public class DisjointSetsDemo {
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(6);
        ds.union(2, 4);
        System.out.println(ds);
        ds.union(5, 2);
        System.out.println(ds);
        ds.union(3, 1);
        System.out.println(ds);
        ds.union(2, 3);
        System.out.println(ds);
        ds.union(3, 6);
        System.out.println(ds);
        
    }
}

class DisjointSet {
    private int[] parent;
    private int[] rank;

    public DisjointSet(int size) {
        this.parent = new int[size+1];
        this.rank = new int[size+1];

        for(int i = 1; i <= size; i++) {
            makeSet(i);
        }
    }

    public void makeSet(int i) {
        parent[i] = i;
    }

    //path compression
    public int find(int i) {
        if(i != parent[i]) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int i, int j) {
        System.out.println("UNION: " + i + ", " + j);
        int i_id = find(i);
        int j_id = find(j);
        
        if(i_id == j_id) {
            return;
        }

        if(rank[i_id] > rank[j_id]) {
            parent[j_id] = i_id;
        } else {
            parent[i_id] = j_id;
            if(rank[i_id] == rank[j_id]) {
                ++rank[j_id];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PARENT\n");
        for(int i = 1; i < parent.length; i++) { 
            sb.append("(" + i + ", " + parent[i] + "), "); 
        }
        sb.append("\nRANK\n");
        for(int i = 1; i < rank.length; i++) {
            sb.append("(" + i + ", " + rank[i] + "), ");
        }
        sb.append("\n");
        return sb.toString();
    }

}
