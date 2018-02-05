/**
 * Created by shengjiezhai on 2017/1/30.
 */
import java.util.*;
public class main {

    public static void main(String[] args) {
        int i, j;
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        Graph graph = new Graph(N);

        for (i = 0; i < N; i++) {
            String line = scanner.next();
            line = (i + 1) + " " + line;
            graph.Name(line);
        }


        for (j = 0; j < M; j++) {
            Integer from = scanner.nextInt();
            from = from - 1;
            Integer to = scanner.nextInt();
            to = to - 1;
            Integer weight = scanner.nextInt(); //no need to consider edges weight...
            graph.Edges(from, to);
            //ts.addWeight(j,weight);

        }
        graph.topologicalSort();
    }
}
