package br.com.kentec.social.repository;

import br.com.kentec.social.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom extends JpaRepository<User, Long> {
}
