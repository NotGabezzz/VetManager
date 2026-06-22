package com.vetmanager.dao;

import com.vetmanager.model.Cita;
import com.vetmanager.model.EstadoReserva;
import java.util.List;

public interface CitaDAO {
    void agendar(Cita cita);
    List<Cita> listarPorMascota(int idMascota);
    List<Cita> listarTodas();    void cambiarEstado(int idCita, EstadoReserva estado);
    void eliminar(int idCita);
}