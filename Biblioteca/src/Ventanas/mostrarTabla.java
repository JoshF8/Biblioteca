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
import java.util.GregorianCalendar;
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
    TableRowSorter<TableModel> elQueOrdena;
    JComboBox<String> ordenar = new JComboBox<String>();
    private int columnaOrdenar = 0, itemSeleccionado = 0;
    JTextField textos[];
    TextPrompt placeHolders[];
    JButton botones[];
    Administrador admin = (Administrador)Biblioteca.usuariosActivos[0];
    
    public mostrarTabla(VentanaPadre anterior, String tipoTabla, String tipoBusqueda) {
        super(tipoBusqueda, anterior);
        Ancho = 1000;
        Alto = 600;
        setSize(Ancho, Alto);
        setLocationRelativeTo(null);
        switch(tipoBusqueda){
            case "Bibliografías":
                if(!tipoTabla.equals("")){
                    if(tipoTabla.contains("favoritos") || tipoTabla.equals("Prestar")){
                        Cliente cliente = (Cliente)Biblioteca.usuarioConectado;
                        llenarTabla(cliente.getFavoritos(), tipoTabla);
                    }else{
                        llenarTabla(Biblioteca.bibliografiasActuales, tipoTabla);
                    }
                }else{
                    llenarTabla(Biblioteca.bibliografiasActuales);
                }
                break;
            case "Usuarios":
                if(!tipoTabla.equals("")){
                    llenarTabla(Biblioteca.usuariosActivos, tipoTabla);
                }else{
                    llenarTabla(Biblioteca.usuariosActivos);
                }
                break;
            case "Préstamos":
                Cliente cliente = (Cliente)Biblioteca.usuarioConectado;
                llenarTabla(Biblioteca.prestamos, "Devolver");
                break;
        }
        tabla.setEnabled(true);
        tabla.setRowHeight(30);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        tabla.getTableHeader().setEnabled(false);
        elQueOrdena = new TableRowSorter<TableModel>(tabla.getModel());
        tabla.setRowSorter(elQueOrdena);
        if(columnaOrdenar == 11){
            elQueOrdena.toggleSortOrder(columnaOrdenar);
        }
        elQueOrdena.toggleSortOrder(columnaOrdenar);
        JScrollPane tablaPanel = new JScrollPane();
        tablaPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tablaPanel.setViewportView(tabla);
        tablaPanel.setBounds(20,140, 960,420);
        JButton botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        botonSalir.setBounds(830, 30, 150, 30);
        getContentPane().add(tablaPanel);
        getContentPane().add(botonSalir);
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
                        return Long.class;
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
                    Number numero = (Number)tabla.getValueAt(fila, 0);
                    controlBotones(tipoTabla + "B", numero.longValue());
                }
            }
        });
        columnaOrdenar = 11;
        ordenar.addItem("Copias (Mayor a menor)");
        ordenar.addItem("Disponibles (Mayor a menor)");
        ordenar.addItem("Tipo (Ascendente)");
        ordenar.addItem("Tipo (Descendente)");
        ordenar.setActionCommand("CambioOrdenMostrarBibliografía");
        ordenar.addActionListener(this);
        ordenar.setBounds(100, 30, 200, 30);
        JLabel texto = new JLabel("Ordenar por:");
        texto.setBounds(20, 30, 200,30);
        textos = new JTextField[3];
        textos[0] = new JTextField();
        textos[1] = new JTextField();
        textos[2] = new JTextField();
        placeHolders = new TextPrompt[3];
        placeHolders[0] = new TextPrompt("Título", textos[0]);
        placeHolders[0].changeAlpha(0.75f);
        placeHolders[1] = new TextPrompt("Autor", textos[1]);
        placeHolders[1].changeAlpha(0.75f);
        placeHolders[2] = new TextPrompt("Palabras clave", textos[2]);
        placeHolders[2].changeAlpha(0.75f);
        textos[0].setBounds(340, 30, 150, 30);
        textos[1].setBounds(510, 30, 150, 30);
        textos[2].setBounds(340, 80 ,320, 30);
        JButton boton = new JButton("Buscar");
        boton.addActionListener(this);
        boton.setActionCommand("BuscarB");
        boton.setBounds(680, 30, 100, 30);
        getContentPane().add(ordenar);
        getContentPane().add(texto);
        getContentPane().add(textos[0]);
        getContentPane().add(textos[1]);
        getContentPane().add(textos[2]);
        getContentPane().add(boton);
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
                        return Long.class;
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
        columnaOrdenar = 11;
        ordenar.addItem("Copias (Mayor a menor)");
        ordenar.addItem("Disponibles (Mayor a menor)");
        ordenar.addItem("Tipo (Ascendente)");
        ordenar.addItem("Tipo (Descendente)");
        ordenar.setActionCommand("CambioOrdenMostrarBibliografía");
        ordenar.addActionListener(this);
        ordenar.setBounds(100, 30, 200, 30);
        JLabel texto = new JLabel("Ordenar por:");
        texto.setBounds(20, 30, 200,30);
        getContentPane().add(ordenar);
        getContentPane().add(texto);
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
        ordenar.addItem("ID(Ascendente)");
        ordenar.addItem("ID(Descendente)");
        ordenar.setActionCommand("CambioOrdenMostrarUsuario");
        ordenar.addActionListener(this);
        ordenar.setBounds(100, 30, 300, 30);
        JLabel texto = new JLabel("Ordenar por:");
        texto.setBounds(20, 30, 200,30);
        getContentPane().add(ordenar);
        getContentPane().add(texto);
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
                    controlBotones(tipoTabla + "U", (Long)tabla.getValueAt(fila, 0));
                }
            }
        });
        columnaOrdenar = 0;
        textos = new JTextField[1];
        textos[0] = new JTextField("");
        textos[0].setBounds(20, 30, 200, 30);
        placeHolders = new TextPrompt[1];
        placeHolders[0] = new TextPrompt("ID (DPI)", textos[0]);
        placeHolders[0].changeAlpha(0.75f);
        botones = new JButton[1];
        botones[0] = new JButton("Buscar");
        botones[0].setActionCommand("BuscarU");
        botones[0].addActionListener(this);
        botones[0].setBounds(240,30,100,30);
        getContentPane().add(textos[0]);
        getContentPane().add(botones[0]);
    }
    
    public void llenarTabla(Prestamo prestamo[], String tipoTabla){
    String columnas[] = {"ID","Título", "Autor", "Fecha préstamo", "Hora préstamo", "Fecha límite", "Nombre usuario", "Apellido usuario",tipoTabla};
        Object datosTabla[][] = new Object[prestamo.length][columnas.length];
        JButton botones[] = new JButton[prestamo.length];
        for(int i = 0; i < prestamo.length; i++){
            datosTabla[i][0] = prestamo[i].getID();
            datosTabla[i][1] = prestamo[i].getTitulo();
            datosTabla[i][2] = prestamo[i].getAutor();
            datosTabla[i][3] = prestamo[i].getFechaPrestamo();
            datosTabla[i][4] = prestamo[i].getHoraPrestamo();
            datosTabla[i][5] = prestamo[i].getFechaLimite();
            datosTabla[i][6] = prestamo[i].getNombre();
            datosTabla[i][7] = prestamo[i].getApellido();
            botones[i] = new JButton(tipoTabla);
            datosTabla[i][8] = botones[i];
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(datosTabla, columnas){
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 0:
                        return Long.class;
                    case 8:
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
                    Number numero = (Number)tabla.getValueAt(fila, 0);
                    controlBotones(tipoTabla + "P", numero.longValue());
                }
            }
        });
        columnaOrdenar = 0;
    }
    
    private void ordenarMostrarUsuario(){
        if(ordenar.getSelectedIndex() != itemSeleccionado){
            tabla.getRowSorter().toggleSortOrder(columnaOrdenar);
            itemSeleccionado = ordenar.getSelectedIndex();
        }
    }
    
    private void ordenarMostrarBiblio(){
        if(ordenar.getSelectedIndex() != itemSeleccionado){
            switch(ordenar.getSelectedIndex()){
                case 0:
                    tabla.getRowSorter().toggleSortOrder(11);
                    tabla.getRowSorter().toggleSortOrder(11);
                    break;
                case 1:
                    tabla.getRowSorter().toggleSortOrder(12);
                    tabla.getRowSorter().toggleSortOrder(12);
                    break;
                case 2:
                    tabla.getRowSorter().toggleSortOrder(0);
                    break;
                case 3:
                    if(itemSeleccionado != 2){
                        tabla.getRowSorter().toggleSortOrder(0);
                    }
                    tabla.getRowSorter().toggleSortOrder(0);
                    break;
            }
            itemSeleccionado = ordenar.getSelectedIndex();
        }
    }
    
    private void controlBotones(String tipoTabla, Long ID){
        switch(tipoTabla){
            case "EliminarU":
                if(JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar al usuario?", "Eliminar usuario", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                    admin.eliminarUsuario(String.valueOf(ID));
                    mostrarTabla ventana = new mostrarTabla(VentanaAnterior, "Eliminar", "Usuarios");
                    ventana.setVisible(true);
                    super.dispose();
                }
                break;
            case "ModificarU":
                if(JOptionPane.showConfirmDialog(this, "¿Está seguro que desea modificar los datos del usuario?", "Modificar usuario", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                    admin.modificarUsuario(String.valueOf(ID), VentanaAnterior);
                    super.dispose();
                }
                break;
            case "EliminarB":
                 if(JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar la bibliografía?", "Eliminar bibliografía", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                    admin.eliminarBibliografia(String.valueOf(ID));
                    mostrarTabla ventana = new mostrarTabla(VentanaAnterior, "Eliminar", "Bibliografías");
                    ventana.setVisible(true);
                    super.dispose();
                }
                break;
            case "ModificarB":
                if(JOptionPane.showConfirmDialog(this, "¿Está seguro que desea modificar los datos de la bibliografía?", "Modificar bibliografía", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                    admin.modificarBibliografia(String.valueOf(ID), VentanaAnterior);
                    super.dispose();
                }
                break;
            case "AgregarB":
                if(JOptionPane.showConfirmDialog(this, "¿Está seguro de agregar esta bibliografía a sus favortitos?", "Agregar bibliografía", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                    Cliente cliente = (Cliente)Biblioteca.usuarioConectado;
                    cliente.agregarFavorito(String.valueOf(ID));
                    JOptionPane.showMessageDialog(this, "Bibliografía agregada con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "Eliminar(favoritos)B":
                if(JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar la bibliografía de favoritos?", "Eliminar bibliografía", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                    Cliente cliente = (Cliente)Biblioteca.usuarioConectado;
                    cliente.eliminarFavorito(String.valueOf(ID));
                    mostrarTabla ventana = new mostrarTabla(VentanaAnterior, "Eliminar(favoritos)", "Bibliografías");
                    ventana.setVisible(true);
                    super.dispose();
                }
                break;
            case "PrestarB":
                if(JOptionPane.showConfirmDialog(this, "¿Está seguro que desea prestar esta bibliografía?", "Prestar bibliografía", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                    int index = Usuario.buscarObjeto(Biblioteca.bibliografiasActuales, String.valueOf(ID));
                    Bibliografia bibliografia = Biblioteca.bibliografiasActuales[index];
                    if(bibliografia.getDisponibles() > 0){
                        Prestamo prestamo = new Prestamo(bibliografia, Biblioteca.usuarioConectado.getID());
                        bibliografia.setDisponibles(bibliografia.getDisponibles() - 1);
                        Cliente cliente = (Cliente)Biblioteca.usuarioConectado;
                        cliente.crearPrestamo(prestamo);
                        mostrarTabla ventana = new mostrarTabla(VentanaAnterior, "Prestar", "Bibliografías");
                        ventana.setVisible(true);
                        super.dispose();
                    }else{
                        GregorianCalendar fecha = null;
                        for(Prestamo prestamo : Biblioteca.prestamos){
                            if(prestamo.getBibliografia() == bibliografia){
                                if(fecha != null){
                                    if(fecha.after(prestamo.getFechaLimiteCalendar())){
                                        fecha = prestamo.getFechaLimiteCalendar();
                                    }
                                }else{
                                    fecha = prestamo.getFechaLimiteCalendar();
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(this, "La devolucón más próxima es el: " + fecha.getTime().getDate() + "/" + (fecha.getTime().getMonth() + 1) + "/" + (fecha.getTime().getYear() + 1900), "Fecha próxima", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                break;
        }
    }
    
    @Override
    public void dispose(){
        cerrar(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "CambioOrdenMostrarUsuario":
                ordenarMostrarUsuario();
                break;
            case "CambioOrdenMostrarBibliografía":
                ordenarMostrarBiblio();
                break;
            case "BuscarU":
                elQueOrdena.setRowFilter(RowFilter.regexFilter(textos[0].getText(), 0));
                break;
            case "BuscarB":
                /* elQueOrdena.setRowFilter(RowFilter.regexFilter(textos[0].getText(), 3));
                elQueOrdena.setRowFilter(RowFilter.regexFilter(textos[1].getText(), 2));*/
                RowFilter filtro = new RowFilter() {
                    @Override
                    public boolean include(RowFilter.Entry entry) {
                        String palabrasClaveBuscar[] = textos[2].getText().split(","), palabrasClave[] = entry.getStringValue(5).split(",") , Titulo = entry.getStringValue(3),  Autor = entry.getStringValue(2);
                        boolean palabraClaveExistente = false;
                        if(!textos[2].getText().trim().equals("")){
                            for(int i = 0; i < palabrasClaveBuscar.length; i++){
                                for(int j = 0; j < palabrasClave.length; j++){
                                    if(palabrasClave[j].trim().toUpperCase().contains(palabrasClaveBuscar[i].trim().toUpperCase())){
                                        palabraClaveExistente = true;
                                    }
                                }
                            }
                        }else{
                            palabraClaveExistente = true;
                        }
                        return (Titulo.toUpperCase().contains(textos[0].getText().toUpperCase()) &&  Autor.toUpperCase().contains(textos[1].getText().toUpperCase()) && palabraClaveExistente);
                    }
                };
                elQueOrdena.setRowFilter(filtro);
                break;
            case "Salir":
                dispose();
                break;
        }
    }
    
}
