/*
 * PROGRAMACIÓN ORIENTADA A OBJETOS EN EL MUNDO LABORAL
 * Ejemplos prácticos de cómo se usa POO en empresas reales
 * 
 * CASOS DE USO EMPRESARIALES:
 * 1. Sistema de E-commerce (Amazon, MercadoLibre)
 * 2. Sistema Bancario (Bancos, Fintech)
 * 3. Sistema de Gestión de Empleados (RRHH)
 * 4. API REST para microservicios
 * 5. Sistema de Inventario
 * 6. Plataforma de Streaming (Netflix, Spotify)
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// ========================================
// 1. SISTEMA DE E-COMMERCE
// ========================================

/*
 * En empresas como Amazon, MercadoLibre, etc.
 * Este patrón se usa para manejar productos, carritos, usuarios
 */

// Clase base para todos los productos
abstract class Producto {
    protected String id;
    protected String nombre;
    protected double precio;
    protected int stock;
    protected String categoria;
    protected boolean activo;
    
    public Producto(String id, String nombre, double precio, int stock, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.activo = true;
    }
    
    // Método abstracto que cada tipo de producto implementa diferente
    public abstract double calcularPrecioFinal();
    public abstract String obtenerDetallesEspecificos();
    
    // Métodos comunes para todos los productos
    public boolean hayStock(int cantidad) {
        return stock >= cantidad && activo;
    }
    
    public boolean reducirStock(int cantidad) {
        if (hayStock(cantidad)) {
            stock -= cantidad;
            return true;
        }
        return false;
    }
    
    // Getters y setters (en el mundo real usarías frameworks como Spring)
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getCategoria() { return categoria; }
}

// Productos específicos (Polimorfismo en acción)
class ProductoFisico extends Producto {
    private double peso;
    private String dimensiones;
    private double costoEnvio;
    
    public ProductoFisico(String id, String nombre, double precio, int stock, 
                         String categoria, double peso, String dimensiones) {
        super(id, nombre, precio, stock, categoria);
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.costoEnvio = calcularCostoEnvio();
    }
    
    @Override
    public double calcularPrecioFinal() {
        // Producto físico incluye costo de envío
        return precio + costoEnvio;
    }
    
    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Peso: %.2f kg, Dimensiones: %s, Envío: $%.2f", 
                           peso, dimensiones, costoEnvio);
    }
    
    private double calcularCostoEnvio() {
        // Lógica empresarial real: envío basado en peso
        return peso * 10.0; // $10 por kg
    }
}

class ProductoDigital extends Producto {
    private String formatoArchivo;
    private long tamanioMB;
    private String linkDescarga;
    
    public ProductoDigital(String id, String nombre, double precio, int stock,
                          String categoria, String formatoArchivo, long tamanioMB) {
        super(id, nombre, precio, stock, categoria);
        this.formatoArchivo = formatoArchivo;
        this.tamanioMB = tamanioMB;
        this.linkDescarga = generarLinkDescarga();
    }
    
    @Override
    public double calcularPrecioFinal() {
        // Producto digital: sin costo de envío, pero con impuesto digital
        return precio * 1.05; // 5% impuesto digital
    }
    
    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Formato: %s, Tamaño: %d MB, Link: %s", 
                           formatoArchivo, tamanioMB, linkDescarga);
    }
    
    private String generarLinkDescarga() {
        return "https://downloads.empresa.com/" + id + "/" + formatoArchivo;
    }
}

// Usuario del sistema (Encapsulación de datos sensibles)
class Usuario {
    private String id;
    private String email;
    private String nombre;
    private String direccion;
    private List<Producto> historialCompras;
    private CarritoCompras carrito;
    private TipoUsuario tipo;
    
    public enum TipoUsuario {
        CLIENTE_REGULAR, CLIENTE_PREMIUM, VENDEDOR, ADMIN
    }
    
    public Usuario(String id, String email, String nombre, String direccion, TipoUsuario tipo) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipo = tipo;
        this.historialCompras = new ArrayList<>();
        this.carrito = new CarritoCompras();
    }
    
    public double obtenerDescuento() {
        // Polimorfismo basado en tipo de usuario
        switch (tipo) {
            case CLIENTE_PREMIUM: return 0.15; // 15% descuento
            case VENDEDOR: return 0.20; // 20% descuento
            default: return 0.0;
        }
    }
    
    public void agregarAlCarrito(Producto producto, int cantidad) {
        carrito.agregarProducto(producto, cantidad);
    }
    
    public void realizarCompra() {
        List<Producto> productosComprados = carrito.procesarCompra(this);
        historialCompras.addAll(productosComprados);
        carrito.limpiar();
        System.out.println("📦 Productos serán enviados a: " + direccion);
    }
    
    // Getters
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getNombre() { return nombre; }
    public CarritoCompras getCarrito() { return carrito; }
}

