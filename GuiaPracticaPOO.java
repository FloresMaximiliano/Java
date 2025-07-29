/*
 * 📚 GUÍA PRÁCTICA PARA APRENDER POO - PASO A PASO
 * 
 * Esta guía está diseñada para que practiques los conceptos de POO
 * desde lo más básico hasta proyectos más complejos.
 * 
 * 🎯 ESTRUCTURA DE LA GUÍA:
 * 1. Ejercicios Básicos (Clases y Objetos)
 * 2. Ejercicios de Encapsulación
 * 3. Ejercicios de Herencia
 * 4. Ejercicios de Polimorfismo
 * 5. Ejercicios de Interfaces
 * 6. Proyectos Integradores
 * 7. Desafíos Avanzados
 * 
 * ⏱️ TIEMPO ESTIMADO: 2-3 semanas practicando 1-2 horas diarias
 */

// ========================================
// NIVEL 1: EJERCICIOS BÁSICOS
// ========================================

/*
 * 🟢 EJERCICIO 1.1: CLASE SIMPLE
 * Dificultad: Muy Fácil ⭐
 * Tiempo: 15 minutos
 * 
 * Crea una clase "Mascota" con:
 * - Atributos: nombre, especie, edad
 * - Constructor
 * - Métodos: dormir(), jugar(), hacerSonido()
 * 
 * EJEMPLO DE USO:
 * Mascota perro = new Mascota("Max", "Perro", 3);
 * perro.jugar();
 * perro.hacerSonido();
 */

class Mascota {
    // TU CÓDIGO AQUÍ
    // Tip: Empieza definiendo los atributos
}

/*
 * 🟢 EJERCICIO 1.2: MÚLTIPLES OBJETOS
 * Dificultad: Fácil ⭐
 * Tiempo: 20 minutos
 * 
 * Crea una clase "Libro" con:
 * - Atributos: titulo, autor, numeroPaginas, paginaActual
 * - Métodos: leer(int paginas), cerrar(), obtenerProgreso()
 * 
 * Crea 3 libros diferentes y simula que los lees
 */

class Libro {
    // TU CÓDIGO AQUÍ
}

/*
 * 🟢 EJERCICIO 1.3: CALCULADORA SIMPLE
 * Dificultad: Fácil ⭐
 * Tiempo: 25 minutos
 * 
 * Crea una clase "Calculadora" con métodos:
 * - sumar(double a, double b)
 * - restar(double a, double b)
 * - multiplicar(double a, double b)
 * - dividir(double a, double b) // Validar división por cero
 */

class Calculadora {
    // TU CÓDIGO AQUÍ
}

// ========================================
// NIVEL 2: ENCAPSULACIÓN
// ========================================

/*
 * 🟡 EJERCICIO 2.1: CUENTA DE USUARIO
 * Dificultad: Intermedio ⭐⭐
 * Tiempo: 30 minutos
 * 
 * Crea una clase "CuentaUsuario" con:
 * - Atributos PRIVADOS: nombreUsuario, contraseña, email, activo
 * - Constructor que valide que el email contenga "@"
 * - Métodos getter y setter con validaciones
 * - Método cambiarContraseña(String actual, String nueva)
 * - Método activar/desactivar cuenta
 */

class CuentaUsuario {
    // TU CÓDIGO AQUÍ
    // Recuerda: Los atributos deben ser private
    // Los métodos públicos controlan el acceso
}

/*
 * 🟡 EJERCICIO 2.2: CAJA REGISTRADORA
 * Dificultad: Intermedio ⭐⭐
 * Tiempo: 40 minutos
 * 
 * Crea una clase "CajaRegistradora" con:
 * - Atributo privado: dineroTotal
 * - Métodos: agregarVenta(double monto), retirarDinero(double monto, String motivo)
 * - Método consultarBalance()
 * - Histórico de transacciones
 * - Validaciones para no permitir números negativos
 */

class CajaRegistradora {
    // TU CÓDIGO AQUÍ
}

/*
 * 🟡 EJERCICIO 2.3: TERMOSTATO INTELIGENTE
 * Dificultad: Intermedio ⭐⭐
 * Tiempo: 35 minutos
 * 
 * Crea una clase "Termostato" con:
 * - Atributos privados: temperaturaActual, temperaturaDeseada, encendido
 * - Métodos para ajustar temperatura (con límites: 16-30°C)
 * - Método para encender/apagar
 * - Método que simule ajuste automático hacia la temperatura deseada
 */

class Termostato {
    // TU CÓDIGO AQUÍ
}

// ========================================
// NIVEL 3: HERENCIA
// ========================================

