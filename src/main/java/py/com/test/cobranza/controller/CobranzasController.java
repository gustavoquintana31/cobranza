package py.com.test.cobranza.controller;

import py.com.test.cobranza.boundary.CobranzaManager;
import py.com.test.cobranza.ec.Receipt;
import py.com.test.ventas.controller.Ventas;

public class CobranzasController {

    public void cobrar(Integer id){
        System.out.println("cobrar ahora");
    }

    public void test(){
        /*Ventas ventas = new Ventas();
        ventas.vender(1);*/
        new Ventas().agregarFactura();
    }

    public void agregarCobranza(Receipt receipt){
        System.out.println("Agregar Receipt");
        new CobranzaManager().agregar(receipt);
    }

    public void actualizarCobranza(Receipt receipt){
        System.out.println("Actualizar Receipt");
        new CobranzaManager().actualizar(receipt);
    }

    public void eliminarCobranza(Receipt receipt){
        new CobranzaManager().eliminar(receipt);
    }


    public static void main(String[] args) {
        //new CobranzasController().test();
        Receipt receipt = new Receipt();
        receipt.setId(3L);
        receipt.setNumber("001-002-0000003");

        //new CobranzasController().agregarCobranza(receipt);

        //new CobranzasController().eliminarCobranza(receipt);

        new CobranzasController().actualizarCobranza(receipt);

    }
}
