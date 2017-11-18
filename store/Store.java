package org.hackathon.common.store;

import org.hackathon.common.model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vh on 11/18/17.
 */

public abstract class Store<E extends Entity> {

    private List<E> items;

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
        items.add(item);
    }

    public E get(int id) {
        List<E> filtered = list(new IdFilter(id));

        return filtered.size() == 0 ? null : filtered.get(0);
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
