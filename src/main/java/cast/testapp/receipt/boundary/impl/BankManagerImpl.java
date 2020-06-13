package cast.testapp.receipt.boundary.impl;

import cast.testapp.receipt.boundary.BankManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import util.ConnectionManager;
import java.sql.SQLException;
import cast.testapp.receipt.entities.Bank;
import util.GenericDao;

public class BankManagerImpl  implements BankManager{

	private static final long serialVersionUID = 1L;

	public List<Bank> getAll() {
		List<Bank> listBank = new ArrayList();
		try (PreparedStatement s1 = ConnectionManager.getConnection()
				.prepareStatement(getStatement())) {
			s1.setMaxRows(100);
			try (ResultSet rs = s1.executeQuery()) {
				while (rs.next()) {
					listBank.add(getFromRsBank(rs));
				}
			}
			return listBank;
		} catch (Exception ex) {
			ex.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	public String getStatement() {
		String statement = "SELECT id, code, description, telephone  FROM bank";
		return statement;
	}

	public Bank getFromRsBank(ResultSet rs) {
		try {
			Bank data = new Bank();
			data.setId(rs.getInt(1));
			data.setCodE(rs.getString(2));
			data.setDescription(rs.getString(3));
			data.setTelephone(rs.getString(4));
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Boolean add(Bank entity) {
		String statement = "INSERT INTO bank (id, code, description, telephone ) VALUES ( ?,?,?,?)";
		try (PreparedStatement s1 = ConnectionManager.getConnection()
				.prepareStatement(statement)) {
			s1.setInt(1, entity.getId());
			s1.setString(2, entity.getCodE());
			s1.setString(3, entity.getDescription());
			s1.setString(4, entity.getTelephone());
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
		List<Bank> listBank = new BankManagerImpl().getAll();
		listBank.forEach(i -> System.out.println("d: " + i.toString()));
	}

    

    @Override
    public Bank update(Bank entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bank getById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Bank> getAll(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}