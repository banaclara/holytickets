package br.com.holytickets.services;

import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.dto.ScheduleDTO;
import br.com.holytickets.models.Establishment;
import br.com.holytickets.models.Schedule;
import br.com.holytickets.models.Seat;
import br.com.holytickets.repositories.ScheduleRepository;
import br.com.holytickets.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final ScheduleService scheduleService;
    private final EstablishmentService establishmentService;
    private final ScheduleRepository scheduleRepository;
    private final Converter converter;

    private Establishment getEstablishment(UUID id) {
        EstablishmentDTO estDTO = establishmentService.findByID(id);
        return converter.convertToEntity(estDTO);
    }

    private Schedule getSchedule(UUID id) {
        ScheduleDTO schDTO = scheduleService.findById(id);
        return converter.convertToEntity(schDTO);
    }

    public Map<Character, String> getDefaultSeatChart(UUID id, List<String> seatsSold) {
        Establishment establishment = getEstablishment(id);
        Integer rows = establishment.getRoom().getRows();
        Integer columns = establishment.getRoom().getColumns();

        List<Character> namedRows = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            // Calcula a letra com base no índice
            char letra = (char) ('A' + (i % 26)); // Usa 'A' como base para letras maiúsculas
            namedRows.add(letra);
        }

        List<String> numberedColumns = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            String numero;
            if (i + 1 < 10) {
                numero = "0" + (i + 1);
            } else {
                numero = "" + (i + 1) + "";
            }
            numberedColumns.add(numero);
        }

        Map<Character, String> seatChart = new HashMap<>();
        seatChart.put('0', "----------------------------------------");
        for (int i = 0; i < rows; i++) {
            String seats = "";
            for (int j = 0; j < columns; j++) {
                String s = namedRows.get(i) + numberedColumns.get(j);
                if (seatsSold.contains(s)) {
                    s = "[X]";
                }
                seats += s + " ";
            }

            seatChart.put(namedRows.get(i), seats);
        }
        return seatChart;
    }

    public Map<Character, String> getAvailableSeatsChart(UUID scheduleId) {
        UUID estID = scheduleRepository.findEstablishmentIdByScheduleId(scheduleId);
        Schedule schedule = getSchedule(scheduleId);
        List<Seat> seats = schedule.getSeats();
        List<String> seatsSold = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);
            seatsSold.add(seat.getSeatNumber());
        }

        return getDefaultSeatChart(estID, seatsSold);
    }

    public Establishment getEstablishmentBySchedule(UUID id){
        UUID estID = scheduleRepository.findEstablishmentIdByScheduleId(id);
        return getEstablishment(estID);

    }
}
