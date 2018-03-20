/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
import Ventanas.Inicio;
import Ventanas.VentanaAdministrador;
/**
 *
 * @author Josh
 */
public class Biblioteca{

    /**
     * @param args the command line arguments
     */
    
    public static Usuario usuariosActivos[], auxUsuarios[], usuarioConectado;
    public static Inicio ventana;
    
    public static void main(String[] args) {
        usuariosActivos = new Usuario[1];
        Administrador admin = new Administrador("admin","administrador","admin","password");
        usuariosActivos[0] = admin;
        ventana = new Inicio();
        //ventana.setVisible(true);
        VentanaAdministrador prueba = new VentanaAdministrador();
        prueba.setVisible(true);
    }
    
}
