package br.com.kentec.social.service;

import br.com.kentec.social.DTO.UserDTO;
import br.com.kentec.social.domain.User;
import br.com.kentec.social.repository.UserRepositoryCustom;
import br.com.kentec.social.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


import java.util.List;
import java.util.Optional;

@Singleton
public class UserService {
    @Inject
    private UserRepository ur;

    @Inject
    private UserRepositoryCustom urc;
    public void createUser(User user){
        ur.persist(user);
    }

    public List<User> listarTodos(){
        return ur.listAll();
    }

    public PanacheQuery<User> listarUsers(){
        return ur.findAll();
    }

    public Optional<User> buscarUm(Long id){
        return ur.findByIdOptional(id);
    }

    public List<User> buscarTodosImp() {
        return urc.findAll();
    }

    public User delete(Long id){
        User user = ur.findById(id);
        if(user != null) {
            ur.delete(user);
            return user;
        }
        return null;
    }

    public UserDTO updateUser(UserDTO userDTO){
        Optional<User> user = ur.findByIdOptional(userDTO.getId());
        if(user.isPresent()){
            user.get().setNome(userDTO.getNome());
            user.get().setIdade(userDTO.getIdade());
            return userDTO;
        }
        return null;
    }
}
