package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Tutoriels;
import SoutenanceBackend.soutenance.Models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Override
    boolean existsById(Long aLong);
}
