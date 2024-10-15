package com.kelompok3.librify.repository;

import com.kelompok3.librify.model.Book;
import com.kelompok3.librify.model.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {
}
