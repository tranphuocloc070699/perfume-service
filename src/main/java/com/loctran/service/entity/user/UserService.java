package com.loctran.service.entity.user;

import com.loctran.service.entity.user.dto.UserLoginDto;
import com.loctran.service.entity.user.dto.UserRegisterDto;
import com.loctran.service.entity.user.dto.UserUpdateDto;
import com.loctran.service.exception.custom.BadRequestException;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User register(UserRegisterDto dto) {
    if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
      throw new BadRequestException("Email này đã tồn tại, vui lòng chọn email khác");
    }
    String encodedPassword = passwordEncoder.encode(dto.getPassword());
    User user = dto.mapToUser();
    user.setPassword(encodedPassword);
    return userRepository.save(user);
  }

  public User login(UserLoginDto dto) {
    Optional<User> userOptional = userRepository.findByEmail(dto.getEmail());

    if (userOptional.isEmpty()) {
      throw new ResourceNotFoundException("User", "email", dto.getEmail());
    }

    User user = userOptional.get();
    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
      throw new BadRequestException("Tài khoản hoặc mật khẩu không chính xác");
    }
    return user;
  }
  public User update(Long id, UserUpdateDto dto) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", id.toString()));
    if (!dto.getName().equals(user.getName())) {
      user.setName(dto.getName());
    }
    user.setAvatar(dto.getAvatar());
    return userRepository.save(user);

  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
  }

}
