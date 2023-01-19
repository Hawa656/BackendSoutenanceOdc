package SoutenanceBackend.soutenance.Repository;


import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.MonJardin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonJardinRepository extends JpaRepository<MonJardin, Long> {
    MonJardin findByNom(String nom);
}
