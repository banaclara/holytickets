package br.com.holytickets.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Director is required")
    private String director;

    @NotNull(message = "Casting is required")
    private String casting;

    @NotNull(message = "Description is required")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "establishment_id", referencedColumnName = "id", nullable = false)
    private Establishment establishment;
}
