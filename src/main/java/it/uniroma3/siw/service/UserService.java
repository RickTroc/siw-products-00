package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UsersRepository;

@Service
public class UserService {
    
     @Autowired
    protected UsersRepository usersRepository;

    /**
     * This method retrieves a Users from the DB based on its ID.
     * @param id the id of the Users to retrieve from the DB
     * @return the retrieved Users, or null if no Users with the passed ID could be found in the DB
     */
    @Transactional
    public User getUsers(Long id) {
        Optional<User> result = this.usersRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a Users in the DB.
     * @param usersUsers the Users to save into the DB
     * @return the saved Users
     * @throws DataIntegrityViolationException if a Users with the same usersUsersname
     *                              as the passed Users already exists in the DB
     */
    @Transactional
    public User saveUsers(User usersUsers) {
        return this.usersRepository.save(usersUsers);
    }

    /**
     * This method retrieves all Userss from the DB.
     * @return a List with all the retrieved Userss
     */
    @Transactional
    public List<User> getAllUserss() {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = this.usersRepository.findAll();
        for(User usersUsers : iterable)
            result.add(usersUsers);
        return result;
    }
}
