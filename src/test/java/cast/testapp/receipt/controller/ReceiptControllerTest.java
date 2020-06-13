/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cast.testapp.receipt.controller;

import cast.testapp.catastro.clientes.ClienteController;
import cast.testapp.catastro.clientes.Cliente;
import cast.testapp.catastro.clientes.Cliente.TipoDoc;
import cast.testapp.invoice.controller.InvoiceController;
import cast.testapp.invoice.entities.Invoice;
import cast.testapp.receipt.boundary.ReceiptManager;
import cast.testapp.catastro.clientes.DocumentType;
import cast.testapp.receipt.entities.ErrorMessage;

import java.util.*;

import cast.testapp.receipt.entities.Receipt;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;    
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author cbustamante
 */
@RunWith(MockitoJUnitRunner.class)
public class ReceiptControllerTest {
    ReceiptController instance;
    ClienteController mockClientCtrl;
    ReceiptManager mockReceiptMgr;
    InvoiceController mockInvoiceCtrl;
    
    
    public ReceiptControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mockClientCtrl = mock(ClienteController.class);
        mockReceiptMgr = mock(ReceiptManager.class);        
        mockInvoiceCtrl = mock(InvoiceController.class);        
        instance = new ReceiptController(mockClientCtrl,mockReceiptMgr,mockInvoiceCtrl);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of listPendingInvoicesByClient method, of class ReceiptController.
     */
    @Test
    public void testListPendingInvoicesByClientNotClient() {
        //parametros
        String nroDoc="234";
        Date fecha  = new Date();
        when(mockClientCtrl.consultaUno(DocumentType.CI, nroDoc)).thenReturn(null);
        
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    List<Invoice> listInvoices = instance.listPendingInvoicesByClient(DocumentType.CI, nroDoc, fecha);                    
                });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ErrorMessage.CLIENT_NOT_FOUND.toString()));
        
        
//        assertTrue("No se encontro ninguna factura pendiente", listInvoices.isEmpty());
    }
    @Test
    public void testListPendingInvoicesByClientWithClient() {                
        //parametros
        String nroDoc="234";
        Date fecha  = new Date();
        //inicializacion
        Cliente cliente = getCliente(nroDoc);

        when(mockClientCtrl.consultaUno(DocumentType.CI, nroDoc)).thenReturn(cliente);
        List<Invoice> listInvoices = instance.listPendingInvoicesByClient(DocumentType.CI, nroDoc, fecha);
        assertTrue("No se encontro ninguna factura pendiente", listInvoices.isEmpty());
    }

    private Cliente getCliente(String nroDoc) {
        Cliente cliente = new Cliente();
        cliente.setTipoDoc(TipoDoc.CIP.toString());
        cliente.setNumeroDoc(nroDoc);
        return cliente;
    }


    @Test
    public void testListPendingInvoicesByDatesBeforeActualDate() {                
        //parametros
        String nroDoc="234";
        Date fecha  = new Date();
        Calendar calendario =  Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.set(Calendar.YEAR,2015);
        
        //Mock Clientes
        Cliente cliente = getCliente(nroDoc);
        cliente.id=1l;
        when(mockClientCtrl.consultaUno(DocumentType.CI, nroDoc)).thenReturn(cliente);
        
        //Mock InvoiceCtrl
        when(mockInvoiceCtrl.listPendingInvoicesByClient(cliente.id, calendario.getTime())).thenReturn(Collections.EMPTY_LIST);
              
        //Invocar a la validacion de facturas pendientes por cliente
        List<Invoice> listInvoices = instance.listPendingInvoicesByClient(DocumentType.CI, nroDoc, calendario.getTime());
        
        //Validar si ingreso o no en este metodo        
        Mockito.verify(mockInvoiceCtrl).listPendingInvoicesByClient(cliente.id, calendario.getTime());
        
        assertTrue("No se encontro ninguna factura pendiente", listInvoices.isEmpty());
    }
    @Test
    public void testListPendingInvoicesByDatesAfterActualDate() {                
        //parametros
        String nroDoc="234";
        Date fecha  = new Date();
        Calendar calendario =  Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.set(Calendar.YEAR,2099);
        
        //Mock Clientes
        Cliente cliente = getCliente(nroDoc);
        cliente.id=1l;
        when(mockClientCtrl.consultaUno(DocumentType.CI, nroDoc)).thenReturn(cliente);
        
        //Mock InvoiceCtrl
        when(mockInvoiceCtrl.listPendingInvoicesByClient(cliente.id, calendario.getTime())).thenReturn(Collections.EMPTY_LIST);
              
        //Invocar a la validacion de facturas pendientes por cliente
        List<Invoice> listInvoices = instance.listPendingInvoicesByClient(DocumentType.CI, nroDoc, calendario.getTime());
        
        //Validar si ingreso o no en este metodo        
        Mockito.verify(mockInvoiceCtrl, Mockito.never()).listPendingInvoicesByClient(cliente.id, calendario.getTime());
        
        assertTrue("No se encontro ninguna factura pendiente", listInvoices.isEmpty());
    }

    @Test
    public void testReceiptUniqueNumberNotFound() {
        Receipt receipt = new Receipt();
        String nroDoc="234";
        Cliente cliente = new Cliente();

        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoiceList.add(invoice);

        when(mockReceiptMgr.getLastReceiptNumber()).thenReturn(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    instance.receiptInvoicePending(cliente, invoiceList);
                });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ErrorMessage.RECEIPT_NUMBER_NOT_FOUND.toString()));

    }


    @Test
    public void testReceiptInvoicePendingNotSave() {
        Receipt receipt = new Receipt();
        String nroDoc="234";
        Cliente cliente = new Cliente();


        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoiceList.add(invoice);

        when(mockReceiptMgr.getLastReceiptNumber()).thenReturn("001-001-0000001");

        when(mockReceiptMgr.add(receipt)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    instance.receiptInvoicePending(cliente, invoiceList);
                });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(ErrorMessage.RECEIPT_NOT_CREATE.toString()));

    }

    @Test
    public void testReceiptInvoicePendingSave() {
        Receipt receipt = new Receipt();
        String nroDoc="234";
        Cliente cliente = new Cliente();

        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoiceList.add(invoice);
        when(mockReceiptMgr.getLastReceiptNumber()).thenReturn("001-001-0000001");

        //Validar si ingreso o no en este metodo
        //Mockito.verify(instance, Mockito.never()).getUniqueReceiptNumber("001-001-0000001");

        when(mockReceiptMgr.add(receipt)).thenReturn(true);
        //instance.receiptInvoicePending(cliente, invoiceList);
    }

    @Test
    public void testReceiptCancel(){
        Integer receiptId = 1;
        when(mockReceiptMgr.getById(receiptId)).thenReturn(new Receipt());
        when(mockReceiptMgr.cancelReceipt(receiptId)).thenReturn(true);
        Boolean result = instance.cancelReceipt(receiptId);
        assertTrue("El recibo no logro ser anulado", result);
    }
    
}
