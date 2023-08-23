/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modele.Mois;


/**
 *
 * @author frankjiatou
 */
@Stateless
public class MoisDao extends AbstractJpaDAO<Mois> {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    protected Class<Mois> getEntityClass() {
        return Mois.class;

    }

    @Override
    protected EntityManager getEntityManager() {

        return em;
    }

    

    public Mois getMoisByEntrepriseByNumero(int annee, int nbre) {
        Map<String, Object> param = new HashMap<>();
        param.put("anneeid", annee);
        param.put("numero", nbre);

        String querry = "select m from Mois m where m.annee.id = :anneeid and m.numero= :numero";
        return super.findEntityByUsingQuery(querry, param);
    }

    public Mois findKey(Mois m) {
        Map<String, Object> param = new HashMap<>();
        param.put("anneeid", m.getAnnee().getId());
        param.put("mois", m.getTypeMois());
//     param.put("code", m.getCode()); 
        param.put("numero", m.getNumero());
        String query = "select m from Mois m where m.annee.id= :anneeid and m.numero= :numero and m.typeMois= :mois ";
        return super.findEntityByUsingQuery(query, param);
    }

    public Mois getNumeroForMois(Mois m) {
        Map<String, Object> param = new HashMap<>();
        param.put("anneeid", m.getAnnee().getId());
        param.put("mois", m.getTypeMois());
        param.put("code", m.getCode());
//     param.put("numero", m.getNumero()); 
        String query = "select m from Mois m where m.annee.id= :anneeid  and m.typeMois= :mois and m.code= :code";
        return super.findEntityByUsingQuery(query, param);
    }

    /**
     * retourner le d une annee
     *
     * @param e
     * @return
     */
 

//     BulletinCollection
 

    /**
     * verifier si le mois a deja ete pays
     *
     * @param e
     * @return
     */
      
}
