package com.daddyrusher.petclinic.service.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {
    protected Map<ID, T> items = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(items.values());
    }

    T findById(ID id) {
        return items.get(id);
    }

    T save(ID id, T object) {
        return items.put(id, object);
    }

    void deleteById(ID id) {
        items.remove(id);
    }

    void delete(T object) {
        items.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(object));
    }
}
