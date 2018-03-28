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
package biblioteca;

import Ventanas.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Josh
 */
public class Usuario implements ActionListener{
    protected String ID, nombre, nick, password;
    protected boolean logueado = false;
    public Usuario(String ID, String nombre, String nick, String password){
        setID(ID);
        setNombre(nombre);
        setNick(nick);
        setPassword(password);
    }
    
    public void setID(String texto){
        ID = texto;
    }
    
    public void setNombre(String texto){
        nombre = texto;
    }
    
    public void setNick(String texto){
        nick = texto;
    }
    
    public void setPassword(String texto){
        password = texto;
    }
    
    public String getID(){
        return ID;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getNick(){
        return nick;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void abrirVentana(VentanaPadre anterior){
        logueado = true;
        if(!ID.equals("admin")){
            VentanaCliente ventana = new VentanaCliente(anterior, (Cliente)this);
            ventana.setVisible(true);
        }else{
            VentanaAdministrador ventana = new VentanaAdministrador(anterior);
            ventana.setVisible(true);
        }
    }
    
    protected int buscarObjeto(Object  coleccion[], String ID){
        try {
            Bibliografia bibliografias[] = (Bibliografia[]) coleccion;
            for(int i = 0; i < bibliografias.length; i++){
                if(ID.equals(String.valueOf(bibliografias[i].getID()))){
                    return i;
                }
            }
        } catch (Exception e) {
            Usuario usuarios[] = (Usuario[]) coleccion;
            for(int i = 1; i < usuarios.length; i++){
                if(ID.equals(usuarios[i].getID())){
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void cerrarSesion(){
        Biblioteca.usuarioConectado = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
    
}
