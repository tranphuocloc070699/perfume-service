package com.loctran.service.user;

import com.loctran.service.exception.custom.AlreadyExistException;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.media.Media;
import com.loctran.service.media.MediaRepository;
import com.loctran.service.user.dto.UserLoginDto;
import com.loctran.service.user.dto.UserRegisterDto;
import com.loctran.service.user.dto.UserUpdateDto;
import com.loctran.service.utils.MediaUtil;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final MediaRepository mediaRepository;
  private final PasswordEncoder passwordEncoder;

  public User register(UserRegisterDto dto) {
    if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
      throw new AlreadyExistException("Email này đã tồn tại, vui lòng chọn email khác");
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
      throw new AlreadyExistException("Tài khoản hoặc mật khẩu không chính xác");
    }
    return user;
  }

  public User update(String id, UserUpdateDto dto) {
    User user = userRepository.findById(Integer.parseInt(id))
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    if (!dto.getName().equals(user.getName())) {
      user.setName(dto.getName());
    }

    if (dto.getAvatar()!=null) {
      Media media = Media.builder()

          .build();
      if (user.getAvatar() == null) {
        media.setPath(dto.getAvatar());
      } else {
        media = user.getAvatar();
        media.setPath(dto.getAvatar());
      }
      mediaRepository.save(media);
      user.setAvatar(media);
      userRepository.save(user);
    }

    return userRepository.save(user);

  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
  }

}
