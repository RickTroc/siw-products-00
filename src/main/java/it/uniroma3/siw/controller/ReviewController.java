package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ProdottoRepository;
import it.uniroma3.siw.repository.ReviewRepository;
import it.uniroma3.siw.repository.UsersRepository;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.ReviewService;

@Controller
public class ReviewController {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CredentialsService credentialsService;

    @Transactional
    @GetMapping(value = "/review")
    public String getReview(Model model) {
        model.addAttribute("prodottos", this.prodottoRepository.findAll());
        model.addAttribute("reviews", this.reviewRepository.findAll());

        return "prodotto.html";
    }

    @Transactional
    @PostMapping(value = "/review")
    public String newReview(@RequestParam("prodotto") Long prodId, @RequestParam("score") int score,
            @RequestParam("title") String title,
            @RequestParam("comment") String comment, Model model) {

        Prodotto prodotto = this.prodottoRepository.findById(prodId).get();
        User user = this.credentialsService.getLoggedUser();

        if (!user.getReviews().containsKey(prodotto)) {
            Review review = new Review();
            review.setProdotto(prodotto);
            review.setScore(score);
            review.setTitle(title);
            review.setComment(comment);
            review.setUser(user);
            this.reviewRepository.save(review);

            user.getReviews().put(prodotto, review);
            prodotto.getReviews().add(review);

            this.usersRepository.save(user);
            this.prodottoRepository.save(prodotto);
        }

        model.addAttribute("userReview", user.getReviews().get(prodotto));
        model.addAttribute("prodotto", prodotto);
        model.addAttribute("prodottoReviews",
                this.reviewRepository.findProdottoReviewsWithoutUser(prodId, user.getId()));
        return "prodotto.html";
    }

    @Transactional
    @GetMapping(value = "updateReview/{reviewId}")
    public String updateReview(@PathVariable("reviewId") Long reviewId, Model model) {
        User user = this.credentialsService.getLoggedUser();
        if (this.reviewService.checkUserReview(user, reviewId)) {
            Review review = this.reviewService.findReviewById(reviewId);
            model.addAttribute("prodotto", review.getProdotto());
            model.addAttribute("userReview", review);
            return "formUpdateReview.html";
        }
        return "failedUpdateReview.html";
    }

    @Transactional
    @PostMapping(value = "updateReview")
    public String updateReview(@RequestParam("review") Long reviewId, @RequestParam("score")int score, 
                                @RequestParam("title")String title, @RequestParam("comment")String comment, Model model){

        Review review = this.reviewService.findReviewById(reviewId);
        review.setComment(comment);
        review.setScore(score);
        review.setTitle(title);
        this.reviewService.save(review);
        
        User user = this.credentialsService.getLoggedUser();
        Prodotto prodotto = review.getProdotto();
        model.addAttribute("userReview", user.getReviews().get(prodotto));
        model.addAttribute("prodotto", prodotto);
        model.addAttribute("prodottoReviews", this.reviewRepository.findProdottoReviewsWithoutUser(prodotto.getId(), user.getId()));
        return "prodotto.html";
    }

    @Transactional
    @GetMapping(value="deleteReview/{reviewId}")
    public String removeReview(@PathVariable("reviewId") Long reviewId, Model model) {
        User user = this.credentialsService.getLoggedUser();
        Prodotto prodotto = this.reviewService.findReviewById(reviewId).getProdotto();
        
        if(this.reviewService.checkUserReview(user, reviewId)){
            this.reviewService.removeReview(reviewId);
            model.addAttribute("prodotto", prodotto);
            model.addAttribute("prodottoReviews", prodotto.getReviews());
            return "prodotto.html";
        }
        return "failedReviewDelete.html";
    }

    @Transactional
    @GetMapping(value = "admin/deleteReview/{reviewId}")
    public String adminRemoveReview(@PathVariable("reviewId") Long reviewId, Model model){
        Prodotto prodotto = this.reviewService.findReviewById(reviewId).getProdotto();
        this.reviewService.removeReview(reviewId);
        model.addAttribute("prodotto", prodotto);
        return "admin/formUpdateProdotto.html";
        
    }
}
