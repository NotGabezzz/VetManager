package com.vetmanager.service;

import com.vetmanager.dao.*;
import com.vetmanager.model.*;
import java.time.LocalDateTime;
import java.util.List;

public class VetManagerService {

    private DuenoDAO duenoDAO = new DuenoDAOImpl();
    private MascotaDAO mascotaDAO = new MascotaDAOImpl();
    private CitaDAO citaDAO = new CitaDAOImpl();
    private HistorialDAO historialDAO = new HistorialDAOImpl();

    // DUEÑOS
    public void registrarDueno(String nombre, String telefono, String correo) {
        duenoDAO.registrar(new Dueno(0, nombre, telefono, correo));
    }

    public List<Dueno> listarDuenos() {
        return duenoDAO.listar();
    }

    public Dueno buscarDueno(int id) {
        return duenoDAO.buscarPorId(id);
    }

    public void eliminarDueno(int id) {
        duenoDAO.eliminar(id);
    }

    // MASCOTAS
    public void registrarPerro(String nombre, int edadMeses, int idDueno, String raza) {
        Dueno dueno = duenoDAO.buscarPorId(idDueno);
        if (dueno == null) { System.out.println("Dueno no encontrado."); return; }
        mascotaDAO.registrar(new Perro(0, nombre, edadMeses, dueno, raza));
    }

    public void registrarGato(String nombre, int edadMeses, int idDueno, boolean toxoplasmosisNeg) {
        Dueno dueno = duenoDAO.buscarPorId(idDueno);
        if (dueno == null) { System.out.println("Dueno no encontrado."); return; }
        mascotaDAO.registrar(new Gato(0, nombre, edadMeses, dueno, toxoplasmosisNeg));
    }

    public List<Mascota> listarMascotasPorDueno(int idDueno) {
        return mascotaDAO.listarPorDueno(idDueno);
    }

    public Mascota buscarMascota(int id) {
        return mascotaDAO.buscarPorId(id);
    }

    public void eliminarMascota(int id) {
        mascotaDAO.eliminar(id);
    }

    // CITAS
    public void agendarCita(int idMascota, String motivo, LocalDateTime fechaHora) {
        Mascota mascota = mascotaDAO.buscarPorId(idMascota);
        if (mascota == null) { System.out.println("Mascota no encontrada."); return; }
        citaDAO.agendar(new Cita(0, mascota, fechaHora, motivo, EstadoReserva.EN_ESPERA));
    }

    public List<Cita> listarCitasPorMascota(int idMascota) {
        return citaDAO.listarPorMascota(idMascota);
    }

    public void cambiarEstadoCita(int idCita, EstadoReserva estado) {
        citaDAO.cambiarEstado(idCita, estado);
    }

    public void eliminarCita(int idCita) {
        citaDAO.eliminar(idCita);
    }

    // HISTORIAL
    public void agregarHistorial(int idMascota, EstadoSalud estado, String detalles) {
        Mascota mascota = mascotaDAO.buscarPorId(idMascota);
        if (mascota == null) { System.out.println("Mascota no encontrada."); return; }
        historialDAO.agregar(new HistorialClinico(0, mascota, estado, detalles, LocalDateTime.now()));
    }

    public List<HistorialClinico> listarHistorialPorMascota(int idMascota) {
        return historialDAO.listarPorMascota(idMascota);
    }

    public void cambiarEstadoSalud(int idHistorial, EstadoSalud estado) {
        historialDAO.cambiarEstado(idHistorial, estado);
    }

    public List<Mascota> listarTodasMascotas() {
        return mascotaDAO.listarTodas();
    }

    public List<Cita> listarTodasCitas() {
        return citaDAO.listarTodas();
    }

    public List<HistorialClinico> listarTodoHistorial() {
        return historialDAO.listarTodas();
    }
}