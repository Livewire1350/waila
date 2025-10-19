package com.livewire1350.waila.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="EndConnector")
@Getter
public class EndConnector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Setter
    @Column(name = "label", unique = true)
    private String label;

    @ManyToOne
    @Setter
    @JoinColumn(name = "cable")
    private Cable cable;

    @Setter
    @Column(name = "end")
    private int end;

    @ManyToOne
    @Setter
    @JoinColumn(name = "connector_type")
    private ConnectorType connectorType;

}
