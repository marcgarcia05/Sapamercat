public class Electronica extends Producte {

    int diesGarantia;

    public Electronica(String nom, float preu, int codiBarres, int diesGarantia) throws Exception {
        super(nom, preu, codiBarres);
        this.diesGarantia = diesGarantia;
    }

    //Getters
    public int getDiesGarantia() {
        return diesGarantia;
    }

    @Override
    public float getPreu() {
        preu = (float) (preu + preu * (diesGarantia / 365) * 0.1);
        return preu;
    }

    //Setters
    public void setDiesGarantia(int diesGarantia) {
        this.diesGarantia = diesGarantia;
    }
}