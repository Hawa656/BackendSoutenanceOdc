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
    public String create(@Valid @RequestParam(value = "etape") String etape,
                         @Valid @Param(value="file") MultipartFile file, @PathVariable("idTuto") Tutoriels idTuto) throws JsonProcessingException {
       String img= "img";


        String nomfile = StringUtils.cleanPath(file.getOriginalFilename());
        Etape etape2= new JsonMapper().readValue(etape,Etape.class);
        etape2.setPhoto(Image.save(file, nomfile));

        etape2.setTutoriels(idTuto);
        etapeService.creer(etape2);
        return " ajouter avec succès";

    }
    //====================Liste des etapes d'un tutoriel=============

    @GetMapping("/etapesParTuto")
    public List<Etape> getEtapesByTutoriels() {
        return etapeRepository.findByTutorielsIsNotNull();
    }

}
