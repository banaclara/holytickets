package br.com.holytickets.services;

import br.com.holytickets.dto.UserDTO;
import br.com.holytickets.exception.ResourceNotFoundException;
import br.com.holytickets.models.User;
import br.com.holytickets.repositories.UserRepository;
import br.com.holytickets.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Converter converter;

    public UserDTO create(UserDTO dto) {
        User user = converter.convertToEntity(dto);
        return converter.convertToDTO(userRepository.save(user));
    }

    public List<UserDTO> list() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found.");
        }
        return users.stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));
        return converter.convertToDTO(user);
    }

    public UserDTO update(UUID id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        User updatedUser = userRepository.save(user);

        return converter.convertToDTO(updatedUser);
    }

    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));

        userRepository.delete(user);
    }
}
