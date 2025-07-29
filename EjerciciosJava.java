/*
 * PROGRAMACIÓN ORIENTADA A OBJETOS EN JAVA
 * Tutorial completo con ejemplos prácticos
 * 
 * CONCEPTOS FUNDAMENTALES:
 * 1. Clases y Objetos
 * 2. Atributos y Métodos
 * 3. Encapsulación
 * 4. Herencia
 * 5. Polimorfismo
 * 6. Abstracción
 */

// ========================================
// 1. CLASES Y OBJETOS - CONCEPTOS BÁSICOS
// ========================================

/*
 * Una CLASE es un molde o plantilla que define las características 
 * y comportamientos que tendrán los objetos.
 * 
 * Un OBJETO es una instancia de una clase.
 */

// Ejemplo 1: Clase básica Persona
class Persona {
    // ATRIBUTOS (características)
    String nombre;
    int edad;
    final String dni;
    
    // CONSTRUCTOR (cómo se crea el objeto)
    public Persona(String nombre, int edad, String dni) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
    }
    
    // MÉTODOS (comportamientos/acciones)
    public void presentarse() {
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años");
    }
    
    public void cumplirAnios() {
        edad++;
        System.out.println("¡Feliz cumpleaños! Ahora tengo " + edad + " años");
    }
    
    public boolean esMayorDeEdad() {
        return edad >= 18;
    }
    
    public String getDni() {
        return dni;
    }
}

// ========================================
// 2. ENCAPSULACIÓN
// ========================================

/*
 * La ENCAPSULACIÓN es ocultar los detalles internos de una clase
 * y controlar el acceso a los datos mediante métodos getter y setter.
 * Usamos modificadores de acceso: private, public, protected
 */

class CuentaBancaria {
    // Atributos PRIVADOS (no se pueden acceder directamente desde fuera)
    private final String numeroCuenta;
    private double saldo;
    private String titular;
    
    // Constructor
    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }
    
    // MÉTODOS GETTER (para obtener valores)
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public String getTitular() {
        return titular;
    }
    
    // MÉTODOS SETTER (para modificar valores con validación)
    public void setTitular(String titular) {
        if (titular != null && !titular.trim().isEmpty()) {
            this.titular = titular;
        }
    }
    
    // Métodos de negocio
    public boolean depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Depósito exitoso. Saldo actual: $" + saldo);
            return true;
        }
        System.out.println("Error: La cantidad debe ser positiva");
        return false;
    }
    
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            System.out.println("Retiro exitoso. Saldo actual: $" + saldo);
            return true;
        }
        System.out.println("Error: Fondos insuficientes o cantidad inválida");
        return false;
    }
    
    public void consultarSaldo() {
        System.out.println("Saldo de " + titular + ": $" + saldo);
    }
}

// ========================================
// 3. HERENCIA
// ========================================

/*
 * La HERENCIA permite crear nuevas clases basadas en clases existentes.
 * La clase hija (subclase) hereda atributos y métodos de la clase padre (superclase).
 * Palabra clave: extends
 */

// Clase padre (superclase)
class Vehiculo {
    protected String marca;
    protected String modelo;
    protected int anio;
    protected boolean encendido;
    
    public Vehiculo(String marca, String modelo, int anio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.encendido = false;
    }
    
    public void encender() {
        if (!encendido) {
            encendido = true;
            System.out.println("El vehículo está encendido");
        } else {
            System.out.println("El vehículo ya está encendido");
        }
    }
    
    public void apagar() {
        if (encendido) {
            encendido = false;
            System.out.println("El vehículo está apagado");
        } else {
            System.out.println("El vehículo ya está apagado");
        }
    }
    
    public void mostrarInfo() {
        System.out.println("Vehículo: " + marca + " " + modelo + " (" + anio + ")");
    }
}

// Clase hija (subclase)
class Automovil extends Vehiculo {
    private final int numeroPuertas;
    private final String tipoCombustible;
    
    public Automovil(String marca, String modelo, int anio, int numeroPuertas, String tipoCombustible) {
        super(marca, modelo, anio); // Llama al constructor de la clase padre
        this.numeroPuertas = numeroPuertas;
        this.tipoCombustible = tipoCombustible;
    }
    
    // Método específico de Automovil
    public void acelerar() {
        if (encendido) {
            System.out.println("El automóvil está acelerando...");
        } else {
            System.out.println("Primero debes encender el automóvil");
        }
    }
    
