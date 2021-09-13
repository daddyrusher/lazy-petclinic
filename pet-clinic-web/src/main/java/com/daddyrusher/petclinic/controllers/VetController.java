package com.daddyrusher.petclinic.controllers;

import com.daddyrusher.petclinic.service.VetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
@AllArgsConstructor
public class VetController {
    private final VetService vetService;

    @GetMapping()
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}
