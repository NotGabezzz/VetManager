package com.vetmanager.dao;

import com.vetmanager.config.ConexionBD;
import com.vetmanager.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAOImpl implements HistorialDAO {

    private Connection conn = ConexionBD.getInstancia().getConexion();

    @Override
    public void agregar(HistorialClinico historial) {
        String sql = "INSERT INTO historial_clinico (estado_salud, detalles_medicos, id_mascota) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, historial.getEstadoSalud().name());
            ps.setString(2, historial.getDetallesMedicos());
            ps.setInt(3, historial.getMascota().getIdMascota());
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    @Override
    public List<HistorialClinico> listarPorMascota(int idMascota) {
        List<HistorialClinico> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_clinico WHERE id_mascota = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMascota);
            ResultSet rs = ps.executeQuery();
            MascotaDAO mascotaDAO = new MascotaDAOImpl();
            while (rs.next()) {
                Mascota mascota = mascotaDAO.buscarPorId(rs.getInt("id_mascota"));
                lista.add(new HistorialClinico(
                        rs.getInt("id_historial"),
                        mascota,
                        EstadoSalud.valueOf(rs.getString("estado_salud")),
                        rs.getString("detalles_medicos"),
                        rs.getTimestamp("ultima_actualizacion").toLocalDateTime()
                ));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    @Override
    public void cambiarEstado(int idHistorial, EstadoSalud estado) {
        String sql = "UPDATE historial_clinico SET estado_salud = ? WHERE id_historial = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, estado.name());
            ps.setInt(2, idHistorial);
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    @Override
    public List<HistorialClinico> listarTodas() {
        List<HistorialClinico> lista = new ArrayList<>();
        String sql = "SELECT * FROM historial_clinico";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            MascotaDAO mascotaDAO = new MascotaDAOImpl();
            while (rs.next()) {
                Mascota mascota = mascotaDAO.buscarPorId(rs.getInt("id_mascota"));
                lista.add(new HistorialClinico(
                        rs.getInt("id_historial"), mascota,
                        EstadoSalud.valueOf(rs.getString("estado_salud")),
                        rs.getString("detalles_medicos"),
                        rs.getTimestamp("ultima_actualizacion").toLocalDateTime()
                ));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }
}