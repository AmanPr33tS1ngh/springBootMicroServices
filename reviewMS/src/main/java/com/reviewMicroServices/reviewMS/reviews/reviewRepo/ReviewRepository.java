package com.reviewMicroServices.reviewMS.reviews.reviewRepo;

import com.reviewMicroServices.reviewMS.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long id);
}
