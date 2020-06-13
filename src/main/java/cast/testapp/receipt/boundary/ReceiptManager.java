/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cast.testapp.receipt.boundary;

import cast.testapp.receipt.entities.Receipt;
import cast.testapp.receipt.entities.ReceiptInvoice;
import util.GenericDao;

/**
 *
 * @author cbustamante
 */
public interface ReceiptManager extends GenericDao<Receipt, Integer> {

    String getLastReceiptNumber();

    
}