/*
 * 🟠 EJERCICIO 3.1: JERARQUÍA DE EMPLEADOS
 * Dificultad: Intermedio-Avanzado ⭐⭐⭐
 * Tiempo: 45 minutos
 * 
 * Crea una clase base "Empleado" y las siguientes clases hijas:
 * - EmpleadoVentas (con comisiones)
 * - EmpleadoTecnico (con certificaciones)
 * - Gerente (con equipo a cargo)
 * 
 * Cada uno debe calcular su salario de manera diferente
 */

abstract class Empleado {
    // TU CÓDIGO AQUÍ - Clase base
}

class EmpleadoVentas extends Empleado {
    // TU CÓDIGO AQUÍ
}

class EmpleadoTecnico extends Empleado {
    // TU CÓDIGO AQUÍ
}

class Gerente extends Empleado {
    // TU CÓDIGO AQUÍ
}

/*
 * 🟠 EJERCICIO 3.2: FORMAS GEOMÉTRICAS
 * Dificultad: Intermedio-Avanzado ⭐⭐⭐
 * Tiempo: 50 minutos
 * 
 * Crea una clase abstracta "Forma" y las siguientes implementaciones:
 * - Rectangulo
 * - Circulo
 * - Triangulo
 * 
 * Cada forma debe poder calcular área y perímetro
 */

abstract class Forma {
    // TU CÓDIGO AQUÍ
    public abstract double calcularArea();
    public abstract double calcularPerimetro();
}

/*
 * 🟠 EJERCICIO 3.3: VEHÍCULOS
 * Dificultad: Intermedio-Avanzado ⭐⭐⭐
 * Tiempo: 1 hora
 * 
 * Crea una jerarquía de vehículos:
 * - Clase base: Vehiculo
 * - Subclases: Auto, Motocicleta, Bicicleta
 * - Cada uno con características específicas
 * - Métodos para acelerar, frenar, mostrar información
 */

class Vehiculo {
    // TU CÓDIGO AQUÍ
}

// ========================================
// NIVEL 4: POLIMORFISMO
// ========================================

/*
 * 🔴 EJERCICIO 4.1: SISTEMA DE PAGOS
 * Dificultad: Avanzado ⭐⭐⭐⭐
 * Tiempo: 1 hora
 * 
 * Crea diferentes métodos de pago que respondan al mismo mensaje:
 * - PagoTarjeta, PagoEfectivo, PagoTransferencia
 * - Todos deben implementar procesarPago(double monto)
 * - Cada uno con validaciones y comportamientos específicos
 */

interface MetodoPago {
    boolean procesarPago(double monto);
    String obtenerComprobante();
}

/*
 * 🔴 EJERCICIO 4.2: PROCESADOR DE ARCHIVOS
 * Dificultad: Avanzado ⭐⭐⭐⭐
 * Tiempo: 1.5 horas
 * 
 * Crea procesadores para diferentes tipos de archivo:
 * - ProcesadorPDF, ProcesadorExcel, ProcesadorImagen
 * - Todos responden a: procesar(), validar(), obtenerInformacion()
 * - Cada uno con lógica específica según el tipo de archivo
 */

abstract class ProcesadorArchivo {
    // TU CÓDIGO AQUÍ
}

// ========================================
// NIVEL 5: PROYECTOS INTEGRADORES
// ========================================

/*
 * 🚀 PROYECTO 5.1: SISTEMA DE BIBLIOTECA
 * Dificultad: Avanzado ⭐⭐⭐⭐
 * Tiempo: 3-4 horas
 * 
 * Desarrolla un sistema completo con:
 * 
 * CLASES NECESARIAS:
 * - Libro (titulo, autor, isbn, disponible)
 * - Usuario (nombre, id, librosPrestados)
 * - Bibliotecario (hereda de Usuario, puede gestionar libros)
 * - Prestamo (libro, usuario, fechaPrestamo, fechaDevolucion)
 * - Biblioteca (gestiona todo el sistema)
 * 
 * FUNCIONALIDADES:
 * - Agregar/quitar libros
 * - Registrar usuarios
 * - Prestar libros (verificar disponibilidad)
 * - Devolver libros
 * - Consultar disponibilidad
 * - Listar libros prestados por usuario
 * - Calcular multas por retraso
 * 
 * VALIDACIONES:
 * - No prestar libros no disponibles
 * - Límite de libros por usuario (3 max)
 * - Validar fechas de devolución
 */

// Empezar aquí el proyecto de biblioteca
class SistemaBiblioteca {
    public static void main(String[] args) {
        // TU CÓDIGO AQUÍ
        // Tip: Empieza por las clases más simples (Libro, Usuario)
        // Luego construye la funcionalidad paso a paso
    }
}

