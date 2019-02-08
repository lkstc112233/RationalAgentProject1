package com.photoncat.rationalagent.project1.util;


import java.util.*;

/**
 * A compiled, append only program. Basically it's a Short array. You can iterate through it by using for-each loop.
 * @author Xu Ke
 *
 */
public class LoadedGraph implements Iterable<LoadedGraph.GraphNode>{
    public class GraphNode {
        private int id;
        private int x;
        private int y;

        GraphNode(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public int getId() {
            return id;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    /**
     * Nodes storage.
     */
    private List<GraphNode> nodes = new ArrayList<>();
    private Map<GraphNode, Map<GraphNode, Integer>> edges = new TreeMap<>();
    void addNode(int id, int x, int y) {
        nodes.add(new GraphNode(id, x, y));
    }
    void sortNodes() {
        nodes.sort(Comparator.comparingInt(n -> n.id));
    }
    void addEdge(int from, int to, int distance) {
        if (!edges.containsKey(nodes.get(from))) {
            edges.put(nodes.get(from), new TreeMap<>());
        }
        if (!edges.containsKey(nodes.get(to))) {
            edges.put(nodes.get(to), new TreeMap<>());
        }
        edges.get(nodes.get(from)).put(nodes.get(to), distance);
        edges.get(nodes.get(to)).put(nodes.get(from), distance);
    }
    /**
     * Provides support for <b>for-each</b> loop.
     */
    @Override
    public Iterator<GraphNode> iterator() {
        return nodes.iterator();
    }
}