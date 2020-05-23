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
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;    
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
        String nroDoc="234";
        Date fecha  = new Date();
        //inicializacion
        Cliente cliente = new Cliente(TipoDoc.CIP,nroDoc,"","");        
        when(mockClientCtrl.consultarCliente(DocumentType.CI, nroDoc)).thenReturn(cliente);
        List<Invoice> listInvoices = instance.listPendingInvoicesByClient(DocumentType.CI, nroDoc, fecha);
        assertTrue("No se encontro ninguna factura pendiente", listInvoices.isEmpty());
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
        Cliente cliente = new Cliente(TipoDoc.CIP,nroDoc,"","");        
        cliente.id=1l;
        when(mockClientCtrl.consultarCliente(DocumentType.CI, nroDoc)).thenReturn(cliente);
        
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
        Cliente cliente = new Cliente(TipoDoc.CIP,nroDoc,"","");        
        cliente.id=1l;
        when(mockClientCtrl.consultarCliente(DocumentType.CI, nroDoc)).thenReturn(cliente);
        
        //Mock InvoiceCtrl
        when(mockInvoiceCtrl.listPendingInvoicesByClient(cliente.id, calendario.getTime())).thenReturn(Collections.EMPTY_LIST);
              
        //Invocar a la validacion de facturas pendientes por cliente
        List<Invoice> listInvoices = instance.listPendingInvoicesByClient(DocumentType.CI, nroDoc, calendario.getTime());
        
        //Validar si ingreso o no en este metodo        
        Mockito.verify(mockInvoiceCtrl, Mockito.never()).listPendingInvoicesByClient(cliente.id, calendario.getTime());
        
        assertTrue("No se encontro ninguna factura pendiente", listInvoices.isEmpty());
    }
    
}
