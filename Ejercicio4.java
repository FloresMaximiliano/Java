
import java.util.ArrayList;
import java.util.Scanner;


public class Ejercicio4 {
    public static void main(String[] args){
        ArrayList<String> ListaNombres = new ArrayList<>();
        int opcion=0;
        Scanner scanner = new  Scanner(System.in);
        do { 
            System.out.println("*** MENÚ PRINCIPAL ***");
            System.out.println("1.- Ingresar nombre.");
            System.out.println("2.- Mostrar todos los nombres ingresados.");
            System.out.println("3.- Buscar un nombre.");
            System.out.println("4.- Salir del programa.");

            System.out.println("Ingrese una opcion: ");
            
            if (scanner.hasNextInt()){
                opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion>=1 && opcion<=4){
                    System.out.println("Se ha Accedido a la opcion " + opcion);
                } else{
                    System.out.println("Opcion Fuera de rango");
                }
            } else{
                System.out.println("Debe Seleccionar una.");
            }
            switch (opcion) {
                case 1:
                    String NombreIngresado;
                    do { 
                        System.out.println("Ingrese un nombre: ");
                        NombreIngresado = scanner.nextLine().trim();
                        if (NombreIngresado.isEmpty()){
                            System.out.println("Debe ingresar un nombre. No puede estar vacio.");
                        }
                    } while (NombreIngresado.isEmpty());
                    System.out.println("Nombre ingresado correctamente: " + NombreIngresado);
                    ListaNombres.add(NombreIngresado);
                    break;
                case 2:
                    if (ListaNombres.isEmpty()){
                        System.out.println("Aun no se ingresan nombres.");
                    } else{
                        System.out.println(ListaNombres);
                    }
                case 3:
                    String buscar;
                    System.out.println("Ingrese nombre a buscar: ");
                    buscar = scanner.nextLine().trim();
                    if (buscar.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")){
                        boolean encontrado = false;
                        for (String nombre : ListaNombres){
                            if (nombre.equalsIgnoreCase(buscar)){
                                System.out.println("Se ha encontrado a " + buscar + " en la lista");
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado){
                            System.out.println("El nombre " + buscar + " no se encuentra ingresado.");
                        }

                    } else{
                        System.out.println("Debe ingresar un nombre valido ej: Maria Lopez");
                    }
                case 4:
                    System.out.println("Saliendo del Programa...");
                    break;
            }
        } while (opcion!=4);
        scanner.close();
    }

}

