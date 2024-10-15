/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok3.librify.service;

import com.kelompok3.librify.dto.RatingDto;
import com.kelompok3.librify.dto.UserDto;
import com.kelompok3.librify.mapper.RatingMapper;
import com.kelompok3.librify.mapper.UserMapper;
import com.kelompok3.librify.model.Rating;
import com.kelompok3.librify.model.Role;
import com.kelompok3.librify.model.User;
import com.kelompok3.librify.repository.RoleRepository;
import com.kelompok3.librify.repository.UserRepository;
import com.kelompok3.librify.util.TbConstants;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.kelompok3.librify.mapper.UserMapper.mapToUserDto;

/**
 *
 * @author Kel 3
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Roles.ADMIN);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.ADMIN));

        User user;
        user = new User(
                userDto.getName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role)
        );
        userRepository.save(user);

    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map((user) -> (UserMapper.mapToUserDto(user)))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> getUsersUser() {
        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getRoles().stream()
                        .anyMatch(role -> role.getName().equals(TbConstants.Roles.USER)))
                .collect(Collectors.toList());
        List<UserDto> userDtos = users.stream()
                .map((user) -> (UserMapper.mapToUserDto(user)))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public int getJmlUser() {
        return getUsersUser().size();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public UserDto findUserDtoByEmail(String email) {
        return mapToUserDto(userRepository.findByEmail(email)) ;
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }
}
