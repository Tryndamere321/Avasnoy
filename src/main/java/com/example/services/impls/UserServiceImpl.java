package com.example.services.impls;

import com.example.dtos.UserDtos.UserRegisterDto;
import com.example.models.UserEntity;
import com.example.repostories.UserRepository;
import com.example.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        UserEntity user = userRepository.findByEmail(userRegisterDto.getEmail());
        if (user != null) {
            throw new UsernameNotFoundException("istifadeci tapilmadi");
        }
        UserEntity newUser = modelMapper.map(userRegisterDto, UserEntity.class);
        newUser.setPassword(encoder.encode(userRegisterDto.getPassword()));
        userRepository.save(newUser);
    }
    @Override
    public UserEntity findByEmail(String email) {
        UserEntity user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public UserEntity findByUserId(Long id) {
        return userRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public boolean confirmEmail(String email, String token) {

//        UserEntity findUser = userRepository.findByEmail(email);
//        if (findUser.getConfirmationToken().equals(token) && findUser != null)
//        {
//            findUser.setEmailConfirmed(true);
//            userRepository.saveAndFlush(findUser);
//            return true;
//        }
        return false;
    }

}
