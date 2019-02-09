package com.photoncat.rationalagent.project1.util;


import java.util.Map;
import java.util.PriorityQueue;

/**
 * A test class for OnetimePriorityQueue.
 */
public class OnetimePriorityQueueTest {
    public static void main(String[] args) {
        OnetimePriorityQueue<Integer> queue = new OnetimePriorityQueue<>();
        assert queue.isEmpty();
        assert queue.add(1, 4);
        assert queue.peek().getKey() == 1;
        assert queue.add(2, 5);
        assert queue.peek().getKey() == 1;
        assert queue.add(3, 3);
        assert queue.peek().getKey() == 3;
        assert queue.poll().getKey() == 3;
        assert queue.peek().getKey() == 1;
        assert !queue.add(3, 3);
        assert !queue.add(3, 2);
        assert !queue.add(3, 5);
        assert queue.peek().getKey() == 1;
        assert queue.add(2, 3);
        assert queue.peek().getKey() == 2;
        assert queue.poll().getKey() == 2;
        assert !queue.isEmpty();
        assert queue.poll().getKey() == 1;
        assert queue.isEmpty();
        System.out.println("All test passed.");
    }
}
