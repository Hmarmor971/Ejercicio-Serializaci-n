package controller;

import model.Coche;
import model.CocheManager;
import view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;


public class CocheController {
    private MainFrame view;
    private CocheManager model;
    private List<Coche> coches;

    public CocheController(MainFrame view, CocheManager model) {
        this.view = view;
        this.model = model;
        this.coches = model.cargarCoches();

        view.setAgregarButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCoche();
            }
        });

        view.setListarButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCoches();
            }
        });
    }

    private void agregarCoche() {
        String modelo = view.getModelo();
        String marca = view.getMarca();
        String matricula = view.getMatricula();
        int anno;
        String fechaVenta = view.getVenta();

        try {
            anno = Integer.parseInt(view.getAnno());
            Coche coche = new Coche(modelo, marca, matricula, anno, fechaVenta);
            coches.add(coche);
            model.guardarCoches(coches);
            view.mostrarMensaje("Coche agregado correctamente.");
        } catch (NumberFormatException e) {
            view.mostrarMensaje("Por favor, ingrese un formato válido.");
        }
    }

    private void listarCoches() {
        StringBuilder sb = new StringBuilder();
        for (Coche u : coches) {
            sb.append(u.toString()).append("\n");
        }
        view.mostrarCoches(sb.toString());
    }






}
