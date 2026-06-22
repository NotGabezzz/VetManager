package com.vetmanager.dao;

import com.vetmanager.config.ConexionBD;
import com.vetmanager.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAOImpl implements MascotaDAO {

    private Connection conn = ConexionBD.getInstancia().getConexion();
    private DuenoDAO duenoDAO = new DuenoDAOImpl();

    @Override
    public void registrar(Mascota mascota) {
        String sql = "INSERT INTO mascotas (nombre, tipo_especie, edad_meses, atributo_especifico, id_dueno) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getTipoEspecie());
            ps.setInt(3, mascota.getEdadMeses());
            if (mascota instanceof Perro) {
                ps.setString(4, ((Perro) mascota).getRaza());
            } else if (mascota instanceof Gato) {
                ps.setString(4, String.valueOf(((Gato) mascota).isEsToxoplasmosisNeg()));
            } else {
                ps.setString(4, "");
            }
            ps.setInt(5, mascota.getDueno().getIdDueno());
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    @Override
    public Mascota buscarPorId(int id) {
        String sql = "SELECT * FROM mascotas WHERE id_mascota = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return construirMascota(rs);
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return null;
    }

    @Override
    public List<Mascota> listarPorDueno(int idDueno) {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas WHERE id_dueno = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDueno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(construirMascota(rs));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM mascotas WHERE id_mascota = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
    }

    @Override
    public List<Mascota> listarTodas() {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) lista.add(construirMascota(rs));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    private Mascota construirMascota(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_mascota");
        String nombre = rs.getString("nombre");
        int edad = rs.getInt("edad_meses");
        String tipo = rs.getString("tipo_especie");
        String atributo = rs.getString("atributo_especifico");
        Dueno dueno = duenoDAO.buscarPorId(rs.getInt("id_dueno"));

        if (tipo.equals("Perro")) {
            return new Perro(id, nombre, edad, dueno, atributo);
        } else if (tipo.equals("Gato")) {
            return new Gato(id, nombre, edad, dueno, Boolean.parseBoolean(atributo));
        }
        return null;
    }
}
