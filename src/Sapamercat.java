import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Sapamercat {

    //Variable Final per limitar el carret a 100 productes
    public static final int LIMITPRODUCTES = 100;

    //Hasmap on guardarem els productes que el client anirà afegint
    public static HashMap<Integer, Producte> Productes = new HashMap<Integer, Producte>();


    //Mostrem el menú principal
    public static void menu() {
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
            System.out.println("ERROR! - Has d'introduir un número!");
            //Registrem l'error a l'arxiu "Exceptions.dat"
            try{
                File log = new File("src\\logs\\Exceptions.dat");
                FileWriter writer = new FileWriter(log, true);
                LocalDate dataActual = LocalDate.now();
                writer.write(dataActual + "     ERROR! - Has d'introduir un número!\n");
                writer.close();
            } catch (Exception x) {
                System.out.println("S'ha produït un error!");
            }
        } finally {
            menu();
        }
    }

    //Mostrem el menú de productes
    public static void menuProductes() {
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
            System.out.println("ERROR! - Has d'introduir un número!");
            //Registrem l'error a l'arxiu "Exceptions.dat"
            try{
                File log = new File("src\\logs\\Exceptions.dat");
                FileWriter writer = new FileWriter(log, true);
                LocalDate dataActual = LocalDate.now();
                writer.write(dataActual + "     ERROR! - Has d'introduir un número!\n");
                writer.close();
            } catch (Exception x) {
                System.out.println("S'ha produït un error!");
            }
        }   finally {
            menu();
        }
    }

    //Mètode per comprovar si el producte que volem afegir ja l'hem introduït prèviament
    public static boolean comprovarProducte(int codiBarres){
        for (int k : Productes.keySet()){
            if (k == codiBarres) return true;
        }
        return false;
    }

    //Menú per afegir productes d'alimentació
    public static void afegirAlimentacio(Scanner resposta) {
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
            if (comprovarProducte(codiBarres)) {
                System.out.println("ERROR! - Aquest producte ja està afegit al carret!");
            } else if(nom.length() > 15){
                System.out.println("ERROR! - El nom del producte no pot tenir més de 15 caràcters!");
            } else if(Productes.size() > LIMITPRODUCTES){
                System.out.println("ERROR! - Has arribat al límit de productes (100), per continuar, has de passar per caixa primer");
            } else {
                Productes.put(codiBarres, new Alimentacio(nom, preu, codiBarres, quantitat, data));
            }
        } catch (Exception e) {
            System.out.println("ERROR! - L'últim paràmetre introduït és incorrecte!");
            //Registrem l'error a l'arxiu "Exceptions.dat"
            try{
                File path = new File("src\\logs\\Exceptions.dat");
                FileWriter log = new FileWriter(path, true);
                LocalDate dataActual = LocalDate.now();
                log.write(dataActual + "    ERROR! - L'últim paràmetre introduït és incorrecte!\n");
                log.close();
            } catch (Exception x) {
                System.out.println("S'ha produït un error!");
            }
        } finally {
            menu();
        }
        menu();
    }
    //Menú per afegir productes tèxtils
    public static void afegirTextil(Scanner resposta) {
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
            if (comprovarProducte(codiBarres)) {
                System.out.println("ERROR! - Aquest producte ja està afegit al carret!");
            } else if(nom.length() > 15){
                System.out.println("ERROR! - El nom del producte no pot tenir més de 15 caràcters!");
            } else if(Productes.size() > LIMITPRODUCTES){
                System.out.println("ERROR! - Has arribat al límit de productes (100), per continuar, has de passar per caixa primer");
            } else {
                //Comprovem que el preu introduït per l'usuari és correcte
                try{
                    File update = new File("src\\updates\\UpdateTextilPrices.dat");
                    Scanner reader = new Scanner(update);
                    while (reader.hasNextLine()) {
                        String p = reader.nextLine();
                        String[] producte = p.split(",");
                        if (Objects.equals(producte[0], Integer.toString(codiBarres))){
                            preu = Integer.valueOf(producte[1]);
                        }
                    }
                    reader.close();
                } catch (Exception x) {
                    System.out.println("S'ha produït un error al comprovar el preu!");
                }
                Productes.put(codiBarres, new Textil(nom, preu, codiBarres, quantitat, composicio));
            }
        } catch (Exception e) {
            System.out.println("ERROR! - L'últim paràmetre introduït és incorrecte!");
            //Registrem l'error a l'arxiu "Exceptions.dat"
            try{
                File log = new File("src\\logs\\Exceptions.dat");
                FileWriter writer = new FileWriter(log, true);
                LocalDate dataActual = LocalDate.now();
                writer.write(dataActual + "     ERROR! - L'últim paràmetre introduït és incorrecte!");
                writer.close();
            } catch (Exception x) {
                System.out.println("S'ha produït un error!");
            }
        } finally {
            menu();
        }
        menu();
    }
    //Menú per afegir productes d'electrònica
    public static void afegirElectronica(Scanner resposta) {
        //Control d'errors
        try {
            System.out.println("Afegir Electrònica");
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
            if (comprovarProducte(codiBarres)) {
                System.out.println("ERROR! - Aquest producte ja està afegit al carret!");
            } else if(nom.length() > 15){
                System.out.println("ERROR! - El nom del producte no pot tenir més de 15 caràcters!");
            } else if(Productes.size() > LIMITPRODUCTES){
                System.out.println("ERROR! - Has arribat al límit de productes (100), per continuar, has de passar per caixa primer");
            } else {
                Productes.put(codiBarres, new Electronica(nom, preu, codiBarres, quantitat, garantia));
            }
        } catch (Exception e) {
            System.out.println("ERROR! - L'últim paràmetre introduït és incorrecte!");
            //Registrem l'error a l'arxiu "Exceptions.dat"
            try{
                File log = new File("src\\logs\\Exceptions.dat");
                FileWriter writer = new FileWriter(log, true);
                LocalDate dataActual = LocalDate.now();
                writer.write(dataActual + "     ERROR! - L'últim paràmetre introduït és incorrecte!");
                writer.close();
            } catch (Exception x) {
                System.out.println("S'ha produït un error!");
            }

        } finally {
            menu();
        }
        menu();
    }

    //Mètode que imprimeix tiquet
    public static void pasarCaixa() {
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
    //Mètode que mostra els productes que tenim actualment al carret
    public static void mostrarCarret() {
        System.out.println("Carret");
        Productes.forEach((k, v) -> System.out.println(v.getNom() + " -> " + v.getQuantitat()));
        menu();
    }

    public static void main(String[] args) {
        System.out.println("BENVINGUT AL SAPAMERCAT");
        menu();
    }
}