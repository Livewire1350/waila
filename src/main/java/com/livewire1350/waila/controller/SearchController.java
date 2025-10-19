package com.livewire1350.waila.controller;

import com.livewire1350.waila.repository.CableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    private final CableRepository cableRepository;

    public SearchController(CableRepository cableRepository) {
        this.cableRepository = cableRepository;
    }

    @GetMapping("/search/cable")
    public String getSearch(
            Model model,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String label,
            @RequestParam(required = false) String name
        ) {
        if (id != null) {
            if (cableRepository.existsById(id)) {
                return "redirect:/cable/" + id;
            } else {
                return "search/not-found";
            }
        }
        return "cable-search";
    }

}
