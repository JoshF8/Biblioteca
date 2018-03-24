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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Josh
 */
public class mostrarTabla extends VentanaPadre implements ActionListener{
    
    JTable tabla;
    JComboBox<String> ordenar = new JComboBox<String>();
    private int columnaOrdenar = 0;
    
    public mostrarTabla(VentanaPadre anterior, String tipoTabla, String tipoBusqueda) {
        super(tipoBusqueda, anterior);
        Ancho = 1000;
        Alto = 480;
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        if(tipoBusqueda.contains("Bib")){
            if(!tipoTabla.equals("")){
                llenarTabla(Biblioteca.bibliografiasActuales, tipoTabla);
            }else{
                llenarTabla(Biblioteca.bibliografiasActuales);
            }
        }else{
            if(!tipoTabla.equals("")){
                llenarTabla(Biblioteca.usuariosActivos, tipoTabla);
            }else{
                llenarTabla(Biblioteca.usuariosActivos);
            }
        }
        tabla.setEnabled(true);
        tabla.setRowHeight(30);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        tabla.getTableHeader().setEnabled(false);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(tabla.getModel());
        tabla.setRowSorter(elQueOrdena);
        elQueOrdena.toggleSortOrder(columnaOrdenar);
        JScrollPane tablaPanel = new JScrollPane();
        tablaPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tablaPanel.setViewportView(tabla);
        tablaPanel.setBounds(20,120, 960,300);
        getContentPane().add(tablaPanel);
    }
    
    public void llenarTabla(Bibliografia bibliografias[], String tipoTabla){
        String columnas[] = {"ID","Tipo", "Autor", "Título", "Descripción", "Palabras Clave", "Edición", "Temas", "Frecuencia actual", "Ejemplares", "Área", "Copias", "Disponibles", tipoTabla};
        Object datosTabla[][] = new Object[bibliografias.length][columnas.length];
        JButton botones[] = new JButton[bibliografias.length];
        for(int i = 0; i < bibliografias.length; i++){
            datosTabla[i][0] = bibliografias[i].getID();
            datosTabla[i][1] = bibliografias[i].getTipo();
            datosTabla[i][2] = bibliografias[i].getAutor();
            datosTabla[i][3] = bibliografias[i].getTitulo();
            datosTabla[i][4] = bibliografias[i].getDescripcion();
            datosTabla[i][5] = bibliografias[i].getPalabrasClave();
            datosTabla[i][6] = bibliografias[i].getEdicion();
            datosTabla[i][7] = bibliografias[i].getTemas();
            datosTabla[i][8] = bibliografias[i].getFrecuenciaActual();
            datosTabla[i][9] = bibliografias[i].getEjemplares();
            datosTabla[i][10] = bibliografias[i].getArea();
            datosTabla[i][11] = bibliografias[i].getCopias();
            datosTabla[i][12] = bibliografias[i].getDisponibles();
            botones[i] = new JButton(tipoTabla);
            datosTabla[i][13] = botones[i];
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(datosTabla, columnas){
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 0:
                    case 1:
                    case 6:
                    case 9:
                    case 11:
                    case 12:
                        return Integer.class;
                    case 13:
                        return JButton.class;
                    default:
                        return String.class;
                }
            }
            
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        DefaultTableCellRenderer render = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                if(value instanceof JButton){
                    JButton boton = (JButton)value;
                    return boton;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.setDefaultRenderer(Object.class, render);
        tabla.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());
                if(modeloTabla.getColumnClass(columna).equals(JButton.class)){
                    System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0));
                }
            }
        });
        columnaOrdenar = 6;
    }
    
    public void llenarTabla(Bibliografia bibliografias[]){
        String columnas[] = {"ID","Tipo", "Autor", "Título", "Descripción", "Palabras Clave", "Edición", "Temas", "Frecuencia actual", "Ejemplares", "Área", "Copias", "Disponibles"};
        Object datosTabla[][] = new Object[bibliografias.length][columnas.length];
        for(int i = 0; i < bibliografias.length; i++){
            datosTabla[i][0] = bibliografias[i].getID();
            datosTabla[i][1] = bibliografias[i].getTipo();
            datosTabla[i][2] = bibliografias[i].getAutor();
            datosTabla[i][3] = bibliografias[i].getTitulo();
            datosTabla[i][4] = bibliografias[i].getDescripcion();
            datosTabla[i][5] = bibliografias[i].getPalabrasClave();
            datosTabla[i][6] = bibliografias[i].getEdicion();
            datosTabla[i][7] = bibliografias[i].getTemas();
            datosTabla[i][8] = bibliografias[i].getFrecuenciaActual();
            datosTabla[i][9] = bibliografias[i].getEjemplares();
            datosTabla[i][10] = bibliografias[i].getArea();
            datosTabla[i][11] = bibliografias[i].getCopias();
            datosTabla[i][12] = bibliografias[i].getDisponibles();
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(datosTabla, columnas){
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 0:
                    case 1:
                    case 6:
                    case 9:
                    case 11:
                    case 12:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
            
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        columnaOrdenar = 6;
    }
    
    public void llenarTabla(Usuario usuarios[]){
    String columnas[] = {"ID","Nombre", "Apellido", "Nick", "Rol", "Password"};
        Object datosTabla[][] = new Object[usuarios.length - 1][columnas.length];
        Cliente user;
        for(int i = 1; i < usuarios.length; i++){
            user = (Cliente)usuarios[i];
            datosTabla[i - 1][0] = Long.valueOf(user.getID());
            datosTabla[i - 1][1] = user.getNombre();
            datosTabla[i - 1][2] = user.getApellido();
            datosTabla[i - 1][3] = user.getNick();
            datosTabla[i - 1][4] = user.getRol();
            datosTabla[i - 1][5] = user.getPassword();
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(datosTabla, columnas){
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 0:
                        return Long.class;
                    default:
                        return String.class;
                }
            }
            
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        
        tabla = new JTable(modeloTabla);
        columnaOrdenar = 0;
    }
    
    public void llenarTabla(Usuario usuarios[] , String tipoTabla){
    String columnas[] = {"ID","Nombre", "Apellido", "Nick", "Rol", "Password", tipoTabla};
        Object datosTabla[][] = new Object[usuarios.length - 1][columnas.length];
        JButton botones[] = new JButton[usuarios.length - 1];
        Cliente user;
        for(int i = 1; i < usuarios.length; i++){
            user = (Cliente)usuarios[i];
            datosTabla[i - 1][0] = Long.valueOf(user.getID());
            datosTabla[i - 1][1] = user.getNombre();
            datosTabla[i - 1][2] = user.getApellido();
            datosTabla[i - 1][3] = user.getNick();
            datosTabla[i - 1][4] = user.getRol();
            datosTabla[i - 1][5] = user.getPassword();
            botones[i - 1] = new JButton(tipoTabla);
            datosTabla[i - 1][6] = botones[i - 1];
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(datosTabla, columnas){
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 0:
                        return Long.class;
                    case 6:
                        return JButton.class;
                    default:
                        return String.class;
                }
            }
            
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        DefaultTableCellRenderer render = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                if(value instanceof JButton){
                    JButton boton = (JButton)value;
                    return boton;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.setDefaultRenderer(Object.class, render);
        tabla.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());
                if(modeloTabla.getColumnClass(columna).equals(JButton.class)){
                    System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0));
                }
            }
        });
        columnaOrdenar = 0;
    }
    
    @Override
    public void dispose(){
        cerrar(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println(e.getActionCommand());
    }
    
}
