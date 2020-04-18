package py.com.test.cobranza.boundary;

import py.com.test.cobranza.ec.Receipt;
import py.com.test.cobranza.manager.ConnectionManager;

import java.sql.*;
import java.util.Properties;

public class CobranzaManager {

    public void agregar(Receipt receipt){

        Connection connection = null;
        PreparedStatement psInsert = null;

        try {
            connection = ConnectionManager.getConnection();
            psInsert = connection.prepareStatement("INSERT INTO receipt (id, number) VALUES (?, ?)");

            psInsert.setLong(1, receipt.getId());
            psInsert.setString(2, receipt.getNumber());
            psInsert.executeUpdate();
            System.out.println("Receipt Number "+receipt.getNumber()+" added");

        } catch (SQLException e) {
            System.out.println("Receipt add failure.");
            e.printStackTrace();
        }finally {
            if(psInsert != null){
                try {
                    psInsert.close();
                }catch (SQLException sqlEx){}
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException sqlEx){}
            }
        }
    }

    public void eliminar(Receipt receipt){
        Connection connection = null;
        PreparedStatement psDelete = null;
        try {
            connection = ConnectionManager.getConnection();
            psDelete = connection.prepareStatement("DELETE FROM receipt WHERE id = ?");

            psDelete.setLong(1, receipt.getId());
            psDelete.executeUpdate();
            System.out.println("Receipt Number "+receipt.getNumber()+" deleted");

        } catch (SQLException e) {
            System.out.println("Receipt delete failure.");
            e.printStackTrace();
        }finally {
            if(psDelete != null){
                try {
                    psDelete.close();
                }catch (SQLException sqlEx){}
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException sqlEx){}
            }
        }
    }


    public void actualizar(Receipt receipt){
        Connection connection = null;
        PreparedStatement psDelete = null;
        try {
            connection = ConnectionManager.getConnection();
            psDelete = connection.prepareStatement("UPDATE receipt SET number = ? WHERE id = ?");

            psDelete.setString(1, receipt.getNumber());
            psDelete.setLong(2, receipt.getId());
            psDelete.executeUpdate();
            System.out.println("Receipt Number "+receipt.getNumber()+" updated");

        } catch (SQLException e) {
            System.out.println("Receipt update failure.");
            e.printStackTrace();
        }finally {
            if(psDelete != null){
                try {
                    psDelete.close();
                }catch (SQLException sqlEx){}
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException sqlEx){}
            }
        }
    }

}
