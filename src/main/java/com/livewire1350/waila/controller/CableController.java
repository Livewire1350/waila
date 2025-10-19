package com.livewire1350.waila.controller;

import com.livewire1350.waila.model.Cable;
import com.livewire1350.waila.model.EndConnector;
import com.livewire1350.waila.repository.CableRepository;
import com.livewire1350.waila.repository.ConnectorTypeRepository;
import com.livewire1350.waila.repository.EndConnectorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CableController {

    private final CableRepository cableRepository;
    private final ConnectorTypeRepository connectorTypeRepository;
    private final EndConnectorRepository endConnectorRepository;

    public CableController(CableRepository cableRepository, ConnectorTypeRepository connectorTypeRepository, EndConnectorRepository endConnectorRepository) {
        this.cableRepository = cableRepository;
        this.connectorTypeRepository = connectorTypeRepository;
        this.endConnectorRepository = endConnectorRepository;
    }

    @GetMapping("/cable/{id}")
    public String getCable(
            Model model,
            @PathVariable int id
    ) {
        Cable cable = cableRepository.getById(id);
        model.addAttribute("cable", cable);
        model.addAttribute("first_end", cable.getEndConnectors().stream().filter(e -> e.getEnd() == 1).toList());
        model.addAttribute("second_end", cable.getEndConnectors().stream().filter(e -> e.getEnd() == 2).toList());
        model.addAttribute("connector_types", connectorTypeRepository.findAll());
        return "cable/cable";
    }

    @PostMapping("/cable/{id}/update")
    public String updateCable(
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam String description
    ) {
        Cable cable = cableRepository.getById(id);
        cable.setName(name);
        cable.setDescription(description);
        cableRepository.save(cable);
        return "redirect:/cable/" + id;
    }

    @PostMapping("/cable/{id}/add-connector")
    public String updateCable(
            @PathVariable int id,
            @RequestParam String label,
            @RequestParam int end,
            @RequestParam int connector_type
    ) {
        Cable cable = cableRepository.getById(id);
        EndConnector endConnector = new EndConnector();
        endConnector.setLabel(label);
        endConnector.setEnd(end);
        endConnector.setCable(cable);
        endConnector.setConnectorType(connectorTypeRepository.getById(connector_type));
        cable.getEndConnectors().add(endConnector);
        cableRepository.saveAndFlush(cable);
        return "redirect:/cable/" + id;
    }

    @PostMapping("/cable/{id}/remove-connector")
    public String updateCable(
            @PathVariable int id,
            @RequestParam int end_connector
    ) {
        endConnectorRepository.deleteById(end_connector);
        endConnectorRepository.flush();
        return "redirect:/cable/" + id;
    }

    @GetMapping("/cable/new")
    public String getCreateCable() {
        return "cable/new";
    }

    @PostMapping("/cable/new")
    public String createCable(
            @RequestParam String name,
            @RequestParam String description
    ) {
        Cable cable = new Cable();
        cable.setName(name);
        cable.setDescription(description);
        cableRepository.saveAndFlush(cable);
        return "redirect:/cable/" + cable.getId();
    }

}
