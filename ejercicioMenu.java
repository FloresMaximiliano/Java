import java.util.Scanner;
public class ejercicioMenu {
    public static void main (String[] args){
        Scanner scanner= new Scanner(System.in);
        int opcion=-1;

        do{
            System.out.println("Menu");
            System.out.println("1. Saludar");
            System.out.println("2. mostrar la hora");
            System.out.println("3. salir");
            System.out.println("Ingrese una opcion: ");
            if (scanner.hasNextInt()){
                opcion=scanner.nextInt();
                switch (opcion){
                    case 1:
                    System.out.println("Hola, usuario!");
                    break;
                    case 2:
                    System.out.println("hora actual: " + java.time.LocalTime.now());
                    break;
                    case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                    default:
                    System.out.println("opcion no valida, intente nuevamente...");
                }  
            }else {
                System.out.println("Ingrese un numero entero.");
                scanner.next();
            }
            System.out.println("");
        }while (opcion != 3);
        scanner.close();
    }
    
}
