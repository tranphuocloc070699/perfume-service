package com.loctran.service.user;

import com.loctran.service.exception.custom.AlreadyExistException;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.user.dto.UserRegisterDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


  @Value("${application.security.jwt.access-token.name}")
  private String accessTokenName;

  @Value("${application.security.jwt.expiration}")
  private int expiration;
  private final UserRepository userRepository;

  public User login(String email, String password) {
    return null;
  }

  public User register(UserRegisterDto dto) {
    if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
      throw new AlreadyExistException("Email này đã tồn tại, vui lòng chọn email khác");
    }
    return userRepository.save(dto.mapToUser());
  }

  public User login(UserRegisterDto dto) {
    Optional<User> userOptional = userRepository.findByEmail(dto.getEmail());

    if (userOptional==null) {
      throw new ResourceNotFoundException("User", "email", dto.getEmail());
    }

    if(userOptional.get().getPassword().equals(dto.getPassword()))



    return userRepository.save(dto.mapToUser());
  }

}
