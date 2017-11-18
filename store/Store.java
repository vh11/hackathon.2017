package org.hackathon.common.store;

import org.hackathon.common.model.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by vh on 11/18/17.
 */

public abstract class Store<E extends Entity> {

    private AtomicInteger counter;
    private List<E> items;

    public Store() {
        this.items = new ArrayList<>();
        this.counter = new AtomicInteger();
    }

    abstract E newItem();

    public List<E> update(List<E> items) {
        ArrayList<E> newItems = new ArrayList<>();
        if (items != null) {
            for (E item : items) {
                if (this.items.contains(item)) {
                    newItems.add(item);
                }
            }

            items.addAll(newItems);
        }

        return newItems;
    }

    public void insert(List<E> items) {
        if (items != null) {
            for (E item : items) {
                item.setId(counter.incrementAndGet());
            }

            this.items.addAll(items);
        }
    }

    public List<E> list() {
        return new ArrayList<>(items);
    }

    public List<E> list(Filter<E> filter) {
        ArrayList<E> items = new ArrayList<>();

        for (E item : this.items) {
            if (filter.accept(item)) {
                items.add(item);
            }
        }

        return items;
    }

    public void add(E item) {
        System.out.println("add: " + item);
        items.add(item);
    }

    public E getFirst(Filter<E> filter) {
        List<E> filtered = list(filter);

        return filtered.size() == 0 ? null : filtered.get(0);
    }

    public E get(int id) {
        return getFirst((Filter<E>) new IdFilter(id));
    }

    public void loadCSV(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while((line = reader.readLine()) != null) {
            E e = newItem();
            e.deserialize(line);
            add(e);
        }
    }

    public void loadCSV(String fileName) throws IOException {
        FileInputStream in = new FileInputStream(fileName);
        loadCSV(in);
        in.close();
    }

    interface Filter<E extends Entity> {

        boolean accept(E e);
    }

    static class IdFilter implements Filter {

        private int id;

        public IdFilter(int id) {
            this.id = id;
        }

        @Override
        public boolean accept(Entity e) {
            return e.getId() == id;
        }
    }
}
