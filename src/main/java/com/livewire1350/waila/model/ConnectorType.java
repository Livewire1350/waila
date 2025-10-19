package com.livewire1350.waila.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name="ConnectorType")
public class ConnectorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Nonnull
    private String name;

    @Setter
    private String description;

    @Setter
    private char gender;

}
