package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Conseils;
import SoutenanceBackend.soutenance.Models.Etape;
import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.Tutoriels;
import SoutenanceBackend.soutenance.Repository.EtapeRepository;
import SoutenanceBackend.soutenance.Repository.LegumesFruitsRepository;
import SoutenanceBackend.soutenance.images.Image;
import SoutenanceBackend.soutenance.services.EtapeService;
import SoutenanceBackend.soutenance.services.TutorielsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/etape")
@CrossOrigin(origins = "http://localhost:8100")
public class EtapeController {
    private EtapeRepository etapeRepository;
    private EtapeService etapeService;
    private TutorielsService tutorielsService;
    private final LegumesFruitsRepository legumesFruitsRepository;

    public EtapeController(EtapeRepository etapeRepository, EtapeService etapeService, TutorielsService tutorielsService,
                           LegumesFruitsRepository legumesFruitsRepository) {
        this.etapeRepository = etapeRepository;
        this.etapeService = etapeService;
        this.tutorielsService = tutorielsService;
        this.legumesFruitsRepository = legumesFruitsRepository;
    }



    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UNE ETAPE°°°°°°°°°°°°°°°°°°°°°
    @PostMapping("/ajouterEtape/{idTuto}")
    public String create(@Param("etape") String etape,
                         @Param(value = "titre") String titre,
                         @Param(value="file") MultipartFile file,
                         @PathVariable("idTuto") Tutoriels idTuto) throws JsonProcessingException {

        if (titre == null) {
            return "Le titre est manquant";
        }


        String img= "img";


        //String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        Etape etape2= new Etape();
        etape2.setPhoto(Image.save(file, nomfile));
        etape2.setEtape(etape);
        etape2.setTitre(titre); // Ajouter le titre à l'objet Etape

        etape2.setTutoriels(idTuto);
        etapeService.creer(etape2);
        return "Etape ajouter avec succès";

    }
    //====================Liste des etapes d'un tutoriel=============

    @GetMapping("/etapesParTuto/{tutoriels}")
    public List<Etape> getEtapesByTutoriels(@PathVariable("tutoriels") Tutoriels tutoriels) {
        return etapeRepository.findByTutoriels(tutoriels);
    }

}
