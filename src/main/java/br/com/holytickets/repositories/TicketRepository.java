package br.com.holytickets.repositories;

import br.com.holytickets.dto.PrintTicketDTO;
import br.com.holytickets.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    @Query(value = "select t.id,  es.name, e.title, s.exhibition_date, ss.seat_number, t.purchase_date,  u.name from tickets t\n" +
            "join users u on t.user_id = u.id \n" +
            "join seats_sold ss on t.seat_id = ss.id\n" +
            "join schedules s on s.id = ss.schedule_id \n" +
            "join events e on e.id = s.event_id \n" +
            "join establishments es on es.id = e.establishment_id \n" +
            "where t.id = :ticketId",  nativeQuery = true)
    Optional<PrintTicketDTO> getPrintTicketInfos(@Param("ticketId")UUID ticketId);
}
