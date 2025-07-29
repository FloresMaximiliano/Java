/*
 * 📖 EJEMPLOS RESUELTOS - GUÍA DE POO
 * 
 * Estos son ejemplos resueltos de algunos ejercicios de la guía
 * para que veas cómo implementarlos correctamente.
 * 
 * 💡 TIP: Intenta resolver los ejercicios por tu cuenta primero,
 * y luego compara con estos ejemplos.
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// ========================================
// EJEMPLO RESUELTO: EJERCICIO 1.1 - MASCOTA
// ========================================

class MascotaEjemplo {
    // Atributos
    private String nombre;
    private String especie;
    private int edad;
    private boolean durmiendo;
    private int energia; // 0-100
    
    // Constructor
    public MascotaEjemplo(String nombre, String especie, int edad) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.durmiendo = false;
        this.energia = 100;
    }
    
    // Métodos
    public void dormir() {
        if (durmiendo) {
            System.out.println(nombre + " ya está durmiendo 😴");
        } else {
            durmiendo = true;
            energia = 100; // Restaura energía
            System.out.println(nombre + " se fue a dormir. Zzz... 😴");
        }
    }
    
    public void jugar() {
        if (durmiendo) {
            System.out.println(nombre + " está durmiendo. No puede jugar ahora.");
            return;
        }
        
        if (energia < 20) {
            System.out.println(nombre + " está muy cansado para jugar. Necesita dormir.");
            return;
        }
        
        durmiendo = false;
        energia -= 20;
        System.out.println(nombre + " está jugando alegremente! 🎾 (Energía: " + energia + "%)");
    }
    
    public void hacerSonido() {
        if (durmiendo) {
            System.out.println(nombre + " hace ruidos mientras duerme... 😴");
            return;
        }
        
        switch (especie.toLowerCase()) {
            case "perro":
                System.out.println(nombre + " dice: ¡Guau guau! 🐕");
                break;
            case "gato":
                System.out.println(nombre + " dice: ¡Miau miau! 🐱");
                break;
            case "pajaro":
                System.out.println(nombre + " dice: ¡Pío pío! 🐦");
                break;
            default:
                System.out.println(nombre + " hace un sonido característico de " + especie);
        }
    }
    
    public void despertar() {
        if (durmiendo) {
            durmiendo = false;
            System.out.println(nombre + " se despertó! 😊");
        } else {
            System.out.println(nombre + " ya está despierto!");
        }
    }
    
    // Getters
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public int getEdad() { return edad; }
    public int getEnergia() { return energia; }
    public boolean estaDurmiendo() { return durmiendo; }
}

// ========================================
// EJEMPLO RESUELTO: EJERCICIO 2.1 - CUENTA USUARIO
// ========================================

class CuentaUsuarioEjemplo {
    // Atributos privados (encapsulación)
    private String nombreUsuario;
    private String contraseña;
    private String email;
    private boolean activo;
    private LocalDateTime fechaCreacion;
    private int intentosFallidos;
    private static final int MAX_INTENTOS = 3;
    
    // Constructor con validaciones
    public CuentaUsuarioEjemplo(String nombreUsuario, String contraseña, String email) {
        // Validar email
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email inválido. Debe contener @ y un dominio");
        }
        
        // Validar nombre de usuario
        if (nombreUsuario == null || nombreUsuario.trim().length() < 3) {
            throw new IllegalArgumentException("Nombre de usuario debe tener al menos 3 caracteres");
        }
        
        // Validar contraseña
        if (!esContraseñaSegura(contraseña)) {
            throw new IllegalArgumentException("Contraseña debe tener al menos 8 caracteres, una mayúscula y un número");
        }
        
        this.nombreUsuario = nombreUsuario.trim();
        this.contraseña = contraseña;
        this.email = email.toLowerCase().trim();
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
        this.intentosFallidos = 0;
        
        System.out.println("✅ Cuenta creada exitosamente para: " + nombreUsuario);
    }
    
    // Getters
    public String getNombreUsuario() { return nombreUsuario; }
    public String getEmail() { return email; }
    public boolean isActivo() { return activo; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    
    // Setters con validación
    public void setEmail(String nuevoEmail) {
        if (!nuevoEmail.contains("@") || !nuevoEmail.contains(".")) {
            System.out.println("❌ Email inválido");
            return;
        }
        this.email = nuevoEmail.toLowerCase().trim();
        System.out.println("✅ Email actualizado");
    }
    
    public void setNombreUsuario(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.trim().length() < 3) {
            System.out.println("❌ Nombre de usuario debe tener al menos 3 caracteres");
            return;
        }
        this.nombreUsuario = nuevoNombre.trim();
        System.out.println("✅ Nombre de usuario actualizado");
    }
    
    // Método para cambiar contraseña
    public boolean cambiarContraseña(String contraseñaActual, String nuevaContraseña) {
        if (!activo) {
            System.out.println("❌ Cuenta inactiva");
            return false;
        }
        
        if (!contraseña.equals(contraseñaActual)) {
            intentosFallidos++;
            System.out.println("❌ Contraseña actual incorrecta. Intentos: " + intentosFallidos + "/" + MAX_INTENTOS);
            
            if (intentosFallidos >= MAX_INTENTOS) {
                desactivarCuenta();
                System.out.println("🔒 Cuenta bloqueada por múltiples intentos fallidos");
            }
            return false;
        }
        
        if (!esContraseñaSegura(nuevaContraseña)) {
            System.out.println("❌ La nueva contraseña no cumple con los requisitos de seguridad");
            return false;
        }
        
        this.contraseña = nuevaContraseña;
        this.intentosFallidos = 0; // Resetear intentos fallidos
        System.out.println("✅ Contraseña cambiada exitosamente");
        return true;
    }
    
    // Método para activar/desactivar cuenta
    public void activarCuenta() {
        this.activo = true;
        this.intentosFallidos = 0;
        System.out.println("✅ Cuenta activada");
    }
    
    public void desactivarCuenta() {
        this.activo = false;
        System.out.println("❌ Cuenta desactivada");
    }
    
    // Método para verificar login
    public boolean login(String contraseñaIngresada) {
        if (!activo) {
            System.out.println("❌ Cuenta inactiva");
            return false;
        }
        
        if (contraseña.equals(contraseñaIngresada)) {
            intentosFallidos = 0;
            System.out.println("✅ Login exitoso. ¡Bienvenido " + nombreUsuario + "!");
            return true;
        } else {
            intentosFallidos++;
            System.out.println("❌ Contraseña incorrecta. Intentos: " + intentosFallidos + "/" + MAX_INTENTOS);
            
            if (intentosFallidos >= MAX_INTENTOS) {
                desactivarCuenta();
            }
            return false;
        }
    }
    
    // Método auxiliar para validar contraseña
    private boolean esContraseñaSegura(String contraseña) {
        if (contraseña == null || contraseña.length() < 8) {
            return false;
        }
        
        boolean tieneMayuscula = false;
        boolean tieneNumero = false;
        
        for (char c : contraseña.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            }
            if (Character.isDigit(c)) {
                tieneNumero = true;
            }
        }
        
        return tieneMayuscula && tieneNumero;
    }
    
    public void mostrarInformacion() {
        System.out.println("\n📋 INFORMACIÓN DE CUENTA:");
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Email: " + email);
        System.out.println("Estado: " + (activo ? "Activa" : "Inactiva"));
        System.out.println("Fecha creación: " + fechaCreacion.toLocalDate());
        System.out.println("Intentos fallidos: " + intentosFallidos + "/" + MAX_INTENTOS);
    }
}

// ========================================
// EJEMPLO RESUELTO: EJERCICIO 3.1 - JERARQUÍA EMPLEADOS
// ========================================

// Clase base abstracta
abstract class EmpleadoEjemplo {
    protected String id;
    protected String nombre;
    protected String email;
    protected String departamento;
    protected LocalDateTime fechaIngreso;
    protected double salarioBase;
    protected boolean activo;
    
    public EmpleadoEjemplo(String id, String nombre, String email, String departamento, double salarioBase) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.departamento = departamento;
        this.salarioBase = salarioBase;
        this.fechaIngreso = LocalDateTime.now();
        this.activo = true;
    }
    
    // Métodos abstractos que cada subclase debe implementar
    public abstract double calcularSalario();
    public abstract String obtenerDetallesEspecificos();
    
    // Métodos comunes
    public int calcularAñosServicio() {
        return LocalDateTime.now().getYear() - fechaIngreso.getYear();
    }
    
    public double calcularBonoAntiguedad() {
        return calcularAñosServicio() * (salarioBase * 0.02); // 2% por año
    }
    
    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public double getSalarioBase() { return salarioBase; }
    
    public void mostrarInformacion() {
        System.out.println("\n👤 " + nombre + " (" + id + ")");
        System.out.println("Departamento: " + departamento);
        System.out.println("Años de servicio: " + calcularAñosServicio());
        System.out.println("Salario total: $" + String.format("%.2f", calcularSalario()));
        System.out.println("Detalles: " + obtenerDetallesEspecificos());
    }
}

// Empleado de ventas
class EmpleadoVentasEjemplo extends EmpleadoEjemplo {
    private double ventasDelMes;
    private double porcentajeComision;
    private double metaMensual;
    
    public EmpleadoVentasEjemplo(String id, String nombre, String email, String departamento, 
                               double salarioBase, double porcentajeComision, double metaMensual) {
        super(id, nombre, email, departamento, salarioBase);
        this.porcentajeComision = porcentajeComision;
        this.metaMensual = metaMensual;
        this.ventasDelMes = 0;
    }
    
    public void registrarVenta(double montoVenta) {
        ventasDelMes += montoVenta;
        System.out.println("💰 Venta registrada: $" + montoVenta + " (Total del mes: $" + ventasDelMes + ")");
    }
    
    @Override
    public double calcularSalario() {
        double comision = ventasDelMes * (porcentajeComision / 100);
        double bonoMeta = (ventasDelMes >= metaMensual) ? salarioBase * 0.1 : 0; // 10% si cumple meta
        return salarioBase + calcularBonoAntiguedad() + comision + bonoMeta;
    }
    
    @Override
    public String obtenerDetallesEspecificos() {
        double porcentajeMeta = (ventasDelMes / metaMensual) * 100;
        return String.format("Ventas: $%.2f (%.1f%% de meta), Comisión: %.1f%%", 
                           ventasDelMes, porcentajeMeta, porcentajeComision);
    }
    
    public void reiniciarVentasMes() {
        ventasDelMes = 0;
    }
}

// Empleado técnico
class EmpleadoTecnicoEjemplo extends EmpleadoEjemplo {
    private List<String> certificaciones;
    private String especialidad;
    private int proyectosCompletos;
    
    public EmpleadoTecnicoEjemplo(String id, String nombre, String email, String departamento,
                                double salarioBase, String especialidad) {
        super(id, nombre, email, departamento, salarioBase);
        this.especialidad = especialidad;
        this.certificaciones = new ArrayList<>();
        this.proyectosCompletos = 0;
    }
    
    public void agregarCertificacion(String certificacion) {
        certificaciones.add(certificacion);
        System.out.println("🎓 Nueva certificación agregada: " + certificacion);
    }
    
    public void completarProyecto() {
        proyectosCompletos++;
        System.out.println("✅ Proyecto completado. Total: " + proyectosCompletos);
    }
    
    @Override
    public double calcularSalario() {
        double bonoCertificaciones = certificaciones.size() * 2000; // $2000 por certificación
        double bonoProyectos = proyectosCompletos * 1500; // $1500 por proyecto
        return salarioBase + calcularBonoAntiguedad() + bonoCertificaciones + bonoProyectos;
    }
    
    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Especialidad: %s, Certificaciones: %d, Proyectos: %d",
                           especialidad, certificaciones.size(), proyectosCompletos);
    }
}

// Gerente
class GerenteEjemplo extends EmpleadoEjemplo {
    private List<EmpleadoEjemplo> equipoACargo;
    private double presupuestoAnual;
    private double gastosActuales;
    
    public GerenteEjemplo(String id, String nombre, String email, String departamento,
                        double salarioBase, double presupuestoAnual) {
        super(id, nombre, email, departamento, salarioBase);
        this.equipoACargo = new ArrayList<>();
        this.presupuestoAnual = presupuestoAnual;
        this.gastosActuales = 0;
    }
    
    public void agregarEmpleado(EmpleadoEjemplo empleado) {
        equipoACargo.add(empleado);
        System.out.println("👥 " + empleado.getNombre() + " agregado al equipo de " + nombre);
    }
    
    public void registrarGasto(double monto, String concepto) {
        if (gastosActuales + monto <= presupuestoAnual) {
            gastosActuales += monto;
            System.out.println("💸 Gasto registrado: $" + monto + " - " + concepto);
        } else {
            System.out.println("❌ Gasto rechazado: excede el presupuesto disponible");
        }
    }
    
    @Override
    public double calcularSalario() {
        double bonoEquipo = equipoACargo.size() * 3000; // $3000 por empleado a cargo
        double bonoPresupuesto = (presupuestoAnual - gastosActuales) * 0.01; // 1% del presupuesto no usado
        return salarioBase + calcularBonoAntiguedad() + bonoEquipo + bonoPresupuesto;
    }
    
    @Override
    public String obtenerDetallesEspecificos() {
        double porcentajePresupuesto = (gastosActuales / presupuestoAnual) * 100;
        return String.format("Equipo a cargo: %d personas, Presupuesto usado: %.1f%%",
                           equipoACargo.size(), porcentajePresupuesto);
    }
    
    public void mostrarEquipo() {
        System.out.println("\n👥 EQUIPO DE " + nombre.toUpperCase() + ":");
        for (EmpleadoEjemplo emp : equipoACargo) {
            System.out.println("  - " + emp.getNombre() + " (" + emp.getDepartamento() + ")");
        }
    }
}

// ========================================
// CLASE PRINCIPAL CON EJEMPLOS
// ========================================

public class EjemplosResueltos {
    public static void main(String[] args) {
        System.out.println("🎓 EJEMPLOS RESUELTOS DE POO");
        System.out.println("=" .repeat(50));
        
        // ===== EJEMPLO 1: MASCOTA =====
        System.out.println("\n🐕 EJEMPLO 1: CLASE MASCOTA");
        System.out.println("-" .repeat(30));
        
        MascotaEjemplo perro = new MascotaEjemplo("Max", "Perro", 3);
        MascotaEjemplo gato = new MascotaEjemplo("Whiskers", "Gato", 2);
        
        perro.hacerSonido();
        perro.jugar();
        perro.jugar(); // Menos energía
        perro.dormir();
        perro.jugar(); // No puede jugar mientras duerme
        perro.despertar();
        perro.jugar(); // Ahora puede jugar
        
        gato.hacerSonido();
        gato.jugar();
        
        // ===== EJEMPLO 2: CUENTA USUARIO =====
        System.out.println("\n👤 EJEMPLO 2: CUENTA USUARIO");
        System.out.println("-" .repeat(30));
        
        try {
            CuentaUsuarioEjemplo cuenta = new CuentaUsuarioEjemplo("juanperez", "MiPassword123", "juan@email.com");
            cuenta.mostrarInformacion();
            
            // Intentar login
            cuenta.login("MiPassword123"); // Correcto
            cuenta.login("contraseña_incorrecta"); // Incorrecto
            
            // Cambiar contraseña
            cuenta.cambiarContraseña("MiPassword123", "NuevaPassword456");
            
            // Cambiar email
            cuenta.setEmail("juan.nuevo@email.com");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear cuenta: " + e.getMessage());
        }
        
        // ===== EJEMPLO 3: JERARQUÍA DE EMPLEADOS =====
        System.out.println("\n👥 EJEMPLO 3: EMPLEADOS");
        System.out.println("-" .repeat(30));
        
        // Crear diferentes tipos de empleados
        EmpleadoVentasEjemplo vendedor = new EmpleadoVentasEjemplo("V001", "Ana Vendedora", 
                                                                 "ana@empresa.com", "Ventas", 
                                                                 80000, 5.0, 100000);
        
        EmpleadoTecnicoEjemplo desarrollador = new EmpleadoTecnicoEjemplo("T001", "Carlos Dev",
                                                                        "carlos@empresa.com", "IT",
                                                                        120000, "Java Backend");
        
        GerenteEjemplo gerente = new GerenteEjemplo("G001", "María Gerente",
                                                  "maria@empresa.com", "IT",
                                                  150000, 500000);
        
        // Simular actividades
        vendedor.registrarVenta(25000);
        vendedor.registrarVenta(30000);
        vendedor.registrarVenta(50000); // Supera la meta
        
        desarrollador.agregarCertificacion("Oracle Java SE");
        desarrollador.agregarCertificacion("AWS Solutions Architect");
        desarrollador.completarProyecto();
        desarrollador.completarProyecto();
        
        gerente.agregarEmpleado(vendedor);
        gerente.agregarEmpleado(desarrollador);
        gerente.registrarGasto(50000, "Nuevo servidor");
        gerente.registrarGasto(25000, "Licencias de software");
        
        // Mostrar información de todos
        vendedor.mostrarInformacion();
        desarrollador.mostrarInformacion();
        gerente.mostrarInformacion();
        gerente.mostrarEquipo();
        
        System.out.println("\n✨ CONCEPTOS DEMOSTRADOS:");
        System.out.println("✅ Encapsulación: Atributos privados con acceso controlado");
        System.out.println("✅ Herencia: EmpleadoVentas, EmpleadoTecnico, Gerente extienden Empleado");
        System.out.println("✅ Polimorfismo: Cada empleado calcula su salario diferente");
        System.out.println("✅ Abstracción: Clase base Empleado con métodos abstractos");
        System.out.println("✅ Validaciones: Control de datos de entrada");
        System.out.println("✅ Estado: Objetos mantienen y modifican su estado");
        
        System.out.println("\n🎯 PRÓXIMOS PASOS:");
        System.out.println("1. Implementa los otros ejercicios de la guía");
        System.out.println("2. Experimenta modificando estos ejemplos");
        System.out.println("3. Agrega nueva funcionalidad");
        System.out.println("4. Intenta los proyectos integradores");
    }
}

/*
 * 📚 ANÁLISIS DE LOS EJEMPLOS:
 * 
 * MASCOTA:
 * - Demuestra encapsulación básica
 * - Manejo de estado (durmiendo, energía)
 * - Validaciones simples
 * - Métodos que interactúan entre sí
 * 
 * CUENTA USUARIO:
 * - Encapsulación avanzada (datos privados)
 * - Validaciones complejas
 * - Manejo de errores y excepciones
 * - Seguridad (intentos fallidos, bloqueo)
 * 
 * EMPLEADOS:
 * - Herencia multinivel
 * - Polimorfismo (calcularSalario diferente)
 * - Métodos abstractos
 * - Composición (listas de objetos)
 * - Cálculos complejos de negocio
 * 
 * ESTOS EJEMPLOS TE MUESTRAN:
 * ✅ Cómo estructurar clases correctamente
 * ✅ Cómo usar encapsulación para proteger datos
 * ✅ Cómo implementar herencia y polimorfismo
 * ✅ Cómo manejar validaciones y errores
 * ✅ Cómo crear objetos que colaboran entre sí
 * ✅ Cómo escribir código limpio y profesional
 */
