package br.com.holytickets.repositories;

import br.com.holytickets.dto.SeatDTO;
import br.com.holytickets.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SeatRepository extends JpaRepository<Seat, UUID> {
    @Query(value = "select ss.seat_number from seats_sold ss " +
            "where ss.schedule_id = :scheduleId", nativeQuery = true)
    Optional<List<String>> findSeatsSold(@Param("scheduleId") UUID scheduleId);
}
