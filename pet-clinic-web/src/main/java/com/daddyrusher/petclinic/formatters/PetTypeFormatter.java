package com.daddyrusher.petclinic.formatters;

import com.daddyrusher.petclinic.model.PetType;
import com.daddyrusher.petclinic.service.PetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
@AllArgsConstructor
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService service;

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = service.findAll();
        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }
}
