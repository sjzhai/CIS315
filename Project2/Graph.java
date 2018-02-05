/**
 * Created by shengjiezhai on 2017/1/30.
 */
import java.util.*;

class Graph {
    private int V;   // vertices number
    private LinkedList<Integer> adj[]; // Adjacency List
    private ArrayList<String> NameHolder;

    //Constructor
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        NameHolder = new ArrayList<>();
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }
    void Name(String name) {
        NameHolder.add(name);
    }

    void Edges(int v, int w) {
        adj[v].add(w);
    }

    // A recursive function used by topologicalSort
    void TopoSortIter(int v, boolean visited[], Stack stack) {
        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i]) {
                TopoSortIter(i, visited, stack);
            }
        }

        stack.push(new Integer(v));
    }

    void topologicalSort() { //To generate the topological order
        Stack st = new Stack();

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                TopoSortIter(i, visited, st);
            }
        }

        // Print contents of stack
        while (st.empty() == false) {
            String index = st.pop().toString();
            int Index  = Integer.parseInt(index);

            System.out.println(NameHolder.get(Index) + " ");

        }
    }
}