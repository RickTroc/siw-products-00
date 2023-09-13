package it.uniroma3.siw.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Prodotto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

  
    private String nome;
    
    private String prezzo;

   // private Image immagine;
/*
 *  public Image getImmagine() {
        return immagine;
    }

    public void setImmagine(Image immagine) {
        this.immagine = immagine;
    }

 */
   
    private String descrizione;

    @ManyToMany(mappedBy = "prodotti")
    private List<Fornitore> fornitori;


    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.REMOVE)
    private List<Review> reviews;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((prezzo == null) ? 0 : prezzo.hashCode());
        result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
        result = prime * result + ((fornitori == null) ? 0 : fornitori.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Prodotto other = (Prodotto) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (prezzo == null) {
            if (other.prezzo != null)
                return false;
        } else if (!prezzo.equals(other.prezzo))
            return false;
        if (descrizione == null) {
            if (other.descrizione != null)
                return false;
        } else if (!descrizione.equals(other.descrizione))
            return false;
        if (fornitori == null) {
            if (other.fornitori != null)
                return false;
        } else if (!fornitori.equals(other.fornitori))
            return false;
        return true;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

     public List<Fornitore> getFornitori() {
        return fornitori;
    }

    public void setFornitori(List<Fornitore> fornitori) {
        this.fornitori = fornitori;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    
    

}
