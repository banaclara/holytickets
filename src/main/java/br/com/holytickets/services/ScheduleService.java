package br.com.holytickets.services;

import br.com.holytickets.dto.EventDTO;
import br.com.holytickets.dto.ScheduleDTO;
import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.models.Event;
import br.com.holytickets.models.Schedule;
import br.com.holytickets.repositories.ScheduleRepository;
import br.com.holytickets.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final Converter converter;

    private final EventService eventService;

    public ScheduleDTO register(ScheduleDTO scheduleDTO) {
//checar se existe por event e por data
        if (scheduleDTO.getEventId() == null) {
            throw new ResourceNotFoundException("The event ID was not provided.");
        }

        EventDTO eventDTO = eventService.findByID(scheduleDTO.getEventId());

        Schedule schedule = converter.convertToEntity(scheduleDTO);
        schedule.setEvent(converter.convertToEntity(eventDTO));

        schedule = scheduleRepository.save(schedule);
        return converter.convertToDTO(schedule);

    }

    public ScheduleDTO findById(UUID id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule with ID " + id + " not found."));
        return converter.convertToDTO(schedule);
    }
}