// Carrito de compras (Patrón común en e-commerce)
class CarritoCompras {
    private Map<Producto, Integer> productos;
    private LocalDateTime fechaCreacion;
    
    public CarritoCompras() {
        this.productos = new HashMap<>();
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public void agregarProducto(Producto producto, int cantidad) {
        if (producto.hayStock(cantidad)) {
            productos.put(producto, productos.getOrDefault(producto, 0) + cantidad);
            System.out.println("✅ Agregado al carrito: " + cantidad + "x " + producto.getNombre());
        } else {
            System.out.println("❌ Stock insuficiente para: " + producto.getNombre());
        }
    }
    
    public double calcularTotal(Usuario usuario) {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            total += producto.calcularPrecioFinal() * cantidad;
        }
        
        // Aplicar descuento del usuario
        total *= (1 - usuario.obtenerDescuento());
        return total;
    }
    
    public List<Producto> procesarCompra(Usuario usuario) {
        List<Producto> productosComprados = new ArrayList<>();
        double total = calcularTotal(usuario);
        
        System.out.println("🛒 Procesando compra para: " + usuario.getNombre());
        System.out.println("💰 Total a pagar: $" + String.format("%.2f", total));
        
        // Reducir stock de cada producto
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            if (producto.reducirStock(cantidad)) {
                productosComprados.add(producto);
            }
        }
        
        return productosComprados;
    }
    
    public void limpiar() {
        productos.clear();
    }
    
    public void mostrarResumen() {
        System.out.println("\n📋 RESUMEN DEL CARRITO:");
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            System.out.printf("- %dx %s: $%.2f c/u\n", 
                            cantidad, producto.getNombre(), producto.calcularPrecioFinal());
        }
    }
}

// ========================================
// 2. SISTEMA BANCARIO (FINTECH)
// ========================================

/*
 * Usado en bancos, fintech como Mercado Pago, Ualá, etc.
 * Manejo de diferentes tipos de cuentas y transacciones
 */

// Clase abstracta para diferentes tipos de cuenta
abstract class CuentaBancaria {
    protected String numeroCuenta;
    protected String titular;
    protected double saldo;
    protected List<Transaccion> historialTransacciones;
    protected boolean activa;
    
    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historialTransacciones = new ArrayList<>();
        this.activa = true;
    }
    
    // Métodos abstractos (cada tipo de cuenta los implementa diferente)
    public abstract boolean puedeRetirar(double monto);
    public abstract double calcularComision(double monto);
    public abstract double getLimiteTransferencia();
    
    // Método común para todas las cuentas
    public boolean transferir(double monto, CuentaBancaria cuentaDestino) {
        if (!activa || !cuentaDestino.activa) {
            System.out.println("❌ Una de las cuentas está inactiva");
            return false;
        }
        
        if (monto > getLimiteTransferencia()) {
            System.out.println("❌ Monto supera el límite de transferencia");
            return false;
        }
        
        if (!puedeRetirar(monto)) {
            System.out.println("❌ Fondos insuficientes");
            return false;
        }
        
        double comision = calcularComision(monto);
        double montoTotal = monto + comision;
        
        this.saldo -= montoTotal;
        cuentaDestino.saldo += monto;
        
        // Registrar transacciones
        this.registrarTransaccion(new Transaccion("TRANSFERENCIA_ENVIADA", -montoTotal, 
                                  "Transferencia a " + cuentaDestino.numeroCuenta));
        cuentaDestino.registrarTransaccion(new Transaccion("TRANSFERENCIA_RECIBIDA", monto,
                                          "Transferencia desde " + this.numeroCuenta));
        
        System.out.printf("✅ Transferencia exitosa: $%.2f (comisión: $%.2f)\n", monto, comision);
        return true;
    }
    
    protected void registrarTransaccion(Transaccion transaccion) {
        historialTransacciones.add(transaccion);
    }
    
    public void mostrarEstadoCuenta() {
        System.out.println("\n💳 ESTADO DE CUENTA: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.printf("Saldo actual: $%.2f\n", saldo);
        System.out.println("Últimas 5 transacciones:");
        
        int inicio = Math.max(0, historialTransacciones.size() - 5);
        for (int i = inicio; i < historialTransacciones.size(); i++) {
            System.out.println("  " + historialTransacciones.get(i));
        }
    }
    
    // Getters
    public String getNumeroCuenta() { return numeroCuenta; }
    public double getSaldo() { return saldo; }
}

