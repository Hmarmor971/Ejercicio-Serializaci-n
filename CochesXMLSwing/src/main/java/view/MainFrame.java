package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JTextField marcaField;
    private JTextField modeloField;
    private JTextField matriculaField;
    private JTextField annoField;
    private JTextField ventaField;
    private JTextArea CochesArea;
    private JButton agregarButton;
    private JButton listarButton;

    public MainFrame() {
        setTitle("Gestor de Coches");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        inputPanel.add(new JLabel("Marca:"));
        marcaField = new JTextField();
        inputPanel.add(marcaField);

        inputPanel.add(new JLabel("Modelo:"));
        modeloField = new JTextField();
        inputPanel.add(modeloField);

        inputPanel.add(new JLabel("Matricula:"));
        matriculaField = new JTextField();
        inputPanel.add(matriculaField);

        inputPanel.add(new JLabel("Año:"));
        annoField = new JTextField();
        inputPanel.add(annoField);

        inputPanel.add(new JLabel("Fecha de Venta(yyyy-mm-dd):"));
        ventaField = new JTextField();
        inputPanel.add(ventaField);

        agregarButton = new JButton("Agregar Coche");
        inputPanel.add(agregarButton);

        listarButton = new JButton("Listar Coches");
        inputPanel.add(listarButton);

        add(inputPanel, BorderLayout.NORTH);

        CochesArea = new JTextArea();
        add(new JScrollPane(CochesArea), BorderLayout.CENTER);
    }

    public String getMarca() {
        return marcaField.getText();
    }

    public String getModelo() {
        return modeloField.getText();
    }

    public String getMatricula() {
        return matriculaField.getText();
    }

    public String getAnno() {
        return annoField.getText();
    }

    public String getVenta() {
        return ventaField.getText();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarCoches(String alumnos) {
        CochesArea.setText(alumnos);
    }

    public void setAgregarButtonListener(ActionListener listener) {
        agregarButton.addActionListener(listener);
    }

    public void setListarButtonListener(ActionListener listener) {
        listarButton.addActionListener(listener);
    }
}
