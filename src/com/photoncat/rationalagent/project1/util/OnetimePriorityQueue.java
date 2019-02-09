package com.photoncat.rationalagent.project1.util;

import java.util.*;

/**
 * Note: There's not a built-in Fibonacci heap in java, plus the heap it self has a huge constant factor
 * in most implementations.
 *
 * However, I found this useful trick for the search problem.
 *
 * This Queue will only extract elements with same key ONCE.
 *
 * So multiple decreased value can be simply inserted into this queue. And when removed, they will only be removed once.
 *
 * Moreover, this queue offers a method to check whether an element has been removed or not.
 */
public class OnetimePriorityQueue<Element> extends PriorityQueue<Map.Entry<Element, Integer>> {
    private Set<Element> extracted = new HashSet<>();

    public OnetimePriorityQueue() {
        super(Map.Entry.comparingByValue());
    }

    public boolean add(Element key, Integer value) {
        return offer(new AbstractMap.SimpleEntry<>(key, value));
    }

    /**
     * @return true if the element is added into the queue. false if the element is extracted already.
     */
    @Override
    public boolean offer(Map.Entry<Element, Integer> element) {
        if (!extracted.contains(element.getKey())) {
            return super.offer(element);
        }
        return false;
    }

    @Override
    public Map.Entry<Element, Integer> poll() {
        var elem = peek();
        extracted.add(elem.getKey());
        super.poll();
        return elem;
    }

    @Override
    public Map.Entry<Element, Integer> peek(){
        while (!super.isEmpty()) {
            if (extracted.contains(super.peek().getKey())) {
                // Remove extracted elements.
                super.poll();
            } else {
                return super.peek();
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return peek() == null;
    }

    @Override
    public void clear() {
        super.clear();
        extracted.clear();
    }

    @Override
    public int size() {
        throw new IllegalStateException();
    }
}
