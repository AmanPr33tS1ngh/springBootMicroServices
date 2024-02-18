package com.reviewMicroServices.reviewMS.reviews.reviewService;


import com.reviewMicroServices.reviewMS.reviews.model.Review;

import java.util.List;

public interface ReviewService {
    public List<Review> getAllReviews();

    public Review createReview(Review review);

    public Review getReviewById(Long companyId);
}
