package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    @Query(value = "select * from review as r where r.prodotto_id = :prodId and r.user_id <> :userId",nativeQuery = true)
    public Iterable<Review> findProdottoReviewsWithoutUser(@Param("prodId") Long prodId, @Param("userId") Long userId);
}
