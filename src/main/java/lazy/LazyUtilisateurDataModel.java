/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazy;

import dao.OrclassUtilisateursDao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.inject.Inject;
import modele.OrclassEntreprises;
import modele.OrclassUtilisateurs;
import modele.Societe;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;



/**
 *
 * @author hp
 */
public class LazyUtilisateurDataModel extends LazyDataModel<OrclassUtilisateurs> {
    
    @Inject
    OrclassUtilisateursDao utilisateursDao;
     private List<OrclassUtilisateurs> datasource=new ArrayList<>();
     Societe societe;

    public LazyUtilisateurDataModel(List<OrclassUtilisateurs> datasource, Societe s) {
        this.datasource = datasource;
        this.societe=s;
    }

    public LazyUtilisateurDataModel(Societe s) {
        this.societe = s;
        this.datasource=(List<OrclassUtilisateurs>) utilisateursDao.listUtilisateurWithFilters( societe);
    }
    

    @Override
    public OrclassUtilisateurs getRowData(String rowKey) {
        for (OrclassUtilisateurs user : datasource) {
            if (user.getIdUtilisateur() == Integer.parseInt(rowKey)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(OrclassUtilisateurs user) {
        return String.valueOf(user.getIdUtilisateur());
    }

    @Override
    public List<OrclassUtilisateurs> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
       String filterValue = null;
        Set set = filterBy.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            filterValue = (String) me.getValue();
        }
        if (filterValue == null || filterValue.trim().equals("")) {
            datasource = utilisateursDao.findRange(offset, offset + pageSize);
            setRowCount(utilisateursDao.count().intValue());
        } else {
            datasource = (List<OrclassUtilisateurs>) utilisateursDao.listUtilisateurWithFilters(offset, offset, filterValue, societe);
            setRowCount(utilisateursDao.countUtilisateurWithFilters(filterValue, societe).intValue());
        }


     

        return datasource;
    }

//    @Override
//    public int count(Map<String, FilterMeta> map) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void forEach(Consumer<? super OrclassUtilisateurs> action) {
        super.forEach(action); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Spliterator<OrclassUtilisateurs> spliterator() {
        return super.spliterator(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count(Map<String, FilterMeta> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


  
     
     
}
