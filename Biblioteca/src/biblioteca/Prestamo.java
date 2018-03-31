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
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Josh
 */
public class Prestamo {
   private static int IDContador = 0;
   private int ID, indexBibliografia, indexUsuario;
   private GregorianCalendar fechaPrestamo, fechaLimite;
   private Bibliografia bibliografia;
   private Cliente cliente;
   
   public Prestamo(Bibliografia bibliografia, String IDUsuario){
       this.bibliografia = bibliografia;
       this.indexUsuario = Usuario.buscarObjeto(Biblioteca.usuariosActivos, IDUsuario);
       this.cliente = (Cliente)Biblioteca.usuariosActivos[indexUsuario];
       this.ID = IDContador++;
       this.fechaPrestamo = new GregorianCalendar();
       this.fechaLimite = new GregorianCalendar();
       this.fechaLimite.add(GregorianCalendar.DATE, 7);
   }
   
   public int getID(){
       return ID;
   }
   
   public String getAutor(){
       return bibliografia.getAutor();
   }
   
   public String getTitulo(){
       return bibliografia.getTitulo();
   }
   
   public String getNombre(){
       return cliente.getNombre();
   }
   
   public String getApellido(){
       return cliente.getApellido();
   }
   
   public String getFechaPrestamo(){
       String fecha = fechaPrestamo.getTime().getDate() + "/" + (fechaPrestamo.getTime().getMonth() + 1) + "/" + (fechaPrestamo.getTime().getYear() + 1900);
       return fecha;
   }
   public String getFechaLimite(){
       String fecha = fechaLimite.getTime().getDate() + "/" + (fechaLimite.getTime().getMonth() + 1) + "/" + (fechaLimite.getTime().getYear() + 1900);
       return fecha;
   }
   
   public String getHoraPrestamo(){
       String cero = "";
       if(!(fechaPrestamo.getTime().getMinutes() > 9)){
           cero = "0";
       }
       String hora = fechaPrestamo.getTime().getHours() + ":" + cero + fechaPrestamo.getTime().getMinutes();
       return hora;
   }
   
   public GregorianCalendar getFechaPrestamoCalendar(){
       return fechaPrestamo;
   }
   
   public GregorianCalendar getFechaLimiteCalendar(){
       return fechaLimite;
   }
   
   public Bibliografia getBibliografia(){
       return this.bibliografia;
   }
   
   public Usuario getUsuario(){
       return this.cliente;
   }
}
