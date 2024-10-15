package com.kelompok3.librify.repository;

import com.kelompok3.librify.model.Book;
import com.kelompok3.librify.model.Pengembalian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PengembalianRepository extends JpaRepository<Pengembalian, Long> {
}