// Diferentes tipos de cuenta (Herencia + Polimorfismo)
class CuentaAhorro extends CuentaBancaria {
    private final double LIMITE_RETIRO_DIARIO = 5000.0;
    private final double COMISION_FIJA = 50.0;
    
    public CuentaAhorro(String numeroCuenta, String titular, double saldoInicial) {
        super(numeroCuenta, titular, saldoInicial);
    }
    
    @Override
    public boolean puedeRetirar(double monto) {
        return saldo >= monto && monto <= LIMITE_RETIRO_DIARIO;
    }
    
    @Override
    public double calcularComision(double monto) {
        // Cuentas de ahorro: comisión fija
        return COMISION_FIJA;
    }
    
    @Override
    public double getLimiteTransferencia() {
        return LIMITE_RETIRO_DIARIO;
    }
}

class CuentaCorriente extends CuentaBancaria {
    private double sobregiro;
    private final double LIMITE_SOBREGIRO = 10000.0;
    private final double COMISION_PORCENTUAL = 0.005; // 0.5%
    
    public CuentaCorriente(String numeroCuenta, String titular, double saldoInicial) {
        super(numeroCuenta, titular, saldoInicial);
        this.sobregiro = 0;
    }
    
    @Override
    public boolean puedeRetirar(double monto) {
        return (saldo + LIMITE_SOBREGIRO - sobregiro) >= monto;
    }
    
    @Override
    public double calcularComision(double monto) {
        // Cuenta corriente: comisión porcentual
        return monto * COMISION_PORCENTUAL;
    }
    
    @Override
    public double getLimiteTransferencia() {
        return 50000.0; // Mayor límite para cuenta corriente
    }
}

class CuentaPremium extends CuentaBancaria {
    private final double LIMITE_SIN_COMISION = 10000.0;
    
    public CuentaPremium(String numeroCuenta, String titular, double saldoInicial) {
        super(numeroCuenta, titular, saldoInicial);
    }
    
    @Override
    public boolean puedeRetirar(double monto) {
        return saldo >= monto; // Sin límites de retiro
    }
    
    @Override
    public double calcularComision(double monto) {
        // Premium: sin comisión hasta cierto monto
        return monto > LIMITE_SIN_COMISION ? (monto - LIMITE_SIN_COMISION) * 0.001 : 0;
    }
    
    @Override
    public double getLimiteTransferencia() {
        return 100000.0; // Límite muy alto
    }
}

// Clase para manejar transacciones
class Transaccion {
    private String tipo;
    private double monto;
    private String descripcion;
    private LocalDateTime fecha;
    
    public Transaccion(String tipo, double monto, String descripcion) {
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("[%s] %s: $%.2f - %s", 
                           fecha.format(formatter), tipo, monto, descripcion);
    }
}

// ========================================
// 3. SISTEMA DE GESTIÓN DE EMPLEADOS
// ========================================

/*
 * Usado en departamentos de RRHH de cualquier empresa
 * Manejo de diferentes tipos de empleados y nóminas
 */

// Interfaz para calcular salarios (Strategy Pattern)
interface CalculadorSalario {
    double calcularSalario();
    double calcularBonificaciones();
}

// Clase base para empleados
abstract class Empleado implements CalculadorSalario {
    protected String id;
    protected String nombre;
    protected String email;
    protected String departamento;
    protected LocalDateTime fechaIngreso;
    protected boolean activo;
    
    public Empleado(String id, String nombre, String email, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.departamento = departamento;
        this.fechaIngreso = LocalDateTime.now();
        this.activo = true;
    }
    
    // Método común para calcular antigüedad
    public int calcularAntiguedad() {
        return LocalDateTime.now().getYear() - fechaIngreso.getYear();
    }
    
    public abstract String obtenerDetallesContrato();
    
    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
}

// Diferentes tipos de empleados
class EmpleadoTiempoCompleto extends Empleado {
    private double salarioBase;
    private double bonoPorAntiguedad;
    
    public EmpleadoTiempoCompleto(String id, String nombre, String email, 
                                 String departamento, double salarioBase) {
        super(id, nombre, email, departamento);
        this.salarioBase = salarioBase;
        this.bonoPorAntiguedad = 1000.0; // Por año de antigüedad
    }
    
