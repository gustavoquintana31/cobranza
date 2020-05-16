package cast.testapp.receipt.boundary.impl;

import cast.testapp.receipt.boundary.ReceiptInvoiceManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import util.ConnectionManager;
import java.sql.SQLException;
import cast.testapp.receipt.entities.ReceiptInvoice;

public class ReceiptInvoiceManagerImpl implements ReceiptInvoiceManager {

	private static final long serialVersionUID = 1L;

	public List<ReceiptInvoice> getAll() {
		List<ReceiptInvoice> listReceiptInvoice = new ArrayList();
		try (PreparedStatement s1 = ConnectionManager.getConnection()
				.prepareStatement(getStatement())) {
			s1.setMaxRows(100);
			try (ResultSet rs = s1.executeQuery()) {
				while (rs.next()) {
					listReceiptInvoice.add(getFromRsReceiptInvoice(rs));
				}
			}
			return listReceiptInvoice;
		} catch (Exception ex) {
			ex.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	public String getStatement() {
		String statement = "SELECT id, invoice_id, receipt_id, balance, total FROM receipt_invoice";
		return statement;
	}

	public ReceiptInvoice getFromRsReceiptInvoice(ResultSet rs) {
		try {
			ReceiptInvoice data = new ReceiptInvoice();
			data.setId(rs.getInt(1));
			data.setInvoiceId(rs.getInt(2));
			data.setReceiptId(rs.getInt(3));
			data.setBalance(rs.getDouble(4));
			data.setTotal(rs.getDouble(5));
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Boolean add(ReceiptInvoice entity) {
		String statement = "INSERT INTO receipt_invoice (id, invoice_id, receipt_id, balance, total ) VALUES ( ?,?,?,?,?)";
		try (PreparedStatement s1 = ConnectionManager.getConnection()
				.prepareStatement(statement)) {
			s1.setInt(1, entity.getId());
			s1.setInt(2, entity.getInvoiceId());
			s1.setInt(3, entity.getReceiptId());
			s1.setDouble(4, entity.getBalance());
			s1.setDouble(5, entity.getTotal());
			Integer rs = s1.executeUpdate();
			if (rs > 0) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}

	public static void main(String[] args) {
		List<ReceiptInvoice> listReceiptInvoice = new ReceiptInvoiceManagerImpl()
				.getAll();
		listReceiptInvoice
				.forEach(i -> System.out.println("d: " + i.toString()));
	}

    @Override
    public ReceiptInvoice update(ReceiptInvoice entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReceiptInvoice getById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<ReceiptInvoice> getAll(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}