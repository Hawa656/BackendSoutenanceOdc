package SoutenanceBackend.soutenance.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Lob
    private String etape1;
    //@Size(max = 9000)
    @Lob
    private String etape2;
    //@Size(max = 9000)
    @Lob
    private String etape3;
    @Lob
    private String etape4;
    private  String etatDeLaTerre;
    private  String espacementEntreGraine;
//    @Size(max = 9000)
//    private String description;
    //sous forme select



//    private Boolean semis;
//    private  Boolean bouture;


}
