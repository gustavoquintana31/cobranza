/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cast.testapp.receipt.entities;

/**
 *
 * @author cbustamante
 */
public enum ErrorMessage {
    CLIENT_NOT_FOUND("CLIENT NOT FOUD"),
    CLIENT_IS_EMPTY("CLIENT IS EMPTY"),
    INVALID_DATE_BEFORE("INVALID DATE"),
    RECEIPT_NOT_CREATE("RECEIPT CAN'T SAVE"),
    INVOICE_LIST_IS_EMPTY("INVOICE LIST IS EMPTY"),
    RECEIPT_NUMBER_NOT_FOUND("RECEIPT NUMBER NOT FOUND"),
    RECEIPT_NOT_FOUND("RECEIPT_NOT_FOUND");

    private String mensaje;

    private ErrorMessage(String mensaje) {
        this.mensaje = mensaje;
    }
}
