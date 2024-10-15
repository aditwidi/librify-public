/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kelompok3.librify.service;

import com.kelompok3.librify.dto.UserDto;
import com.kelompok3.librify.model.User;

import java.util.List;

/**
 *
 * @author Kel 3
 */
public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    UserDto findUserDtoByEmail(String email);

    User findUserByName(String name);
    public List<UserDto> getUsers();
    public List<UserDto> getUsersUser();

    int getJmlUser();
}
