package menu_restaurant_team_.noche;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu_Restaurant_Team_Noche {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);

        //Definimos las variables
        String nom, bebidas, comidas;
        int opcionMenu, acumulac, acumulab, seleccion, mesa;
        double sumar;

        //inicializamos las variable, algunas en cero
        sumar = 0;
        comidas = "";
        acumulac = 0;
        bebidas = "";
        acumulab = 0;
        String[] vectorComidas = {"Quesadillas", "Tacos", "Fajitas", "Burritos", "Botanas", "Nachos", "Tamales"};
        String[] vectorBebidas = {"Margarita", "Chelada", "Cerveza Corona", "Tequila", "Agua", "Gaseosas"};
        int[] cantidades_comidas = {0, 0, 0, 0, 0, 0, 0};
        int[] precios_comidas = {950, 1500, 1200, 1500, 2000, 500, 1000};
        int[] cantidades_bebidas = {0, 0, 0, 0, 0, 0};
        int[] precios_bebidas = {700, 500, 500, 600, 300, 300};
        int subtotal_comidas = 0, subtotal_bebidas = 0, total;

        //mostramos nuestro logo
        logo();

        // Pedimos el nombre del cliente
        System.out.println("Ingrese su nombre: ");
        nom = teclado.nextLine().toUpperCase();
        System.out.println();
        esperar(1);

        mesa = mesas(teclado);

        //iniciamos un ciclo repetir-Hasta Que con un ciclo segun anidado para la selecion de menu.
        do {
            logo();

            System.out.println("Bienvenido " + nom);
            System.out.println("Su mesa es la " + mesa + "\n");
            System.out.println("Elija una opcion");
            System.out.println("1.-Menu de comidas");
            System.out.println("2.-Menu de bebidas");
            System.out.println("3.-Finalizar pedido");
            opcionMenu = teclado.nextInt();
            switch (opcionMenu) {
                case 1:
                    comidas = menuComidas(vectorComidas, cantidades_comidas, precios_comidas, teclado, comidas, subtotal_comidas);
                    subtotal_comidas = calcularCostos(cantidades_comidas, precios_comidas);
                    break;
                case 2:
                    bebidas = menuBebidas(vectorBebidas, cantidades_bebidas, precios_bebidas, teclado, bebidas, subtotal_bebidas);
                    subtotal_bebidas = calcularCostos(cantidades_bebidas, precios_bebidas);
                    break;
                case 3:
                    if(comidas == "" && bebidas == ""){
                        System.out.println("No ha elegido nada todavia. Vuelva a intentarlo.\n");
                    }
            }
        } while (opcionMenu != 3 || comidas == "" && bebidas == "");
        logo();
        total = subtotal_comidas + subtotal_bebidas;
        if (total != 0) {
            System.out.println("\nSu cuenta es de: $ " + total);
            System.out.println("\nLos platos que ordeno son: " + comidas);
            System.out.println("\nLas bebidas que ordeno son: " + bebidas);
            realizarPago((float) total, teclado);
            System.out.println("\n--------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------\n");
        }

    }

    //este esl el metodo para la demora de la escritura
    public static void esperar(long tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //este es el logo
    public static void logo() {
        esperar(100);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        esperar(100);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        esperar(100);
        System.out.println(" Bienvenidos a ");
        esperar(100);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        esperar(100);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        esperar(100);
        System.out.println("		 ____   _____   _____   __  _   _____   _           ");
        esperar(100);
        System.out.println("		|  __| |  _  | |  _  | |  \\| | |  _  | | |          ");
        esperar(100);
        System.out.println("		| |    | |_| | | |_|_| | |\\\\ | | |_| | | |         ");
        esperar(100);
        System.out.println("		| |__  |  _  | |  _ \\  | | \\ | |  _  | | |___      ");
        esperar(100);
        System.out.println("		|____| |_| |_| |_| |_| |_| |_| |_| |_| |_____|         by TEAM NOCHE");
        esperar(100);
        System.out.println("");
        esperar(100);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        esperar(100);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        esperar(100);
        System.out.println("						      Restaurante de comida mexicana.\n");

    }

    //asignamos el tamaño de la matriz
    public static int mesas(Scanner teclado) throws IOException {
        int i, j, mesa;
        String sTexto;

        int lugares[][] = new int[3][3];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (i = 0; i <= 2; i++) {
            for (j = 0; j <= 2; j++) {
                lugares[i][j] = 3 * i + j + 1;
            }
        }

        logo();

        for (i = 0; i <= 2; i++) {
            System.out.println("---------------------------");
            for (j = 0; j <= 2; j++) {
                System.out.print("|   " + lugares[i][j] + "   |");
            }
            System.out.println("");
        }
        System.out.println("---------------------------");

        //mostramos el mensaje de elija su mesa y guardamos la opcion en mesa
        System.out.println("Elija su mesa: ");
        mesa = teclado.nextInt();
        while(mesa < 1 || mesa > 9){
            System.out.print("Ha elegido una mesa inexistente. Vuelva a intentarlo: ");
            mesa = teclado.nextInt();
        }

        for (i = 0; i <= 2; i++) {
            System.out.println("---------------------------");
            for (j = 0; j <= 2; j++) {
                if (mesa == lugares[i][j]) {
                    System.out.print("|   X   |");
                } else {
                    System.out.print("|   " + lugares[i][j] + "   |");
                }

            }
            System.out.println("");
        }
        System.out.println("---------------------------");
        System.out.println("Presione ENTER para continuar");
        sTexto = br.readLine();
        return mesa;

    }

    //mostramos el menu de comidas
    public static String menuComidas(String[] comidas, int[] cantidades, int[] precios, Scanner teclado, String comidas_elegidas, double subtotal){
        int opcion_elegida, cantidad;
        do {
            System.out.println("\n");
            System.out.println("1.- Quesadillas Rellenas de queso mozzarella, albahaca, olivas negras y tomate ................................$950");
            System.out.println("2.- Tres tacos de res en tortilla de maíz crujiente con crema ácida, tomate, salsa mexicana y queso cheddar ...$1500");
            System.out.println("3.- Fajitas de Carne para 2 personas ..........................................................................$1200");
            System.out.println("4.- Burrito relleno de res con queso mozzarella, frijoles, lechuga, tomate, guacamole y crema ácida ...........$1500");
            System.out.println("5.- Botanas - Variedad de tacos, flautitas rellenas de pollo, burritos y quesadillas ..........................$2000");
            System.out.println("6.- Nachos bañados en queso cheddar ...........................................................................$500");
            System.out.println("7.- Tamales relleno de res con puerros, cebollas y tomates ....................................................$1000");
            System.out.println("8.- Regresar al menu principal.");
            opcion_elegida = teclado.nextInt();
            while (opcion_elegida < 1 || opcion_elegida > 8) {
                System.out.print("Ha elegida una opcion incorrecta. Vuelva a intentar");
                opcion_elegida = teclado.nextInt();
            }
            System.out.println("");
            if (opcion_elegida != 8) {
                if (!comidas_elegidas.contains(comidas[opcion_elegida - 1])) {
                    comidas_elegidas += " " + comidas[opcion_elegida - 1];
                }
                System.out.print("Elija la cantidad: ");
                cantidad = teclado.nextInt();
                while (cantidad < 1) {
                    System.out.print("Ha ingresado una cantidad no valida. Vuelva a intentarlo: ");
                    cantidad = teclado.nextInt();
                }
                cantidades[opcion_elegida - 1] += cantidad;
                subtotal += precios[opcion_elegida - 1] * cantidad;
                System.out.println("\nHas escogido: " + comidas[opcion_elegida - 1]);
                System.out.print("El subtotal es: $ " + subtotal);
            }

        } while (opcion_elegida != 8);
        esperar(2);
        return comidas_elegidas;
    }

    //mostramos el menu de bebidas
    public static String menuBebidas(String[] bebidas, int[] cantidadesB, int[] preciosB, Scanner teclado, String bebidas_elegidas, double subtotal) {
        int opcion_elegidaB, cantidad;
        do {
            System.out.println("\n");
            System.out.println("1.- Margarita ...................$700");
            System.out.println("2.- Chelada .....................$500");
            System.out.println("3.- Cerveza Corona  .............$500");
            System.out.println("4.- Tequila......................$600");
            System.out.println("5.- Agua ........................$300");
            System.out.println("6.- Gasoesas ....................$300");
            System.out.println("7.- Regresar al menu principal.");
            opcion_elegidaB = teclado.nextInt();
            while (opcion_elegidaB < 1 || opcion_elegidaB > 7) {
                System.out.print("Ha elegida una opcion incorrecta. Vuelva a intentar");
                opcion_elegidaB = teclado.nextInt();
            }
            System.out.println("");
            if (opcion_elegidaB != 7) {
                if (!bebidas_elegidas.contains(bebidas[opcion_elegidaB - 1])) {
                    bebidas_elegidas += " " + bebidas[opcion_elegidaB - 1];
                }

                System.out.print("Elija la cantidad: ");
                cantidad = teclado.nextInt();
                while (cantidad < 1) {
                    System.out.print("Ha ingresado una cantidad no valida. Vuelva a intentarlo: ");
                    cantidad = teclado.nextInt();
                }
                cantidadesB[opcion_elegidaB - 1] += cantidad;
                subtotal += preciosB[opcion_elegidaB - 1] * cantidad;
                System.out.println("\nHas escogido: " + bebidas[opcion_elegidaB - 1]);
                System.out.print("El subtotal es: $ " + subtotal);
            }

        } while (opcion_elegidaB != 7);
        esperar(2);
        return bebidas_elegidas;
    }

    public static int calcularCostos(int[] cantidades, int[] precios) {
        int subtotal = 0;
        for (int i = 0; i < cantidades.length; i++) {
            subtotal += cantidades[i] * precios[i];
        }
        return subtotal;
    }

    public static void realizarPago(float total, Scanner teclado) {
        int opcion, cuotas;
        float descuento, intereses;
        System.out.println("--------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("Elija la forma de pago: ");
        System.out.println("1-Efectivo 10% de descuento");
        System.out.println("2-Tarjeta de debito 5% de descuento");
        System.out.println("3-Tarjeta de credito hasta 3 cuotas sin interes");
        System.out.println("4-Transferencia bancaria 5% de descuento");
        opcion = teclado.nextInt();
        while (opcion < 1 || opcion > 4) {
            System.out.print("Ha ingresado una opcion no valida. Vuelva a intentarlo: ");
            opcion = teclado.nextInt();
        }
        switch (opcion) {
            case 1:
                descuento = total / 100 * 10;
                total -= descuento;
                System.out.println("\nEFECTIVO");
                System.out.println("Total a pagar con 10% de descueno es: $ " + total);
                break;
            case 2:
                descuento = total / 100 * 5;
                total -= descuento;
                System.out.println("\nTARJETA DE DEBITO");
                System.out.println("Total a pagar con 5% de descuento es: $ " + total);
                break;
            case 3:
                do {
                    System.out.print("Elija el numero de cuotas: ");
                    cuotas = teclado.nextInt();
                    if (cuotas >= 1 && cuotas <= 3) {
                        total = total / cuotas;
                        System.out.println("A pagar " + cuotas + " cuotas de $ " + total);
                    } else if (cuotas > 3 && cuotas <= 12) {
                        System.out.println("Tiene 10% de interes");
                        intereses = total / 100 * 10;
                        total = (total + intereses) / cuotas;
                        System.out.println("A pagar " + cuotas + " cuotas de $ " + total);
                    } else {
                        System.out.println("No es posible procesar el pago, intente elegir otro numero de cuotas.");
                    }
                } while (cuotas < 1 || cuotas >= 13);
                break;
            case 4:
                descuento = total / 100 * 5;
                total -= descuento;
                System.out.println("TRANSFERENCIA BANCARIA");
                System.out.println("Alias: TEAM.NOCHE.MP");
                System.out.println("Total a pagar con 5% de descuento es: $ " + total);
                break;
        }
    }

}
