package com.kelompok3.librify.repository;

import com.kelompok3.librify.model.Book;
import com.kelompok3.librify.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
