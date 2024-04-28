import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Sapamercat {

    //Variable Final per limitar el carret a 100 productes
    public static final int LIMITPRODUCTES = 100;

    //Hasmap on guardarem els productes que el client anira afegint
    public static HashMap<Integer, Producte> Productes = new HashMap<Integer, Producte>();


    //Mostrem el menu principal
    public static void menu() throws Exception {
        Scanner i = new Scanner(System.in);
        System.out.println("-----------\n-- INICI --\n-----------");
        System.out.println("1) Introduir producte\n2) Passar per caixa\n3) Mostrar carret de compra\n0) Acabar");
        //Control d'errors
        try {
            int seleccionat = i.nextInt();
            if (seleccionat == 1){
                menuProductes();
            } else if (seleccionat == 2){
                pasarCaixa();
            } else if (seleccionat == 3){
                mostrarCarret();
            } else {System.exit(1);}
        } catch (Exception e){
            System.out.println("ERROR! - Has d'introduir un numero!");
        } finally {
            menu();
        }
    }

    //Mostrem el menu de productes
    public static void menuProductes() throws Exception {
        Scanner i = new Scanner(System.in);
        System.out.println("\n--------------\n-- PRODUCTE --\n--------------");
        System.out.println("1) Alimentació\n2) Tèxtil\n3) Electrònica\n0) Tornar");
        int seleccionat = i.nextInt();
        Scanner resposta = new Scanner(System.in);
        //Control d'errors
        try{
            if (seleccionat == 1){
                afegirAlimentacio(resposta);
            } else if (seleccionat == 2){
                afegirTextil(resposta);
            } else if (seleccionat == 3){
                afegirElectronica(resposta);
            } else {
                menu();
            }
        }   catch (Exception e){
            System.out.println("ERROR! - Has d'introduir un numero!");
        }   finally {
            menu();
        }
    }

    //Metode per comprobar si el producte que volem afegir ja l'hem introduit previament
    public static boolean comprovarProducte(int codiBarres){
        for (int k : Productes.keySet()){
            if (k == codiBarres) return false;
        }
        return true;
    }

    //Menu per afegir productes d'alimentació
    public static void afegirAlimentacio(Scanner resposta) throws Exception {
        //Control d'errors
        try {
            System.out.println("Afegir aliment");
            System.out.println("Nom producte: ");
            String nom = resposta.nextLine();
            System.out.println("Preu: ");
            float preu = resposta.nextFloat();
            System.out.println("Data de caducitat (dd/mm/aaaa): ");
            String dataCaducitat = resposta.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataCaducitat, formatter);
            System.out.println("Quantitat: ");
            int quantitat = resposta.nextInt();
            System.out.println("Codi de barres: ");
            int codiBarres = resposta.nextInt();
            if (comprovarProducte(codiBarres) && Productes.size() <= LIMITPRODUCTES) {
                Productes.put(codiBarres, new Alimentacio(nom, preu, codiBarres, quantitat, data));
            } else {
                System.out.println("ERROR! - Aquest producte ja esta afegit al carret!");
            }
        } catch (Exception e) {
            System.out.println("ERROR! - L'ultim parametre introduit es incorrecte!");
        } finally {
            menu();
        }
        menu();
    }
    //Menu per afegir productes textils
    public static void afegirTextil(Scanner resposta) throws Exception {
        //Control d'errors
        try {
            System.out.println("Afegir tèxtil");
            System.out.println("Nom producte: ");
            String nom = resposta.nextLine();
            System.out.println("Preu: ");
            float preu = resposta.nextFloat();
            System.out.println("Composició: ");
            String composicio = resposta.next();
            System.out.println("Quantitat: ");
            int quantitat = resposta.nextInt();
            System.out.println("Codi de barres: ");
            int codiBarres = resposta.nextInt();
            if (comprovarProducte(codiBarres) && Productes.size() <= LIMITPRODUCTES) {
                Productes.put(codiBarres, new Textil(nom, preu, codiBarres, quantitat, composicio));
            } else {
                System.out.println("ERROR! - Aquest producte ja esta afegit al carret!");
            }
        } catch (Exception e) {
            System.out.println("ERROR! - L'ultim parametre introduit es incorrecte!");
        } finally {
            menu();
        }
        menu();
    }
    //Menu per afegir productes d'electronica
    public static void afegirElectronica(Scanner resposta) throws Exception {
        //Control d'errors
        try {
            System.out.println("Afegir Electronica");
            System.out.println("Nom producte: ");
            String nom = resposta.nextLine();
            System.out.println("Preu: ");
            float preu = resposta.nextFloat();
            System.out.println("Garantia (dies): ");
            int garantia = resposta.nextInt();
            System.out.println("Quantitat: ");
            int quantitat = resposta.nextInt();
            System.out.println("Codi de barres: ");
            int codiBarres = resposta.nextInt();
            if (comprovarProducte(codiBarres) && Productes.size() <= LIMITPRODUCTES) {
                Productes.put(codiBarres, new Electronica(nom, preu, codiBarres, quantitat, garantia));
            } else {
                System.out.println("ERROR! - Aquest producte ja esta afegit al carret!");
            }
        } catch (Exception e) {
            System.out.println("ERROR! - L'ultim parametre introduit es incorrecte!");
        } finally {
            menu();
        }
        menu();
    }

    //Metode que imprimeix tiquet
    public static void pasarCaixa() throws Exception {
        LocalDate dataActual = LocalDate.now();
        System.out.println("--------------\nSAPAMERCAT\n--------------");
        System.out.println("Data: " + dataActual + "\n--------------");
        float total = 0;
        for (Producte v : Productes.values()){
            System.out.println(v.getNom() + "   " + v.getQuantitat() + "    " + v.getPreu() + " " + v.getPreu() * v.getQuantitat());
            total = total + (v.getPreu() * v.getQuantitat());
        }
        System.out.println("--------------\nTotal: " + total);
        Productes.clear();
    }
    //Metode que mostra els productes que tenim actualment al carret
    public static void mostrarCarret() throws Exception {
        System.out.println("Carret");
        Productes.forEach((k, v) -> System.out.println(v.getNom() + " -> " + v.getQuantitat()));
        menu();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("BENVINGUT AL SAPAMERCAT");
        menu();
    }
}
