package cast.testapp.receipt.entities;

import java.io.Serializable;
import java.sql.Date;

public class ReceiptInvoice implements Serializable {

	private static final long serialVersionUID = 1L;
	private Double total;
	private Double balance;
	private Integer invoiceId;
	private Integer receiptId;
	private Integer id;

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}