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
    //@Size(max = 9000)
    //pour que ça prennent plusieurs chaine de caractere de long text
   /* @Lob
    private String etape1;

    @Lob
    private String etape2;

    @Lob
    private String etape3;
    @Lob
    private String etape4;*/
    //private  String etatDeLaTerre;
    private  String espacementEntreGraine;

    @OneToMany(mappedBy = "tutoriels", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Etape> etapes = new ArrayList<>();

    public void addEtape(Etape etape) {
        etapes.add(etape);
        etape.setTutoriels(this);
    }

    public void removeEtape(Etape etape) {
        etapes.remove(etape);
        etape.setTutoriels(null);
    }

}