    @Override
    public double calcularSalario() {
        return salarioBase;
    }
    
    @Override
    public double calcularBonificaciones() {
        return calcularAntiguedad() * bonoPorAntiguedad;
    }
    
    @Override
    public String obtenerDetallesContrato() {
        return "Tiempo Completo - Salario fijo mensual";
    }
}

class EmpleadoPorHoras extends Empleado {
    private double tarifaPorHora;
    private int horasTrabajadasMes;
    
    public EmpleadoPorHoras(String id, String nombre, String email, 
                           String departamento, double tarifaPorHora) {
        super(id, nombre, email, departamento);
        this.tarifaPorHora = tarifaPorHora;
        this.horasTrabajadasMes = 0;
    }
    
    public void registrarHoras(int horas) {
        this.horasTrabajadasMes += horas;
    }
    
    @Override
    public double calcularSalario() {
        double salarioBase = horasTrabajadasMes * tarifaPorHora;
        // Horas extra (más de 160 horas al mes)
        if (horasTrabajadasMes > 160) {
            int horasExtra = horasTrabajadasMes - 160;
            salarioBase += horasExtra * tarifaPorHora * 0.5; // 50% más por hora extra
        }
        return salarioBase;
    }
    
    @Override
    public double calcularBonificaciones() {
        // Bono por productividad si trabaja más de 180 horas
        return horasTrabajadasMes > 180 ? 5000.0 : 0;
    }
    
    @Override
    public String obtenerDetallesContrato() {
        return String.format("Por Horas - $%.2f/hora, %d horas este mes", 
                           tarifaPorHora, horasTrabajadasMes);
    }
    
    public void reiniciarHorasMes() {
        this.horasTrabajadasMes = 0;
    }
}

class Freelancer extends Empleado {
    private List<Proyecto> proyectos;
    
    public Freelancer(String id, String nombre, String email, String departamento) {
        super(id, nombre, email, departamento);
        this.proyectos = new ArrayList<>();
    }
    
    public void agregarProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
    }
    
    @Override
    public double calcularSalario() {
        return proyectos.stream()
                       .filter(p -> p.estaCompletado())
                       .mapToDouble(Proyecto::getMonto)
                       .sum();
    }
    
    @Override
    public double calcularBonificaciones() {
        // Bono por cantidad de proyectos completados
        long proyectosCompletados = proyectos.stream()
                                           .filter(Proyecto::estaCompletado)
                                           .count();
        return proyectosCompletados * 2000.0;
    }
    
    @Override
    public String obtenerDetallesContrato() {
        return String.format("Freelancer - %d proyectos activos", proyectos.size());
    }
}

// Clase auxiliar para proyectos de freelancers
class Proyecto {
    private String nombre;
    private double monto;
    private boolean completado;
    private LocalDateTime fechaEntrega;
    
    public Proyecto(String nombre, double monto, LocalDateTime fechaEntrega) {
        this.nombre = nombre;
        this.monto = monto;
        this.fechaEntrega = fechaEntrega;
        this.completado = false;
    }
    
    public void marcarCompletado() {
        this.completado = true;
    }
    
    public boolean estaCompletado() {
        return completado;
    }
    
    public double getMonto() {
        return monto;
    }
    
    public String getNombre() {
        return nombre;
    }
}

// Gestor de nómina (usa polimorfismo para diferentes tipos de empleados)
class GestorNomina {
    private List<Empleado> empleados;
    
    public GestorNomina() {
        this.empleados = new ArrayList<>();
    }
    
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        System.out.println("✅ Empleado agregado: " + empleado.getNombre());
    }
    
    public void procesarNomina() {
        System.out.println("\n💰 PROCESANDO NÓMINA MENSUAL");
        System.out.println("=" .repeat(50));
        
        double totalNomina = 0;
        
        for (Empleado empleado : empleados) {
            if (empleado.activo) {
                double salario = empleado.calcularSalario();
                double bonificaciones = empleado.calcularBonificaciones();
                double total = salario + bonificaciones;
                totalNomina += total;
                
                System.out.printf("👤 %s (%s)\n", empleado.getNombre(), empleado.getDepartamento());
                System.out.printf("   Salario: $%.2f\n", salario);
                System.out.printf("   Bonificaciones: $%.2f\n", bonificaciones);
                System.out.printf("   Total: $%.2f\n", total);
                System.out.printf("   Detalles: %s\n\n", empleado.obtenerDetallesContrato());
            }
        }
        
        System.out.printf("💵 TOTAL NÓMINA: $%.2f\n", totalNomina);
    }
    
    public List<Empleado> buscarPorDepartamento(String departamento) {
        return empleados.stream()
                       .filter(e -> e.getDepartamento().equalsIgnoreCase(departamento))
                       .toList();
    }
}

