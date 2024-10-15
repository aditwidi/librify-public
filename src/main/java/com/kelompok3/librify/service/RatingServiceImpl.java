package com.kelompok3.librify.service;

import com.kelompok3.librify.dto.RatingDto;
import com.kelompok3.librify.mapper.RatingMapper;
import com.kelompok3.librify.model.Rating;
import com.kelompok3.librify.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService{
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<RatingDto> getRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        List<RatingDto> ratingDtos = ratings.stream()
                .map((rating) -> (RatingMapper.mapToRatingDto(rating)))
                .collect(Collectors.toList());
        return ratingDtos;
    }

    @Override
    public float getAvgRating(){
        float star = 0;
        float count = 0;
        List<RatingDto> ratings = getRatings();
        for (RatingDto rating: ratings) {
            star += rating.getStar();
            count++;
        }
        return star/count;
    }

    @Override
    public RatingDto getRating(Long ratingId) {
        Optional<Rating> rating = ratingRepository.findById(ratingId);
        if (rating.isPresent()) {
            return RatingMapper.mapToRatingDto(rating.get());
        } else {
            return null;
        }
    }
    @Override
    public void updateRating(RatingDto ratingDto) {
        Rating rating = RatingMapper.mapToRating(ratingDto);
        ratingRepository.save(rating);
    }
    @Override
    public void deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    @Override
    public void saveRating(RatingDto ratingDto) {
        Rating rating = RatingMapper.mapToRating(ratingDto);
        ratingRepository.save(rating);
    }
}
