package com.photoncat.rationalagent.project1.algorithm;

import com.photoncat.rationalagent.project1.util.LoadedGraph;
import com.photoncat.rationalagent.project1.util.OnetimePriorityQueue;

/**
 * A class that holds the algorithm as in Dijkstra's algorithm.
 */
public class DijkstraAlgorithm implements Algorithm{
    private LoadedGraph graph;

    public DijkstraAlgorithm(LoadedGraph graph) {
        this.graph = graph;
    }

    public int shortestPath(int start, int end) {
        OnetimePriorityQueue<LoadedGraph.GraphNode> frienge = new OnetimePriorityQueue<LoadedGraph.GraphNode>();
        frienge.add(graph.getNode(start), 0);
        var goal = graph.getNode(end);
        while (!frienge.isEmpty() && frienge.peek().getKey() != goal) {
            var pair = frienge.poll();
            var currentNode = pair.getKey();
            int currentValue = pair.getValue();
            for (var nodeNeighbour: currentNode) {
                if (!frienge.extracted(nodeNeighbour))
                    frienge.add(nodeNeighbour, currentValue + currentNode.distanceTo(nodeNeighbour));
            }
        }
        if (frienge.isEmpty()) {
            return -1;
        }
        return frienge.peek().getValue();
    }
}
