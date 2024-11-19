package br.com.holytickets.utils;

import br.com.holytickets.dto.EventDTO;
import br.com.holytickets.models.Event;

public class Converter {

    public static Event convertDtoToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setTitle(eventDTO.getTitle());
        event.setDirector(eventDTO.getDirector());
        event.setCasting(eventDTO.getCasting());
        event.setDescription(eventDTO.getDescription());
        return event;
    }

    public static EventDTO convertToDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setTitle(event.getTitle());
        eventDTO.setDirector(event.getDirector());
        eventDTO.setCasting(event.getCasting());
        eventDTO.setDescription(event.getDescription());
        return eventDTO;
    }
}
