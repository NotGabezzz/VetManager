package com.vetmanager.model;

import java.time.LocalDateTime;

public class Cita {
    private int idCita;
    private Mascota mascota;
    private LocalDateTime fechaHora;
    private String motivoConsulta;
    private EstadoReserva estadoReserva;

    public Cita(int idCita, Mascota mascota, LocalDateTime fechaHora, String motivoConsulta, EstadoReserva estadoReserva) {
        this.idCita = idCita;
        this.mascota = mascota;
        this.fechaHora = fechaHora;
        this.motivoConsulta = motivoConsulta;
        this.estadoReserva = estadoReserva;
    }

    public int getIdCita() { return idCita; }
    public Mascota getMascota() { return mascota; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getMotivoConsulta() { return motivoConsulta; }
    public EstadoReserva getEstadoReserva() { return estadoReserva; }
    public void setEstadoReserva(EstadoReserva estadoReserva) { this.estadoReserva = estadoReserva; }

    public String toString() {
        return "[" + idCita + "] " + mascota.getNombre() + " - " + motivoConsulta + " | " + estadoReserva;
    }
}