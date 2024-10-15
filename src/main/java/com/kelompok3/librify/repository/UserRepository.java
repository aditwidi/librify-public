/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kelompok3.librify.repository;

import com.kelompok3.librify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kel 3
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);

    User findByName(String name);
}
