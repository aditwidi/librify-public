package com.kelompok3.librify.repository;

import com.kelompok3.librify.model.Book;
import com.kelompok3.librify.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByNameContaining(String searchTerm);
}