// ========================================
// 4. EJEMPLO PRINCIPAL - CASOS DE USO REALES
// ========================================

public class POO_Laboral {
    public static void main(String[] args) {
        System.out.println("🏢 EJEMPLOS DE POO EN EL MUNDO LABORAL");
        System.out.println("=" .repeat(60));
        
        // ===== CASO 1: E-COMMERCE =====
        System.out.println("\n🛒 CASO 1: SISTEMA DE E-COMMERCE");
        System.out.println("-" .repeat(40));
        
        // Crear productos
        ProductoFisico laptop = new ProductoFisico("LAP001", "Laptop Gaming", 80000, 10, 
                                                  "Electrónicos", 2.5, "35x25x3 cm");
        ProductoDigital curso = new ProductoDigital("CUR001", "Curso de Java", 15000, 100,
                                                   "Educación", "MP4", 2048);
        
        // Crear usuarios
        Usuario clienteRegular = new Usuario("USR001", "juan@email.com", "Juan Pérez", 
                                           "Av. Siempre Viva 123", Usuario.TipoUsuario.CLIENTE_REGULAR);
        Usuario clientePremium = new Usuario("USR002", "ana@email.com", "Ana García",
                                           "Calle Falsa 456", Usuario.TipoUsuario.CLIENTE_PREMIUM);
        
        // Simular compras
        clienteRegular.agregarAlCarrito(laptop, 1);
        clienteRegular.agregarAlCarrito(curso, 1);
        clienteRegular.getCarrito().mostrarResumen();
        System.out.printf("Total cliente regular: $%.2f\n", 
                         clienteRegular.getCarrito().calcularTotal(clienteRegular));
        
        clientePremium.agregarAlCarrito(laptop, 1);
        clientePremium.agregarAlCarrito(curso, 2);
        System.out.printf("Total cliente premium (con 15%% descuento): $%.2f\n",
                         clientePremium.getCarrito().calcularTotal(clientePremium));
        
        // ===== CASO 2: SISTEMA BANCARIO =====
        System.out.println("\n🏦 CASO 2: SISTEMA BANCARIO");
        System.out.println("-" .repeat(40));
        
        CuentaAhorro cuentaAhorro = new CuentaAhorro("AH001", "María López", 25000);
        CuentaCorriente cuentaCorriente = new CuentaCorriente("CC001", "Pedro Ramírez", 50000);
        CuentaPremium cuentaPremium = new CuentaPremium("PR001", "Sofía Mendez", 100000);
        
        // Transferencias entre diferentes tipos de cuenta
        cuentaCorriente.transferir(15000, cuentaAhorro);
        cuentaPremium.transferir(8000, cuentaCorriente);
        
        // Mostrar estados de cuenta
        cuentaAhorro.mostrarEstadoCuenta();
        cuentaCorriente.mostrarEstadoCuenta();
        
        // ===== CASO 3: GESTIÓN DE EMPLEADOS =====
        System.out.println("\n👥 CASO 3: GESTIÓN DE EMPLEADOS");
        System.out.println("-" .repeat(40));
        
        GestorNomina gestorNomina = new GestorNomina();
        
        // Diferentes tipos de empleados
        EmpleadoTiempoCompleto dev = new EmpleadoTiempoCompleto("EMP001", "Carlos Developer", 
                                                              "carlos@empresa.com", "IT", 120000);
        
        EmpleadoPorHoras soporte = new EmpleadoPorHoras("EMP002", "Lucia Soporte",
                                                       "lucia@empresa.com", "IT", 500);
        soporte.registrarHoras(170); // Registrar horas trabajadas
        
        Freelancer diseñador = new Freelancer("FREE001", "Miguel Designer",
                                             "miguel@freelance.com", "Marketing");
        // Agregar proyectos al freelancer
        Proyecto proyecto1 = new Proyecto("Rediseño Web", 25000, LocalDateTime.now().plusDays(30));
        proyecto1.marcarCompletado();
        Proyecto proyecto2 = new Proyecto("Logo Empresa", 8000, LocalDateTime.now().plusDays(15));
        proyecto2.marcarCompletado();
        
        diseñador.agregarProyecto(proyecto1);
        diseñador.agregarProyecto(proyecto2);
        
        // Agregar empleados al gestor
        gestorNomina.agregarEmpleado(dev);
        gestorNomina.agregarEmpleado(soporte);
        gestorNomina.agregarEmpleado(diseñador);
        
        // Procesar nómina
        gestorNomina.procesarNomina();
        
        System.out.println("\n✨ CONCLUSIONES PARA EL MUNDO LABORAL:");
        System.out.println("=" .repeat(60));
        System.out.println("1. 🔄 POLIMORFISMO: Permite tratar diferentes objetos de manera uniforme");
        System.out.println("   - Diferentes tipos de productos, cuentas, empleados");
        System.out.println("   - Mismo código para procesar diferentes tipos");
        
        System.out.println("\n2. 🔒 ENCAPSULACIÓN: Protege datos sensibles del negocio");
        System.out.println("   - Información bancaria, datos de empleados");
        System.out.println("   - Control de acceso mediante métodos");
        
        System.out.println("\n3. 🧬 HERENCIA: Reutiliza código común");
        System.out.println("   - Clase base Producto para todos los productos");
        System.out.println("   - Clase base Empleado para todos los empleados");
        
        System.out.println("\n4. 🎨 ABSTRACCIÓN: Simplifica sistemas complejos");
        System.out.println("   - Interfaces claras para diferentes módulos");
        System.out.println("   - Oculta complejidad innecesaria");
        
        System.out.println("\n🚀 ESTAS TÉCNICAS SE USAN EN:");
        System.out.println("• Microservicios (Spring Boot, Node.js)");
        System.out.println("• APIs REST");
        System.out.println("• Sistemas de gestión empresarial (ERP)");
        System.out.println("• Aplicaciones móviles");
        System.out.println("• Plataformas web");
        System.out.println("• Sistemas bancarios y fintech");
        System.out.println("• E-commerce y marketplaces");
    }
}

