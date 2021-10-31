package question1.backtrack;

import java.util.ArrayList;

public class GraphColoringBackTracking {

    public void colorGraph(Graph G) {
        if(isGraphColorable(G, G.colors)){
            System.out.println("With the available colors the graph was colorable");
        }
        else{
            System.out.println("With the available colors the graph was not colorable");
        }
    }

    public boolean isSafe(ArrayList<Edge> edges){
        for (Edge e: edges){
            if (e.getStartVertex().color == e.getEndVertex().color)
                return false;
        }
        return true;
    }

    public boolean colorGraphUsingBackTrack(Graph G, ArrayList<Vertex> vertices, int index, int numberOfColors){

        //if all vertices are covered
        if (index == vertices.size())
            return true;

        for (int i = 0; i < numberOfColors; i++){

            //color vertex
            vertices.get(index).color = i;

            //check if it safe
            if (this.isSafe(G.getEdgesFromAdj(vertices.get(index)))){
                //color next vertex
                if (colorGraphUsingBackTrack(G, vertices, index+1, numberOfColors))
                    return true;
                //if next vertex was not colorable. reset the color and try next one
                vertices.get(index).color = -1; //reset color
            }
        }

        return false;
    }

    public boolean isGraphColorable(Graph G, int noOfColors) {
        // TODO Auto-generated method stub
        ArrayList<Vertex> vertices;

        vertices = G.getVertices();

        return this.colorGraphUsingBackTrack(G, vertices, 0, noOfColors);

    }

    public static void main(String args[]) {
        Graph graph = new Graph();

        FileIO.readDataAndCreateGraphFromEdges(".\\coloring\\src\\main\\resources\\datasets\\g5.col",graph);
        graph.printGraph();
        GraphColoringBackTracking graphColoringBackTracking = new GraphColoringBackTracking();
        graphColoringBackTracking.colorGraph(graph);

        graph.printGraphColoring();

    }
}
