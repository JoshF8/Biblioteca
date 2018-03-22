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
package Ventanas;
import javax.swing.*;
import java.awt.*;
import biblioteca.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Josh
 */
public class cargaMasivaBibliografias extends VentanaPadre implements ActionListener{
    
    JTextArea areaTexto = new JTextArea("", 10 , 10);
    TextPrompt ejemplo = new TextPrompt("Tipo; Autor; Título; Descripción; Palabras Clave; Edición; Temas; Frecuencia Actual; Ejemplares; Área;\n" +
"Copias; Disponibles;", areaTexto);
    Administrador admin = (Administrador) Biblioteca.usuarioConectado;
    
    
    public cargaMasivaBibliografias(VentanaPadre anterior){
        super("Carga masiva de bibliografías", anterior);
        Ancho = 840;
        Alto = 500;
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        areaTexto.setBounds(20,20,800,300);
        ejemplo.changeAlpha(0.75f);
        ejemplo.setVerticalAlignment(TextPrompt.TOP);
        JPanel botonesPanel = new JPanel(new GridLayout(1,4,20,40));
        JButton aceptarBoton = new JButton("Aceptar"), borrarBoton = new JButton("Borrar"), cerrarBoton = new JButton("Cerrar");
        aceptarBoton.addActionListener(this);
        borrarBoton.addActionListener(this);
        cerrarBoton.addActionListener(this);
        botonesPanel.add(aceptarBoton);
        botonesPanel.add(borrarBoton);
        botonesPanel.add(cerrarBoton);
        botonesPanel.setBounds(120,400,600,50);
        getContentPane().add(areaTexto);
        getContentPane().add(botonesPanel);
    }
    
    public void comprobarGuardado(){
        String datos[] = areaTexto.getText().split(";"), mensaje = "";
        for(int i = 0; i < datos.length; i++){
            datos[i] = datos[i].trim();
        }
        if(datos.length%12 == 0){
            for(int i = 0; i < datos.length/12; i++){
                if(!(esNumero(datos[i*12]) && esNumero(datos[i*12 + 5]) && esNumero(datos[i*12 + 10]) && esNumero(datos[i*12 + 11]))){
                    mensaje = "Revise el orden en el que ingresó los datos en los campos.";
                    break;
                }
                if(datos[i*12+1].equals("") || datos[i*12+2].equals("") || datos[i*12+3].equals("") || datos[i*12+4].equals("") || datos[i*12+6].equals("")){
                    mensaje = "Existe un error en la sintaxis de los datos que desea ingresar";
                    break;
                }
                if((Integer.valueOf(datos[i*12]) == 0) && !(datos[i*12+7].equals("") && datos[i*12+8].equals("") && datos[i*12+9].equals(""))){
                    mensaje = "Existe un error en la sintaxis de los datos que desea ingresar.";
                    break;
                }
                if((Integer.valueOf(datos[i*12]) == 1) && !(!datos[i*12+7].equals("") && esNumero(datos[i*12+8]) && datos[i*12+9].equals(""))){
                    mensaje = "Existe un error en la sintaxis de los datos que desea ingresar.";
                    break;
                }
                if((Integer.valueOf(datos[i*12]) == 2) && !(datos[i*12+7].equals("") && datos[i*12+8].equals("") && !datos[i*12+9].equals(""))){
                    mensaje = "Existe un error en la sintaxis de los datos que desea ingresar.";
                    break;
                }
                if(Integer.valueOf(datos[i*12]) != 1){
                    datos[i*12 + 8] = "0";
                }
            }
            for(int i = 0; i < datos.length/12; i++){
                admin.crearBibliografia(Integer.valueOf(datos[i*12]), datos[i*12+1], datos[i*12+2], datos[i*12+3], datos[i*12+4].split(","), Integer.valueOf(datos[i*12+5]), datos[i*12+6].split(","), datos[i*12+7], Integer.valueOf(datos[i*12+8]), datos[i*12+9], Integer.valueOf(datos[i*12+10]), Integer.valueOf(datos[i*12+11]));
            }
            JOptionPane.showMessageDialog(null, "Biliografías creadas con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
            borrarTexto();
        }else{
            mensaje = "Revise la cantidad de campos ingresados.";
        }
        if(!mensaje.equals("")){
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void borrarTexto(){
        areaTexto.setText("");
    }
    
    public boolean esNumero(String texto){
        try{
            Integer.parseInt(texto);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
    
    @Override
    public void dispose(){
        if(!areaTexto.getText().equals("")){
            if(JOptionPane.showConfirmDialog(this, "Al salir se perderán los datos. ¿Está seguro de salir?", "Salir",JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION){
                cerrar(true);
            }
        }else{
            cerrar(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         switch(e.getActionCommand()){
            case "Cerrar":
                dispose();
                break;
            case "Borrar":
                borrarTexto();
                break;
            case "Aceptar":
                comprobarGuardado();
                break;
        }
    }
}
