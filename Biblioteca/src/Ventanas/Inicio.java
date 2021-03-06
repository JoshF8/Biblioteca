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
import biblioteca.Reportes;
/**
 *
 * @author Josh
 */


public class Inicio extends VentanaPadre implements ActionListener{
    
    public Inicio(VentanaPadre anterior){
        super("Proyecto 1 - IPC1", anterior);
        Ancho = 240;
        Alto = 240;
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2,1,0,30));
        JButton loginBoton = new JButton("Login"), aboutBoton = new JButton("About");
        loginBoton.addActionListener(this);
        loginBoton.setActionCommand("Login");
        aboutBoton.addActionListener(this);
        aboutBoton.setActionCommand("About");
        panelBotones.add(loginBoton);
        panelBotones.add(aboutBoton);
        panelBotones.setOpaque(false);
        JPanel panelImagen = new JPanel(new FlowLayout());
        ImageIcon logo = new ImageIcon("src\\Ventanas\\logousac.png");
        JLabel imagenContenedor = new JLabel();
        imagenContenedor.setSize(Ancho, Alto);
        Icon icono = new ImageIcon(logo.getImage().getScaledInstance(imagenContenedor.getWidth(), imagenContenedor.getHeight()-20, Image.SCALE_SMOOTH));
        imagenContenedor.setIcon(icono);
        repaint();
        panelImagen.setBounds(0, 0, Ancho, Alto);
        panelImagen.add(imagenContenedor);
        panelBotones.setBounds(70, 30, 100, 150);
        getContentPane().add(panelBotones);
        getContentPane().add(panelImagen);
    }
    
    @Override
    public void dispose(){
        try {
            Reportes.escribirReportes();
        } catch (Exception e) {
        }
        
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Login":
                Login nuevoLogin = new Login(this);
                nuevoLogin.setVisible(true);
                this.setVisible(false);
                break;
            case "About":
                String mensaje = "Universidad de San Carlos de Guatemala 2018\nJoshua Estuardo Franco Equité 201708845\nPrimer proyecto de la clase de IPC1";
                JOptionPane.showMessageDialog(this, mensaje, "About", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
}
