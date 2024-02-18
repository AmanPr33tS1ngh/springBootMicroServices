package com.reviewMicroServices.reviewMS.reviews.reviewService;

import com.reviewMicroServices.reviewMS.reviews.model.Review;
import com.reviewMicroServices.reviewMS.reviews.reviewRepo.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImp implements ReviewService{
    private ReviewRepository reviewRepository;
    public ReviewServiceImp(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    @Override
    public Review createReview(Review review) {
        this.reviewRepository.save(review);
        return review;
    }

    @Override
    public Review getReviewById(Long id) {
        return this.reviewRepository.findById(id).stream().findFirst().orElse(null);
    }
}
