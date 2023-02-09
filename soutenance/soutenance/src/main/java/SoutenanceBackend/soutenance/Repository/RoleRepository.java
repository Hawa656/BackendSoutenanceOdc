package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    //Role findByNomRole(String NomRole);



    @Modifying
    @Transactional
    @Query(value = "INSERT INTO roles (name) VALUES('ROLE_ADMIN'), ('ROLE_USER')", nativeQuery = true)
    void createRole();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_roles (user_id,role_id) VALUES(1,1)", nativeQuery = true)
    void AddRoleToAdmin();



}
