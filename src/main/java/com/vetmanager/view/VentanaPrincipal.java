package com.vetmanager.view;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("VetManager - Sistema Veterinario");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel titulo = new JLabel("VetManager", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(41, 128, 185));
        titulo.setForeground(Color.WHITE);
        titulo.setPreferredSize(new Dimension(0, 60));
        add(titulo, BorderLayout.NORTH);

        // Botones del menú
        JPanel panelMenu = new JPanel(new GridLayout(4, 1, 10, 10));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JButton btnDuenos = new JButton("👤 Gestionar Dueños");
        JButton btnMascotas = new JButton("🐾 Gestionar Mascotas");
        JButton btnCitas = new JButton("📅 Gestionar Citas");
        JButton btnHistorial = new JButton("📋 Historial Clínico");

        for (JButton btn : new JButton[]{btnDuenos, btnMascotas, btnCitas, btnHistorial}) {
            btn.setFont(new Font("Arial", Font.PLAIN, 16));
            btn.setPreferredSize(new Dimension(0, 50));
            panelMenu.add(btn);
        }

        add(panelMenu, BorderLayout.CENTER);

        // Acciones
        btnDuenos.addActionListener(e -> new PanelDuenos().setVisible(true));
        btnMascotas.addActionListener(e -> new PanelMascotas().setVisible(true));
        btnCitas.addActionListener(e -> new PanelCitas().setVisible(true));
        btnHistorial.addActionListener(e -> new PanelHistorial().setVisible(true));

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}