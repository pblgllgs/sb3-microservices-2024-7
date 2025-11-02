package com.pblgllgs.review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> getAllReviewsByCompanyId(Long companyId);

    boolean createReview(Long companyId, Review review);

    Optional<Review> getReviewById(Long id);

    Review getReviewByReviewIdByCompanyId(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReview(Long companyId,Long reviewId);
}
