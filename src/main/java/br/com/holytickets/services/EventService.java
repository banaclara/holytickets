package br.com.holytickets.services;

import br.com.holytickets.dto.EventDTO;
import br.com.holytickets.models.Event;
import br.com.holytickets.repositories.EventRepository;
import br.com.holytickets.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event register(EventDTO eventDTO) {
        Event event = Converter.convertDtoToEntity(eventDTO);
        return eventRepository.save(event);
    }

    public List<EventDTO> listAll() {
        return eventRepository.findAll()
                .stream()
                .map(Converter::convertToDTO)  // Converte cada Event para EventDTO
                .collect(Collectors.toList());
    }
    public Optional<EventDTO> findByID(UUID id) {
        return eventRepository.findById(id)
                .map(Converter::convertToDTO);
    }
    public List<EventDTO> findByTitle(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }


    public Optional<EventDTO> update(UUID id, EventDTO eventDTO) {
        return eventRepository.findById(id).map(event -> {
            event.setTitle(eventDTO.getTitle());
            event.setDirector(eventDTO.getDirector());
            event.setCasting(eventDTO.getCasting());
            event.setDescription(eventDTO.getDescription());

            Event updatedEvent = eventRepository.save(event);

            return Converter.convertToDTO(updatedEvent);
        });
    }
    public boolean deleteEvent(UUID id) {
        try {
            eventRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {

            return false;
        }
    }

}
