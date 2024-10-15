package com.kelompok3.librify.repository;

import com.kelompok3.librify.model.Keuangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeuanganRepository extends JpaRepository <Keuangan, Long>{
}
