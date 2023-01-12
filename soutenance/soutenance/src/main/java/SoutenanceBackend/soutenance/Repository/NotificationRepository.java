package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
