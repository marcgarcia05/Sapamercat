public class Producte {
    String nom;
    float preu;
    int codiBarres;
    int quantitat;

    public Producte(String nom, float preu, int codiBarres, int quantitat) {
        this.nom = nom;
        this.preu = preu;
        this.codiBarres = codiBarres;
        this.quantitat = quantitat;
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

    public int getQuantitat() {
        return quantitat;
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

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }
}
