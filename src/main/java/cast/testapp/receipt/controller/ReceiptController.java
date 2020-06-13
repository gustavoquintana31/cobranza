/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cast.testapp.receipt.controller;


import cast.testapp.catastro.clientes.Cliente;
import cast.testapp.catastro.clientes.ClienteController;
import cast.testapp.catastro.clientes.ClienteControllerImpl;
import cast.testapp.catastro.clientes.DocumentType;
import cast.testapp.invoice.controller.InvoiceController;
import cast.testapp.invoice.entities.Invoice;
import cast.testapp.receipt.boundary.ReceiptManager;
import cast.testapp.receipt.boundary.impl.ReceiptManagerImpl;
import cast.testapp.receipt.entities.ErrorMessage;
import cast.testapp.receipt.entities.Receipt;

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


    //
    /*
        BR1	Listar Facturas Pendientes por cliente, fecha de vencimiento
        BR2	Cobrar facturas pendientes
        BR2-1	Utilizar un numero de recibo unico
        BR3	Anular Cobranza
        BR4	Listar Cobranzas anuladas
     */


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
        Cliente cliente = clienteCtrl.consultaUno(docType, numeroDoc);
        
        if (cliente == null){
            throw new IllegalArgumentException(ErrorMessage.CLIENT_NOT_FOUND.toString());
        }else{
            System.out.println("Cliente encontrado > cliente:" + cliente);
        }
        
        if (fecha.before(new Date())){
            //Controler de Facturacion y obtener las facturas pendientes por x fecha
            invoiceCtrl.listPendingInvoicesByClient(cliente.id, fecha);
        }
        //@TODO
        return Collections.EMPTY_LIST;
    }



    /***
     * receiptInvoicePendig
     * BR2	Cobrar factura pendientes
     * @param cliente
     * @param invoiceList
     * @return
     */
    public Receipt receiptInvoicePending(Cliente client, List<Invoice> invoiceList){

        if(client == null){
            throw new IllegalArgumentException(ErrorMessage.CLIENT_IS_EMPTY.toString());
        }
        if(invoiceList.size() == 0){
            throw new IllegalArgumentException(ErrorMessage.INVOICE_LIST_IS_EMPTY.toString());
        }

        String lastReceiptNumber = receiptMgr.getLastReceiptNumber();

        System.out.println("RECEIPT NUMBER: "+lastReceiptNumber);
        if(lastReceiptNumber == null){
            throw new IllegalArgumentException(ErrorMessage.RECEIPT_NUMBER_NOT_FOUND.toString());
        }

        String receiptNumber = getUniqueReceiptNumber(lastReceiptNumber);

        Receipt receipt = new Receipt();
        receipt.setCustomerCode(client.numeroDoc);
        receipt.setCustomerName(client.razonSocial);
        receipt.setCreationDate(new java.sql.Date(System.currentTimeMillis()));

        String[] receiptNumberArray = receiptNumber.split("-");

        receipt.setReceiptBranch(receiptNumberArray[0]);
        receipt.setReceiptPrinter(receiptNumberArray[1]);
        receipt.setReceiptNumber(receiptNumberArray[2]);

        boolean saved = receiptMgr.add(receipt);

        System.out.println("GUARDADO: "+saved);

        if(!saved){
            throw new IllegalArgumentException(ErrorMessage.RECEIPT_NOT_CREATE.toString());
        }
        return receipt;
    }

    public String getUniqueReceiptNumber(String lastReceiptNumber){

        String[] lastReceiptNumberArray = lastReceiptNumber.split("-");

        String lastNumber = lastReceiptNumberArray[2];

        int number = Integer.valueOf(lastNumber)+1;
        String numberStr = String.valueOf(number);
        lastNumber = numberStr;
        for (int i = numberStr.length(); i < 7; i++){
            lastNumber = "0"+lastNumber;
        }
        return lastReceiptNumberArray[0]+"-"+lastReceiptNumberArray[1]+"-"+lastNumber;
    }


    public static void main(String[] args) {
       ReceiptController ctrl = new ReceiptController();
       ctrl.listPendingInvoicesByClient(DocumentType.RUC,"1231231", new Date());
        
    }

    public Boolean verifyIfReceiptExist(Integer id){
        Receipt receipt = receiptMgr.getById(id);
        return receipt != null;
    }

    public Boolean cancelReceipt(Integer id){
        if(verifyIfReceiptExist(id)){
            return receiptMgr.cancelReceipt(id);
        }else{
            throw new IllegalArgumentException(ErrorMessage.RECEIPT_NOT_FOUND.toString());
        }
    }


}
