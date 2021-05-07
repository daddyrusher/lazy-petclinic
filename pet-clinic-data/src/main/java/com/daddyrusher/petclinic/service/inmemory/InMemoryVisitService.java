package com.daddyrusher.petclinic.service.inmemory;

import com.daddyrusher.petclinic.exception.EntityException;
import com.daddyrusher.petclinic.model.Visit;
import com.daddyrusher.petclinic.service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "inmemory"})
public class InMemoryVisitService extends AbstractInMemoryService<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        if (object.isNotValid()) {
            throw new EntityException("Invalid Visit!");
        }
        return super.save(object);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
