package com.livewire1350.waila.repository;

import com.livewire1350.waila.model.EndConnector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EndConnectorRepository extends JpaRepository<EndConnector, Integer> {

    Optional<EndConnector> findByLabel(String label);

}