/*
 * 🚀 PROYECTO 5.2: TIENDA ONLINE
 * Dificultad: Avanzado ⭐⭐⭐⭐⭐
 * Tiempo: 4-5 horas
 * 
 * Desarrolla una tienda online con:
 * 
 * CLASES NECESARIAS:
 * - Producto (id, nombre, precio, stock, categoria)
 * - Cliente (datos personales, historial compras)
 * - CarritoCompras (productos, cantidades, total)
 * - Pedido (cliente, productos, estado, fecha)
 * - Tienda (catálogo, clientes, procesamiento)
 * 
 * FUNCIONALIDADES:
 * - Catálogo de productos por categoría
 * - Búsqueda de productos
 * - Agregar/quitar del carrito
 * - Procesar pedidos
 * - Gestión de stock
 * - Historial de compras
 * - Diferentes tipos de descuento
 * - Estados de pedido (pendiente, procesando, enviado, entregado)
 */

/*
 * 🚀 PROYECTO 5.3: JUEGO RPG SIMPLE
 * Dificultad: Avanzado ⭐⭐⭐⭐⭐
 * Tiempo: 5-6 horas
 * 
 * Crea un juego de rol básico con:
 * 
 * CLASES NECESARIAS:
 * - Personaje (nombre, nivel, vida, experiencia)
 * - Guerrero, Mago, Arquero (diferentes habilidades)
 * - Enemigo (diferentes tipos con distintas dificultades)
 * - Objeto (armas, pociones, armaduras)
 * - Inventario (gestión de objetos)
 * - Batalla (sistema de combate)
 * - Juego (loop principal)
 * 
 * MECÁNICAS:
 * - Sistema de combate por turnos
 * - Subida de nivel y distribución de puntos
 * - Inventario con límite de peso
 * - Diferentes tipos de objetos y efectos
 * - Enemigos con IA básica
 * - Sistema de experiencia y recompensas
 */

// ========================================
// NIVEL 6: DESAFÍOS AVANZADOS
// ========================================

/*
 * 💎 DESAFÍO 6.1: PATRÓN OBSERVER
 * Implementa un sistema de notificaciones donde múltiples
 * observadores reaccionen a cambios en un objeto observado
 */

/*
 * 💎 DESAFÍO 6.2: PATRÓN FACTORY
 * Crea una fábrica que genere diferentes tipos de objetos
 * según parámetros de entrada
 */

/*
 * 💎 DESAFÍO 6.3: PATRÓN SINGLETON
 * Implementa un gestor de configuración que garantice
 * una sola instancia en toda la aplicación
 */

// ========================================
// 📋 PLAN DE ESTUDIO RECOMENDADO
// ========================================

/*
 * SEMANA 1: FUNDAMENTOS
 * - Día 1-2: Ejercicios Nivel 1 (Básicos)
 * - Día 3-4: Ejercicios Nivel 2 (Encapsulación)
 * - Día 5-7: Repaso y práctica adicional
 * 
 * SEMANA 2: CONCEPTOS AVANZADOS
 * - Día 1-3: Ejercicios Nivel 3 (Herencia)
 * - Día 4-5: Ejercicios Nivel 4 (Polimorfismo)
 * - Día 6-7: Preparación para proyectos
 * 
 * SEMANA 3: PROYECTOS INTEGRADORES
 * - Día 1-2: Proyecto 5.1 (Biblioteca)
 * - Día 3-4: Proyecto 5.2 (Tienda Online)
 * - Día 5-7: Proyecto 5.3 (Juego RPG)
 * 
 * SEMANA 4: PERFECCIONAMIENTO
 * - Día 1-3: Desafíos avanzados
 * - Día 4-5: Optimización de proyectos
 * - Día 6-7: Preparación para exámenes/entrevistas
 */

// ========================================
// 🎯 TIPS PARA MAXIMIZAR EL APRENDIZAJE
// ========================================

/*
 * 1. 📝 DOCUMENTA TU CÓDIGO
 *    - Explica qué hace cada clase y método
 *    - Usa nombres descriptivos para variables
 * 
 * 2. 🧪 PRUEBA TODO
 *    - Crea métodos main() para probar cada ejercicio
 *    - Prueba casos extremos (valores negativos, nulls, etc.)
 * 
 * 3. 🔍 DEBUGGEA PASO A PASO
 *    - Usa System.out.println() para ver valores
 *    - Usa el debugger de tu IDE
 * 
 * 4. 🏗️ CONSTRUYE GRADUALMENTE
 *    - Empieza con lo mínimo que funcione
 *    - Agrega funcionalidades de a poco
 * 
 * 5. 🤔 REFLEXIONA SOBRE EL DISEÑO
 *    - ¿Es esta la mejor forma de organizar las clases?
 *    - ¿Qué pasaría si necesito agregar nueva funcionalidad?
 * 
 * 6. 📚 CONSULTA RECURSOS
 *    - Documentación oficial de Java
 *    - Stack Overflow para dudas específicas
 *    - Tutoriales en YouTube para conceptos complejos
 * 
 * 7. 👥 BUSCA FEEDBACK
 *    - Comparte tu código con compañeros
 *    - Participa en foros de programación
 *    - Haz code reviews
 */

