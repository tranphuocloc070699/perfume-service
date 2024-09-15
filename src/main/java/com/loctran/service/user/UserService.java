package com.loctran.service.user;

import com.loctran.service.exception.custom.AlreadyExistException;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.user.dto.UserLoginDto;
import com.loctran.service.user.dto.UserRegisterDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;


  public User register(UserRegisterDto dto) {
    if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
      throw new AlreadyExistException("Email này đã tồn tại, vui lòng chọn email khác");
    }
    return userRepository.save(dto.mapToUser());
  }

  public User login(UserLoginDto dto) {
    Optional<User> userOptional = userRepository.findByEmail(dto.getEmail());

    if (userOptional.isEmpty()) {
      throw new ResourceNotFoundException("User", "email", dto.getEmail());
    }

    User user = userOptional.get();
    if(!user.getPassword().equals(dto.getPassword())){
      throw new AlreadyExistException("Tài khoản hoặc mật khẩu không chính xác");
    }
    return user;
  }

  public User findByEmail(String email) {

    return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
  }

}
