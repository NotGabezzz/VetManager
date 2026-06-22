package com.vetmanager.dao;

import com.vetmanager.config.ConexionBD;
import com.vetmanager.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAOImpl implements CitaDAO {

    private Connection conn = ConexionBD.getInstancia().getConexion();

    @Override
    public void agendar(Cita cita) {
        String sql = "INSERT INTO citas (fecha_hora, motivo_consulta, estado_reserva, id_mascota) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            ps.setString(2, cita.getMotivoConsulta());
            ps.setString(3, cita.getEstadoReserva().name());
            ps.setInt(4, cita.getMascota().getIdMascota());
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    @Override
    public List<Cita> listarPorMascota(int idMascota) {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM citas WHERE id_mascota = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMascota);
            ResultSet rs = ps.executeQuery();
            MascotaDAO mascotaDAO = new MascotaDAOImpl();
            while (rs.next()) {
                Mascota mascota = mascotaDAO.buscarPorId(rs.getInt("id_mascota"));
                lista.add(new Cita(
                        rs.getInt("id_cita"),
                        mascota,
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("motivo_consulta"),
                        EstadoReserva.valueOf(rs.getString("estado_reserva"))
                ));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    @Override
    public void cambiarEstado(int idCita, EstadoReserva estado) {
        String sql = "UPDATE citas SET estado_reserva = ? WHERE id_cita = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, estado.name());
            ps.setInt(2, idCita);
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    @Override
    public void eliminar(int idCita) {
        String sql = "DELETE FROM citas WHERE id_cita = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCita);
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    @Override
    public List<Cita> listarTodas() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT * FROM citas";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            MascotaDAO mascotaDAO = new MascotaDAOImpl();
            while (rs.next()) {
                Mascota mascota = mascotaDAO.buscarPorId(rs.getInt("id_mascota"));
                lista.add(new Cita(
                        rs.getInt("id_cita"), mascota,
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("motivo_consulta"),
                        EstadoReserva.valueOf(rs.getString("estado_reserva"))
                ));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }
}