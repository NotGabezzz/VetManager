package com.vetmanager.model;

public class Gato extends Mascota {
    private boolean esToxoplasmosisNeg;

    public Gato(int idMascota, String nombre, int edadMeses, Dueno dueno, boolean esToxoplasmosisNeg) {
        super(idMascota, nombre, edadMeses, dueno);
        this.esToxoplasmosisNeg = esToxoplasmosisNeg;
    }

    public boolean isEsToxoplasmosisNeg() { return esToxoplasmosisNeg; }

    @Override
    public String emitirSonido() { return "Miau"; }

    @Override
    public String getTipoEspecie() { return "Gato"; }
}
