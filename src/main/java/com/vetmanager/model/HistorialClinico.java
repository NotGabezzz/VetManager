package com.vetmanager.model;

import java.time.LocalDateTime;

public class HistorialClinico {
    private int idHistorial;
    private Mascota mascota;
    private EstadoSalud estadoSalud;
    private String detallesMedicos;
    private LocalDateTime ultimaActualizacion;

    public HistorialClinico(int idHistorial, Mascota mascota, EstadoSalud estadoSalud, String detallesMedicos, LocalDateTime ultimaActualizacion) {
        this.idHistorial = idHistorial;
        this.mascota = mascota;
        this.estadoSalud = estadoSalud;
        this.detallesMedicos = detallesMedicos;
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public int getIdHistorial() { return idHistorial; }
    public Mascota getMascota() { return mascota; }
    public EstadoSalud getEstadoSalud() { return estadoSalud; }
    public String getDetallesMedicos() { return detallesMedicos; }
    public LocalDateTime getUltimaActualizacion() { return ultimaActualizacion; }
    public void setEstadoSalud(EstadoSalud estadoSalud) { this.estadoSalud = estadoSalud; }

    public String toString() {
        return "[" + idHistorial + "] " + mascota.getNombre() + " - " + estadoSalud + " | " + detallesMedicos;
    }
}