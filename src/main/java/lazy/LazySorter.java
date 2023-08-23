/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazy;

import java.util.Comparator;
import modele.OrclassUtilisateurs;
import org.primefaces.model.SortOrder;

/**
 *
 * @author hp
 */
public class LazySorter implements Comparator<OrclassUtilisateurs>{
    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(OrclassUtilisateurs customer1, OrclassUtilisateurs customer2) {
        try {
            Object value1 = customer1.getClass().getField(this.sortField).get(customer1);
            Object value2 = customer2.getClass().getField(this.sortField).get(customer2);

            int value = ((Comparable)value1).compareTo(value2);
            
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
