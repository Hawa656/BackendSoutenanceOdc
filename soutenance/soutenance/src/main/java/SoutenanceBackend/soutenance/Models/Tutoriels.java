package SoutenanceBackend.soutenance.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
    @Getter
    @Entity
@Table(name = "tutoriels")
public class Tutoriels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private  String espacementEntreGraine;

    //pour que ça prennent plusieurs chaine de caractere de long text
   /* @Lob



    @ManyToOne
    @JoinColumn(name = "tutoriels_id")
    private LegumesFruits legumesFruits;


  /*    @OneToMany(mappedBy = "tutoriels", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Etape> etapes = new ArrayList<>();


    public void addEtape(Etape etape) {
        etapes.add(etape);
        etape.setTutoriels(this);
    }*/


    /* public void removeEtape(Etape etape) {
        etapes.remove(etape);
        etape.setTutoriels(null);
    }*/

}
