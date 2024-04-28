public class Electronica extends Producte {

    int diesGarantia;

    //Constructor
    public Electronica(String nom, float preu, int codiBarres, int quantitat, int diesGarantia) throws Exception {
        super(nom, preu, codiBarres, quantitat);
        this.diesGarantia = diesGarantia;
    }

    //Getters
    public int getDiesGarantia() {
        return diesGarantia;
    }

    @Override
    public float getPreu() {
        return (float) (preu + preu * (diesGarantia / 365) * 0.1);
    }

    //Setters
    public void setDiesGarantia(int diesGarantia) {
        this.diesGarantia = diesGarantia;
    }
}
