package com.pblgllgs.review;
/*
 *
 * @author pblgl
 * Created on 23-10-2024
 *
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviewByCompany(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviewsByCompanyId(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean success = reviewService.createReview(companyId, review);
        if (success) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review cant be created because company dont exists", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewByIdAndCompanyId(@PathVariable Long companyId,@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewByReviewIdByCompanyId(companyId,reviewId),HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> getReviewByIdAndCompanyId(
            @PathVariable Long companyId,
            @PathVariable Long reviewId,
            @RequestBody Review review
    ) {
        boolean success = reviewService.updateReview(companyId, reviewId, review);
        if (success) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review cant be updated because company dont exists", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId) {
        boolean success = reviewService.deleteReview(companyId,reviewId);
        if (success) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review cant be deleted", HttpStatus.BAD_REQUEST);
    }
}
