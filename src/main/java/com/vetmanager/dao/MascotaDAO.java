package com.vetmanager.dao;

import com.vetmanager.model.Mascota;
import java.util.List;

public interface MascotaDAO {
    void registrar(Mascota mascota);
    Mascota buscarPorId(int id);
    List<Mascota> listarPorDueno(int idDueno);
    List<Mascota> listarTodas();
    void eliminar(int id);
}