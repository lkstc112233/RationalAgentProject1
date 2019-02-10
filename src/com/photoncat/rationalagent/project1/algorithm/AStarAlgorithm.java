package com.photoncat.rationalagent.project1.algorithm;

import com.photoncat.rationalagent.project1.util.LoadedGraph;
import com.photoncat.rationalagent.project1.util.OnetimePriorityQueue;

import java.util.HashMap;
import java.util.Map;

public class AStarAlgorithm implements Algorithm {
    private LoadedGraph graph;
    private Heuristics heuristics;

    public interface Heuristics {
        int heuristic(LoadedGraph.GraphNode current, LoadedGraph.GraphNode target);
    }

    public AStarAlgorithm(LoadedGraph graph) {
        this(graph, (current, target) -> {
            int xDistance = Math.abs(current.getX() - target.getX());
            int yDistance = Math.abs(current.getY() - target.getY());
            if (xDistance > 0) {
                xDistance -= 1;
            }
            if (yDistance > 0) {
                yDistance -= 1;
            }
            return (int) (100 * Math.sqrt(xDistance * xDistance + yDistance * yDistance));
        });
    }

    public AStarAlgorithm(LoadedGraph graph, Heuristics heuristics) {
        this.graph = graph;
        this.heuristics = heuristics;
    }

    @Override
    public int shortestPath(int start, int end) {
        OnetimePriorityQueue<LoadedGraph.GraphNode> frienge = new OnetimePriorityQueue<>();
        Map<LoadedGraph.GraphNode, Integer> realCost = new HashMap<>(); // g(n)
        var goal = graph.getNode(end);
        frienge.add(graph.getNode(start), heuristics.heuristic(graph.getNode(start), goal));
        while (!frienge.isEmpty() && frienge.peek().getKey() != goal) {
            var pair = frienge.poll();
            var currentNode = pair.getKey();
            int currentCost = realCost.get(currentNode);
            for (var nodeNeighbour : currentNode) {
                if (!frienge.extracted(nodeNeighbour)) {
                    int newCost = currentCost + currentNode.distanceTo(nodeNeighbour);
                    if (realCost.containsKey(nodeNeighbour) && realCost.get(nodeNeighbour) > newCost) {
                        realCost.put(nodeNeighbour, newCost);
                    }
                    frienge.add(nodeNeighbour, newCost + heuristics.heuristic(nodeNeighbour, goal));
                }
            }
        }
        if (frienge.isEmpty()) {
            return -1;
        }
        return frienge.peek().getValue();
    }
}
