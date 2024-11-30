package br.com.holytickets.services;

import br.com.holytickets.models.Seat;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    public List<Character> getRows(Integer capacity) {
        List<Character> rows = new ArrayList<>();
        // Preencher a lista com as letras do alfabeto
        for (int i = 0; i < capacity; i++) {
            // Calcula a letra com base no índice
            char letter = (char) ('A' + (i % 26)); // Usa 'A' como base para letras maiúsculas
            rows.add(letter);
        }
        return rows;
    }
 /*   public void defaultRoomSeats (List<Seat> seatSold, Integer capacity) {
        capacity = getCapacity();
        roomDisplay = new String[capacity/2][capacity/2];

    }*/
}
