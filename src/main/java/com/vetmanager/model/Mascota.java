package com.vetmanager.model;

public abstract class Mascota {
    protected int idMascota;
    protected String nombre;
    protected int edadMeses;
    protected Dueno dueno;

    public Mascota(int idMascota, String nombre, int edadMeses, Dueno dueno) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.edadMeses = edadMeses;
        this.dueno = dueno;
    }

    public abstract String emitirSonido();
    public abstract String getTipoEspecie();

    public int getIdMascota() { return idMascota; }
    public String getNombre() { return nombre; }
    public int getEdadMeses() { return edadMeses; }
    public Dueno getDueno() { return dueno; }

    public String toString() {
        return "[" + idMascota + "] " + nombre + " (" + getTipoEspecie() + ") - Dueño: " + dueno.getNombre();
    }
}