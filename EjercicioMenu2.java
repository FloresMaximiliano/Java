
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EjercicioMenu2{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        ArrayList<Integer> numerostotales= new ArrayList<>();
        do{
            System.out.println("1.- Ingresar número.\r\n" + //
                                "2.- Mostrar mayor.\r\n" + //
                                "3.- Mostrar menor.\r\n" + //
                                "4.- Salir.\r\n" + //
                                "");
            System.out.println("Elija una opcion: ");
            if (scanner.hasNextInt()){
                opcion=scanner.nextInt();
                scanner.nextLine();
                if (opcion <1 || opcion>4){
                    System.out.println("Ingrese una opcion valida.");
                }else{
                    switch (opcion){
                        case 1:
                            System.out.println("Ingrese Solo un numero: ");
                            String Entrada= scanner.nextLine().trim();
                            String[] partes= Entrada.split("\\s+");
                            if (partes.length !=1){
                                System.out.println("Solo debes ingresar un numero");
                            } else{
                                try {
                                    int numero = Integer.parseInt(partes[0]);
                                    if (numerostotales.contains(numero)){
                                        System.out.println("El numero ya fue ingresado");
                                    } else{
                                        numerostotales.add(numero);
                                        System.out.println("Numero ingresado correctamente");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada invalida, solo se permiten enteros!");
                                }
                            }break;
                        case 2:
                            if (numerostotales.isEmpty()){
                                System.out.println("No se han ingresado numeros");
                            } else{
                                int mayor= Collections.max(numerostotales);
                                System.out.println("el numero mayor es: " + mayor);
                            }
                            break;
                        case 3:
                            if (numerostotales.isEmpty()){
                            System.out.println("Aun no se ingresan numeros");
                        } else{
                            int menor = Collections.min(numerostotales);
                            System.out.println("el numero menor es: " + menor);
                            }
                            break;
                        case 4:
                            System.out.println("Saliendo del programa...");
                            break;
                    } 
                }
            } else{
                System.out.println("Solo se permiten numero enteros!");
                scanner.next();
                opcion=0;

            }


        } while (opcion !=4);
        scanner.close();
    }
}
