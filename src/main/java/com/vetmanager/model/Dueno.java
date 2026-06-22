package com.vetmanager.model;

public class Dueno {
    private int idDueno;
    private String nombre;
    private String telefono;
    private String correo;

    public Dueno(int idDueno, String nombre, String telefono, String correo) {
        this.idDueno = idDueno;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getIdDueno() { return idDueno; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String toString() {
        return "[" + idDueno + "] " + nombre + " - " + telefono;
    }
}