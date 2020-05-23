/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cast.testapp.receipt.controller;

import cast.testapp.catastro.controller.ClienteController;
import cast.testapp.catastro.controller.ClienteControllerImpl;
import cast.testapp.catastro.entities.Cliente;
import cast.testapp.invoice.controller.InvoiceController;
import cast.testapp.invoice.entities.Invoice;
import cast.testapp.receipt.boundary.ReceiptManager;
import cast.testapp.receipt.boundary.impl.ReceiptManagerImpl;
import cast.testapp.catastro.entities.DocumentType;
import cast.testapp.receipt.entities.ErrorMessage;
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
    InvoiceController invoiceCtrl;
    
    public ReceiptController(){
        clienteCtrl = new ClienteControllerImpl();
        receiptMgr = new ReceiptManagerImpl();
        invoiceCtrl = new InvoiceController();
    }
    public ReceiptController(ClienteController clienteCtrl,ReceiptManager receiptMgr, InvoiceController invoiceCtrl){
        this.clienteCtrl = clienteCtrl;
        this.receiptMgr = receiptMgr;
        this.invoiceCtrl = invoiceCtrl;
    }
    /***
     * listPendingInvoicesByClient
     * BR1	Listar Facturas Pendientes por cliente, fecha de vencimiento
     * @param tipoDoc
     * @param numeroDoc
     * @param fecha
     * @return 
     */
    public List<Invoice> listPendingInvoicesByClient(DocumentType docType, String numeroDoc, Date fecha){
        
        //Consulta al cliente
        Cliente cliente = clienteCtrl.consultarCliente(docType, numeroDoc);
        
        if (cliente == null){
            throw new IllegalArgumentException(ErrorMessage.CLIENT_NOT_FOUND.toString());
        }
        else{
            System.out.println("Cliente encontrado > cliente:" + cliente);
        }
        
        //Controler de Facturacion y obtener las facturas pendientes por x fecha   
        
        //@TODO
        return Collections.EMPTY_LIST;
    }
    public static void main(String[] args) {
       ReceiptController ctrl = new ReceiptController();
       ctrl.listPendingInvoicesByClient(DocumentType.RUC,"1231231", new Date());
        
    }
//        
    /*    
    BR2	Cobrar facturas pendientes
    BR2-1	Utilizar un numero de recibo unico		
    BR3	Anular Cobranza		
    BR4	Listar Cobranzas anuladas		
     */
    
    
    
}
