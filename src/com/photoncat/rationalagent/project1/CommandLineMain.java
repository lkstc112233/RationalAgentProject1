package com.photoncat.rationalagent.project1;

import com.photoncat.rationalagent.project1.algorithm.Algorithm;
import com.photoncat.rationalagent.project1.algorithm.DijkstraAlgorithm;
import com.photoncat.rationalagent.project1.util.FileLoader;
import com.photoncat.rationalagent.project1.util.LoadedGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CommandLineMain {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: ");
            System.err.println("CommandLineMain inputFilename startIndex endIndex");
            System.exit(0xDEADBEEF);
        }
        File inputFile = new File(args[0]);
        LoadedGraph graph = null;
        try {
            graph = FileLoader.load(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int start = Integer.valueOf(args[1]);
        int end = Integer.valueOf(args[2]);
        Algorithm algorithm = new DijkstraAlgorithm(graph);
        var before = System.nanoTime();
        int answer = algorithm.shortestPath(start, end);
        var after = System.nanoTime();
        System.out.println("The path between " + start + " and " + end + " has a length of: " + answer);
        System.out.println("Calculation ends in " + (after - before) + " ns.");
    }
}
