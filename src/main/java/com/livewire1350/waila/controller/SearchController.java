package com.livewire1350.waila.controller;

import com.livewire1350.waila.model.EndConnector;
import com.livewire1350.waila.repository.CableRepository;
import com.livewire1350.waila.repository.EndConnectorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SearchController {

    private final CableRepository cableRepository;
    private final EndConnectorRepository endConnectorRepository;

    public SearchController(CableRepository cableRepository, EndConnectorRepository endConnectorRepository) {
        this.cableRepository = cableRepository;
        this.endConnectorRepository = endConnectorRepository;
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
        if (label != null) {
            Optional<EndConnector> endConnector = endConnectorRepository.findByLabel(label);
            if (endConnector.isPresent()) {
                return "redirect:/cable/" + endConnector.get().getCable().getId();
            } else {
                return "search/not-found";
            }
        }
        // TODO: name
        return "search/cable-search";
    }

}
