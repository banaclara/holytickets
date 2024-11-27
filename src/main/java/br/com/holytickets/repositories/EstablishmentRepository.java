package br.com.holytickets.repositories;

import br.com.holytickets.models.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, UUID> {
    Optional<Establishment> findByEmail(@Param("email") String email);
    List<Establishment> findByName(@Param("name") String name);
}
