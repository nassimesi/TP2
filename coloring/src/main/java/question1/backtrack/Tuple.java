package question1.backtrack;

import java.util.LinkedList;

class Tuple{
    Vertex v;
    LinkedList<Edge> list;

    public Tuple(Vertex v){
        this.v = v;
        this.list = new LinkedList<>();
    }
}