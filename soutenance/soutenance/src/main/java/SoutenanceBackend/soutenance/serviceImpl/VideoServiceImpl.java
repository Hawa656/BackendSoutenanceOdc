package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Video;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.Repository.VideoRepository;
import SoutenanceBackend.soutenance.services.VideoService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VideoServiceImpl implements VideoService {
    private VideoRepository videoRepository;
    //private VideoService videoService;
    private UserRepository userRepository;

    public VideoServiceImpl(VideoRepository videoRepository, UserRepository userRepository) {
        this.videoRepository = videoRepository;
        //this.videoService = videoService;
        this.userRepository = userRepository;
    }

    @Override
    public Video creer(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public List<Video> lire() {

        return videoRepository.findAll();
    }

    @Override
    public Video modifier(Long id, Video video) {

        return videoRepository.findById(id)
                .map(v->{
                    v.setTitre(video.getTitre());
                    v.setVideo(video.getVideo());


                    return videoRepository.save(v);
                }).orElseThrow(() -> new RuntimeException("question non trouve"));
    };


    @Override
    public String supprimer(Long id) {

        videoRepository.deleteById(id);
        return "video effacer avec succès";
    }
}
