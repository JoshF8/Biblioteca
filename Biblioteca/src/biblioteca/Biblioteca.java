/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
import Ventanas.Inicio;
import Ventanas.*;
/**
 *
 * @author Josh
 */
public class Biblioteca{

    /**
     * @param args the command line arguments
     */
    
    public static Usuario usuariosActivos[], auxUsuarios[], usuarioConectado;
    public static Bibliografia bibliografiasActuales[] = new Bibliografia[0], auxBibliografia[];
    public static VentanaPadre ventana;
    
    public static void main(String[] args) {
        usuariosActivos = new Usuario[2];
        Administrador admin = new Administrador("admin","administrador","admin","password");
        usuariosActivos[0] = admin;
        usuariosActivos[1] = new Cliente("abc", "Juan", "ra", "abcd", "Estudiante", "123");
        ventana = new Inicio(null);
        ventana.setVisible(true);
        /*VentanaAdministrador prueba = new VentanaAdministrador();
        prueba.setVisible(true);*/
    }
    
}
