import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tondeuse {
    private Integer x;
    private Integer y;
    private String p;

    public Tondeuse(Integer x, Integer y, String p) {
        this.x = x;
        this.y = y;
        this.p = p;
}

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        if (p.toUpperCase() == "N" || p.toUpperCase() == "S" || p.toUpperCase() == "W" || p.toUpperCase() == "E"){
            this.y = y;
        }
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    private Map<String, Runnable> mapDir = new HashMap<String, Runnable>(){{
        put("AN", () -> setY(getY() + 1));
        put("AE", () -> setX(getX() + 1));
        put("AS", () -> setY(getY() - 1));
        put("AW", () -> setX(getX() - 1));
        put("DN", () -> setP("E"));
        put("DE", () -> setP("S"));
        put("DS", () -> setP("W"));
        put("DW", () -> setP("N"));
        put("GN", () -> setP("W"));
        put("GE", () -> setP("N"));
        put("GS", () -> setP("E"));
        put("GW", () -> setP("S"));
    }};



    public void move(char sens){
                mapDir.getOrDefault(sens + "" + this.p, new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Veuillez saisir comme sens soit A, G ou D");
                    }
                }).run();
    }

    public void showFinalPosition(){
        if (Arrays.asList("N","E","S","W").contains(this.p.toUpperCase())){
            System.out.println("Position: ("+this.x+","+this.y+","+this.p+")");
        } else {
            System.out.println("Revoir La position de la tondeuse: ("+this.x+","+this.y+","+this.p+") qui n'appartient ni à N, S, W, E");
        }
    }

    public boolean Inside(Gazon g) {
        return (this.getX() >= 0 && this.getX() <= g.getAbsMax() && this.getY() >= 0 && this.getY() <= g.getOrdMax());
    }
}