/*
 * The MIT License
 *
 * Copyright 2018 Josh.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import biblioteca.*;

/**
 *
 * @author Josh
 */
public class FormularioBibliografiaNueva extends VentanaPadre implements ActionListener{

    private JTextField cuadrosTexto[] = new JTextField[11];
    //private TextPrompt placeholder[] = new TextPrompt[11];
    private JLabel titulos[] = new JLabel[12];
    private JComboBox cuadroSeleccion = new JComboBox();
    private JPanel formulario = new JPanel(new GridLayout((cuadrosTexto.length) - 1,2,20,10));
    private String textoLabels[] = {"Tipo", "Autor", "Título", "Descripción", "Palabras Clave", "Edición", "Temas", "Copias", "Disponibles", "Frecuencia actual", "Ejemplares", "Área"};
    private Administrador admin = (Administrador) Biblioteca.usuarioConectado;
    private int index = -1;
    
    public FormularioBibliografiaNueva(VentanaPadre anterior, int tipo){
        super("Nueva bibliografía", anterior);
        Ancho = 840;
        Alto = 630;
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        cuadroSeleccion.addItem("Libro");
        cuadroSeleccion.addItem("Revista");
        cuadroSeleccion.addItem("Tesis");
        cuadroSeleccion.addActionListener(this);
        JPanel combobox = new JPanel(new GridLayout(2,1,5,5));
        titulos[0] = new JLabel(textoLabels[0]);
        for(int i = 0; i < cuadrosTexto.length; i++){
            titulos[i + 1] = new JLabel(textoLabels[i + 1]);
            cuadrosTexto[i] = new JTextField();
        }
        agregarCuadrosTexto(0);
        combobox.add(titulos[0]);
        combobox.add(cuadroSeleccion);
        combobox.setBounds(20,20,390,60);
        formulario.setBounds(20,80,800,400);
        JPanel botonesPanel = new JPanel(new GridLayout(1,4,20,40));
        JButton aceptarBoton = new JButton("Guardar"), borrarBoton = new JButton("Borrar"), cerrarBoton = new JButton("Cerrar"), cargaBoton = new JButton("Carga masiva");
        aceptarBoton.addActionListener(this);
        borrarBoton.addActionListener(this);
        cerrarBoton.addActionListener(this);
        cargaBoton.addActionListener(this);
        if(tipo == 1){
            aceptarBoton.setActionCommand("GuardarCambios");
        }
        botonesPanel.add(aceptarBoton);
        botonesPanel.add(borrarBoton);
        botonesPanel.add(cargaBoton);
        botonesPanel.add(cerrarBoton);
        botonesPanel.setBounds(120,520,600,50);
        getContentPane().add(combobox);
        
        getContentPane().add(botonesPanel);
    }
    
    public void agregarCuadrosTexto(int tipo){
        int limite = 0;
        switch(tipo){
            case 0:
                cuadrosTexto[10].setText("");
            case 2:
                cuadrosTexto[9].setText("");
                cuadrosTexto[8].setText("");
                limite = 4;
                break;
            case 1:
                cuadrosTexto[10].setText("");
                limite = 5;
                break;
        }
        
        formulario.removeAll();
        for(int i = 0; i < limite; i++){
            formulario.add(titulos[(2*i) + 1]);
            formulario.add(titulos[(2*i) + 2]);
            formulario.add(cuadrosTexto[(2*i)]);
            formulario.add(cuadrosTexto[(2*i) + 1]);
        }
        if(tipo == 2){
            formulario.add(titulos[11]);
            formulario.add(new JLabel(""));
            formulario.add(cuadrosTexto[10]);
        }
        getContentPane().add(formulario);
        getContentPane().validate();
        getContentPane().repaint();
    }
    
    public void borrarTextos(){
        for(int i = 0; i < cuadrosTexto.length; i++){
            if(i < cuadrosTexto.length){
                cuadrosTexto[i].setText("");
            }
            cuadroSeleccion.setSelectedIndex(0);
        }
    }
    
    public void llenarTextos(int index){
        this.index = index;
        Bibliografia bibliografia = Biblioteca.bibliografiasActuales[index];
        cuadroSeleccion.setSelectedIndex(bibliografia.getTipo());
        cuadrosTexto[0].setText(bibliografia.getAutor());
        cuadrosTexto[1].setText(bibliografia.getTitulo());
        cuadrosTexto[2].setText(bibliografia.getDescripcion());
        cuadrosTexto[3].setText(bibliografia.getPalabrasClave());
        cuadrosTexto[4].setText(String.valueOf(bibliografia.getEdicion()));
        cuadrosTexto[5].setText(bibliografia.getTemas());
        cuadrosTexto[6].setText(String.valueOf(bibliografia.getCopias()));
        cuadrosTexto[7].setText(String.valueOf(bibliografia.getDisponibles()));
        cuadrosTexto[8].setText(bibliografia.getFrecuenciaActual());
        cuadrosTexto[9].setText(String.valueOf(bibliografia.getEjemplares()));
        cuadrosTexto[10].setText(bibliografia.getArea());
    }
    
