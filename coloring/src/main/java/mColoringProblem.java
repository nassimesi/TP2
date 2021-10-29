import java.util.Arrays;

class ColoringProblem
{
    private int n;

    public ColoringProblem(int n) {
        this.n = n;
    }

    private boolean isSafe(int v, int[][] graph, int[] color, int c)
    {
        for (int i = 0; i < n; i++)
            if (graph[v][i] == 1 && c == color[i])
                return false;
        return true;
    }

    private boolean graphColoringUtil(int[][] graph, int m, int[] color, int v)
    {
        if (v == this.n)
            return true;
        for (int c = 1; c <= m; c++)
        {
            if (isSafe(v, graph, color, c))
            {
                color[v] = c;
                if (graphColoringUtil(graph, m, color, v + 1))
                    return true;
                color[v] = 0;
            }
        }
        return false;
    }

    private void graphColoring(int[][] graph, int m)
    {
        int[] color = new int[n];
        for (int i = 0; i < n; i++)
            color[i] = 0;

        if (!graphColoringUtil(graph, m, color, 0)) {
            System.out.println(
                    "Solution does not exist");
            return;
        }
        System.out.println(Arrays.toString(color));
    }


    public static void main(String[] args)
    {
        ColoringProblem Coloring = new ColoringProblem(6);

        int [][] graph = {
                { 0, 1, 1, 0, 0, 0 },
                { 1, 0, 0, 0, 1, 1 },
                { 1, 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 1, 1 },
                { 0, 1, 0, 1, 0, 1 },
                { 0, 1, 0, 1, 1, 0 }};

        int m = 3; // Number of colors
        Coloring.graphColoring(graph, m);
    }
}

