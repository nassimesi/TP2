package question5;

import question1.backtrack.FileIO;
import question1.backtrack.Graph;
import question1.backtrack.GraphColoringBackTracking;
import question2_3.ChocoColoring;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String args[]) {
        //Backtracking init
        Graph graph = new Graph();
        GraphColoringBackTracking graphColoringBackTracking = new GraphColoringBackTracking();

        //Choco solver init
        ChocoColoring chocoColoring = new ChocoColoring();

        //can: 1-2-5-6-7-12-13-14
        //cant: 3-4-8-9-10-11-
        for (int i = 9;i<10;i++){
            String file1 = ".\\coloring\\src\\main\\resources\\datasets\\g"+i+".col";

            /*FileIO.readDataAndCreateGraphFromEdges(file1,graph);
            Instant start = Instant.now();
            graphColoringBackTracking.colorGraph(graph);
            Instant end = Instant.now();
            System.out.println("Backtracking g"+i+": "+ Duration.between(start, end));*/

            ChocoColoring.fin = new File(file1);
            ChocoColoring.start();
            Instant start1 = Instant.now();
            chocoColoring.solve();
            Instant end1 = Instant.now();
            System.out.println("Choco solver g"+i+": "+Duration.between(start1, end1));
            System.out.println("--------------------------------------------------------------");
        }
    }
}
