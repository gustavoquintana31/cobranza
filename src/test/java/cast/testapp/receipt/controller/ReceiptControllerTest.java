/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cast.testapp.receipt.controller;

import cast.testapp.catastro.controller.ClienteController;
import cast.testapp.catastro.entities.Cliente;
import cast.testapp.invoice.entities.Invoice;
import cast.testapp.receipt.boundary.ReceiptManager;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
        mockClientCtrl = mock (ClienteController.class);
        mockReceiptMgr = mock(ReceiptManager.class);        
        instance = new ReceiptController(mockClientCtrl,mockReceiptMgr);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of listPendingInvoicesByClient method, of class ReceiptController.
     */
    @Test
    public void testListPendingInvoicesByClient() {
        //inicializacion
        Cliente cliente = null;
        
        //parametros
        String tipoDoc="CI";
        String nroDoc="234";
        Date fecha  = new Date();
        when(mockClientCtrl.consultarCliente(tipoDoc, nroDoc)).thenReturn(null);
        List<Invoice> listInvoices = instance.listPendingInvoicesByClient(tipoDoc, nroDoc, fecha);
        assertTrue("No se encontro ningun cliente", listInvoices.isEmpty());
    }
    
}
