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
    INVALID_DATE_BEFORE("INVALID DATE");
    private String mensaje;

    private ErrorMessage(String mensaje) {
        this.mensaje = mensaje;
    }
}
