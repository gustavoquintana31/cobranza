package cast.testapp.receipt.entities;

import java.io.Serializable;
import java.sql.Date;

public class Bank implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codE;
	private String description;
	private String telephone;
	private Integer id;

	public String getCodE() {
		return codE;
	}

	public void setCodE(String codE) {
		this.codE = codE;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}