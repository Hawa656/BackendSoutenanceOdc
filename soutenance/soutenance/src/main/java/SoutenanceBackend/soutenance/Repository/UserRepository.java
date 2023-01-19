package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByNumero(String numero);

    User findByEmail(String email);

    Optional<User> findByNumeroOrEmail(String numero, String email);

    Boolean existsByNumero(String numero);

    Boolean existsByEmail(String email);
}
