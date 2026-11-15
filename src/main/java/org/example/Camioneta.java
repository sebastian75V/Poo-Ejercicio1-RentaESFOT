package org.example;

public class Camioneta extends Vehiculo{
    private double capacidadCarga;

    public Camioneta(String placa, String marca, String modelo, double tarifaBase, double capacidadCarga) {
        super(placa, marca, modelo, tarifaBase);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }
    @Override
    public double calcularTarifaDiaria() {
        double tarifa=super.calcularTarifaDiaria();
        if(capacidadCarga>1.5){
            tarifa+=tarifa*0.15;
        }
        return tarifa;

    }
    @Override
    public String toString() {
        return"[Camioneta] "+ super.toString()+"Carga: "+capacidadCarga+" Tarifa diaria: "+calcularTarifaDiaria();
    }


}
