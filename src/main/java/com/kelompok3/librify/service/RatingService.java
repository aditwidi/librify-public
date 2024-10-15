package com.kelompok3.librify.service;

import com.kelompok3.librify.dto.RatingDto;

import java.util.List;

public interface RatingService {
    public List<RatingDto> getRatings();
    public RatingDto getRating(Long ratingId);
    public void updateRating(RatingDto ratingDto);
    public void deleteRating(Long ratingId);
    public void saveRating(RatingDto ratingDto);
    public float getAvgRating();

}