    // SOBRESCRITURA de método (Override)
    @Override
    public void mostrarInfo() {
        super.mostrarInfo(); // Llama al método de la clase padre
        System.out.println("Puertas: " + numeroPuertas + ", Combustible: " + tipoCombustible);
    }
}

// Otra clase hija
class Motocicleta extends Vehiculo {
    private final int cilindrada;
    
    public Motocicleta(String marca, String modelo, int anio, int cilindrada) {
        super(marca, modelo, anio);
        this.cilindrada = cilindrada;
    }
    
    public void hacerCaballito() {
        if (encendido) {
            System.out.println("¡Haciendo caballito! 🏍️");
        } else {
            System.out.println("Primero debes encender la motocicleta");
        }
    }
    
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Cilindrada: " + cilindrada + "cc");
    }
}

// ========================================
// 4. POLIMORFISMO
// ========================================

/*
 * El POLIMORFISMO permite que objetos de diferentes clases respondan
 * de manera diferente al mismo método. "Una misma acción, diferentes comportamientos"
 */

// Clase abstracta (no se puede instanciar directamente)
abstract class Animal {
    protected String nombre;
    protected int edad;
    
    public Animal(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    
    // Método abstracto (debe ser implementado por las clases hijas)
    public abstract void hacerSonido();
    
    // Método concreto (puede ser usado tal como está o sobrescrito)
    public void dormir() {
        System.out.println(nombre + " está durmiendo... 💤");
    }
    
    public void mostrarInfo() {
        System.out.println("Animal: " + nombre + ", Edad: " + edad + " años");
    }
}

class Perro extends Animal {
    private final String raza;
    
    public Perro(String nombre, int edad, String raza) {
        super(nombre, edad);
        this.raza = raza;
    }
    
    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: ¡Guau guau! 🐕");
    }
    
    public void traerPelota() {
        System.out.println(nombre + " trajo la pelota 🎾");
    }
    
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Raza: " + raza);
    }
}

class Gato extends Animal {
    private final boolean esInterior;
    
    public Gato(String nombre, int edad, boolean esInterior) {
        super(nombre, edad);
        this.esInterior = esInterior;
    }
    
    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: ¡Miau miau! 🐱");
    }
    
    public void ronronear() {
        System.out.println(nombre + " está ronroneando... purr purr");
    }
    
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Vive en: " + (esInterior ? "Interior" : "Exterior"));
    }
}

class Pajaro extends Animal implements Volador {
    private final boolean puedeVolar;
    
    public Pajaro(String nombre, int edad, boolean puedeVolar) {
        super(nombre, edad);
        this.puedeVolar = puedeVolar;
    }
    
    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: ¡Pío pío! 🐦");
    }
    
    @Override
    public void volar() {
        if (puedeVolar) {
            System.out.println(nombre + " está volando ✈️");
        } else {
            System.out.println(nombre + " no puede volar");
        }
    }
    
    @Override
    public void aterrizar() {
        System.out.println(nombre + " aterrizó en una rama 🌳");
    }
}

// ========================================
// 5. INTERFACES
// ========================================

/*
 * Las INTERFACES definen un contrato que las clases deben cumplir.
 * Una clase puede implementar múltiples interfaces.
 */

interface Volador {
    void volar();
    void aterrizar();
}

interface Nadador {
    void nadar();
    void bucear();
}

// Una clase puede implementar múltiples interfaces
class Pato extends Animal implements Volador, Nadador {
    
    public Pato(String nombre, int edad) {
        super(nombre, edad);
    }
    
    @Override
    public void hacerSonido() {
        System.out.println(nombre + " dice: ¡Cuac cuac! 🦆");
    }
    
    @Override
    public void volar() {
        System.out.println(nombre + " está volando sobre el agua ✈️");
    }
    
    @Override
    public void aterrizar() {
        System.out.println(nombre + " aterrizó en el agua 💧");
    }
    
    @Override
    public void nadar() {
        System.out.println(nombre + " está nadando 🏊");
    }
    
    @Override
    public void bucear() {
        System.out.println(nombre + " está buceando por comida 🤿");
    }
}

// ========================================
// 6. CLASE PRINCIPAL CON EJEMPLOS
// ========================================

public class EjerciciosJava {
    
