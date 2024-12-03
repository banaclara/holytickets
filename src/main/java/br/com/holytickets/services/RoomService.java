package br.com.holytickets.services;

import br.com.holytickets.dto.EstablishmentDTO;
import br.com.holytickets.dto.ScheduleDTO;
import br.com.holytickets.exception.ConflictException;
import br.com.holytickets.exception.ResourceNotFoundException;
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
        if (estDTO == null) {
            throw new ResourceNotFoundException("Establishment with ID " + id + " not found.");
        }
        return converter.convertToEntity(estDTO);
    }

    private Schedule getSchedule(UUID id) {
        ScheduleDTO schDTO = scheduleService.findById(id);
        if (schDTO == null) {
            throw new ResourceNotFoundException("Schedule with ID " + id + " not found.");
        }
        return converter.convertToEntity(schDTO);
    }

    public Map<Character, String> getDefaultSeatChart(UUID id, List<String> seatsSold) {
        Establishment establishment = getEstablishment(id);
        if (establishment.getRoom() == null) {
            throw new ConflictException("Room information is not available for the establishment with ID " + id);
        }

        Integer rows = establishment.getRoom().getRows();
        Integer columns = establishment.getRoom().getColumns();

        if (rows == null || columns == null || rows <= 0 || columns <= 0) {
            throw new ConflictException("Invalid room configuration for establishment with ID " + id);
        }

        List<Character> namedRows = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            char letra = (char) ('A' + (i % 26)); // Usa 'A' como base para letras maiúsculas
            namedRows.add(letra);
        }

        List<String> numberedColumns = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            String numero = (i + 1 < 10) ? "0" + (i + 1) : String.valueOf(i + 1);
            numberedColumns.add(numero);
        }

        Map<Character, String> seatChart = new HashMap<>();
        seatChart.put('0', "----------------------------------------");

        for (int i = 0; i < rows; i++) {
            StringBuilder seats = new StringBuilder();
            for (int j = 0; j < columns; j++) {
                String s = namedRows.get(i) + numberedColumns.get(j);
                if (seatsSold.contains(s)) {
                    s = "[X]";
                }
                seats.append(s).append(" ");
            }
            seatChart.put(namedRows.get(i), seats.toString());
        }

        return seatChart;
    }

    public Map<Character, String> getAvailableSeatsChart(UUID scheduleId) {
        UUID estID = scheduleRepository.findEstablishmentIdByScheduleId(scheduleId);
        if (estID == null) {
            throw new ResourceNotFoundException("Establishment not found for schedule ID " + scheduleId);
        }

        Schedule schedule = getSchedule(scheduleId);
        List<Seat> seats = schedule.getSeats();
        List<String> seatsSold = new ArrayList<>();
        for (Seat seat : seats) {
            seatsSold.add(seat.getSeatNumber());
        }

        return getDefaultSeatChart(estID, seatsSold);
    }

    public Establishment getEstablishmentBySchedule(UUID id) {
        UUID estID = scheduleRepository.findEstablishmentIdByScheduleId(id);
        if (estID == null) {
            throw new ResourceNotFoundException("Establishment not found for schedule ID " + id);
        }
        return getEstablishment(estID);
    }
}
