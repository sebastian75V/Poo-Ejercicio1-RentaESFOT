package org.example;

public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private double tarifaBase;

    public Vehiculo(String placa, String marca, String modelo, double tarifaBase) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaBase = tarifaBase;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }
    //Metodos
    public double calcularTarifaDiaria(){
        return tarifaBase;
    }
    public double calcularAlquiler(){
        return tarifaBase;
    }
    //Sobrecarga
    public double calcularAlquiler(int dias){
        return calcularTarifaDiaria()*dias;
    }
    public double calcularAlquiler(int dias, double porcentajeDescuento){
        double subtotal=calcularAlquiler(dias);
        return subtotal-(subtotal*(porcentajeDescuento/100.0));
    }

    @Override
    public String toString() {
        return  "Placa: "+placa + '\'' +
                 "Marca: "+marca + '\'' +
                 "Modelo: "+modelo + '\''
                ;
    }
}
