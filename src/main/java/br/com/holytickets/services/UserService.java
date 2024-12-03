package br.com.holytickets.services;

import br.com.holytickets.dto.UserDTO;
import br.com.holytickets.models.User;
import br.com.holytickets.repositories.UserRepository;
import br.com.holytickets.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Converter converter;
    private final PasswordEncoder passwordEncoder;

    public UserDTO create(UserDTO dto) {
        User user = converter.convertToEntity(dto);
        return converter.convertToDTO(userRepository.save(user));
    }
}
