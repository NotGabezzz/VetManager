package com.vetmanager.dao;

import com.vetmanager.model.HistorialClinico;
import com.vetmanager.model.EstadoSalud;
import java.util.List;

public interface HistorialDAO {
    void agregar(HistorialClinico historial);
    List<HistorialClinico> listarPorMascota(int idMascota);
    List<HistorialClinico> listarTodas();
    void cambiarEstado(int idHistorial, EstadoSalud estado);
}