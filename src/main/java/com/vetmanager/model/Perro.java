package com.vetmanager.model;

public class Perro extends Mascota {
    private String raza;

    public Perro(int idMascota, String nombre, int edadMeses, Dueno dueno, String raza) {
        super(idMascota, nombre, edadMeses, dueno);
        this.raza = raza;
    }

    public String getRaza() { return raza; }

    @Override
    public String emitirSonido() { return "Guau"; }

    @Override
    public String getTipoEspecie() { return "Perro"; }
}
