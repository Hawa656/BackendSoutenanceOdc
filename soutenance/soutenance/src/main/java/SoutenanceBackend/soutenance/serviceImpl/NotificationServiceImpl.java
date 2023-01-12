package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Notification;
import SoutenanceBackend.soutenance.Repository.NotificationRepository;
import SoutenanceBackend.soutenance.services.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification creer(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> lire() {

        return notificationRepository.findAll();
    }

    @Override
    public Notification modifier(Long id, Notification notification) {

        return notificationRepository.findById(id)
                .map(n ->{
                    n.setDate(notification.getDate());
                    n.setTitre(notification.getTitre());
                    n.setHeure(notification.getHeure());

                    return notificationRepository.save(n);
                } ).orElseThrow(() -> new RuntimeException("notification non trouvé"));


    }

    @Override
    public String supprimer(Long id) {

         notificationRepository.deleteById(id);
        return "notification supprimer";
    }
}
