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
    private JLabel titulos[] = new JLabel[11];
    private JComboBox cuadroSeleccion = new JComboBox();
    private JPanel formulario = new JPanel(new GridLayout((cuadrosTexto.length),2,20,10));
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
        titulos[0] = new JLabel(textoLabels[0]);
        for(int i = 0; i < cuadrosTexto.length; i++){
            titulos[i + 1] = new JLabel(textoLabels[i + 1]);
            cuadrosTexto[i] = new JTextField();
        }
        
        formulario.setBounds(60,10,300,480);
        JPanel botonesPanel = new JPanel(new GridLayout(1,2,20,40));
        JButton aceptarBoton = new JButton("Aceptar"), borrarBoton = new JButton("Borrar"), cerrarBoton = new JButton("Cerrar");
        aceptarBoton.addActionListener(this);
        borrarBoton.addActionListener(this);
        cerrarBoton.addActionListener(this);
        botonesPanel.add(aceptarBoton);
        botonesPanel.add(borrarBoton);
        botonesPanel.add(cerrarBoton);
        botonesPanel.setBounds(60,520,300,50);
        getContentPane().add(formulario);
        getContentPane().add(botonesPanel);
    }
    
    public void agregarBotones(int tipo){
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
