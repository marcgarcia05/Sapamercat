public class Textil extends Producte{

    String composocioTextil;

    //Constructor
    public Textil(String nom, float preu, int codiBarres, int quantitat, String composocioTextil) {
        super(nom, preu, codiBarres, quantitat);
        this.composocioTextil = composocioTextil;
    }

    //Getters
    public String getComposocioTextil() {
        return composocioTextil;
    }

    //Setters
    public void setComposocioTextil(String composocioTextil) {
        this.composocioTextil = composocioTextil;
    }
}
