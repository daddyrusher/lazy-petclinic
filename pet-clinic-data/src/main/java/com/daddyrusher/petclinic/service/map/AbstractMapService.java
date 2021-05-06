package com.daddyrusher.petclinic.service.map;

import com.daddyrusher.petclinic.exception.EntityException;
import com.daddyrusher.petclinic.model.BaseEntity;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> items = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(items.values());
    }

    T findById(ID id) {
        return items.get(id);
    }

    T save(T object) {
        if (nonNull(object) && isNull(object.getId())) {
            object.setId(getNextId());
            items.put(object.getId(), object);
            return object;
        }

        throw new EntityException("Entity is null");
    }

    void deleteById(ID id) {
        items.remove(id);
    }

    void delete(T object) {
        items.entrySet().removeIf(idtEntry -> idtEntry.getValue().equals(object));
    }

    private Long getNextId() {
        Long nextId;

        try {
            nextId = Collections.max(items.keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextId = 1L;
        }

        return nextId;
    }
}
