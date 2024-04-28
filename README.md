# Pràctica Collections

1er DAW - M01 Programació - Pràctica final UF5
Marc Garcia Martínez


## Justificacions

#### Com emmagatzemem els productes?
He decidit emmagatzemar els productes dins d'un Hasmap, en el que la Key és el codi de barres del producte, i el Value és l'objecte (el producte)

```sh
public static HashMap<Integer, Producte> Productes = new HashMap<Integer, Producte>();
```

#### Constructor producte
Al constructor de productes he decidit afegir el paràmetre quantitat, de forma que m'ha sigut molt més senzill gestionar la quantitat de productes que seleccionava l'usuari
```sh
public Producte(String nom, float preu, int codiBarres, int quantitat) {
    this.nom = nom;
    this.preu = preu;
    this.codiBarres = codiBarres;
    this.quantitat = quantitat;
}
```

#### Com controlem el limit de 100 productes com a maxim?
Per controlar el límit de productes he creat una variable final amb el valor del límit per poder fer el control de forma més còmode més endavant
```sh
public static final int LIMITPRODUCTES = 100;
```

#### Com gestionem un error?
En aquest cas gestionem 2 possibles errors amb 2 try catch, el primer per controlar que l'usuari no introdueix text quan el que estem demanant és un número, amb el segon registrem a un arxiu ".dat" l'error i la data per tenir un control més precís de la nostra aplicació, en cas que hi hagués alguna exception, gràcies al finally tornaríem a l'inici
```sh
public static void menu() {
    Scanner i = new Scanner(System.in);
    System.out.println("-----------\n-- INICI --\n-----------");
    System.out.println("1) Introduir producte\n2) Passar per caixa\n3) Mostrar carret de compra\n0) Acabar");
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
        try{
            File log = new File("src\\logs\\Exceptions.dat");
            FileWriter writer = new FileWriter(log, true);
            LocalDate dataActual = LocalDate.now();
            writer.write(dataActual + "     ERROR! - Has d'introduir un numero!\n");
            writer.close();
        } catch (Exception x) {
            System.out.println("S'ha produit un error!");
        }
    } finally {
        menu();
    }
}
```
Així es veuria al registre (l'arxiu ".dat")
```sh
2024-04-28    ERROR! - L'ultim parametre introduit es incorrecte!
```