package cast.testapp.receipt.entities;

import java.io.Serializable;
import java.sql.Date;

public class Receipt implements Serializable {

	private static final long serialVersionUID = 1L;
	private String receiptPrinter;
	private String observation;
	private Date creationDate;
	private Double totalPayed;
	private String cancelledReason;
	private String receiptBranch;
	private String receiptNumber;
	private Date cancelledDate;
	private Date receiptDate;
	private Double totalToPay;
	private Boolean cancelled;
	private Integer id;
	private String customerName;
	private String customerCode;
	private Double advanceAmount;
	private String status;

	public String getReceiptPrinter() {
		return receiptPrinter;
	}

	public void setReceiptPrinter(String receiptPrinter) {
		this.receiptPrinter = receiptPrinter;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Double getTotalPayed() {
		return totalPayed;
	}

	public void setTotalPayed(Double totalPayed) {
		this.totalPayed = totalPayed;
	}

	public String getCancelledReason() {
		return cancelledReason;
	}

	public void setCancelledReason(String cancelledReason) {
		this.cancelledReason = cancelledReason;
	}

	public String getReceiptBranch() {
		return receiptBranch;
	}

	public void setReceiptBranch(String receiptBranch) {
		this.receiptBranch = receiptBranch;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public Date getCancelledDate() {
		return cancelledDate;
	}

	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public Double getTotalToPay() {
		return totalToPay;
	}

	public void setTotalToPay(Double totalToPay) {
		this.totalToPay = totalToPay;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Double getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(Double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}