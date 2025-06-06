package View;

import Controller.*;
import Model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class VistaCineMas {

    PeliculaControlador peliculaControlador = new PeliculaControlador();
    SalaControlador salaControlador = new SalaControlador();
    FuncionControlador funcionControlador = new FuncionControlador();
    ClienteControlador clienteControlador = new ClienteControlador();
    SnackControlador snackControlador = new SnackControlador();
    FacturaControlador facturaControlador = new FacturaControlador();
    PromocionControlador promocionControlador = new PromocionControlador();

    SimpleDateFormat sdfFechaCompleta = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfDiaHora = new SimpleDateFormat("EEEE dd/MM/yyyy HH:mm", new Locale("es", "ES"));
    SimpleDateFormat sdfDiaSemana = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
    SimpleDateFormat sdfParaArchivo = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static final String archivoRegistro = "registro_ventas_cine.csv";

    public static void main(String[] args) {
        VistaCineMas app = new VistaCineMas();
        app.cargarDatosIniciales();
        app.crearEncabezadoCSV();

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        System.out.println("========================================");
        System.out.println("|           Sistema de CineMas         |");
        System.out.println("========================================");

        while (!salir) {
            app.mostrarMenuPrincipal();
            int opcion = -1;
            try {
                System.out.print("Seleccione una opcion: ");
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un numero valido.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    app.verPeliculasEnCartelera();
                    break;
                case 2:
                    app.comprarBoletos(scanner);
                    break;
                case 3:
                    app.comprarSnacks(scanner);
                    break;
                case 4:
                    app.verRegistroDeVentas();
                    break;
                case 5:
                    salir = true;
                    System.out.println("Gracias por usar el Sistema de CineMas");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
        scanner.close();
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n MENU PRINCIPAL ");
        System.out.println("1. Ver Peliculas en Cartelera");
        System.out.println("2. Comprar Boletos");
        System.out.println("3. Comprar Snacks");
        System.out.println("4. Ver Registro de Ventas");
        System.out.println("5. Salir");
    }

    private void escribirLineaEnCSV(String datosCSV) {
        try (FileWriter fileWriter = new FileWriter(archivoRegistro, true);
             Formatter formatter = new Formatter(fileWriter)) {
            formatter.format("%s%n", datosCSV);
        } catch (IOException e) {
            System.err.println("Error de E/S al escribir en el archivo CSV: " + e.getMessage());
        }
    }

    public void crearEncabezadoCSV() {
        File csvFile = new File(archivoRegistro);
        if (!csvFile.exists() || csvFile.length() == 0) {
            String encabezado = "FechaHoraTransaccion;TipoFactura;NumeroFactura;ClienteNombre;PeliculaTitulo;SalaNombre;HorarioFuncion;CantidadBoletos;PrecioUnitarioBoleto;SubtotalBoletos;PromocionDescripcion;DescuentoValor;SnackNombre;CantidadSnack;PrecioUnitarioSnack;SubtotalSnacks;TotalPagado";
            escribirLineaEnCSV(encabezado);
        }
    }

    public void cargarDatosIniciales() {
        List<String[]> datosClientes = new ArrayList<>();
        datosClientes.add(new String[]{"Carlos Solis", "carlos.solis@example.com", "0981234567"});
        datosClientes.add(new String[]{"Ana Paredes", "ana.paredes@example.net", "0992345678"});
        datosClientes.add(new String[]{"Luis Guzman", "luis.guzman@example.org", "0973456789"});
        datosClientes.add(new String[]{"Maria Endara", "maria.endara@example.com", "0964567890"});
        datosClientes.add(new String[]{"Pedro Alvear", "pedro.alvear@example.net", "0955678901"});
        datosClientes.add(new String[]{"Sofia Castillo", "sofia.castillo@example.org", "0988765432"});
        datosClientes.add(new String[]{"Jorge Luna", "jorge.luna@example.com", "0999876543"});

        for (String[] data : datosClientes) {
            clienteControlador.agregarCliente(new Cliente(data[0], data[1], data[2]));
        }

        List<Pelicula> listaPeliculas = new ArrayList<>();
        listaPeliculas.add(new Pelicula("Intensamente 2", "Riley se enfrenta a nuevas emociones en la adolescencia.", "Animacion", "ATP"));
        listaPeliculas.add(new Pelicula("Bad Boys: Hasta la Muerte", "Los policias rebeldes regresan para una ultima mision.", "Accion", "Mayores de 13"));
        listaPeliculas.add(new Pelicula("El Planeta de los Simios: Nuevo Reino", "Varias generaciones despues del reinado de Cesar, los simios son la especie dominante.", "Ciencia Ficcion", "Mayores de 13"));
        listaPeliculas.add(new Pelicula("Garfield: Fuera de Casa", "El gato casero que odia los lunes esta a punto de vivir una salvaje aventura.", "Animacion", "ATP"));
        listaPeliculas.add(new Pelicula("Observados", "Una artista queda varada en un bosque en el oeste de Irlanda y es acechada por criaturas misteriosas.", "Terror", "Mayores de 16"));
        listaPeliculas.add(new Pelicula("Furiosa: De la saga Mad Max", "La joven Furiosa es arrebatada del Lugar Verde de Muchas Madres y cae en manos de una gran Horda de Motociclistas.", "Accion", "Mayores de 16"));
        listaPeliculas.add(new Pelicula("Amigos Imaginarios", "Una nina descubre que puede ver a los amigos imaginarios de todo el mundo y se embarca en una aventura magica.", "Familiar", "ATP"));
        listaPeliculas.add(new Pelicula("Hachiko 2: Siempre a tu lado", "La conmovedora historia de lealtad de un perro que espera a su dueño en la estacion, incluso despues de su muerte.", "Drama", "ATP"));
        listaPeliculas.add(new Pelicula("Kung Fu Panda 4", "Po se prepara para convertirse en el lider espiritual del Valle de la Paz, pero necesita encontrar un sucesor como Guerrero Dragon.", "Animacion", "ATP"));

        for (Pelicula pelicula : listaPeliculas) {
            peliculaControlador.agregarPelicula(pelicula);
        }

        Sala sala1 = new Sala(1, "Sala VIP", 40);
        Sala sala2 = new Sala(2, "Sala Standard", 80);
        Sala sala3 = new Sala(3, "Sala 3D", 60);
        Sala sala4 = new Sala(4, "Sala IMAX", 120);
        salaControlador.agregarSala(sala1);
        salaControlador.agregarSala(sala2);
        salaControlador.agregarSala(sala3);
        salaControlador.agregarSala(sala4);

        promocionControlador.agregarPromocion(new Promocion("Martes Loco 20%", "martes", 0.20));
        promocionControlador.agregarPromocion(new Promocion("Jueves Estudiantil 15%", "jueves", 0.15));
        promocionControlador.agregarPromocion(new Promocion("FinDeSemana Familiar 10% (sabado)", "sabado", 0.10));

        snackControlador.agregarSnackACatalogo(new Snack("Popcorn Grande", 5.50, 1));
        snackControlador.agregarSnackACatalogo(new Snack("Gaseosa Mediana", 2.50, 1));
        snackControlador.agregarSnackACatalogo(new Snack("Nachos con Queso", 4.75, 1));
        snackControlador.agregarSnackACatalogo(new Snack("Hot Dog Clasico", 3.00, 1));
        snackControlador.agregarSnackACatalogo(new Snack("Combo Pareja (2 Gaseosas M, 1 Popcorn G)", 10.00, 1));

        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JUNE, 3);
        Date hoyMartes = cal.getTime();
        cal.set(2025, Calendar.JUNE, 5);
        Date unJueves = cal.getTime();
        cal.set(2025, Calendar.JUNE, 7);
        Date unSabado = cal.getTime();
        cal.set(2025, Calendar.JUNE, 6);
        Date unViernes = cal.getTime();

        try {
            funcionControlador.agregarFuncion(new Funcion(peliculaControlador.buscarPorTitulo("Intensamente 2"), new Horario(sdfFechaCompleta.parse(sdfFechaCompleta.format(hoyMartes)), "18:00"), sala1));
            funcionControlador.agregarFuncion(new Funcion(peliculaControlador.buscarPorTitulo("Bad Boys: Hasta la Muerte"), new Horario(sdfFechaCompleta.parse(sdfFechaCompleta.format(hoyMartes)), "20:30"), sala2));
            funcionControlador.agregarFuncion(new Funcion(peliculaControlador.buscarPorTitulo("El Planeta de los Simios: Nuevo Reino"), new Horario(sdfFechaCompleta.parse(sdfFechaCompleta.format(unJueves)), "19:00"), sala3));
            funcionControlador.agregarFuncion(new Funcion(peliculaControlador.buscarPorTitulo("Garfield: Fuera de Casa"), new Horario(sdfFechaCompleta.parse(sdfFechaCompleta.format(unJueves)), "17:00"), sala1));
            funcionControlador.agregarFuncion(new Funcion(peliculaControlador.buscarPorTitulo("Observados"), new Horario(sdfFechaCompleta.parse(sdfFechaCompleta.format(unViernes)), "22:00"), sala2));
            funcionControlador.agregarFuncion(new Funcion(peliculaControlador.buscarPorTitulo("Furiosa: De la saga Mad Max"), new Horario(sdfFechaCompleta.parse(sdfFechaCompleta.format(unViernes)), "21:00"), sala4));
            funcionControlador.agregarFuncion(new Funcion(peliculaControlador.buscarPorTitulo("Amigos Imaginarios"), new Horario(sdfFechaCompleta.parse(sdfFechaCompleta.format(unSabado)), "16:00"), sala1));
            funcionControlador.agregarFuncion(new Funcion(peliculaControlador.buscarPorTitulo("Hachiko 2: Siempre a tu lado"), new Horario(sdfFechaCompleta.parse(sdfFechaCompleta.format(unSabado)), "18:30"), sala3));
            funcionControlador.agregarFuncion(new Funcion(peliculaControlador.buscarPorTitulo("Kung Fu Panda 4"), new Horario(sdfFechaCompleta.parse(sdfFechaCompleta.format(hoyMartes)), "16:30"), sala4));

        } catch (ParseException e) {
            System.err.println("Error al parsear fechas para funciones iniciales: " + e.getMessage());
        }
    }

    public void verPeliculasEnCartelera() {
        System.out.println("\n--- PELICULAS EN CARTELERA ---");
        List<Pelicula> peliculas = peliculaControlador.obtenerPeliculas();
        if (peliculas.isEmpty()) {
            System.out.println("No hay peliculas disponibles en este momento.");
            return;
        }
        for (int i = 0; i < peliculas.size(); i++) {
            Pelicula p = peliculas.get(i);
            System.out.println((i + 1) + ". " + p.getTitulo() + " (" + p.getClasificacion() + ")");
            System.out.println("   Genero: " + p.getGenero());
            System.out.println("   Sinopsis: " + p.getSinopsis());
        }
    }

    public void comprarBoletos(Scanner scanner) {
        System.out.println("\n--- COMPRAR BOLETOS ---");
        List<Cliente> clientesDisponibles = clienteControlador.obtenerClientes();
        Cliente clienteDeLaTransaccion = null;

        if (!clientesDisponibles.isEmpty()) {
            Random rand = new Random();
            clienteDeLaTransaccion = clientesDisponibles.get(rand.nextInt(clientesDisponibles.size()));
            System.out.println("Transaccion para el cliente: " + clienteDeLaTransaccion.getNombreApellidoCliente());
        } else {
            System.out.println("Error: No hay clientes registrados. Por favor, agregue clientes primero.");
            return;
        }

        List<Funcion> funciones = funcionControlador.obtenerFunciones();
        if (funciones.isEmpty()) {
            System.out.println("No hay funciones disponibles en este momento.");
            return;
        }

        System.out.println("Funciones Disponibles:");
        for (int i = 0; i < funciones.size(); i++) {
            Funcion f = funciones.get(i);
            Date fechaHoraCompleta = null;
            try {
                fechaHoraCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sdfFechaCompleta.format(f.getHorario().getFecha()) + " " + f.getHorario().getHora());
            } catch (ParseException e) { }
            System.out.println((i + 1) + ". " + f.getPelicula().getTitulo() +
                               " - Sala: " + f.getSala().getNombre() +
                               " - Horario: " + (fechaHoraCompleta != null ? sdfDiaHora.format(fechaHoraCompleta) : sdfFechaCompleta.format(f.getHorario().getFecha()) + " " + f.getHorario().getHora()));
        }

        System.out.print("Seleccione el numero de la funcion: ");
        int numFuncion;
        try {
            numFuncion = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada invalida.");
            scanner.nextLine();
            return;
        }

        if (numFuncion < 1 || numFuncion > funciones.size()) {
            System.out.println("Seleccion de funcion no valida.");
            return;
        }
        Funcion funcionSeleccionada = funciones.get(numFuncion - 1);

        System.out.print("Ingrese la cantidad de boletos: ");
        int cantidadBoletos;
         try {
            cantidadBoletos = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Cantidad invalida.");
            scanner.nextLine();
            return;
        }

        if (cantidadBoletos <= 0) {
            System.out.println("La cantidad de boletos debe ser mayor a cero.");
            return;
        }

        double precioUnitarioBoleto = 8.50;
        Boleto boletoVenta = new Boleto(funcionSeleccionada, cantidadBoletos, precioUnitarioBoleto);

        Promocion promocionAplicada = null;
        Date fechaDeLaFuncion = funcionSeleccionada.getHorario().getFecha();

        for (Promocion promo : promocionControlador.obtenerPromociones()) {
            if (promo.aplicaDescuento(fechaDeLaFuncion)) {
                promocionAplicada = promo;
                System.out.println("Promocion Aplicada!: " + promo.getDescripcionPromo());
                break;
            }
        }

        String numFacturaFuncion = "FF-" + (facturaControlador.obtenerFacturasFuncion().size() + 1);
        FacturaFuncion factura = new FacturaFuncion(numFacturaFuncion, new Date(), clienteDeLaTransaccion, boletoVenta, promocionAplicada);
        facturaControlador.agregarFacturaFuncion(factura);

        String fechaHoraRegistroCSV = sdfParaArchivo.format(factura.getFecha());
        String clienteNombreCSV = factura.getCliente() != null ? factura.getCliente().getNombreApellidoCliente().replace(";", "").replace(",", " ") : "N/A";
        String peliculaTituloCSV = factura.getBoleto().getFuncion().getPelicula().getTitulo().replace(";", "").replace(",", " ");
        String salaNombreCSV = factura.getBoleto().getFuncion().getSala().getNombre().replace(";", "").replace(",", " ");
        String horarioFuncionCSV = sdfFechaCompleta.format(funcionSeleccionada.getHorario().getFecha()) + " " + funcionSeleccionada.getHorario().getHora();
        horarioFuncionCSV = horarioFuncionCSV.replace(";", "").replace(",", " ");
        
        double subtotalBoletosCSV = factura.getSubtotal();
        double descuentoValorCSV = (factura.getPromocion() != null) ? (subtotalBoletosCSV * factura.getPromocion().getPorcentajeDescuento()) : 0.0;
        String promocionDescCSV = (factura.getPromocion() != null ? factura.getPromocion().getDescripcionPromo().replace(";", "").replace(",", " ") : "Ninguna");
        double totalPagadoCSV = factura.getValorTotal();

        String lineaCSVBoletos = String.format(Locale.US, "%s;FACTURA_FUNCION;%s;%s;%s;%s;%s;%d;%.2f;%.2f;%s;%.2f;%s;%d;%.2f;%.2f;%.2f",
                fechaHoraRegistroCSV,
                factura.getNumero(),
                clienteNombreCSV,
                peliculaTituloCSV,
                salaNombreCSV,
                horarioFuncionCSV,
                factura.getBoleto().getCantidad(),
                factura.getBoleto().getPrecioUnitario(),
                subtotalBoletosCSV,
                promocionDescCSV,
                descuentoValorCSV,
                "N/A", 
                0,     
                0.0,   
                0.0,   
                totalPagadoCSV
        );
        escribirLineaEnCSV(lineaCSVBoletos);

        System.out.println("\n--- Factura de Boletos Generada ---");
        System.out.println("Numero: " + factura.getNumero());
        System.out.println("Cliente: " + clienteNombreCSV);
        System.out.println("Pelicula: " + peliculaTituloCSV);
        System.out.println("Sala: " + salaNombreCSV);

        Date fechaFuncionParaMostrar = null;
        String horaFuncionParaMostrar = funcionSeleccionada.getHorario().getHora();
        try {
            fechaFuncionParaMostrar = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sdfFechaCompleta.format(funcionSeleccionada.getHorario().getFecha()) + " " + horaFuncionParaMostrar);
        } catch (ParseException e) { }

        System.out.println("Fecha y Hora de la Funcion: " + (fechaFuncionParaMostrar != null ? sdfDiaHora.format(fechaFuncionParaMostrar) : "N/A"));
        System.out.println("Cantidad de Boletos: " + factura.getBoleto().getCantidad());
        System.out.println("Precio Unitario: $" + String.format(Locale.US, "%.2f", factura.getBoleto().getPrecioUnitario()));
        System.out.println("Subtotal: $" + String.format(Locale.US, "%.2f", subtotalBoletosCSV));
        if (factura.getPromocion() != null) {
            System.out.println("Descuento ("+ promocionDescCSV +"): -$" + String.format(Locale.US, "%.2f", descuentoValorCSV));
        }
        System.out.println("Valor Total: $" + String.format(Locale.US, "%.2f", totalPagadoCSV));
        System.out.println("Factura registrada en: " + archivoRegistro);
    }

    public void comprarSnacks(Scanner scanner) {
        System.out.println("\n--- COMPRAR SNACKS ---");
        List<Cliente> clientesDisponibles = clienteControlador.obtenerClientes();
        Cliente clienteDeLaTransaccion = null;

        if (!clientesDisponibles.isEmpty()) {
            Random rand = new Random();
            clienteDeLaTransaccion = clientesDisponibles.get(rand.nextInt(clientesDisponibles.size()));
            System.out.println("Transaccion para el cliente (seleccionado aleatoriamente): " + clienteDeLaTransaccion.getNombreApellidoCliente());
        } else {
            System.out.println("Error: No hay clientes registrados. Por favor, agregue clientes primero.");
            return;
        }

        List<Snack> snacksCatalogo = snackControlador.obtenerSnacksDeCatalogo();
        if (snacksCatalogo.isEmpty()) {
            System.out.println("No hay snacks disponibles en este momento.");
            return;
        }

        System.out.println("Snacks Disponibles:");
        for (int i = 0; i < snacksCatalogo.size(); i++) {
            Snack s = snacksCatalogo.get(i);
            System.out.println((i + 1) + ". " + s.getNombre() + " - $" + String.format(Locale.US, "%.2f", s.getPrecio()));
        }

        System.out.print("Seleccione el numero del snack: ");
        int numSnack;
        try {
            numSnack = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no valida.");
            scanner.nextLine();
            return;
        }

        if (numSnack < 1 || numSnack > snacksCatalogo.size()) {
            System.out.println("Seleccion de snack no valida.");
            return;
        }
        Snack snackSeleccionadoCatalogo = snacksCatalogo.get(numSnack - 1);

        System.out.print("Ingrese la cantidad de '" + snackSeleccionadoCatalogo.getNombre() + "': ");
        int cantidadSnacks;
        try {
            cantidadSnacks = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: Cantidad no valida.");
            scanner.nextLine();
            return;
        }

        if (cantidadSnacks <= 0) {
            System.out.println("La cantidad de snacks debe ser mayor a cero.");
            return;
        }

        Snack snackParaFactura = new Snack(snackSeleccionadoCatalogo.getNombre(), snackSeleccionadoCatalogo.getPrecio(), cantidadSnacks);

        String numFacturaSnack = "FS-" + (facturaControlador.obtenerFacturasSnack().size() + 1);
        FacturaSnack factura = new FacturaSnack(numFacturaSnack, new Date(), clienteDeLaTransaccion, snackParaFactura);
        facturaControlador.agregarFacturaSnack(factura);

        String fechaHoraRegistroCSV = sdfParaArchivo.format(factura.getFecha());
        String clienteNombreCSV = factura.getCliente() != null ? factura.getCliente().getNombreApellidoCliente().replace(";", "").replace(",", " ") : "N/A";
        String snackNombreCSV = factura.getSnack().getNombre().replace(";", "").replace(",", " ");
        double subtotalSnacksCSV = factura.getSnack().getPrecio() * factura.getSnack().getCantidad();
        double totalPagadoCSV = factura.calcularValorTotal();

        String lineaCSVSnacks = String.format(Locale.US, "%s;FACTURA_SNACK;%s;%s;%s;%s;%s;%d;%.2f;%.2f;%s;%.2f;%s;%d;%.2f;%.2f;%.2f",
                fechaHoraRegistroCSV,
                factura.getNumero(),
                clienteNombreCSV,
                "N/A", 
                "N/A", 
                "N/A", 
                0,     
                0.0,   
                0.0,   
                "Ninguna", 
                0.0,   
                snackNombreCSV,
                factura.getSnack().getCantidad(),
                factura.getSnack().getPrecio(),
                subtotalSnacksCSV,
                totalPagadoCSV
        );
        escribirLineaEnCSV(lineaCSVSnacks);

        System.out.println("\n--- Factura de Snacks Generada ---");
        System.out.println("Numero: " + factura.getNumero());
        System.out.println("Cliente: " + clienteNombreCSV);
        System.out.println("Snack: " + snackNombreCSV);
        System.out.println("Cantidad: " + factura.getSnack().getCantidad());
        System.out.println("Precio Unitario: $" + String.format(Locale.US, "%.2f", factura.getSnack().getPrecio()));
        System.out.println("Valor Total: $" + String.format(Locale.US, "%.2f", totalPagadoCSV));
        System.out.println("Factura registrada en: " + archivoRegistro);
    }

    public void verRegistroDeVentas() {
        System.out.println("\n--- REGISTRO DE VENTAS ---");

        List<FacturaFuncion> facturasFuncion = facturaControlador.obtenerFacturasFuncion();
        List<FacturaSnack> facturasSnack = facturaControlador.obtenerFacturasSnack();

        int totalBoletosVendidos = 0;
        double ingresosTotalesPorBoletos = 0.0;
        int totalItemsSnacksVendidos = 0;
        double ingresosTotalesPorSnacks = 0.0;

        if (facturasFuncion.isEmpty() && facturasSnack.isEmpty()) {
            System.out.println("Aun no se han realizado ventas en memoria.");
        } else {
            System.out.println("\n--- Resumen de Ventas de Boletos (Memoria) ---");
            if (facturasFuncion.isEmpty()) {
                System.out.println("No se han vendido boletos.");
            } else {
                for (FacturaFuncion ff : facturasFuncion) {
                    if (ff.getBoleto() != null) {
                        totalBoletosVendidos += ff.getBoleto().getCantidad();
                    }
                    ingresosTotalesPorBoletos += ff.getValorTotal();
                }
                System.out.println("Total de Boletos Vendidos: " + totalBoletosVendidos);
                System.out.println("Ingresos Totales por Boletos: $" + String.format(Locale.US, "%.2f", ingresosTotalesPorBoletos));

                System.out.println("\nDetalle de Facturas de Boletos (Memoria):");
                for(FacturaFuncion ff : facturasFuncion) {
                    System.out.println("  Factura No: " + ff.getNumero() +
                                       ", Cliente: " + (ff.getCliente() != null ? ff.getCliente().getNombreApellidoCliente() : "N/A") +
                                       ", Pelicula: " + (ff.getBoleto() != null && ff.getBoleto().getFuncion() != null && ff.getBoleto().getFuncion().getPelicula() != null ? ff.getBoleto().getFuncion().getPelicula().getTitulo() : "N/A") +
                                       ", Cant: " + (ff.getBoleto() != null ? ff.getBoleto().getCantidad() : 0) +
                                       ", Total: $" + String.format(Locale.US, "%.2f", ff.getValorTotal()));
                }
            }

            System.out.println("\n--- Resumen de Ventas de Snacks (Memoria) ---");
            if (facturasSnack.isEmpty()) {
                System.out.println("No se han vendido snacks.");
            } else {
                for (FacturaSnack fs : facturasSnack) {
                    if (fs.getSnack() != null) {
                        totalItemsSnacksVendidos += fs.getSnack().getCantidad();
                    }
                    ingresosTotalesPorSnacks += fs.getValorTotal();
                }
                System.out.println("Total de Items de Snacks Vendidos: " + totalItemsSnacksVendidos);
                System.out.println("Ingresos Totales por Snacks: $" + String.format(Locale.US, "%.2f", ingresosTotalesPorSnacks));

                System.out.println("\nDetalle de Facturas de Snacks (Memoria):");
                for(FacturaSnack fs : facturasSnack) {
                     System.out.println("  Factura No: " + fs.getNumero() +
                                       ", Cliente: " + (fs.getCliente() != null ? fs.getCliente().getNombreApellidoCliente() : "N/A") +
                                       ", Snack: " + (fs.getSnack() != null ? fs.getSnack().getNombre() : "N/A") +
                                       ", Cant: " + (fs.getSnack() != null ? fs.getSnack().getCantidad() : 0) +
                                       ", Total: $" + String.format(Locale.US, "%.2f", fs.getValorTotal()));
                }
            }

            System.out.println("\n--- Totales Generales (Desde Memoria) ---");
            System.out.println("Ingresos Totales (Boletos + Snacks): $" + String.format(Locale.US, "%.2f", (ingresosTotalesPorBoletos + ingresosTotalesPorSnacks)));
        }
        
        System.out.println("\n--- REGISTRO DE VENTAS (Desde Archivo CSV: " + archivoRegistro + ") ---");
        File archivoCSV = new File(archivoRegistro);
        if (!archivoCSV.exists() || archivoCSV.length() == 0) {
            System.out.println("El archivo de registro CSV '" + archivoRegistro + "' esta vacio o no existe aun.");
        } else {
            try (Scanner fileScanner = new Scanner(archivoCSV)) {
                while (fileScanner.hasNextLine()) {
                    System.out.println(fileScanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error al leer el archivo de registro CSV '" + archivoRegistro + "'.");
            }
        }
    }
}