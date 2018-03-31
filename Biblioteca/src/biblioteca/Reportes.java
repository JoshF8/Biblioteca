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
        String ruta = "Reportes\\ReporteBibliografias.html";
        File archivo = new File(ruta);
        BufferedWriter buffer = new BufferedWriter(new FileWriter(archivo));
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
    
    private static void escribirReporteBibliografias(){
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
        for(Bibliografia bibliografia : Biblioteca.bibliografiasActuales){
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
