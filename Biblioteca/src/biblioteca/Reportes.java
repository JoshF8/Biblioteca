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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Josh
 */
public class Reportes {
    static String pagina;
    
    public static void escribirReportes() throws IOException{
        String ruta = "Reportes\\ReporteUsuarios.html";
        File archivo = new File(ruta);
        BufferedWriter buffer = new BufferedWriter(new FileWriter(archivo));
        escribirCabecera();
        escribirReporteUsuarios();
        buffer.write(pagina);
        buffer.close();
        ruta = "Reportes\\ReporteBibliografias.html";
        archivo = new File(ruta);
        buffer = new BufferedWriter(new FileWriter(archivo));
        escribirCabecera();
        escribirReporteBibliografias();
        buffer.write(pagina);
        buffer.close();
        ruta = "Reportes\\ReportePrestamos.html";
        archivo = new File(ruta);
        buffer = new BufferedWriter(new FileWriter(archivo));
        escribirCabecera();
        escribirReportePrestamos();
        buffer.write(pagina);
        buffer.close();
    }
    
    private static void escribirCabecera(){
        pagina = "<!DOCTYPE html>\n" +
        "<html lang=\"en\" >\n" +
        "<head>\n" +
        "  <meta charset=\"UTF-8\">\n" +
        "  <title></title>\n" +
        "      <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
        "</head>/n";
    }
    
    private static void escribirReporteUsuarios(){
        pagina += "<div class=\"wrapper\">";
        Usuario usuarios[] = Biblioteca.usuariosActivos;
        Cliente cliente;
        for(int i = 1; i < usuarios.length; i++){
            cliente = (Cliente)usuarios[i];
            pagina += "<div><h2 style= 'color: #fff'>" + cliente.getNombre() + " " + cliente.getApellido() + "</h2>";
           pagina +=
"  <div class=\"table\">\n" +
"    <div class=\"row header green\">\n" +
"      <div class=\"cell\">\n" +
"        ID\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Título bibliografía\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Fecha préstamo\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Fecha devolución\n" +
"      </div>\n" +
"    </div>";
        for(Prestamo prestamo : Biblioteca.prestamos){
            if(prestamo.getUsuario() == usuarios[i]){
                    pagina += "<div class=\"row\">\n" +
        "      <div class=\"cell\" data-title=\"ID\">\n" +
        "        "+ prestamo.getID() +"\n" +
        "      </div>\n" +
        "      <div class=\"cell\" data-title=\"Título bibliografía\">\n" +
        "        " + prestamo.getTitulo() +"\n" +
        "      </div>\n" +
        "      <div class=\"cell\" data-title=\"Fecha préstamo\">\n" +
        "        "+ prestamo.getFechaPrestamo() +"\n" +
        "      </div>\n" +
        "      <div class=\"cell\" data-title=\"Fecha devolución\">\n" +
        "        "+ prestamo.getFechaLimite()+"\n" +
        "      </div>\n" +
        "    </div>";
            }
        }
        pagina += 
"      </div>\n" ;
        Bibliografia bibliografia;
        pagina += "  <div class=\"table\">\n" +
"    <div class=\"row header blue\">\n" +
"      <div class=\"cell\">\n" +
"        ID\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Título\n" +
"      </div>\n" + "<div class=\"cell\">\n" +
"        Autor\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Cantidad de préstamos\n" +
"      </div>\n" +
"    </div>";
        for(int j = 0; j < cliente.getFavoritos().length; j++){
            bibliografia = cliente.getFavoritos()[j];
            pagina += "<div class=\"row\">\n" +
"      <div class=\"cell\" data-title=\"ID\">\n" +
"        "+ bibliografia.getID() +"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Título\">\n" +
"        " + bibliografia.getTitulo() +"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Autor\">\n" +
"        " + bibliografia.getAutor()+"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Cantidad de préstamos\">\n" +
"        "+ bibliografia.getVecesPrestado() +"\n" +
"      </div>\n" +
"    </div>";
        }
        pagina += "</div>";
        }
        pagina += "</div>\n" +
"</div>\n" +
"</body>\n" +
"</html>";
    }
    
