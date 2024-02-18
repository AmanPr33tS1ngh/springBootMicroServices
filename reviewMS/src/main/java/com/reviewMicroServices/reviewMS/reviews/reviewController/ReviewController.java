package com.reviewMicroServices.reviewMS.reviews.reviewController;

import com.reviewMicroServices.reviewMS.reviews.model.Review;
import com.reviewMicroServices.reviewMS.reviews.reviewService.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        return new ResponseEntity<>(this.reviewService.getAllReviews(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        return new ResponseEntity<>(this.reviewService.createReview(review), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id){
        return new ResponseEntity<>(this.reviewService.getReviewById(id), HttpStatus.OK);
    }
}
