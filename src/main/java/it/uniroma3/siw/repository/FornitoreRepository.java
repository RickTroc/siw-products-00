package it.uniroma3.siw.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;

public interface FornitoreRepository extends CrudRepository<Fornitore, Long>
{
   // public List<Fornitore> findByNome(String nome); 

   public boolean existsByNomeAndIndirizzo(String nome, String indirizzo);

     @Query(value="select * "
		+ "from fornitore f "
		+ "where f.id not in "
		+ "(select fornitori_id "
		+ "from fornitore_prodotti fp "
	    + "where fp.prodotti_id = :prodId)", nativeQuery=true)               
    public List<Fornitore> findFornitoreNotInProdotto(@Param("prodId") Long id);
    
}
