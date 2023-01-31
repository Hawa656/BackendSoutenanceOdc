package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {
    Tache findByDate(Date date);
}
