package solshop.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import solshop.shopCart.repository.ShopCartRepository;
import solshop.user.UserNotFoundException;
import solshop.user.model.UserDTO;
import solshop.user.model.UserEntity;
import solshop.user.model.UserMapper;
import solshop.user.repository.UserRepository;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ShopCartRepository shopCartRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, ShopCartRepository shopCartRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.shopCartRepository = shopCartRepository;
    }

    public UserDTO findUserById(Long id) {
        return userMapper.toUserDTO(userRepository.findOne(id));
    }

    public Set<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDTO)
                .collect(toSet());
    }
    public void saveUser(UserDTO user) {
        if (!userRepository.findOneByEmail(user.getMail()).isPresent()) {
            if (user.getPassword().equals(user.getConfirmPassword())) {
                UserEntity userEntity = new UserEntity(user.getMail(), passwordEncoder.encode(user.getPassword()), "ROLE_USER", true);
                userRepository.save(userEntity);
            }
        }

    }
    public void saveAdmin(UserDTO user) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            UserEntity userEntity = new UserEntity(user.getMail(), passwordEncoder.encode(user.getPassword()), "ROLE_ADMIN", true);
            userRepository.save(userEntity);
        }
    }
    public UserDTO findUserByEmail(String email) {
        return userMapper.toUserDTO(userRepository.findOneByEmail(email).orElseThrow(UserNotFoundException::new));
    }
}
