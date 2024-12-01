package br.com.holytickets.services;

import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.models.Establishment;
import br.com.holytickets.repositories.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final EstablishmentRepository establishmentRepository;

    public Map<Character, String> getDefaultSeatChart(UUID id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Establishment with ID " + id + " not found."));
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
            List<String> seatList = new ArrayList<>();
            String seats = "";
            for (int j = 0; j < columns; j++) {
                String s = namedRows.get(i) + numberedColumns.get(j);
                seatList.add(s);
                seats += s + " ";
            }

            //String seats = mapper.writeValueAsString(seatList);
            seatChart.put(namedRows.get(i), seats);
        }


        return seatChart;
    }
}
