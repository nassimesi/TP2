package question1.backtrack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class FileIO {
    public static Graph readDataAndCreateGraphFromEdges(String filename, Graph G){

        String vertexProperty = "value";

        ArrayList<Vertex> vList = new ArrayList<>();
        HashMap<String, Integer> vertexes = new HashMap<>();

        File initialFile = new File(filename);
        String line = "";
        int lineNumber = -1;
        try {

            BufferedReader reader = new BufferedReader(new FileReader(initialFile));
            while (line != null) {
                if (!line.isEmpty() && !line.equals(" ")){
                    if (line.charAt(0) == '@'){
                        break;
                    }
                }
                line = reader.readLine();
            }

            G.colors = Integer.parseInt(line.split(" ")[2]);

            line = reader.readLine();
            lineNumber++;
            while (line != null){

                //add vertexes

                String[] edgs = line.split(" ");
                String v1 = edgs[0];
                String v2 = edgs[1];

                if (!vertexes.containsKey(v1)) {
                    Vertex v = new Vertex();
                    v.addProp(vertexProperty, v1.trim());
                    vList.add(v);
                    G.addNewVertex(v);
                    vertexes.put(v1, vList.size()-1);
                }
                if (!vertexes.containsKey(v2)) {
                    Vertex v = new Vertex();
                    v.addProp(vertexProperty, v2.trim());
                    vList.add(v);
                    G.addNewVertex(v);
                    vertexes.put(v2, vList.size()-1);
                }

                Edge e = new Edge();

                Vertex first = vList.get(vertexes.get(v1));
                Vertex second = vList.get(vertexes.get(v2));
                e.setStartVertex(first);
                e.setEndVertex(second);
                G.addNewEdge(e);
                line = reader.readLine();
                lineNumber++;
//				 System.out.println(lineNumber);
            }

            reader.close();

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        //return graph
        return G;
    }

}
