package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.User;

public interface UsersRepository extends CrudRepository<User, Long>{
    
}
