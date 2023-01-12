package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity est une annotation qui indique que la classe correspond à une table de la base de données.
public class Questions {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;


    @ManyToOne
    private User user;



}

