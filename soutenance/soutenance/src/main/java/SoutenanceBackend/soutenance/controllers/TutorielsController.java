package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.LegumesFruits;
import SoutenanceBackend.soutenance.Models.Tutoriels;
import SoutenanceBackend.soutenance.Repository.LegumesFruitsRepository;
import SoutenanceBackend.soutenance.Repository.TutorielsRepository;
import SoutenanceBackend.soutenance.services.LegumesFruitsService;
import SoutenanceBackend.soutenance.services.TutorielsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tuto")
public class TutorielsController {
    private TutorielsService tutorielsService;
    private TutorielsRepository tutorielsRepository;
    private LegumesFruitsService legumesFruitsService;
    private LegumesFruitsRepository legumesFruitsRepository;

    public TutorielsController(TutorielsService tutorielsService, TutorielsRepository tutorielsRepository, LegumesFruitsService legumesFruitsService, LegumesFruitsRepository legumesFruitsRepository) {
        this.tutorielsService = tutorielsService;
        this.tutorielsRepository = tutorielsRepository;
        this.legumesFruitsService = legumesFruitsService;
        this.legumesFruitsRepository = legumesFruitsRepository;
    }
    //°°°°°°°°°°°°°°°°°°°°°°RECUPERER UN TUTORIEL PAR SON ID°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("RecupererIdTutoriel/{idTuto}")
    public Tutoriels RecupererId(@PathVariable("idTuto") Long idTuto){
        return tutorielsService.RecupereIdTuto(idTuto);
    }
    //°°°°°°°°°°°°°°°°°°°°°°AJOUTER UN TUTORIEL°°°°°°°°°°°°°°°°°°°°°
    @PostMapping("/AjouterTutoriel")
    public String create(@RequestBody Tutoriels tutoriels){
        tutoriels.setTitre(tutoriels.getTitre());
        tutoriels.setEspacementEntreGraine(tutoriels.getEspacementEntreGraine());
        tutorielsService.creer(tutoriels);
            return "tutoriel ajouter avec succès";

        }

    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER UN TUTORIEL°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierTutoriel/{id}")
    public Tutoriels update(@PathVariable Long id, @RequestBody Tutoriels tutoriels){
        return tutorielsService.modifier(id, tutoriels);
    }



    //°°°°°°°°°°°°°°°°°°°°°°LIRE UN TUTORIEL°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireTutoriel")
    public List<Tutoriels> read(){
        return tutorielsService.lire();

    }

    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER UN TUTORIEL°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerTutoriel")
    public String delete(@PathVariable Long id){
        return tutorielsService.supprimer(id);
    }
}
