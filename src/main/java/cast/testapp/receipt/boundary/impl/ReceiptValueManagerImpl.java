package cast.testapp.receipt.boundary.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import util.ConnectionManager;
import java.sql.SQLException;
import cast.testapp.receipt.entities.ReceiptValue;

public class ReceiptValueManagerImpl {

	private static final long serialVersionUID = 1L;

	public List<ReceiptValue> getReceiptValue() {
		List<ReceiptValue> listReceiptValue = new ArrayList();
		try (PreparedStatement s1 = ConnectionManager.getConnection()
				.prepareStatement(getStatement())) {
			s1.setMaxRows(100);
			try (ResultSet rs = s1.executeQuery()) {
				while (rs.next()) {
					listReceiptValue.add(getFromRsReceiptValue(rs));
				}
			}
			return listReceiptValue;
		} catch (Exception ex) {
			ex.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	public String getStatement() {
		String statement = "SELECT id, receipt_id, payment_type, payment_date, document_number, total_amount, bank_id, document_emition_date, document_deposit_date, balance, invoice_id, invoice_number FROM receipt_value";
		return statement;
	}

	public ReceiptValue getFromRsReceiptValue(ResultSet rs) {
		try {
			ReceiptValue data = new ReceiptValue();
			data.setId(rs.getInt(1));
			data.setReceiptId(rs.getInt(2));
			data.setPaymentType(rs.getString(3));
			data.setPaymentDate(rs.getDate(4));
			data.setDocumentNumber(rs.getString(5));
			data.setTotalAmount(rs.getDouble(6));
			data.setBankId(rs.getInt(7));
			data.setDocumentEmitionDate(rs.getDate(8));
			data.setDocumentDepositDate(rs.getDate(9));
			data.setBalance(rs.getDouble(10));
			data.setInvoiceId(rs.getInt(11));
			data.setInvoiceNumber(rs.getString(12));
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Boolean add(ReceiptValue entity) {
		String statement = "INSERT INTO receipt_value (id, receipt_id, payment_type, payment_date, document_number, total_amount, bank_id, document_emition_date, document_deposit_date, balance, invoice_id, invoice_number ) VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?)";
		try (PreparedStatement s1 = ConnectionManager.getConnection()
				.prepareStatement(statement)) {
			s1.setInt(1, entity.getId());
			s1.setInt(2, entity.getReceiptId());
			s1.setString(3, entity.getPaymentType());
			s1.setDate(4, entity.getPaymentDate());
			s1.setString(5, entity.getDocumentNumber());
			s1.setDouble(6, entity.getTotalAmount());
			s1.setInt(7, entity.getBankId());
			s1.setDate(8, entity.getDocumentEmitionDate());
			s1.setDate(9, entity.getDocumentDepositDate());
			s1.setDouble(10, entity.getBalance());
			s1.setInt(11, entity.getInvoiceId());
			s1.setString(12, entity.getInvoiceNumber());
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
		List<ReceiptValue> listReceiptValue = new ReceiptValueManagerImpl()
				.getReceiptValue();
		listReceiptValue.forEach(i -> System.out.println("d: " + i.toString()));
	}
}