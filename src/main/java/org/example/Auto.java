package org.example;

public class Auto extends Vehiculo{
    private int numeroPuertas;
    private boolean tieneAireAcondicionado;

    public Auto(String placa, String marca, String modelo, double tarifaBase, int numeroPuertas, boolean tieneAireAcondicionado) {
        super(placa, marca, modelo, tarifaBase);
        this.numeroPuertas = numeroPuertas;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }
    //Metodos
    public boolean isTieneAireAcondicionado(){
        return tieneAireAcondicionado;
    }

    @Override
    public double calcularTarifaDiaria() {
        double tarifa=super.calcularTarifaDiaria();
        if(tieneAireAcondicionado){
            tarifa+=10.0;
        }
        return tarifa;
    }

    @Override
    public String toString() {
        String acTexto=tieneAireAcondicionado ? "con A/C":"sin A/C";
        return "[AUTO] "+ super.toString()+"Puertas: "+numeroPuertas+"Con A/C: "+acTexto+"Tarifa diaria"+calcularTarifaDiaria();

    }
}

