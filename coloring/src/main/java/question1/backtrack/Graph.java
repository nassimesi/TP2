package question1.backtrack;

import java.util.ArrayList;


public class Graph {
    ArrayList<Vertex> vertexes;
    ArrayList<Edge> edges;
    ArrayList<Tuple> adjList;
    int colors;

    public Graph() {
        this.vertexes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.adjList = new ArrayList<Tuple>();
    }

    public void addNewVertex(Vertex v){
        adjList.add(new Tuple(v));
        vertexes.add(v);
    }

    //add edge to adjacency list and return 1 for success -1 for failure
    public void addNewEdge(Edge e){

        Edge e2 = new Edge();
        e2.setEndVertex(e.getStartVertex());
        e2.setStartVertex(e.getEndVertex());
        edges.add(e);
        edges.add(e2);

        for (Tuple t : adjList){
            Vertex i = t.v;
            if (i.props.get("value") == e.getStartVertex().props.get("value")){
                t.list.offer(e);
                break;
            }
        }

        for (Tuple t : adjList){
            Vertex i = t.v;
            if (i.props.get("value") == e2.getStartVertex().props.get("value")){
                t.list.offer(e2);
                break;
            }
        }

    }

    public void printGraph(){
        System.out.println("------------------------------------------");
        for (Tuple t : adjList){
            for (int j = 0; j < t.list.size() ; j++){
                this.printVertexAndEdge(t.list.get(j));
                System.out.println("------------------------------------------");
            }

        }

    }

    public void printGraphColoring(){
        for (Vertex v : vertexes){
            System.out.println(v.props.get("value") + "  "+v.color);
        }
    }

    public ArrayList<Edge> getEdgesFromAdj(Vertex v){

        for (Tuple t : adjList){
            Vertex i = t.v;
            if (v.props.get("value").equals(i.props.get("value"))){
                return new ArrayList<Edge>(t.list);
            }
        }
        return null;
    }

    public ArrayList<Vertex> getVertices(){
        return vertexes;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }

    public void printVertexAndEdge(Edge e){
        System.out.println("Vertex " + e.getStartVertex().props.get("value") +" --> " + e.getEndVertex().props.get("value"));
    }
}