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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final Converter converter;
    private final EventService eventService;

    public ScheduleDTO register(ScheduleDTO scheduleDTO) {
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

        schedule.getSeats().size();

        return converter.convertToDTO(schedule);
    }

    public List<ScheduleDTO> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(UUID id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule with ID " + id + " not found."));
        scheduleRepository.delete(schedule);
    }

    public ScheduleDTO update(UUID id, ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule with ID " + id + " not found."));

        schedule.setExhibitionDate(scheduleDTO.getExhibitionDate());
        if (scheduleDTO.getEventId() != null) {
            EventDTO eventDTO = eventService.findByID(scheduleDTO.getEventId());
            schedule.setEvent(converter.convertToEntity(eventDTO));
        }

        schedule = scheduleRepository.save(schedule);
        return converter.convertToDTO(schedule);
    }

}