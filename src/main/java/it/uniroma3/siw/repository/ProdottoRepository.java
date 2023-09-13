package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Prodotto;

public interface ProdottoRepository extends CrudRepository<Prodotto, Long>{
   // public List<Prodotto> findbyNome(String nome);
    public boolean existsByNomeAndDescrizione(String nome, String descrizione);

	public List<Prodotto> findByNome(String nome);

	public List<Prodotto> findByPrezzo(String prezzo);

	public List<Prodotto> findByDescrizione(String Descrizione);

    @Query(value="select * "
		+ "from prodotto p "
		+ "where p.id not in "
		+ "(select prodotti_id "
		+ "from fornitore_prodotti fp "
	    + "where fp.fornitori_id = :fornId)", nativeQuery=true)               
    public List<Prodotto> findProdottoNotInFornitore(@Param("fornId") Long id);
}
