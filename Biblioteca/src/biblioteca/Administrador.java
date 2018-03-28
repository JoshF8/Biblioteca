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
import javax.swing.JOptionPane;

/**
 *
 * @author Josh
 */
public class Administrador extends Usuario implements ActionListener{
    
    public Administrador(String ID, String nombre, String nick, String password){
        super(ID, nombre, nick, password);
    }
    
    public void crearUsuario(String ID, String nombre,String Apellido, String nick, String Rol, String password, FormularioUsuarioNuevo ventana){
        Usuario nuevoCliente = new Cliente(ID, nombre, Apellido, nick, Rol, password);
        Biblioteca.auxUsuarios = new Usuario[Biblioteca.usuariosActivos.length + 1];
        for(int i = 0; i < Biblioteca.usuariosActivos.length; i++){
            Biblioteca.auxUsuarios[i] = Biblioteca.usuariosActivos[i];
        }
        Biblioteca.auxUsuarios[Biblioteca.auxUsuarios.length - 1] = nuevoCliente;
        Biblioteca.usuariosActivos = Biblioteca.auxUsuarios;
        JOptionPane.showMessageDialog(null, "Usuario guardado con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
        ventana.borrarTextos();
    }
    
    public void eliminarUsuario(String ID){
        int contador = 0, index = buscarObjeto(Biblioteca.usuariosActivos, ID);
        Biblioteca.auxUsuarios = new Usuario[Biblioteca.usuariosActivos.length - 1];
        for(Usuario usuario : Biblioteca.usuariosActivos){
            if(contador != index){
                Biblioteca.auxUsuarios[contador] = usuario;
                contador++;
            }else{
                index = -1;
            }
        }
        Biblioteca.usuariosActivos = Biblioteca.auxUsuarios;
    }
    
    public void modificarUsuario(String ID, VentanaPadre anterior){
        int index = buscarObjeto(Biblioteca.usuariosActivos, ID);
        FormularioUsuarioNuevo ventana = new FormularioUsuarioNuevo(anterior, 1);
        ventana.llenarTextos(index);
        ventana.setVisible(true);
    }
    
    public void guardarCambiosUsuario(int index, String ID, String nombre,String Apellido, String nick, String Rol, String password, FormularioUsuarioNuevo ventana){
        Cliente usuario = (Cliente)Biblioteca.usuariosActivos[index];
        usuario.setID(ID);
        usuario.setNombre(nombre);
        usuario.setApellido(Apellido);
        usuario.setNick(nick);
        usuario.setRol(Rol);
        usuario.setPassword(password);
        JOptionPane.showMessageDialog(null, "Cambios guardados con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
        ventana.borrarTextos();
    }
    
    
    
    public void crearBibliografia(int tipo, String autor, String titulo, String descripcion, String palabrasClave[],int edicion, String temas[], String frecuenciaActual, int ejemplares, String area, int copias, int disponibles){
        Bibliografia nuevaBibliografia = new Bibliografia(tipo, autor, titulo, descripcion, palabrasClave,edicion, temas, frecuenciaActual, ejemplares, area, copias, disponibles);
        Biblioteca.auxBibliografia = new Bibliografia[Biblioteca.bibliografiasActuales.length + 1];
        for(int i = 0; i < Biblioteca.bibliografiasActuales.length; i++ ){
            Biblioteca.auxBibliografia[i] = Biblioteca.bibliografiasActuales[i];
        }
        Biblioteca.auxBibliografia[Biblioteca.auxBibliografia.length - 1] = nuevaBibliografia;
        Biblioteca.bibliografiasActuales = Biblioteca.auxBibliografia;
    }
    
    public void eliminarBibliografia(String ID){
        int contador = 0, index = buscarObjeto(Biblioteca.bibliografiasActuales, ID);
        Biblioteca.auxBibliografia = new Bibliografia[Biblioteca.bibliografiasActuales.length - 1];
        for(Bibliografia bibliografia : Biblioteca.bibliografiasActuales){
            if(contador != index){
                Biblioteca.auxBibliografia[contador] = bibliografia;
                contador++;
            }else{
                index = -1;
            }
        }
        Biblioteca.bibliografiasActuales = Biblioteca.auxBibliografia;
    }
    
    public void modificarBibliografia(String ID, VentanaPadre anterior){
        int index = buscarObjeto(Biblioteca.bibliografiasActuales, ID);
        FormularioBibliografiaNueva ventana = new FormularioBibliografiaNueva(anterior, 1);
        ventana.llenarTextos(index);
        ventana.setVisible(true);
    }
    
    public void guardarCambiosBibliografia(int index, int tipo, String autor, String titulo, String descripcion, String palabrasClave[],int edicion, String temas[], String frecuenciaActual, int ejemplares, String area, int copias, int disponibles, FormularioBibliografiaNueva ventana){
        Bibliografia bibliografia = Biblioteca.bibliografiasActuales[index];
        bibliografia.setTipo(tipo);
        bibliografia.setAutor(autor);
        bibliografia.setTitulo(titulo);
        bibliografia.setDescripcion(descripcion);
        bibliografia.setPalabrasClave(palabrasClave);
        bibliografia.setEdicion(edicion);
        bibliografia.setTemas(temas);
        bibliografia.setFrecuenciaActual(frecuenciaActual);
        bibliografia.setEjemplares(ejemplares);
        bibliografia.setArea(area);
        bibliografia.setCopias(copias);
        bibliografia.setDisponibles(disponibles);
        JOptionPane.showMessageDialog(null, "Cambios guardados con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
        ventana.borrarTextos();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