    private boolean comprobarLlenadoTextos(int tipo){
        boolean r0 =  false, r1 = true, valor;
        int limite = 0;
        switch(cuadroSeleccion.getSelectedIndex()){
            case 0:
            case 2:
                limite = 8;
                try {
                    Integer.parseInt(cuadrosTexto[4].getText().trim());
                    Integer.parseInt(cuadrosTexto[6].getText().trim());
                    Integer.parseInt(cuadrosTexto[7].getText().trim());
                } catch (Exception e) {
                    if(tipo == 1){
                        JOptionPane.showMessageDialog(this, "Hay campos que deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                break;
            case 1:
                limite = 10;
                try {
                    Integer.parseInt(cuadrosTexto[9].getText().trim());
                } catch (Exception e) {
                    if(tipo == 1){
                        JOptionPane.showMessageDialog(this, "Hay campos que deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                break;
        }
        for(int i = 0; i < limite; i++){
            valor = !cuadrosTexto[i].getText().trim().equals("");
            if(valor){
                    r0 = true;
            }else{
                    r1 = false;
            }
        }
        if(cuadroSeleccion.getSelectedIndex() == 2){
            valor = !cuadrosTexto[10].getText().trim().equals("");
            if(valor){
                    r0 = true;
            }else{
                    r1 = false;
            }
        }
        /*if(r0){
            "Existe campo lleno"
        }
        if(!r1){
           "Existe campo vacio"
        }*/
        if((tipo == 1) && !r1){
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos antes de guardar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return (tipo == 0) ? r0 : r1;
    }
    
    private boolean comprobarTextos(){
        if(!comprobarLlenadoTextos(1)){
            return false;
        }else{
            if(Integer.valueOf(cuadrosTexto[6].getText().trim()) < Integer.valueOf(cuadrosTexto[7].getText().trim())){
                JOptionPane.showMessageDialog(this, "El número de disponibles no puede ser mayor al número de copias.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }else if(Integer.valueOf(cuadrosTexto[6].getText().trim()) < 1){
                JOptionPane.showMessageDialog(this, "No pueden existir cero o menos copias de algún documento.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }else if(Integer.valueOf(cuadrosTexto[7].getText()) < 0){
                JOptionPane.showMessageDialog(this, "No pueden existir menos de cero disponibles en el documento.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    
    public void enviarGuardado(int tipo){
        String temas[], palabrasClave[];
        temas = cuadrosTexto[5].getText().split(",");
        palabrasClave = cuadrosTexto[3].getText().split(",");
        limpiarVector(temas);
        limpiarVector(palabrasClave);
        int num = (cuadrosTexto[9].getText().equals(""))? 0 : Integer.valueOf(cuadrosTexto[9].getText());
        if(tipo == 0){
            admin.crearBibliografia(cuadroSeleccion.getSelectedIndex(), cuadrosTexto[0].getText(), cuadrosTexto[1].getText(), cuadrosTexto[2].getText(), palabrasClave, Integer.valueOf(cuadrosTexto[4].getText()), temas, cuadrosTexto[8].getText(), num, cuadrosTexto[10].getText(), Integer.valueOf(cuadrosTexto[6].getText()), Integer.valueOf(cuadrosTexto[7].getText()));
            JOptionPane.showMessageDialog(null, "Biliografía creada con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
        }else{
            admin.guardarCambiosBibliografia(index, cuadroSeleccion.getSelectedIndex(), cuadrosTexto[0].getText(), cuadrosTexto[1].getText(), cuadrosTexto[2].getText(), palabrasClave, Integer.valueOf(cuadrosTexto[4].getText()), temas, cuadrosTexto[8].getText(), num, cuadrosTexto[10].getText(), Integer.valueOf(cuadrosTexto[6].getText()), Integer.valueOf(cuadrosTexto[7].getText()), this);
        }
        
    }
    
    public void limpiarVector(String vector[]){
        for(int i = 0; i < vector.length; i++){
            vector[i] = vector[i].trim();
        }
    }
    
    @Override
    public void dispose(){
        if(comprobarLlenadoTextos(0)){
            if(JOptionPane.showConfirmDialog(this, "Al salir se perderán los datos. ¿Está seguro de salir?", "Salir",JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION){
                cerrar(true);
            }
        }else{
            cerrar(true);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "comboBoxChanged":
                agregarCuadrosTexto(cuadroSeleccion.getSelectedIndex());
                break;
            case "Cerrar":
                dispose();
                break;
            case "Borrar":
                borrarTextos();
                break;
            case "Guardar":
                if(comprobarTextos()){
                    enviarGuardado(0);
                    borrarTextos();
                }
                break;
            case "GuardarCambios":
                if(comprobarTextos()){
                    enviarGuardado(1);
                    borrarTextos();
                }
                break;
            case "Carga masiva":
                cargaMasivaBibliografias ventana = new cargaMasivaBibliografias(this);
                ventana.setVisible(true);
                this.setVisible(false);
                break;
        }
    }
    
}
