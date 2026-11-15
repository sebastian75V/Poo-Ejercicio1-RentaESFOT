package org.example;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private static HashSet<String> placasRegistradas = new HashSet<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n--- MENU RENTAESFOT ---");
            System.out.println("1. Registrar auto");
            System.out.println("2. Registrar camioneta");
            System.out.println("3. Mostrar vehiculos");
            System.out.println("4. Cotizar alquiler");
            System.out.println("5. Mostrar estadisticas");
            System.out.println("6. Salir");
            System.out.print("Ingrese opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarAuto();
                    break;
                case 2:
                    registrarCamioneta();
                    break;
                case 3:
                    mostrarVehiculos();
                    break;
                case 4:
                    cotizarAlquiler();
                    break;
                case 5:
                    mostrarEstadisticas();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema RentaESFOT...");
                    break;
                default:
                    System.out.println("Error: Opción no válida.");
            }
        } while (opcion != 6);
    }

    private static void registrarAuto() {
        System.out.print("Placa: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        if (!placasRegistradas.add(placa)) {
            System.out.println("La placa " + placa + " ya está registrada.");
            return;
        }else{
            placasRegistradas.add(placa);
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine().trim();

        System.out.print("Tarifa diaria base: ");
        double tarifaBase =scanner.nextDouble();
        if (tarifaBase < 0) {
            placasRegistradas.remove(placa);
            System.out.println("Error: La tarifa diaria base no puede ser negativa.");
            return;
        }

        System.out.print("Número de puertas: ");
        int puertas =scanner.nextInt();
        if (puertas <= 0) {
            placasRegistradas.remove(placa);
            System.out.println("Error: El número de puertas debe ser mayor a 0.");
            return;
        }
        scanner.nextLine();

        System.out.print("¿Tiene aire acondicionado? (s/n): ");
        String acInput = scanner.nextLine().trim().toLowerCase();
        if (!acInput.equals("s") && !acInput.equals("n")) {
            placasRegistradas.remove(placa);
            System.out.println("Error: Respuesta inválida. Ingrese 's' o 'n'.");
            return;
        }
        boolean tieneAC = acInput.equals("s");

        vehiculos.add(new Auto(placa, marca, modelo, tarifaBase, puertas, tieneAC));
        System.out.println("Auto registrado exitosamente.");
    }

    private static void registrarCamioneta() {
        System.out.print("Placa: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        if (!placasRegistradas.add(placa)) {
            System.out.println("La placa " + placa + " ya está registrada.");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine().trim();

        System.out.print("Tarifa diaria base: ");
        double tarifaBase = Double.parseDouble(scanner.nextLine().trim());
        if (tarifaBase < 0) {
            placasRegistradas.remove(placa);
            System.out.println("Error: La tarifa diaria base no puede ser negativa.");
            return;
        }

        System.out.print("Capacidad de carga (t): ");
        double capacidadCarga = Double.parseDouble(scanner.nextLine().trim());
        if (capacidadCarga < 0) {
            placasRegistradas.remove(placa);
            System.out.println("Error: La capacidad de carga no puede ser negativa.");
            return;
        }

        vehiculos.add(new Camioneta(placa, marca, modelo, tarifaBase, capacidadCarga));
        System.out.println("Camioneta registrada exitosamente.");
    }

    private static void mostrarVehiculos() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados en el sistema.");
            return;
        }

        for (Vehiculo v : vehiculos) {
            System.out.println(v.toString());
        }
    }

    private static void cotizarAlquiler() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados para cotizar.");
            return;
        }

        System.out.print("Placa: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        Vehiculo encontrado = null;
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                encontrado = v;
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("Error: No existe ningún vehículo con la placa " + placa + ".");
            return;
        }

        System.out.print("Días: ");
        String diasStr = scanner.nextLine().trim();
        int dias = Integer.parseInt(diasStr);

        if (dias <= 0) {
            System.out.println("Error: La cantidad de días debe ser mayor a 0.");
            return;
        }

        System.out.print("¿Aplica descuento? (s/n): ");
        String aplicaDesc = scanner.nextLine().trim().toLowerCase();

        if (aplicaDesc.equals("n")) {
            double total = encontrado.calcularAlquiler(dias);
            System.out.printf("Total a pagar: $"+ total);
        } else if (aplicaDesc.equals("s")) {
            System.out.print("Porcentaje de descuento: ");
            double descuento = scanner.nextDouble();

            if (descuento < 0 || descuento > 50) {
                System.out.println("Error: El porcentaje de descuento debe estar entre 0 y 50 %.");
                return;
            }

            double total = encontrado.calcularAlquiler(dias, descuento);
            System.out.printf("Total a pagar: $"+ total);
        } else {
            System.out.println("Error: Opción inválida. Ingrese 's' o 'n'.");
        }
    }

    private static void mostrarEstadisticas() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados para calcular estadísticas.");
            return;
        }
        Vehiculo mayorTarifa = vehiculos.get(0);
        double sumaTarifas = 0.0;

        for (Vehiculo v : vehiculos) {
            double tarifaActual = v.calcularTarifaDiaria();
            sumaTarifas += tarifaActual;

            if (tarifaActual > mayorTarifa.calcularTarifaDiaria()) {
                mayorTarifa = v;
            }
        }

        double promedio = sumaTarifas / vehiculos.size();

        System.out.printf("Vehículo con mayor tarifa diaria: %s %s ($%.2f)%n",
                mayorTarifa.getMarca(), mayorTarifa.getModelo(), mayorTarifa.calcularTarifaDiaria());
        System.out.printf("Promedio de tarifas diarias: $%.2f%n", promedio);
    }
}