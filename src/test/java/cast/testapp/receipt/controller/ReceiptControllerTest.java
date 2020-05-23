/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cast.testapp.receipt.controller;

import cast.testapp.catastro.controller.ClienteController;
import cast.testapp.catastro.controller.exceptions.ValidacionClientesException;
import cast.testapp.catastro.entities.Cliente;
import cast.testapp.catastro.entities.Cliente.TipoDoc;
import cast.testapp.catastro.entities.MensajeError;
import cast.testapp.invoice.controller.InvoiceController;
import cast.testapp.invoice.entities.Invoice;
import cast.testapp.receipt.boundary.ReceiptManager;
import cast.testapp.catastro.entities.DocumentType;
import cast.testapp.receipt.entities.ErrorMessage;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;    
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author cbustamante
 */
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
        when(mockClientCtrl.consultarCliente(DocumentType.CI, nroDoc)).thenReturn(null);
        
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
        String tipoDoc="CI";
        String nroDoc="234";
        Date fecha  = new Date();
        //inicializacion
        Cliente cliente = new Cliente(TipoDoc.CIP,nroDoc,"","");        
        when(mockClientCtrl.consultarCliente(DocumentType.CI, nroDoc)).thenReturn(cliente);
        List<Invoice> listInvoices = instance.listPendingInvoicesByClient(DocumentType.CI, nroDoc, fecha);
        assertTrue("No se encontro ninguna factura pendiente", listInvoices.isEmpty());
    }
    
    
}
