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

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Josh
 */


public class FormularioUsuarioNuevo extends VentanaPadre{
    
    JTextField cuadrosTexto[] = new JTextField[4];
    JComboBox cuadroSeleccion = new JComboBox();
    JPasswordField cuadrosPassword[] = new JPasswordField[2];
    String textoLabels[] = {"ID", "Nombre", "Apellido", "Nick", "Rol", "Password", "Repetir password"};
    
    public FormularioUsuarioNuevo(){
        super("Nuevo Usuario");
        Ancho = 420;
        Alto = 630;
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        JPanel informacionPanel = new JPanel(new GridLayout((cuadrosTexto.length + cuadrosPassword.length + 1)*2,1,20,10));
        for(int i = 0; i < cuadrosTexto.length; i++){
            cuadrosTexto[i] = new JTextField();
            informacionPanel.add(new JLabel(textoLabels[i]));
            informacionPanel.add(cuadrosTexto[i]);
        }
        informacionPanel.add(new JLabel(textoLabels[cuadrosTexto.length]));
        cuadroSeleccion.addItem("Estudiante");
        cuadroSeleccion.addItem("Catedrático");
        informacionPanel.add(cuadroSeleccion);
        for(int i = 0; i < cuadrosPassword.length; i++){
            cuadrosPassword[i] = new JPasswordField();
            informacionPanel.add(new JLabel(textoLabels[i + cuadrosTexto.length + 1]));
            informacionPanel.add(cuadrosPassword[i]);
        }
        informacionPanel.setBounds(60,10,300,480);
        JPanel botonesPanel = new JPanel(new GridLayout(1,2,20,40));
        JButton aceptarBoton = new JButton("Aceptar"), borrarBoton = new JButton("Borrar"), cerrarBoton = new JButton("Cerrar");
        //aceptarBoton.addActionListener(this);
        //cerrarBoton.addActionListener(this);
        botonesPanel.add(aceptarBoton);
        botonesPanel.add(borrarBoton);
        botonesPanel.add(cerrarBoton);
        botonesPanel.setBounds(60,520,300,50);
        getContentPane().add(informacionPanel);
        getContentPane().add(botonesPanel);
    }
    
}