// ========================================
// 🚨 ERRORES COMUNES A EVITAR
// ========================================

/*
 * ❌ ERROR 1: NO USAR ENCAPSULACIÓN
 *    - Hacer todos los atributos public
 *    - No validar datos de entrada
 * 
 * ❌ ERROR 2: CLASES DEMASIADO GRANDES
 *    - Una clase que hace demasiadas cosas
 *    - Métodos muy largos (más de 20-30 líneas)
 * 
 * ❌ ERROR 3: NO MANEJAR EXCEPCIONES
 *    - División por cero
 *    - Valores null
 *    - Índices fuera de rango
 * 
 * ❌ ERROR 4: NOMBRES POCO DESCRIPTIVOS
 *    - Variables como "x", "data", "thing"
 *    - Métodos como "hacerAlgo()"
 * 
 * ❌ ERROR 5: NO SEGUIR CONVENCIONES
 *    - Nombres de clases en minúscula
 *    - Métodos con nombres en español e inglés mezclados
 * 
 * ❌ ERROR 6: DUPLICAR CÓDIGO
 *    - Copiar y pegar en lugar de crear métodos
 *    - No usar herencia cuando es apropiada
 */

// ========================================
// 📊 CÓMO EVALUAR TU PROGRESO
// ========================================

/*
 * DESPUÉS DE CADA EJERCICIO, PREGÚNTATE:
 * 
 * ✅ FUNCIONALIDAD
 * - ¿El código hace lo que se pidió?
 * - ¿Maneja casos especiales?
 * - ¿Es robusto ante entradas incorrectas?
 * 
 * ✅ DISEÑO
 * - ¿Las clases tienen responsabilidades claras?
 * - ¿Uso correctamente herencia y polimorfismo?
 * - ¿Es fácil agregar nueva funcionalidad?
 * 
 * ✅ CÓDIGO LIMPIO
 * - ¿Es fácil de leer y entender?
 * - ¿Los nombres son descriptivos?
 * - ¿Está bien documentado?
 * 
 * ✅ BUENAS PRÁCTICAS
 * - ¿Uso encapsulación apropiadamente?
 * - ¿Valido entradas de usuario?
 * - ¿Manejo errores correctamente?
 */

public class GuiaPracticaPOO {
    public static void main(String[] args) {
        System.out.println("🎓 ¡BIENVENIDO A LA GUÍA PRÁCTICA DE POO!");
        System.out.println("=" .repeat(50));
        System.out.println();
        System.out.println("📚 INSTRUCCIONES:");
        System.out.println("1. Empezá por el Nivel 1 (Ejercicios Básicos)");
        System.out.println("2. Completá cada ejercicio antes de pasar al siguiente");
        System.out.println("3. Probá tu código con diferentes casos de prueba");
        System.out.println("4. Usá el plan de estudio recomendado");
        System.out.println("5. No tengas miedo de experimentar y cometer errores");
        System.out.println();
        System.out.println("🚀 ¡El mejor momento para empezar es AHORA!");
        System.out.println("¡Mucha suerte en tu journey de aprendizaje!");
        
        // Ejemplo de cómo empezar
        System.out.println("\n--- EJEMPLO: Empezando con Ejercicio 1.1 ---");
        
        // Aquí es donde empezarías a implementar tu primera clase Mascota
        // Mascota miMascota = new Mascota("Firulais", "Perro", 2);
        // miMascota.jugar();
    }
}

/*
 * 🎉 MENSAJE FINAL
 * 
 * Recordá que aprender POO es como aprender a andar en bicicleta:
 * al principio puede parecer complicado, pero una vez que lo
 * entendés, se vuelve natural.
 * 
 * La clave está en la práctica constante y en no tener miedo
 * de experimentar. Cada error es una oportunidad de aprender.
 * 
 * ¡Vas a ser un excelente programador! 💪
 * 
 * RECURSOS ADICIONALES:
 * - Oracle Java Documentation
 * - Codecademy Java Course
 * - LeetCode para algoritmos
 * - GitHub para ver código de otros
 * - Stack Overflow para dudas
 * 
 * ¡Éxitos en tu carrera universitaria y futura carrera profesional! 🚀
 */
