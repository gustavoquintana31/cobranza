/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ibm
 */
public interface GenericDao<T, K extends Serializable> {
    
    Boolean add(T entity);
    
    T update(T entity);

    Boolean delete(K key);

    T getById(K key);

    List<T> getAll();
    
    List<T> getAll(int limit, int offset);

}
