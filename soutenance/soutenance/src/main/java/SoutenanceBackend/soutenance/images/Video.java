package SoutenanceBackend.soutenance.images;/*
package SoutenanceBackend.soutenance.images;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;
public class Image {
    public static String localhost = "http://127.0.0.1/";

    //°°°°°°°°°°°°°°°°°°°°°° nom du dossier contenant l'image°°°°°°°°°°°°°°°°°°°
    public static String serveruser = localhost + "imageSoutenance/";

    //°°°°°°°°°°°°°°°°°°°°°°chemin de l'image°°°°°°°°°°°°°°°°°°°

    public static String Userlocation = "C:/xampp/htdocs/imageSoutenance/";

    public static String save(MultipartFile file, String nomFichier) {
        String src = "";
        String server = "";
        String location = "";

        location = Userlocation;
        server = serveruser;


        /// debut de l'enregistrement
        try {
            int index = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");

            Path chemin = Paths.get(location);
            if (!Files.exists(chemin)) {
                // si le fichier n'existe pas deja
                Files.createDirectories(chemin);
                Files.copy(file.getInputStream(), chemin
                        .resolve(nomFichier + file.getOriginalFilename()+file.getOriginalFilename().substring(index).toLowerCase()));
                src = server + nomFichier
                        + file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase();
            } else {
                // si le fichier existe pas deja
                String newPath = location + nomFichier +file.getOriginalFilename()
                        + file.getOriginalFilename().substring(index).toLowerCase();
                Path newchemin = Paths.get(newPath);
                if (!Files.exists(newchemin)) {
                    // si le fichier n'existe pas deja
                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier +file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase()));
                    src = server + nomFichier +file.getOriginalFilename()
                            + file.getOriginalFilename().substring(index).toLowerCase();
                } else {
                    // si le fichier existe pas deja on le suprime et le recrèe

                    Files.delete(newchemin);

                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier + file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase()));
                    src = server + nomFichier
                            +file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase();
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
            src = null;
        }

        return src;
    }

}
coupe
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.web.multipart.MultipartFile;

public class SaveImage {
    public static String localhost = "http://127.0.0.1/";
    public static String serveruser = localhost + "DesCiwara/Images/";
    public static String userLocation = "C:/xampp/htdocs/DesCiwara/Images/";

    public static String save(MultipartFile file, String fileName) {
        String src = "";
        String server = "";
        String location = "";

        location = userLocation;
        server = serveruser;

        try {
            Path filePath = Paths.get(location + fileName);

            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.copy(file.getInputStream(), filePath);
                src = server + fileName;
            } else {
                Files.delete(filePath);
                Files.copy(file.getInputStream(), filePath);
                src = server + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
            src = null;
        }

        return src;
    }
}
*/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


import org.springframework.web.multipart.MultipartFile;

public class Video {
    public static String localhost = "http://127.0.0.1/";
    public static String serveruser = localhost + "videoSoutenance/";
    public static String userLocation = "C:/xampp/htdocs/videoSoutenance/";

    public static String save(MultipartFile file, String fileName) {
        String src = "";
        String server = "";
        String location = "";

        location = userLocation;
        server = serveruser;

        try {
            Path filePath = Paths.get(location + fileName);

            if (!Files.exists(filePath)) {
                //Files.createDirectories(filePath.getParent());
                Random rd = new Random();
                Files.copy(file.getInputStream(), filePath);
                src = server + rd.nextInt(1000)+ fileName;
            } else {
                //Files.delete(filePath);
                Files.copy(file.getInputStream(), filePath);
                src = server + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
            src = null;
        }

        return src;
    }
}