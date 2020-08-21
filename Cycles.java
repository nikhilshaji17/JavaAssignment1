import java.lang.*;
import java.io.*;
import java.util.*;

public class Cycles {

    static final int MAXN = 100;

    // Infinite value for array
    static int INF = (int) 1e7;

    static int[][] dis = new int[MAXN][MAXN];
    static int[][] Next = new int[MAXN][MAXN];

    static void initialise(int V, int[][] graph) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dis[i][j] = graph[i][j];

                if (graph[i][j] == INF)
                    Next[i][j] = -1;
                else
                    Next[i][j] = j;
            }
        }
    }

    static Vector<Integer> constructPath(int u, int v) {
        if (Next[u][v] == -1)
            return null;

        Vector<Integer> path = new Vector<Integer>();
        path.add(u);

        while (u != v) {
            u = Next[u][v];
            path.add(u);
        }
        return path;
    }

    static void floydWarshall(int V) {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {

                    if (dis[i][k] == INF || dis[k][j] == INF)
                        continue;

                    if (dis[i][j] > dis[i][k] + dis[k][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                        Next[i][j] = Next[i][k];
                    }
                }
            }
        }
    }

    // Print the shortest path
    static void printPath(Vector<Integer> path) {
        int n = path.size();
        for (int i = 0; i < n - 1; i++)
            System.out.print(path.get(i) + " -> ");
        System.out.print(path.get(n - 1) + "\n");
    }

    public static void main(String[] args) {
        int V = 4;
        int[][] graph = { { 0, 3, INF, 7 }, { 8, 0, 8, INF }, { 5, INF, 0, 1 }, { 2, INF, INF, 0 } };
        initialise(V, graph);

        floydWarshall(V);
        Vector<Integer> path;

        Scanner in = new Scanner(System.in);
        int u, v;
        System.out.println("Enter the vertices between 0 and 3");
        u = in.nextInt();
        v = in.nextInt();
        System.out.print("Shortest path from" + u + "to" + v + ": ");
        path = constructPath(u, v);
        if (path == null) {
            System.out.println("Negative Cycles exist");
        } else {
            printPath(path);
        }
        in.close();
    }
}