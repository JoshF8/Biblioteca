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
/**
 *
 * @author Josh
 */


public class Inicio extends JFrame{
    public Inicio(){
        super("Proyecto 1 - IPC1");
        setSize(400, 300);
        setLocation(300,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2,1,5,50));
        panelBotones.add(new JButton("Login"));
        panelBotones.add(new JButton("About"));
        JPanel panelImagen = new JPanel();
        ImageIcon logo = new ImageIcon("C:\\Users\\Josh\\Documents\\GitHub\\Biblioteca\\Biblioteca\\src\\Ventanas\\logousac.png");
        JLabel imagenContenedor = new JLabel(logo);
        panelImagen.add(imagenContenedor);
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(panelImagen, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.CENTER);
    }
}
