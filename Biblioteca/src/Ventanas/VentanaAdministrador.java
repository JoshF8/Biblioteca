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
import biblioteca.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


/**
 *
 * @author Josh
 */
public class VentanaAdministrador extends VentanaPadre implements ActionListener{
    
    private Administrador admin = (Administrador)Biblioteca.usuarioConectado;
    public VentanaAdministrador(VentanaPadre anterior){
        super("Administrador", anterior);
        Ancho = 600;
        Alto = 280;
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        JLabel titulo = new JLabel("Administrador");
        titulo.setFont(new Font(titulo.getFont().getFontName(), titulo.getFont().getSize(),20));
        titulo.setBounds(40,10,150,50);
        JButton logout = new JButton("Cerrar sesión");
        logout.addActionListener(this);
        logout.setBounds(400,10,150,50);
        JPanel UsuariosPanel = new JPanel(new GridLayout(2,2,30,30));
        JPanel BibliografiasPanel = new JPanel(new GridLayout(2,2,30,30));
        JButton Botones[] = new JButton[8];
        Botones[0] = new JButton("Crear");
        Botones[0].addActionListener(this);
        Botones[0].setActionCommand("CrearU");
        Botones[1] = new JButton("Modificar");
        Botones[1].addActionListener(this);
        Botones[1].setActionCommand("ModificarU");
        Botones[2] = new JButton("Eliminar");
        Botones[2].addActionListener(this);
        Botones[2].setActionCommand("EliminarU");
        Botones[3] = new JButton("Mostrar");
        Botones[3].addActionListener(this);
        Botones[3].setActionCommand("MostrarU");
        Botones[4] = new JButton("Crear");
        Botones[4].addActionListener(this);
        Botones[4].setActionCommand("CrearB");
        Botones[5] = new JButton("Modificar");
        Botones[5].addActionListener(this);
        Botones[5].setActionCommand("ModificarB");
        Botones[6] = new JButton("Eliminar");
        Botones[6].addActionListener(this);
        Botones[6].setActionCommand("EliminarB");
        Botones[7] = new JButton("Mostrar");
        Botones[7].addActionListener(this);
        Botones[7].setActionCommand("MostrarB");
        for(int i = 0; i < 8; i++){
            if(i<4){
                UsuariosPanel.add(Botones[i]);
            }else{
                BibliografiasPanel.add(Botones[i]);
            }
        }
        UsuariosPanel.setBorder(new TitledBorder(new EtchedBorder(Color.BLACK, null), "Usuarios"));
        BibliografiasPanel.setBorder(new TitledBorder(new EtchedBorder(Color.BLACK, null), "Bibliografías"));
        UsuariosPanel.setBounds(30, 80, 240, 150);
        BibliografiasPanel.setBounds(330, 80, 240, 150);
        getContentPane().add(titulo);
        getContentPane().add(logout);
        getContentPane().add(UsuariosPanel);
        getContentPane().add(BibliografiasPanel);
    }
    
    public void crearVentana(int tipo){
        VentanaPadre nuevaVentana = null;
        switch(tipo){
            case 0:
                //Cambiar
                nuevaVentana = new FormularioUsuarioNuevo(this);
                break;
            case 1:
                nuevaVentana = new FormularioBibliografiaNueva(this);
                break;
        }
        nuevaVentana.setVisible(true);
        this.setVisible(false);
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
            case "Cerrar sesión":
                    dispose();
                break;
            case "CrearU":
                crearVentana(0);
                break;
            case "CrearB":
                crearVentana(1);
                break;
            case "MostrarU":
                ventana = new mostrarTabla(this, "", "Usuarios");
                ventana.setVisible(true);
                this.setVisible(false);
                break;
            case "ModificarU":
                ventana = new mostrarTabla(this, "Modificar", "Usuarios");
                ventana.setVisible(true);
                this.setVisible(false);
                break;
            case "EliminarU":
                ventana = new mostrarTabla(this, "Eliminar", "Usuarios");
                ventana.setVisible(true);
                this.setVisible(false);
                break;
            case "MostrarB":
                ventana = new mostrarTabla(this, "", "Bibliografías");
                ventana.setVisible(true);
                this.setVisible(false);
                break;
            case "ModificarB":
                ventana = new mostrarTabla(this, "Modificar", "Bibliografías");
                ventana.setVisible(true);
                this.setVisible(false);
                break;
            case "EliminarB":
                ventana = new mostrarTabla(this, "Eliminar", "Bibliografías");
                ventana.setVisible(true);
                this.setVisible(false);
                break;
        }
    }
}
