package com.vetmanager.view;

import com.vetmanager.model.Dueno;
import com.vetmanager.service.VetManagerService;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelDuenos extends JFrame {

    VetManagerService service = new VetManagerService();
    JTextField txtNombre = new JTextField(15);
    JTextField txtTelefono = new JTextField(15);
    JTextField txtCorreo = new JTextField(15);
    JTextField txtId = new JTextField(5);
    JTextArea reporte = new JTextArea(10, 40);

    public PanelDuenos() {
        setTitle("Gestionar Dueños");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        // Panel campos
        JPanel pCampos = new JPanel(new GridLayout(5, 2, 5, 8));
        pCampos.setBorder(BorderFactory.createTitledBorder("Datos del Dueño"));
        pCampos.add(new JLabel("  ID (para eliminar):")); pCampos.add(txtId);
        pCampos.add(new JLabel("  Nombre:")); pCampos.add(txtNombre);
        pCampos.add(new JLabel("  Teléfono:")); pCampos.add(txtTelefono);
        pCampos.add(new JLabel("  Correo:")); pCampos.add(txtCorreo);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar");
        JPanel pBotones = new JPanel();
        pBotones.add(btnRegistrar); pBotones.add(btnEliminar); pBotones.add(btnListar);
        pCampos.add(pBotones);

        reporte.setEditable(false);
        JScrollPane scroll = new JScrollPane(reporte);
        scroll.setBorder(BorderFactory.createTitledBorder("Dueños registrados"));

        add(pCampos, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrar());
        btnEliminar.addActionListener(e -> eliminar());
        btnListar.addActionListener(e -> listar());

        listar();
    }

    void registrar() {
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();
        if (nombre.isEmpty() || telefono.isEmpty()) { msg("Nombre y teléfono obligatorios."); return; }
        service.registrarDueno(nombre, telefono, correo);
        listar(); limpiar();
    }

    void eliminar() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            service.eliminarDueno(id);
            listar(); limpiar();
        } catch (Exception e) { msg("ID inválido."); }
    }

    void listar() {
        reporte.setText("");
        List<Dueno> lista = service.listarDuenos();
        for (Dueno d : lista) reporte.append(d + "\n");
    }

    void limpiar() {
        txtId.setText(""); txtNombre.setText("");
        txtTelefono.setText(""); txtCorreo.setText("");
    }

    void msg(String m) { JOptionPane.showMessageDialog(this, m); }
}