    public static void main(String[] args) {
        System.out.println("=== TUTORIAL DE PROGRAMACIÓN ORIENTADA A OBJETOS ===\n");
        
        // ===== EJEMPLO 1: CLASES Y OBJETOS =====
        System.out.println("1. CLASES Y OBJETOS:");
        System.out.println("---------------------");
        
        // Crear objetos (instancias de la clase Persona)
        Persona persona1 = new Persona("Juan", 25, "12345678");
        Persona persona2 = new Persona("María", 17, "87654321");
        
        persona1.presentarse();
        persona2.presentarse();
        
        System.out.println("¿Juan es mayor de edad? " + persona1.esMayorDeEdad());
        System.out.println("¿María es mayor de edad? " + persona2.esMayorDeEdad());
        
        persona1.cumplirAnios();
        System.out.println();
        
        // ===== EJEMPLO 2: ENCAPSULACIÓN =====
        System.out.println("2. ENCAPSULACIÓN:");
        System.out.println("-----------------");
        
        CuentaBancaria cuenta = new CuentaBancaria("001-123456", "Ana García", 1000.0);
        
        cuenta.consultarSaldo();
        cuenta.depositar(500);
        cuenta.retirar(200);
        cuenta.retirar(2000); // Error: fondos insuficientes
        
        // No podemos acceder directamente a saldo (está privado)
        // cuenta.saldo = 999999; // Esto daría error de compilación
        
        System.out.println();
        
        // ===== EJEMPLO 3: HERENCIA =====
        System.out.println("3. HERENCIA:");
        System.out.println("-------------");
        
        Automovil auto = new Automovil("Toyota", "Corolla", 2023, 4, "Gasolina");
        Motocicleta moto = new Motocicleta("Honda", "CBR", 2022, 600);
        
        auto.mostrarInfo();
        auto.encender();
        auto.acelerar();
        
        System.out.println();
        
        moto.mostrarInfo();
        moto.encender();
        moto.hacerCaballito();
        
        System.out.println();
        
        // ===== EJEMPLO 4: POLIMORFISMO =====
        System.out.println("4. POLIMORFISMO:");
        System.out.println("----------------");
        
        // Array polimórfico: diferentes tipos de animales en el mismo array
        Animal[] animales = {
            new Perro("Rex", 3, "Pastor Alemán"),
            new Gato("Whiskers", 2, true),
            new Pajaro("Tweety", 1, true),
            new Pato("Donald", 4)
        };
        
        // Polimorfismo en acción: el mismo método, diferentes comportamientos
        for (Animal animal : animales) {
            animal.mostrarInfo();
            animal.hacerSonido(); // Cada animal hace un sonido diferente
            animal.dormir();
            System.out.println();
        }
        
        // ===== EJEMPLO 5: INTERFACES =====
        System.out.println("5. INTERFACES:");
        System.out.println("---------------");
        
        Pato pato = new Pato("Pato Lucas", 3);
        pato.hacerSonido();
        pato.nadar();
        pato.volar();
        pato.bucear();
        pato.aterrizar();
        
        System.out.println();
        
        // ===== EJEMPLO 6: CONCEPTOS AVANZADOS =====
        System.out.println("6. CONCEPTOS AVANZADOS:");
        System.out.println("-----------------------");
        
        // Polimorfismo con interfaces
        Volador[] voladores = {
            new Pajaro("Águila", 5, true),
            new Pato("Pato Salvaje", 2)
        };
        
        for (Volador volador : voladores) {
            volador.volar();
        }
        
        System.out.println("\n=== FIN DEL TUTORIAL ===");
    }
    
    /*
     * EJERCICIOS PARA PRACTICAR:
     * 
     * 1. Crea una clase "Estudiante" que herede de "Persona" y añade:
     *    - Atributo: carrera, promedio
     *    - Métodos: estudiar(), rendir_examen()
     * 
     * 2. Crea una clase "Libro" con encapsulación:
     *    - Atributos privados: titulo, autor, precio, disponible
     *    - Métodos getter y setter apropiados
     *    - Método prestar() y devolver()
     * 
     * 3. Crea una jerarquía de clases para figuras geométricas:
     *    - Clase abstracta "Figura" con método abstracto calcularArea()
     *    - Clases hijas: "Rectangulo", "Circulo", "Triangulo"
     * 
     * 4. Crea una interfaz "Trabajador" con métodos trabajar() y cobrar()
     *    - Implementa en clases: "Programador", "Doctor", "Profesor"
     * 
     * CONCEPTOS CLAVE PARA RECORDAR:
     * - Clase: molde o plantilla
     * - Objeto: instancia de una clase
     * - Encapsulación: ocultar detalles internos
     * - Herencia: reutilizar código de clases padre
     * - Polimorfismo: mismo método, diferentes comportamientos
     * - Abstracción: enfocarse en lo esencial
     * - Interface: contrato que deben cumplir las clases
     */
}