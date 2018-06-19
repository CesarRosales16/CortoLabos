package Dao;

import Conexion.Conexion;
import Interfaces.Metodos;
import Modelo.Filtro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LN710Q
 */
public class FiltroDao implements Metodos<Filtro> {

    private static final String SQL_INSERT = "INSERT INTO alumnos(carnet,nombres,apellidos,edad,universidad,estado) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE alumnos SET nombres = ?, apellidos=?, edad=?, universidad=?, estado=? WHERE carnet=?";
    private static final String SQL_DELETE = "DELETE FROM alumnos WHERE carnet=?";
    private static final String SQL_READ = "SELECT * FROM alumnos WHERE carnet=?";
    private static final String SQL_READALL = "SELECT*FROM alumnos";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Filtro g) {
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getCarne());
            ps.setString(2, g.getNombres());
            ps.setString(3, g.getApellidos());
            ps.setInt(4, g.getEdad());
            ps.setString(5, g.getUniversidad());
            //ps.setBoolean(6, g.getEstado());//este me lo invente yo ahorita
            ps.setBoolean(6, true);//este es el que tenia el ejercicio del codigo de la base de datos de labo 
//            ps.setString(1, g.getCodigo());
//            ps.setString(2,g.getMarca());
//            ps.setInt(3, g.getStock());
//            ps.setBoolean(4,true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = (PreparedStatement) con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getCarne());
            ps = (PreparedStatement) con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombres());
            ps.setString(2, c.getApellidos());
            ps.setInt(3, c.getEdad());
            ps.setString(4, c.getUniversidad());
            ps.setBoolean(5, c.getEstado());
            ps.setString(6, c.getCarne());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Filtro read(Object key) {
        PreparedStatement ps;
        Filtro f = null;
        ResultSet rs;
        try {
            ps = (PreparedStatement) con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());

            rs = ps.executeQuery();
            while (rs.next()) {
                f = new Filtro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getBoolean(7));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    @Override
    public ArrayList<Filtro> readAll() {
        ArrayList<Filtro> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while (rs.next()) {
                all.add(new Filtro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getBoolean(7)));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }

}
