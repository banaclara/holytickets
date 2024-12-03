package br.com.holytickets.repositories;

import br.com.holytickets.models.Ticket;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    @Query(value = "select t.id as id,  es.name as establishment_name, e.title as event_title, s.exhibition_date, ss.seat_number, t.purchase_date,  u.name as user_name from tickets t\n" +
            "join users u on t.user_id = u.id \n" +
            "join seats_sold ss on t.seat_id = ss.id\n" +
            "join schedules s on s.id = ss.schedule_id \n" +
            "join events e on e.id = s.event_id \n" +
            "join establishments es on es.id = e.establishment_id \n" +
            "where t.id = :ticketId",  nativeQuery = true)
    Tuple findRawPrintTicketById(@Param("ticketId")UUID ticketId);
}
