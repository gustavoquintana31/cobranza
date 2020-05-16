package cast.testapp.receipt.entities;

import java.io.Serializable;
import java.sql.Date;

public class ReceiptValue implements Serializable {

	private static final long serialVersionUID = 1L;
	private String paymentType;
	private String documentNumber;
	private Date documentDepositDate;
	private Date documentEmitionDate;
	private Double balance;
	private Double totalAmount;
	private Integer bankId;
	private Integer receiptId;
	private Integer invoiceId;
	private Integer id;
	private String invoiceNumber;
	private Date paymentDate;

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getDocumentDepositDate() {
		return documentDepositDate;
	}

	public void setDocumentDepositDate(Date documentDepositDate) {
		this.documentDepositDate = documentDepositDate;
	}

	public Date getDocumentEmitionDate() {
		return documentEmitionDate;
	}

	public void setDocumentEmitionDate(Date documentEmitionDate) {
		this.documentEmitionDate = documentEmitionDate;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}