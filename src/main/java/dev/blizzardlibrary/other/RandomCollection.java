package dev.blizzardlibrary.other;

import java.util.Collection;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class RandomCollection<K> {

    private NavigableMap<Double, K> map = new TreeMap<>();
    private ThreadLocalRandom random;
    private double total = 0;

    public RandomCollection() {
        this(ThreadLocalRandom.current());
    }

    public RandomCollection(ThreadLocalRandom random) {
        this.random = random;
    }

    public RandomCollection<K> add(double weight, K result) {
        if (weight <= 0) {
            return this;
        }

        total += weight;
        map.put(total, result);
        return this;
    }

    public K next() {
        if (map.isEmpty()) {
            return null;
        }
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }

    public void clear() {
        if (map.isEmpty() && total == 0) {
            return;
        }

        this.map.clear();
        this.total = 0;
    }

    public void reset() {
        this.random = null;
        this.total = 0;
        map.clear();
        this.map = null;
    }


    public Collection<K> values() {
        if (map.isEmpty()) {
            return null;
        }

        return map.values();
    }
}
