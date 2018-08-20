package solshop.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solshop.user.model.UserDTO;
import solshop.user.model.UserEntity;
import solshop.user.model.UserMapper;
import solshop.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO findUserById(Long id) {
        return userMapper.toUserDTO(userRepository.findOne(id));
    }


    public void saveUser(UserDTO user) {

        UserEntity userEntity = new UserEntity(user.getName(), user.getMail(), user.getPassword(), "USER");
        userRepository.save(userEntity);
    }

    /*public UserDTO findUserByEmail(String email) {
        return userMapper.toUserDTO(userRepository.findOneByEmail(email);
    }*/

}
