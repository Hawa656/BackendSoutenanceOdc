package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Questions;
import SoutenanceBackend.soutenance.Models.TypeLegumeFruit;
import SoutenanceBackend.soutenance.Repository.TypeLegumeFruitRepository;
import SoutenanceBackend.soutenance.services.TypeLegumeFruitService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/TypeLegumeFruit")
public class TypeLegumeFruitController {
    private TypeLegumeFruitService typeLegumeFruitService;
    private TypeLegumeFruitRepository typeLegumeFruitRepository;

    public TypeLegumeFruitController(TypeLegumeFruitService typeLegumeFruitService, TypeLegumeFruitRepository typeLegumeFruitRepository) {
        this.typeLegumeFruitService = typeLegumeFruitService;
        this.typeLegumeFruitRepository = typeLegumeFruitRepository;
    }

    //°°°°°°°°°°°°°°°°°°°°°°AFFICHER TYPELEGUMEFRUIT°°°°°°°°°°°°°°°°°°°°°
    @GetMapping("/lireTypeLegumeFruit")
    public List<TypeLegumeFruit> read(){
        return typeLegumeFruitService.lire();

    }
    //°°°°°°°°°°°°°°°°°°°°°°SUPPRIMER TYPELEGUMEFRUIT°°°°°°°°°°°°°°°°°°°°°
    @DeleteMapping("/supprimerTypeLegumeFruit/{id_type}")
    public String delete(@PathVariable Long id_type){

        return typeLegumeFruitService.supprimer(id_type);
    }
    //°°°°°°°°°°°°°°°°°°°°°°MODIFIER TYPELEGUMEFRUIT°°°°°°°°°°°°°°°°°°°°°
    @PutMapping("modifierTypeLegumeFruit/{id_type}")
    public TypeLegumeFruit update(@PathVariable Long id_user, @RequestBody TypeLegumeFruit typeLegumeFruit){
        return typeLegumeFruitService.modifier(id_user,typeLegumeFruit);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/ajouterTypeLegumeFruit/{id_type}")
    public Object create(@PathVariable Long id_user,
                         @Param("type") String type) throws IOException {
        TypeLegumeFruit typeLegumeFruit1 = new TypeLegumeFruit();
        typeLegumeFruit1.setType(type);

        typeLegumeFruitService.creer(typeLegumeFruit1);
        return "typeLegumeFruit ajouter avec succès";
    }
}

