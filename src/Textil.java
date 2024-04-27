public class Textil extends Producte{

    String composocioTextil;

    public Textil(String nom, float preu, int codiBarres, String composocioTextil) throws Exception {
        super(nom, preu, codiBarres);
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

