package com.photoncat.rationalagent.project1.util;


import java.util.Map;
import java.util.PriorityQueue;

/**
 * A test class for OnetimePriorityQueue.
 */
public class OnetimePriorityQueueTest {
    public static void main(String[] args) {
        OnetimePriorityQueue<Integer> queue = new OnetimePriorityQueue<>();
        if (!queue.isEmpty()) throw new AssertionError();
        // Add some elements
        if (!queue.add(1, 4)) throw new AssertionError();
        if (queue.peek().getKey() != 1) throw new AssertionError();
        if (!queue.add(2, 5)) throw new AssertionError();
        if (queue.peek().getKey() != 1) throw new AssertionError();
        if (!queue.add(3, 3)) throw new AssertionError();
        if (queue.peek().getKey() != 3) throw new AssertionError();
        // Examine poll.
        if (queue.extracted(3)) throw new AssertionError();
        if (queue.poll().getKey() != 3) throw new AssertionError();
        if (!queue.extracted(3)) throw new AssertionError();
        if (queue.peek().getKey() != 1) throw new AssertionError();
        // Polled element cannot be re-added.
        if (queue.add(3, 3)) throw new AssertionError();
        if (queue.add(3, 2)) throw new AssertionError();
        if (queue.add(3, 5)) throw new AssertionError();
        if (queue.peek().getKey() != 1) throw new AssertionError();
        // Decreasing un-polled key
        if (!queue.add(2, 3)) throw new AssertionError();
        if (queue.peek().getKey() != 2) throw new AssertionError();
        if (queue.extracted(2)) throw new AssertionError();
        if (queue.poll().getKey() != 2) throw new AssertionError();
        if (!queue.extracted(2)) throw new AssertionError();
        if (queue.isEmpty()) throw new AssertionError();
        if (queue.poll().getKey() != 1) throw new AssertionError();
        if (!queue.extracted(1)) throw new AssertionError();
        if (!queue.isEmpty()) throw new AssertionError();
        System.out.println("All test passed.");
    }
}
