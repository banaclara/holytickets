package br.com.holytickets.repositories;

import br.com.holytickets.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    boolean existsByTitle(String title);
    List<Event> findByTitleContainingIgnoreCase(String title);
}
