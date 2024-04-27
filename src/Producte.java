public class Producte {
    String nom;
    float preu;
    int codiBarres;

    public Producte(String nom, float preu, int codiBarres) {
        this.nom = nom;
        this.preu = preu;
        this.codiBarres = codiBarres;
    }

    //Getters
    public String getNom() {
        return nom;
    }

    public float getPreu() {
        return preu;
    }

    public int getCodiBarres() {
        return codiBarres;
    }

    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPreu(Float preu) {
        this.preu = preu;
    }

    public void setCodiBarres(int codiBarres) {
        this.codiBarres = codiBarres;
    }
}
