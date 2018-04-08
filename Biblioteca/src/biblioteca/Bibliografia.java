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

/**
 *
 * @author Josh
 */
public class Bibliografia {
    private int ID, Tipo, Edicion, Ejemplares, Copias, Disponibles, vecesPrestado = 0;
    private String Autor, Titulo, Descripcion, PalabrasClave[], Temas[], FrecuenciaActual, Area;
    private static int contadorID = 0;
    public Bibliografia(int tipo, String autor, String titulo, String descripcion, String palabrasClave[],int edicion, String temas[], String frecuenciaActual, int ejemplares, String area, int copias, int disponibles){
        setTipo(tipo);
        setAutor(autor);
        setTitulo(titulo);
        setDescripcion(descripcion);
        setPalabrasClave(palabrasClave);
        setEdicion(edicion);
        setTemas(temas);
        if(getTipo() == 1){
            setFrecuenciaActual(frecuenciaActual);
            setEjemplares(ejemplares);
        }
        if(getTipo() == 2){
            setArea(area);
        }
        setCopias(copias);
        setDisponibles(disponibles);
        ID = contadorID++;
    }
    
    public void setTipo(int valor){
        Tipo = valor;
    }
    
    public void setAutor(String texto){
        Autor = texto;
    }
    
    public void setTitulo(String texto){
        Titulo = texto;
    }
    
    public void setDescripcion(String texto){
        Descripcion = texto;
    }
    
    public void setPalabrasClave(String texto[]){
        PalabrasClave = texto;
    }
    
    public void setEdicion(int valor){
        Edicion = valor;
    }
    
    public void setTemas(String texto[]){
        Temas = texto;
    }
    
    public void setFrecuenciaActual(String texto){
        FrecuenciaActual = texto;
    }
    
    public void setEjemplares(int valor){
        Ejemplares = valor;
    }
    
    public void setArea(String texto){
        Area = texto;
    }
    
    public void setCopias(int valor){
        Copias = valor;
    }
    
    public void setDisponibles(int valor){
        Disponibles = valor;
    }

    public void setVecesPrestado() {
        this.vecesPrestado++;
    }
    
    public int getID(){
        return ID;
    }
    
    public int getTipo(){
        return Tipo;
    }
    
    public String getAutor(){
        return Autor;
    }
    
    public String getTitulo(){
        return Titulo;
    }
    
    public String getDescripcion(){
        return Descripcion;
    }
    
    public String getPalabrasClave(){
        String resultado = "";
        for(int i = 0; i < PalabrasClave.length; i++){
            resultado += PalabrasClave[i] + ", ";
        }
        resultado = resultado.substring(0,resultado.length() - 2);
        return resultado;
    }
    
    public String[] getPalabrasClaveArray(){
        return PalabrasClave;
    }
    
    public int getEdicion(){
        return Edicion;
    }
    
    public String getTemas(){
        String resultado = "";
        for(int i = 0; i < Temas.length; i++){
            resultado += Temas[i] + ", ";
        }
        resultado = resultado.substring(0,resultado.length() - 2);
        return resultado;
    }
    
    public String getFrecuenciaActual(){
        return FrecuenciaActual;
    }
    
    public int getEjemplares(){
        return Ejemplares;
    }
    
    public String getArea(){
        return Area;
    }
    
    public int getCopias(){
        return Copias;
    }
    
    public int getDisponibles(){
        return Disponibles;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }
    
    
}
