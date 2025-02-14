package com.loctran.service.entity.user;

import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaService;
import com.loctran.service.entity.user.dto.UserLoginDto;
import com.loctran.service.entity.user.dto.UserRegisterDto;
import com.loctran.service.entity.user.dto.UserUpdateDto;
import com.loctran.service.exception.custom.BadRequestException;
import com.loctran.service.exception.custom.ResourceNotFoundException;
import com.loctran.service.utils.MessageUtil.ResponseMessage;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final MediaService mediaService;

  public User register(UserRegisterDto dto) {
    if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
      throw new BadRequestException(ResponseMessage.USER_EMAIL_EXISTED);
    }
    String encodedPassword = passwordEncoder.encode(dto.getPassword());
    User user = dto.mapToUser();
    user.setPassword(encodedPassword);
    return userRepository.save(user);
  }

  public User login(UserLoginDto dto) {
    Optional<User> userOptional = userRepository.findByEmail(dto.getEmail());

    if (userOptional.isEmpty()) {
      throw new ResourceNotFoundException(ResponseMessage.USER_LOGIN_FAILED);
    }

    User user = userOptional.get();
    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
      throw new BadRequestException(ResponseMessage.USER_LOGIN_FAILED);
    }
    return user;
  }
  public User update(Long id, UserUpdateDto dto) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ResponseMessage.USER_NOT_FOUND));
    if (!dto.getName().equals(user.getName())) {
      user.setName(dto.getName());
    }
    if(!Objects.equals(user.getAvatar().getId(), dto.getAvatarId())){
      Media media = mediaService.findById(dto.getAvatarId());
      user.setAvatar(media);
    }
    return userRepository.save(user);

  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
  }

}
