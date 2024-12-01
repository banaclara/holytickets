package br.com.holytickets.repositories;

import br.com.holytickets.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    @Query("SELECT e.establishment.id FROM Schedule s " +
            "JOIN s.event e " +
            "WHERE s.id = :scheduleId")
    UUID findEstablishmentIdByScheduleId(@Param("scheduleId") UUID scheduleId);
}
