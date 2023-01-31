package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByNumero(String numero);

    User findByEmail(String email);

    Optional<User> findByNumeroOrEmail(String numero, String email);

    Boolean existsByNumero(String numero);

    Boolean existsByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `users` (`id`, `confirm_notification`, `email`, `nom`, `numero`, `password`, `prenom`) VALUES (NULL, b'0000', 'hawacoulibaly656@gmail.com', 'Coulibaly', '83014698', '12345678', 'Hawa');", nativeQuery = true)
    void createAdminParDefaut();
}
