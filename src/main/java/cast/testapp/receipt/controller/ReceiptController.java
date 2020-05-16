/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cast.testapp.receipt.controller;

import cast.testapp.catastro.controller.ClienteController;
import cast.testapp.catastro.controller.ClienteControllerImpl;
import cast.testapp.catastro.entities.Cliente;
import cast.testapp.invoice.entities.Invoice;
import cast.testapp.receipt.boundary.ReceiptManager;
import cast.testapp.receipt.boundary.impl.ReceiptManagerImpl;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cbustamante
 */
public class ReceiptController {
    
    ClienteController clienteCtrl;
    ReceiptManager receiptMgr;
    
    public ReceiptController(){
        clienteCtrl = new ClienteControllerImpl();
        receiptMgr = new ReceiptManagerImpl();
    }
    public ReceiptController(ClienteController clienteCtrl,ReceiptManager receiptMgr){
        this.clienteCtrl = clienteCtrl;
        this.receiptMgr = receiptMgr;
    }
    
    public List<Invoice> listPendingInvoicesByClient(String tipoDoc, String numeroDoc, Date fecha){
        
        //Consulta al cliente
        Cliente cliente = clienteCtrl.consultarCliente(tipoDoc, numeroDoc);
        
        
        
        if (cliente == null){
            System.out.println("No se encuentra el cliente");
        }
        else{
            System.out.println("Cliente encontrado > cliente:" + cliente);
        }
        return Collections.EMPTY_LIST;
    }
//        
    /*
    BR1	Listar Facturas Pendientes por cliente, fecha de vencimiento		
    BR2	Cobrar facturas pendientes		
    BR2-1	Utilizar un numero de recibo unico		
    BR3	Anular Cobranza		
    BR4	Listar Cobranzas anuladas		
     */
    
    
    
}
