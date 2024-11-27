package br.com.holytickets.services;

import br.com.holytickets.dto.EventDTO;
import br.com.holytickets.exception.BadRequestException;
import br.com.holytickets.exception.ConflictException;
import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.models.Establishment;
import br.com.holytickets.models.Event;
import br.com.holytickets.repositories.EstablishmentRepository;
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
    private final EstablishmentRepository establishmentRepository;
    private final Converter converter;

    public EventDTO register(EventDTO eventDTO) {
        if (eventRepository.existsByTitle(eventDTO.getTitle())) {
            throw new ConflictException("Já existe um evento com o título: " + eventDTO.getTitle());
        }

        if (eventDTO.getEstablishmentId() == null) {
            throw new BadRequestException("O ID do estabelecimento não foi fornecido.");
        }

        Establishment establishment = establishmentRepository.findById(eventDTO.getEstablishmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Estabelecimento com ID " + eventDTO.getEstablishmentId() + " não encontrado."));

        Event event = converter.convertToEntity(eventDTO);
        event.setEstablishment(establishment);

        event = eventRepository.save(event);

        return converter.convertToDTO(event);
    }

    public List<EventDTO> listAll() {
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum evento encontrado.");
        }
        return events.stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public EventDTO findByID(UUID id) {
        return eventRepository.findById(id)
                .map(converter::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Evento com ID " + id + " não encontrado."));
    }
    public List<EventDTO> findByTitle(String title) {
        List<Event> events = eventRepository.findByTitleContainingIgnoreCase(title);

        if (events.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum evento encontrado com o título: " + title);
        }
        return events.stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }


    public EventDTO update(UUID id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento com ID " + id + " não encontrado."));

        event.setTitle(eventDTO.getTitle());
        event.setDirector(eventDTO.getDirector());
        event.setCasting(eventDTO.getCasting());
        event.setDescription(eventDTO.getDescription());

        if (eventDTO.getEstablishmentId() != null) {
            Establishment establishment = establishmentRepository.findById(eventDTO.getEstablishmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Estabelecimento com ID " + eventDTO.getEstablishmentId() + " não encontrado."));
            event.setEstablishment(establishment);
        }

        Event updatedEvent = eventRepository.save(event);

        return converter.convertToDTO(updatedEvent);
    }

    public void deleteEvent(UUID id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento com ID " + id + " não encontrado."));
        eventRepository.delete(event);
    }


}
