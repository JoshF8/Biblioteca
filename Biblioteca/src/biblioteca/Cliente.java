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
public class Cliente extends Usuario{
    
    private String apellido, rol;
    private Bibliografia favoritos[] = new Bibliografia[0];
    
    public Cliente(String ID, String nombre, String apellido, String nick, String rol, String password){
        super(ID, nombre, nick, password);
        this.apellido = apellido;
        this.rol = rol;
    }
    
    public void setApellido(String texto){
        apellido = texto;
    }
    
    public void setRol(String texto){
        rol = texto;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public String getRol(){
        return rol;
    }
    
}
