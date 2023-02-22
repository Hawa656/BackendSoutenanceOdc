package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Etape;
import SoutenanceBackend.soutenance.Models.Tutoriels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtapeRepository extends JpaRepository<Etape, Long> {
    List<Etape> findByTutoriels(Tutoriels tutoriels);
}

