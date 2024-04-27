import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Sapamercat {
    public static HashMap<String, Producte> Productes = new HashMap<String, Producte>();

    //Mostrem el menu principal
    public static void menu() throws Exception {
        Scanner i = new Scanner(System.in);
        System.out.println("-----------\n-- INICI --\n-----------");
        System.out.println("\n1) Introduir producte\n2) Passar per caixa\n3) Mostrar carret de compra\n0) Acabar");
        int seleccionat = i.nextInt();
        if (seleccionat == 1){
            menuProductes();
        } else if (seleccionat == 2){
            pasarCaixa();
        } else if (seleccionat == 3){
            mostrarCarret();
        }
    }

    //Mostrem el menu de productes
    public static void menuProductes() throws Exception {
        Scanner i = new Scanner(System.in);
        System.out.println("\n--------------\n-- PRODUCTE --\n--------------");
        System.out.println("\n1) Alimentació\n2) Tèxtil\n3) Electrònica\n0) Tornar");
        int seleccionat = i.nextInt();
        if (seleccionat == 1){
            afegirAlimentacio();
        } else if (seleccionat == 2){
            afegirTextil();
        } else if (seleccionat == 3){
            afegirElectronica();
        } else {
            menu();
        }
    }

    //Menu per afegir productes d'alimentació
    public static void afegirAlimentacio() throws Exception {
        Scanner i = new Scanner(System.in);
        System.out.println("Afegir aliment");
        System.out.println("Nom producte: ");
        String nom = i.nextLine();
        System.out.println("Preu: ");
        float preu = i.nextFloat();
        System.out.println("Codi de barres: ");
        int codiBarres = i.nextInt();
        i = new Scanner(System.in);
        System.out.println("Data de caducitat (dd/mm/aaaa): ");
        String dataCaducitat = i.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataCaducitat, formatter);
        Productes.put(nom, new Alimentacio(nom, preu, codiBarres, data));
        menu();
    }
    //Menu per afegir productes textils
    public static void afegirTextil() throws Exception {
        Scanner i = new Scanner(System.in);
        System.out.println("Afegir tèxtil");
        System.out.println("Nom producte: ");
        String nom = i.nextLine();
        System.out.println("Preu: ");
        float preu = i.nextFloat();
        System.out.println("Composició: ");
        i = new Scanner(System.in);
        String composicio = i.nextLine();
        i = new Scanner(System.in);
        System.out.println("Codi de barres: ");
        int codiBarres = i.nextInt();
        Productes.put(nom, new Textil(nom, preu, codiBarres, composicio));
        menu();
    }
    //Menu per afegir productes d'electronica
    public static void afegirElectronica() throws Exception {
        Scanner i = new Scanner(System.in);
        System.out.println("Afegir Electronica");
        System.out.println("Nom producte: ");
        String nom = i.nextLine();
        System.out.println("Preu: ");
        float preu = i.nextFloat();
        System.out.println("Garantia (dies): ");
        int garantia = i.nextInt();
        System.out.println("Codi de barres: ");
        int codiBarres = i.nextInt();
        Productes.put(nom, new Electronica(nom, preu, codiBarres, garantia));
        menu();
    }

    public static void pasarCaixa() throws Exception {
        LocalDate dataActual = LocalDate.now();
        System.out.println("--------------\nSAPAMERCAT\n--------------");
        System.out.println("Data: " + dataActual + "\n--------------");
        Productes.forEach((k, v) -> System.out.println(k + " " + v.preu));
        System.out.println("--------------\nTotal: ");
        Productes.clear();
    }

    public static void mostrarCarret() throws Exception {
        System.out.println("Carret");
        Productes.forEach((k, v) -> System.out.println(k + " -> "));
        menu();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("BENVINGUT AL SAPAMERCAT");
        menu();
    }
}