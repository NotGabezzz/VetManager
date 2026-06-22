package com.vetmanager.dao;

import com.vetmanager.config.ConexionBD;
import com.vetmanager.model.Dueno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuenoDAOImpl implements DuenoDAO {

    private Connection conn = ConexionBD.getInstancia().getConexion();

    @Override
    public void registrar(Dueno dueno) {
        String sql = "INSERT INTO duenos (nombre, telefono, correo) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dueno.getNombre());
            ps.setString(2, dueno.getTelefono());
            ps.setString(3, dueno.getCorreo());
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    @Override
    public Dueno buscarPorId(int id) {
        String sql = "SELECT * FROM duenos WHERE id_dueno = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return new Dueno(rs.getInt("id_dueno"), rs.getString("nombre"),
                    rs.getString("telefono"), rs.getString("correo"));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return null;
    }

    @Override
    public List<Dueno> listar() {
        List<Dueno> lista = new ArrayList<>();
        String sql = "SELECT * FROM duenos";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(new Dueno(rs.getInt("id_dueno"), rs.getString("nombre"),
                    rs.getString("telefono"), rs.getString("correo")));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM duenos WHERE id_dueno = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }
}