    private static void escribirReporteBibliografias(){
        int valores[] = new int[3], index[] = new int[3];
        index[0] = -1;
        index[1] = -1;
        index[2] = -1;
        pagina += "<div class=\"wrapper\">\n" +
"  <div class=\"table\">\n" +
"    <div class=\"row header blue\">\n" +
"      <div class=\"cell\">\n" +
"        ID\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Título\n" +
"      </div>\n" + "<div class=\"cell\">\n" +
"        Autor\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Cantidad de préstamos\n" +
"      </div>\n" +
"    </div>";
        Bibliografia bibliografia;
        for(int i = 0; i < Biblioteca.bibliografiasActuales.length; i++){
            bibliografia = Biblioteca.bibliografiasActuales[i];
            pagina += "<div class=\"row\">\n" +
"      <div class=\"cell\" data-title=\"ID\">\n" +
"        "+ bibliografia.getID() +"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Título\">\n" +
"        " + bibliografia.getTitulo() +"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Autor\">\n" +
"        " + bibliografia.getAutor()+"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Cantidad de préstamos\">\n" +
"        "+ bibliografia.getVecesPrestado() +"\n" +
"      </div>\n" +
"    </div>";
            if(bibliografia.getVecesPrestado() > valores[2]){
                if(bibliografia.getVecesPrestado() > valores[1]){
                    if(bibliografia.getVecesPrestado() > valores[0]){
                        valores[2] = valores[1];
                        index[2] = index[1];
                        valores[1] = valores[0];
                        index[1] = index[0];
                        valores[0] = bibliografia.getVecesPrestado();
                        index[0] = i;
                    }else{
                        valores[2] = valores[1];
                        index[2] = valores[1];
                        valores[1] = bibliografia.getVecesPrestado();
                        index[1] = i;
                    }
                }else{
                    valores[2] = bibliografia.getVecesPrestado();
                    index[2] = i;
                }
            }
        }
        
        if(index[0] != -1){
            pagina += "</div><div class=\"wrapper\">\n" +
"  <div class=\"table\">\n" +
"    <div class=\"row header red\">\n" +
"      <div class=\"cell\">\n" +
"        ID\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Título\n" +
"      </div>\n" + "<div class=\"cell\">\n" +
"        Autor\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Cantidad de préstamos\n" +
"      </div>\n" +
"    </div>";
            for(int i = 0; i < 3; i++){
                if(index[i] == -1){
                    break;
                }
                bibliografia = Biblioteca.bibliografiasActuales[index[i]];
            pagina += "<div class=\"row\">\n" +
"      <div class=\"cell\" data-title=\"ID\">\n" +
"        "+ bibliografia.getID() +"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Título\">\n" +
"        " + bibliografia.getTitulo() +"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Autor\">\n" +
"        " + bibliografia.getAutor()+"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Cantidad de préstamos\">\n" +
"        "+ bibliografia.getVecesPrestado() +"\n" +
"      </div>\n" +
"    </div>";
            }
        }
        pagina += "</div>\n" +
"</div>\n" +
"</body>\n" +
"</html>";
        
    }
    
    private static void escribirReportePrestamos(){
        pagina += "<div class=\"wrapper\">\n" +
"  <div class=\"table\">\n" +
"    <div class=\"row header green\">\n" +
"      <div class=\"cell\">\n" +
"        ID\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Título bibliografía\n" +
"      </div>\n" + "<div class=\"cell\">\n" +
"        Nombre usuario\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Fecha préstamo\n" +
"      </div>\n" +
"      <div class=\"cell\">\n" +
"        Fecha devolución\n" +
"      </div>\n" +
"    </div>";
        for(Prestamo prestamo : Biblioteca.prestamosTotales){
            pagina += "<div class=\"row\">\n" +
"      <div class=\"cell\" data-title=\"ID\">\n" +
"        "+ prestamo.getID() +"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Título bibliografía\">\n" +
"        " + prestamo.getTitulo() +"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Nombre usuario\">\n" +
"        " + prestamo.getNombre() + " " + prestamo.getApellido() +"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Fecha préstamo\">\n" +
"        "+ prestamo.getFechaPrestamo() +"\n" +
"      </div>\n" +
"      <div class=\"cell\" data-title=\"Fecha devolución\">\n" +
"        "+ prestamo.getFechaLimite()+"\n" +
"      </div>\n" +
"    </div>";
        }
        pagina += "</div>\n" +
"</div>\n" +
"</body>\n" +
"</html>";
    }
}
