import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        Stream<String> stream = Files.lines(Paths.get("./resources/file.txt"));

        List<Gazon> gazonList = new ArrayList<Gazon>();
        List<Tondeuse> tondeuseList = new ArrayList<Tondeuse>();
        List<String> positionList = new ArrayList<>();

        // Initialisation des Gazon, Tondeuse, Position avec le contenu du fichier
        stream.forEach(m -> {
            if (m.split(" ").length == 2){ // Line mapping Gazon
                gazonList.add(new Gazon(Integer.parseInt(m.split(" ")[0]), Integer.parseInt(m.split(" ")[1])));
            }
            else if (m.split(" ").length == 3){ // Line mapping Gazon
                tondeuseList.add(new Tondeuse(Integer.parseInt(m.split(" ")[0]), Integer.parseInt(m.split(" ")[1]), m.split(" ")[2]));
            }
            else {
                positionList.add(m);
            }
        });

        // Appliquer les mouvements de chaque position sur sa tondeuse
        for (int i = 0; i < tondeuseList.size(); i++){
            if (Arrays.asList("N","S","W","E").contains(tondeuseList.get(i).getP().toUpperCase())){
                for (char position: positionList.get(i).toUpperCase().toCharArray()){
                    if (tondeuseList.get(i).Inside(gazonList.get(0))){
                        tondeuseList.get(i).move(position);
                    }
                }
            }
        }

        // Afficher les positions finales des tondeuses
        tondeuseList.forEach(m -> { m.showFinalPosition(); });

    }
}