/*
 * 📋 PATRONES DE DISEÑO COMUNES EN LA INDUSTRIA:
 * 
 * 1. STRATEGY PATTERN:
 *    - Diferentes algoritmos de cálculo (salarios, precios, descuentos)
 *    - Usado en: sistemas de pago, algoritmos de recomendación
 * 
 * 2. FACTORY PATTERN:
 *    - Crear diferentes tipos de objetos según el contexto
 *    - Usado en: crear usuarios, productos, cuentas bancarias
 * 
 * 3. OBSERVER PATTERN:
 *    - Notificar cambios a múltiples componentes
 *    - Usado en: notificaciones, eventos del sistema
 * 
 * 4. SINGLETON PATTERN:
 *    - Una sola instancia de configuración o conexión a BD
 *    - Usado en: configuraciones globales, logs del sistema
 * 
 * 5. REPOSITORY PATTERN:
 *    - Abstrae el acceso a datos
 *    - Usado en: acceso a bases de datos, APIs externas
 * 
 * 🎯 TECNOLOGÍAS DONDE SE APLICA POO:
 * 
 * BACKEND:
 * • Java (Spring Boot) - Empresas grandes
 * • Python (Django/FastAPI) - Startups, ciencia de datos
 * • C# (.NET) - Empresas Microsoft
 * • Node.js (TypeScript) - Aplicaciones web modernas
 * 
 * FRONTEND:
 * • Angular (TypeScript) - Aplicaciones empresariales
 * • React con TypeScript - Aplicaciones web
 * • Vue.js - Aplicaciones medianas
 * 
 * MÓVIL:
 * • Android (Java/Kotlin)
 * • iOS (Swift)
 * • React Native (JavaScript/TypeScript)
 * • Flutter (Dart)
 * 
 * 💼 ROLES DONDE SE USA POO:
 * • Software Developer / Programmer
 * • Backend Developer
 * • Full Stack Developer
 * • Mobile Developer
 * • Systems Architect
 * • Technical Lead
 * • DevOps Engineer (para scripts y automation)
 * 
 * 📈 NIVEL SALARIAL (Argentina 2024):
 * • Junior Developer: $600K - $1.2M anual
 * • Semi-Senior: $1.2M - $2M anual
 * • Senior Developer: $2M - $4M anual
 * • Tech Lead/Architect: $4M+ anual
 * 
 * (Los salarios varían según empresa, tecnología y experiencia)
 */
