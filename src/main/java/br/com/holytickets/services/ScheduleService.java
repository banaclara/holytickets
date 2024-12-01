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

    // Método para registrar um agendamento
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

    // Método para buscar um agendamento por ID
    public ScheduleDTO findById(UUID id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule with ID " + id + " not found."));

        // Inicializa a coleção seats explicitamente
        schedule.getSeats().size();  // Isso força o carregamento dos seats

        return converter.convertToDTO(schedule);
    }

    // Método para listar todos os agendamentos
    public List<ScheduleDTO> findAll() {
        // Obtém todos os agendamentos do banco de dados
        List<Schedule> schedules = scheduleRepository.findAll();

        // Converte cada agendamento para um DTO
        return schedules.stream()
                .map(converter::convertToDTO) // Usa o conversor para converter cada entidade em DTO
                .collect(Collectors.toList()); // Retorna a lista de DTOs
    }
    // Método para deletar um agendamento por ID
    public void deleteById(UUID id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule with ID " + id + " not found."));
        scheduleRepository.delete(schedule);
    }

    // Método para atualizar um agendamento
    public ScheduleDTO update(UUID id, ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule with ID " + id + " not found."));

        // Atualiza os campos do agendamento
        schedule.setExhibitionDate(scheduleDTO.getExhibitionDate());
        if (scheduleDTO.getEventId() != null) {
            EventDTO eventDTO = eventService.findByID(scheduleDTO.getEventId());
            schedule.setEvent(converter.convertToEntity(eventDTO));
        }

        schedule = scheduleRepository.save(schedule); // Salva a atualização no banco
        return converter.convertToDTO(schedule);      // Retorna o DTO atualizado
    }

}