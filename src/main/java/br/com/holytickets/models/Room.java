package br.com.holytickets.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room extends Establishment {
   // private List<Seat> seatsSold;
    private String seatType;
    protected String roomDisplay[][];

    @Override
    public Integer getCapacity() {
        return super.getCapacity();
    }
}
