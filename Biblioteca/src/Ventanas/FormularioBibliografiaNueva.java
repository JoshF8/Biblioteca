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
    
    public FormularioBibliografiaNueva(VentanaPadre anterior){
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
        borrarTextos();
        cuadroSeleccion.setSelectedIndex(tipo);
        switch(tipo){
            case 0:
            case 2:
                limite = 4;
                break;
            case 1:
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
    
    private boolean comprobarLlenadoTextos(int tipo){
        boolean r0 =  false, r1 = true, valor;
        int limite = 0;
        switch(cuadroSeleccion.getSelectedIndex()){
            case 0:
            case 2:
                limite = 8;
                break;
            case 1:
                limite = 10;
                break;
        }
        for(int i = 0; i < limite; i++){
            valor = !cuadrosTexto[i].getText().equals("");
            if(valor){
                    r0 = true;
            }else{
                    r1 = false;
            }
        }
        if(cuadroSeleccion.getSelectedIndex() == 2){
            valor = !cuadrosTexto[10].getText().equals("");
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
        return (tipo == 0) ? r0 : r1;
    }
    
    private boolean comprobarTextos(){
        if(!comprobarLlenadoTextos(1)){
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos antes de guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public void enviarGuardado(){
        String temas[], palabrasClave[];
        temas = cuadrosTexto[5].getText().split(",");
        palabrasClave = cuadrosTexto[3].getText().split(",");
        limpiarVector(temas);
        limpiarVector(palabrasClave);
        int num = (cuadrosTexto[9].getText().equals(""))? 0 : Integer.valueOf(cuadrosTexto[9].getText());
        admin.crearBibliografia(cuadroSeleccion.getSelectedIndex(), cuadrosTexto[0].getText(), cuadrosTexto[1].getText(), cuadrosTexto[2].getText(), palabrasClave, Integer.valueOf(cuadrosTexto[4].getText()), temas, cuadrosTexto[8].getText(), num, cuadrosTexto[10].getText(), Integer.valueOf(cuadrosTexto[6].getText()), Integer.valueOf(cuadrosTexto[7].getText()));
        JOptionPane.showMessageDialog(null, "Biliografía creada con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
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
                    enviarGuardado();
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
