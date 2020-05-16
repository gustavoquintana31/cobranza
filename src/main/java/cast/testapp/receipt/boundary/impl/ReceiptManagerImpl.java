package cast.testapp.receipt.boundary.impl;

import cast.testapp.receipt.boundary.ReceiptManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import util.ConnectionManager;
import java.sql.SQLException;
import cast.testapp.receipt.entities.Receipt;

public class ReceiptManagerImpl implements ReceiptManager {

	private static final long serialVersionUID = 1L;

	public List<Receipt> getAll() {
		List<Receipt> listReceipt = new ArrayList();
		try (PreparedStatement s1 = ConnectionManager.getConnection()
				.prepareStatement(getStatement())) {
			s1.setMaxRows(100);
			try (ResultSet rs = s1.executeQuery()) {
				while (rs.next()) {
					listReceipt.add(getFromRsReceipt(rs));
				}
			}
			return listReceipt;
		} catch (Exception ex) {
			ex.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	public String getStatement() {
		String statement = "SELECT id, creation_date, customer_code, customer_name, receipt_date, receipt_branch, receipt_number, receipt_printer, status, total_payed, total_to_pay, cancelled, cancelled_date, cancelled_reason, advance_amount, observation   FROM receipt";
		return statement;
	}

	public Receipt getFromRsReceipt(ResultSet rs) {
		try {
			Receipt data = new Receipt();
			data.setId(rs.getInt(1));
			data.setCreationDate(rs.getDate(2));
			data.setCustomerCode(rs.getString(3));
			data.setCustomerName(rs.getString(4));
			data.setReceiptDate(rs.getDate(5));
			data.setReceiptBranch(rs.getString(6));
			data.setReceiptNumber(rs.getString(7));
			data.setReceiptPrinter(rs.getString(8));
			data.setStatus(rs.getString(9));
			data.setTotalPayed(rs.getDouble(10));
			data.setTotalToPay(rs.getDouble(11));
			data.setCancelled(rs.getBoolean(12));
			data.setCancelledDate(rs.getDate(13));
			data.setCancelledReason(rs.getString(14));
			data.setAdvanceAmount(rs.getDouble(15));
			data.setObservation(rs.getString(16));
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Boolean add(Receipt entity) {
		String statement = "INSERT INTO receipt (id, creation_date, customer_code, customer_name, receipt_date, receipt_branch, receipt_number, receipt_printer, status, total_payed, total_to_pay, cancelled, cancelled_date, cancelled_reason, advance_amount, observation ) VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement s1 = ConnectionManager.getConnection()
				.prepareStatement(statement)) {
			s1.setInt(1, entity.getId());
			s1.setDate(2, entity.getCreationDate());
			s1.setString(3, entity.getCustomerCode());
			s1.setString(4, entity.getCustomerName());
			s1.setDate(5, entity.getReceiptDate());
			s1.setString(6, entity.getReceiptBranch());
			s1.setString(7, entity.getReceiptNumber());
			s1.setString(8, entity.getReceiptPrinter());
			s1.setString(9, entity.getStatus());
			s1.setDouble(10, entity.getTotalPayed());
			s1.setDouble(11, entity.getTotalToPay());
			s1.setBoolean(12, entity.getCancelled());
			s1.setDate(13, entity.getCancelledDate());
			s1.setString(14, entity.getCancelledReason());
			s1.setDouble(15, entity.getAdvanceAmount());
			s1.setString(16, entity.getObservation());
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
		List<Receipt> listReceipt = new ReceiptManagerImpl().getAll();
		listReceipt.forEach(i -> System.out.println("d: " + i.toString()));
	}

    @Override
    public Receipt update(Receipt entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Receipt getById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Receipt> getAll(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}