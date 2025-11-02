package com.pblgllgs.review.impl;
/*
 *
 * @author pblgl
 * Created on 23-10-2024
 *
 */

import com.pblgllgs.company.Company;
import com.pblgllgs.company.CompanyService;
import com.pblgllgs.review.Review;
import com.pblgllgs.review.ReviewRepository;
import com.pblgllgs.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final CompanyService companyService;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(CompanyService companyService, ReviewRepository reviewRepository) {
        this.companyService = companyService;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviewsByCompanyId(Long companyId) {
        return reviewRepository.findAllByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Optional<Company> companyOptional = companyService.findCompanyById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Review getReviewByReviewIdByCompanyId(Long companyId, Long reviewId) {
        List<Review> allReviewsByCompanyId = reviewRepository.findAllByCompanyId(companyId);
        return allReviewsByCompanyId.stream().filter(review -> Objects.equals(review.getId(), reviewId)).findFirst().orElseThrow(() -> new RuntimeException("NOT_FOUND"));
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        if (companyService.findCompanyById(companyId) != null) {
            review.setCompany(companyService.findCompanyById(companyId).get());
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Optional<Company> company = companyService.findCompanyById(companyId);
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (company.isPresent() && review.isPresent()) {
            Company companyUpdated= company.get();
            Review reviewToDelete = review.get();
            companyUpdated.getReviews().remove(review);
            reviewToDelete.setCompany(null);
            companyService.updateCompany(companyId,companyUpdated);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
