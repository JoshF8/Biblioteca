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
public class VentanaCliente extends VentanaPadre implements ActionListener{
    
    public VentanaCliente(VentanaPadre anterior, Cliente cliente){
        super("Cliente",anterior);
        Ancho = 680;
        Alto = 230;
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        JLabel titulo = new JLabel("Bienvenido " + cliente.getNombre() + " " + cliente.getApellido());
        titulo.setFont(new Font(titulo.getFont().getFontName(), titulo.getFont().getSize(),16));
        titulo.setBounds(30,10,320,50);
        JPanel botonesPanel = new JPanel(new GridLayout(1,4,20,40));
        JButton bibliografiasBoton = new JButton("Biliografías"), favoritosBoton = new JButton("Favoritos"), prestarBoton = new JButton("Prestar"),prestamosBoton = new JButton("Préstamos"), cerrarBoton = new JButton("Cerrar sesión");
        bibliografiasBoton.addActionListener(this);
        favoritosBoton.addActionListener(this);
        prestarBoton.addActionListener(this);
        prestamosBoton.addActionListener(this);
        cerrarBoton.addActionListener(this);
        botonesPanel.add(bibliografiasBoton);
        botonesPanel.add(favoritosBoton);
        botonesPanel.add(prestarBoton);
        botonesPanel.add(prestamosBoton);
        botonesPanel.add(cerrarBoton);
        botonesPanel.setBounds(20,100,640,50);
        getContentPane().add(titulo);
        getContentPane().add(botonesPanel);
    }
    
    @Override
    public void dispose(){
        int opcion = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesión?", "Salir", JOptionPane.YES_NO_OPTION);
        if(opcion == JOptionPane.YES_OPTION){
            Biblioteca.usuarioConectado.cerrarSesion();
            cerrar(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mostrarTabla ventana;
        switch(e.getActionCommand()){
            case "Biliografías":
                ventana = new mostrarTabla(this, "Agregar", "Bibliografías");
                ventana.setVisible(true);
                this.setVisible(false);
                break;
            case "Favoritos":
                ventana = new mostrarTabla(this, "Eliminar(favoritos)", "Bibliografías");
                ventana.setVisible(true);
                this.setVisible(false);
                break;
            case "Prestar":
                ventana = new mostrarTabla(this, "Prestar", "Bibliografías");
                ventana.setVisible(true);
                this.setVisible(false);
                break;
            case "Préstamos":
                ventana = new mostrarTabla(this, "Devolver", "Préstamos");
                ventana.setVisible(true);
                this.setVisible(false);
                break;
            case "Cerrar sesión":
                dispose();
                break;
        }
    }
    
}
