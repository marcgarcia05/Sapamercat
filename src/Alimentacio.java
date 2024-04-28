import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Alimentacio extends Producte{
    LocalDate dataCaducitat;

    //Constructor
    public Alimentacio(String nom, float preu, int codiBarres, int quantitat, LocalDate dataCaducitat) throws Exception {
        super(nom, preu, codiBarres, quantitat);
        this.dataCaducitat = dataCaducitat;
    }

    //Getters
    public LocalDate getDataCaducitat() {
        return dataCaducitat;
    }

    @Override
    public float getPreu(){
        LocalDate dataActual = LocalDate.now();
        int diferencia = (int) ChronoUnit.DAYS.between(dataActual, dataCaducitat);
        return (float) (preu - preu * (1/ diferencia + 1) + (preu * 0.1));
    }

    //Setters
    public void setDataCaducitat(LocalDate dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

}