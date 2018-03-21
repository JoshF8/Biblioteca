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
import biblioteca.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Josh
 */
public class Login extends VentanaPadre implements ActionListener{
    
    boolean log = false;
    JTextField usuarioTexto = new JTextField("");
    JPasswordField passwordTexto = new JPasswordField("");
    public Login(){
        super("Login");
        Ancho = 340;
        Alto = 500;
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        JPanel informacionPanel = new JPanel(new GridLayout(4,1,20,40));
        informacionPanel.add(new JLabel("Usuario"));
        informacionPanel.add(usuarioTexto);
        informacionPanel.add(new JLabel("Password"));
        informacionPanel.add(passwordTexto);
        informacionPanel.setBounds(50,40,240,220);
        JPanel botonesPanel = new JPanel(new GridLayout(1,2,20,40));
        JButton aceptarBoton = new JButton("Aceptar"), cerrarBoton = new JButton("Cerrar");
        aceptarBoton.addActionListener(this);
        cerrarBoton.addActionListener(this);
        botonesPanel.add(aceptarBoton);
        botonesPanel.add(cerrarBoton);
        botonesPanel.setBounds(50,340,240,50);
        getContentPane().add(informacionPanel);
        getContentPane().add(botonesPanel);
    }
    
    private void comprobarUsuario(){
        for (int i = 0; i < Biblioteca.usuariosActivos.length; i++) {
            if(Biblioteca.usuariosActivos[i].getNick().contentEquals(usuarioTexto.getText())){
                comprobarPassword(i);
                return;
            }
        }
        String mensaje = "El usuario no existe, ponerse en contacto con el administrador para solicitar registro.";
        JOptionPane.showMessageDialog(this, mensaje, "Error", 2);
    }
    
    private void comprobarPassword(int numeroUsuario){
        if(Biblioteca.usuariosActivos[numeroUsuario].getPassword().equals(passwordTexto.getText())){
            log = true;
            Biblioteca.usuarioConectado = Biblioteca.usuariosActivos[numeroUsuario];
            Biblioteca.usuarioConectado.abrirVentana();
            dispose();
        }else{
            String mensaje = "La contraseña que ha ingresado no coincide con el nombre de usuario.";
            JOptionPane.showMessageDialog(this, mensaje, "Contraseña incorrecta", 2);
        }
    }
    
    @Override
    public void dispose(){
        if(!log){
            Biblioteca.ventana.setVisible(true);
        }
        super.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Aceptar":
                comprobarUsuario();
                break;
            case "Cerrar":
                this.dispose();
                break;
        }
    }
    
}
