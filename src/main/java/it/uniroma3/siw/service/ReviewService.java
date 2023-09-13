package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ProdottoRepository;
import it.uniroma3.siw.repository.ReviewRepository;
import it.uniroma3.siw.repository.UsersRepository;
import jakarta.transaction.Transactional;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;

	@Autowired 
	private ProdottoRepository prodottoRepository;

	@Autowired
	private UsersRepository usersRepository;

    @Transactional
    public Iterable<Review> findProdottoReviewsWithoutUser(Long prodId, Long userId){
        return this.reviewRepository.findProdottoReviewsWithoutUser(prodId, userId);
    }

    @Transactional
    public Review findReviewById(Long id){
        return this.reviewRepository.findById(id).get();
    }

    public boolean checkUserReview(User user, Long reviewId){
		Review review  = this.findReviewById(reviewId);
		if(user.equals(review.getUser()))
			return true;
		return false;
	}

    @Transactional
	public void removeReview(Long reviewId){
		Review review = this.findReviewById(reviewId);
		Prodotto prodotto = review.getProdotto();
		User user = review.getUser();
		prodotto.getReviews().remove(review);
		user.getReviews().remove(prodotto);
		this.reviewRepository.deleteById(reviewId);
		this.prodottoRepository.save(prodotto);
		this.usersRepository.save(user);
	}

	public void save(Review review){
		this.reviewRepository.save(review);
	}


}
