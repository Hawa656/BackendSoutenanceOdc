package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Notifications;
import SoutenanceBackend.soutenance.Repository.NotificationsRepository;
import SoutenanceBackend.soutenance.services.NotificationsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsServiceImpl implements NotificationsService {
    private final NotificationsRepository notificationsRepository;

    public NotificationsServiceImpl(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    @Override
    public List<Notifications> lire() {
        return notificationsRepository.findAll();
    }

    @Override
    public List<Notifications> lireNotifUserConecter(Long idNot) {
        System.out.println("--------------------"+ idNot);
        return notificationsRepository.ListeNotifications(idNot);
    }
}
