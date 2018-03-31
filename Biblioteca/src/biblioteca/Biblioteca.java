/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
import Ventanas.Inicio;
import Ventanas.*;
import java.util.Locale;
import java.util.TimeZone;
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
    public static Prestamo prestamos[] = new Prestamo[0];
    
    public static void main(String[] args) {
        usuariosActivos = new Usuario[4];
        Administrador admin = new Administrador("admin","administrador","admin","password");
        usuariosActivos[0] = admin;
        usuariosActivos[1] = new Cliente("2569567840101", "Juan", "ra", "abcd", "Estudiante", "123");
        usuariosActivos[2] = new Cliente("2562427840101", "Jose", "te", "efg", "Estudiante", "456");
        usuariosActivos[3] = new Cliente("2562342490101", "Pedro", "qa", "hij", "Catedrático", "789");
        ventana = new Inicio(null);
        ventana.setVisible(true);
        /*VentanaAdministrador prueba = new VentanaAdministrador();
        prueba.setVisible(true);*/
    }
    
}
