package question4;

import question1.backtrack.FileIO;
import question1.backtrack.Graph;
import question1.backtrack.GraphColoringBackTracking;
import question2_3.ChocoColoring;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String args[]) {
        String file = ".\\coloring\\src\\main\\resources\\datasets\\regions.col";
        //Backtracking
        Graph graph = new Graph();
        FileIO.readDataAndCreateGraphFromEdges(file,graph);
        GraphColoringBackTracking graphColoringBackTracking = new GraphColoringBackTracking();

        Instant start = Instant.now();
        graphColoringBackTracking.colorGraph(graph);
        Instant end = Instant.now();
        System.out.println("Backtracking: "+Duration.between(start, end));

        //Choco solver
        ChocoColoring.fin = new File(file);
        ChocoColoring.start();
        ChocoColoring chocoColoring = new ChocoColoring();
        Instant start1 = Instant.now();
        chocoColoring.solve();
        Instant end1 = Instant.now();
        System.out.println("Choco solver: "+Duration.between(start1, end1));

        /*
        *
        * Result :
        * Backtracking: PT0S
        * Choco solver: PT0.538S
        *
        * */
    }
}
