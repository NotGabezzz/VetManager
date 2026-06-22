package com.vetmanager.dao;

import com.vetmanager.model.Dueno;
import java.util.List;

public interface DuenoDAO {
    void registrar(Dueno dueno);
    Dueno buscarPorId(int id);
    List<Dueno> listar();
    void eliminar(int id);
}