import java.util.Scanner;

public class CalculadoraSuma {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Calculadora");
        System.out.println("ingrese el primer numero: ");
        int Numero1= scanner.nextInt();
        System.out.println("ingrese el segundo numero: ");
        int Numero2= scanner.nextInt();
        
        System.out.println("la suma de " + Numero1 + " y " + Numero2 + " es: ");
        System.out.println(Numero1+Numero2);
    }
}
