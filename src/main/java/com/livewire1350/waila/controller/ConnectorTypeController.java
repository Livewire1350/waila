package com.livewire1350.waila.controller;

import com.livewire1350.waila.model.ConnectorType;
import com.livewire1350.waila.repository.ConnectorTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConnectorTypeController {

    private final ConnectorTypeRepository connectorTypeRepository;

    public ConnectorTypeController(ConnectorTypeRepository connectorTypeRepository) {
        this.connectorTypeRepository = connectorTypeRepository;
    }


    @GetMapping("/connector-types/new")
    public String getCreateConnectorType() {
        return "connector-types/new";
    }

    @PostMapping("/connector-types/new")
    public String createConnectorType(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam char gender
    ) {
        ConnectorType connectorType = new ConnectorType();
        connectorType.setName(name);
        connectorType.setDescription(description);
        connectorType.setGender(gender);
        connectorTypeRepository.saveAndFlush(connectorType);
        return "redirect:/";
    }

}
