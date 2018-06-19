package Vista;

import Dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame {

    public JLabel lblCarne, lblNombres, lblApellidos, lblEdad, lblUniversidad, lblEstado;
    public JTextField carne, nombre, apellidos, edad;
    public JComboBox universidad;
    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JComboBox marca;

    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Inscripciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCarne);
        container.add(lblNombres);
        container.add(lblApellidos);
        container.add(lblEdad);
        container.add(lblUniversidad);
        container.add(lblEstado);
        container.add(carne);
        container.add(nombre);
        container.add(apellidos);
        container.add(edad);
        container.add(universidad);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();
    }

    public final void agregarLabels() {
        lblCarne = new JLabel("Carnet:");
        lblNombres = new JLabel("Nombre:");
        lblApellidos = new JLabel("Apellidos");
        lblEdad = new JLabel("Edad:");
        lblUniversidad = new JLabel("Universidad");
        lblEstado = new JLabel("Estado");

        lblCarne.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombres.setBounds(10, 50, ANCHOC, ALTOC);
        lblApellidos.setBounds(250, 50, ANCHOC, ALTOC);
        lblEdad.setBounds(10, 100, ANCHOC, ALTOC);
        lblUniversidad.setBounds(10, 150, ANCHOC, ALTOC);
        lblEstado.setBounds(10, 200, ANCHOC, ALTOC);
    }

    public final void formulario() {
        carne = new JTextField();
        nombre = new JTextField();
        apellidos = new JTextField();
        edad = new JTextField();

        universidad = new JComboBox();

        si = new JRadioButton("si", true);
        no = new JRadioButton("no");//porbar aca con false

        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();
        universidad.addItem("UCA");
        universidad.addItem("UDB");
        universidad.addItem("UFG");
        universidad.addItem("UGB");

        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);

        carne.setBounds(80, 10, ANCHOC, ALTOC);
        nombre.setBounds(80, 50, ANCHOC, ALTOC);
        apellidos.setBounds(320, 50, ANCHOC, ALTOC);
        edad.setBounds(80, 100, ANCHOC, ALTOC);
        universidad.setBounds(120, 150, ANCHOC, ALTOC);
        si.setBounds(140, 200, 50, ALTOC);
        no.setBounds(210, 200, 50, ALTOC);
        buscar.setBounds(250, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 230, ANCHOC, ALTOC);
        actualizar.setBounds(150, 230, ANCHOC, ALTOC);
        eliminar.setBounds(290, 230, ANCHOC, ALTOC);
        limpiar.setBounds(430, 230, ANCHOC, ALTOC);
        resultados = new JTable();

        resultados = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        table.setBounds(10, 290, 500, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Carnet");
        tm.addColumn("Nombres");
        tm.addColumn("Apellidos");
        tm.addColumn("Universidad");
        tm.addColumn("Estado");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getCarne(), fi.getNombres(), fi.getApellidos(), fi.getUniversidad(), fi.getEstado()});
        }

        resultados.setModel(tm);
    }

    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(carne.getText(), nombre.getText(), apellidos.getText(), Integer.parseInt(edad.getText()), universidad.getSelectedItem().toString(), true);
                System.out.println(no.isSelected());
                if (no.isSelected()) {
                    f.setEstado(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Problema al querer crear el filtro");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(carne.getText(), nombre.getText(), apellidos.getText(), Integer.parseInt(edad.getText()), universidad.getSelectedItem().toString(), true);

                if (no.isSelected()) {
                    f.setEstado(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro Modificado");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Problemas al querer modificar el filtro");

                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(carne.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro Eliminado");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Problema al querer eliminar el filtro");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(carne.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "Filtro no encontrado");
                } else {
                    carne.setText(f.getCarne());
                    nombre.setText(f.getNombres());
                    apellidos.setText(f.getApellidos());
                    edad.setText(Integer.toString(f.getEdad()));
                    universidad.setSelectedItem(f.getUniversidad());

                    if (f.getEstado()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

//        resultados.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent evnt) {
//                if (evnt.getClickCount() == 1) {
//                    carne.setText(resultados.getValueAt(resultados.getSelectedRow(), 0).toString());
//                    
//                    marca.setSelectedItem(resultados.getValueAt(resultados.getSelectedRow(), 1).toString());
//                    stock.setText(resultados.getValueAt(resultados.getSelectedRow(), 2).toString());
//                    if (resultados.getValueAt(resultados.getSelectedRow(), 3).toString() == "false") {
//                        no.setSelected(false);
//                    } else {
//                        si.setSelected(true);
//                    }
//                }
//            }
//        });
    }

    public void limpiarCampos() {        
        carne.setText("");
        nombre.setText("");
        apellidos.setText("");
        edad.setText("");
        universidad.setSelectedItem("UCA");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
}
