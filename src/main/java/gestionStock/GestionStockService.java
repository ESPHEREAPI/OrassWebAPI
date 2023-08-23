///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gestionStock;
//
//import dao.AbstractJpaDAO;
//import dao.OrclassGestionCompagnieAgenceDao;
//import dao.OrclassGestionStockCompagnieDao;
//import enums.Entite_Emmettrice;
//import exception.GlobalException;
//import java.math.BigInteger;
//import java.util.Date;
//import javax.annotation.Resource;
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionManagement;
//import javax.ejb.TransactionManagementType;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.HeuristicMixedException;
//import javax.transaction.HeuristicRollbackException;
//import javax.transaction.NotSupportedException;
//import javax.transaction.RollbackException;
//import javax.transaction.SystemException;
//import javax.transaction.UserTransaction;
//import modele.OrclassEntreprises;
//import modele.OrclassGestionCompagnieAgence;
//import modele.OrclassGestionStockCompagnie;
//import modele.OrclassIntermediaires;
//import modele.OrclassNatureDocument;
//import modele.OrclassOperationStock;
//import modele.OrclassTypeDocument;
//import modele.OrclassUtilisateurs;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sante.Sante;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
//public class GestionStockService implements IGestionStock {
//
//    @Resource
//    private UserTransaction tx;
//    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
//    private EntityManager em;
//    Logger logger = LoggerFactory.getLogger(Sante.class);
//    // Add business logic below. (Right-click in editor and choose
//    // "Insert Code > Add Business Method")
//    @EJB
//    OrclassGestionStockCompagnieDao gestionStockCompagnieDao;
//    @EJB
//    OrclassGestionCompagnieAgenceDao gestionCompagnieAgenceDao;
//
//    @Override
//    public Boolean approvisionnementCompagnie(OrclassEntreprises e, OrclassOperationStock op, OrclassTypeDocument tdoc, OrclassNatureDocument ndoc, Date effet, BigInteger debut_serie, BigInteger fin_serie, Entite_Emmettrice entite_Emmettrice, OrclassUtilisateurs u) {
//        Boolean resultat = Boolean.TRUE;
//        try {
//            OrclassGestionStockCompagnie gestionStockCompagnie = null;
//            String nomComplet = u.getNom();
//            BigInteger stockFinal = BigInteger.ZERO;
//            BigInteger stockInitial = BigInteger.ZERO;
//
//            if (u.getPrenom() != null || !"".equals(u.getPrenom())) {
//                nomComplet += u.getPrenom();
//            }
//            tx.begin();
//            gestionStockCompagnie = gestionStockCompagnieDao.lastRowGestioCompagnie(e, op, ndoc, tdoc);
//            if (gestionStockCompagnie == null || gestionStockCompagnie.getId() == null) {
//                gestionStockCompagnie = gestionStockCompagnieDao.finKey(e, effet, op, ndoc, tdoc);
//                if (gestionStockCompagnie == null) {
//                    gestionStockCompagnie = new OrclassGestionStockCompagnie();
//                    gestionStockCompagnie.setDateEffet(effet);
//                    gestionStockCompagnie.setDebut_serie(debut_serie);
//                    gestionStockCompagnie.setEntite_Emmettrice(entite_Emmettrice);
//                    gestionStockCompagnie.setFin_serie(fin_serie);
//                    gestionStockCompagnie.setIdEntreprise(e);
//                    gestionStockCompagnie.setIdNatureDocument(ndoc);
//                    gestionStockCompagnie.setIdOperationStock(op);
//                    gestionStockCompagnie.setIdTypeDocument(tdoc);
//                    gestionStockCompagnie.setPermission_modifier(Boolean.TRUE);
//                    gestionStockCompagnie.setSaisir_par(nomComplet);
//                    gestionStockCompagnie.setStockEntree(fin_serie.subtract(debut_serie));
//                    gestionStockCompagnie.setStockInitial(BigInteger.ZERO);
//                    gestionStockCompagnie.setStockSortie(BigInteger.ZERO);
//                    stockFinal = (gestionStockCompagnie.getStockInitial().add(gestionStockCompagnie.getStockEntree())).subtract(gestionStockCompagnie.getStockSortie());
//                    gestionStockCompagnie.setStockFinal(stockFinal);
//                    em.persist(gestionStockCompagnie);
//
//                }
//            } else {
//                OrclassGestionStockCompagnie newGestionStockCompagnie = null;
//                stockInitial = gestionStockCompagnie.getStockFinal();
//                newGestionStockCompagnie = gestionStockCompagnieDao.finKey(e, effet, op, ndoc, tdoc);
//                if (newGestionStockCompagnie == null || newGestionStockCompagnie.getId() == null) {
//                    gestionStockCompagnie.setPermission_modifier(Boolean.FALSE);
//                    gestionStockCompagnie.setSaisir_par(nomComplet);
//                    gestionStockCompagnie.setDateModification(new Date());
//                    em.merge(gestionStockCompagnie);
//
//                    newGestionStockCompagnie = new OrclassGestionStockCompagnie();
//                    newGestionStockCompagnie.setDateEffet(effet);
//                    newGestionStockCompagnie.setDebut_serie(debut_serie);
//                    newGestionStockCompagnie.setEntite_Emmettrice(entite_Emmettrice);
//                    newGestionStockCompagnie.setFin_serie(fin_serie);
//                    newGestionStockCompagnie.setIdEntreprise(e);
//                    newGestionStockCompagnie.setIdNatureDocument(ndoc);
//                    newGestionStockCompagnie.setIdOperationStock(op);
//                    newGestionStockCompagnie.setIdTypeDocument(tdoc);
//                    newGestionStockCompagnie.setPermission_modifier(Boolean.TRUE);
//                    newGestionStockCompagnie.setSaisir_par(nomComplet);
//                    newGestionStockCompagnie.setStockEntree(fin_serie.subtract(debut_serie));
//                    newGestionStockCompagnie.setStockInitial(stockInitial);
//                    newGestionStockCompagnie.setStockSortie(BigInteger.ZERO);
//                    stockFinal = (newGestionStockCompagnie.getStockInitial().add(newGestionStockCompagnie.getStockEntree())).subtract(newGestionStockCompagnie.getStockSortie());
//                    newGestionStockCompagnie.setStockFinal(stockFinal);
//                    em.persist(newGestionStockCompagnie);
//                }
//
//            }
//
//            tx.commit();
//        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException ex) {
//
//            try {
//
//                tx.rollback();
//                resultat = Boolean.FALSE;
//                throw ex;
//            } catch (Exception exc) {
//
//            }
//
//        }
//        return resultat;
//    }
//
//    @Override
//    public Boolean approvisionnementAgenceByCompagnie(OrclassEntreprises e, OrclassGestionStockCompagnie gsc, OrclassIntermediaires agence, OrclassOperationStock op, OrclassTypeDocument tdoc, OrclassNatureDocument ndoc, Date effet, BigInteger debut_serie, BigInteger fin_serie, Entite_Emmettrice entite_Emmettrice, OrclassUtilisateurs u) {
//        Boolean resultat = Boolean.TRUE;
//        try {
//            OrclassGestionCompagnieAgence gestionCompagnieAgence = null;
//            String nomComplet = u.getNom();
//            BigInteger stockFinal = BigInteger.ZERO;
//            BigInteger stockInitial = BigInteger.ZERO;
//            BigInteger stocSortie = BigInteger.ZERO;
//
//            if (u.getPrenom() != null || !"".equals(u.getPrenom())) {
//                nomComplet += u.getPrenom();
//            }
//            tx.begin();
//            gestionCompagnieAgence = gestionCompagnieAgenceDao.lasteStockCompagnieAgence(e, gsc, op, ndoc, tdoc, agence);
//            if (gestionCompagnieAgence == null || gestionCompagnieAgence.getId() == null) {
//                gestionCompagnieAgence = new OrclassGestionCompagnieAgence();
//                gestionCompagnieAgence.setDateEffet(effet);
//                gestionCompagnieAgence.setDebut_serie(debut_serie);
//                gestionCompagnieAgence.setFin_serie(fin_serie);
//                gestionCompagnieAgence.setIdEntreprise(e);
//                gestionCompagnieAgence.setIdGestionStockCompagnie(gsc);
//                gestionCompagnieAgence.setIdIntermediaire(agence);
//                gestionCompagnieAgence.setIdNatureDocument(ndoc);
//                gestionCompagnieAgence.setIdOperationStock(op);
//                gestionCompagnieAgence.setIdTypeDocument(tdoc);
//                gestionCompagnieAgence.setStockInitial(BigInteger.ZERO);
//                gestionCompagnieAgence.setStockEntree(fin_serie.subtract(debut_serie));
//                gestionCompagnieAgence.setStockSortie(BigInteger.ZERO);
//                gestionCompagnieAgence.setStockFinal(gestionCompagnieAgence.getStockEntree());
//                gestionCompagnieAgence.setSaisir_par(nomComplet);
//                stocSortie = gestionCompagnieAgence.getStockEntree();//le stocke entre en agence est la quantite de stock sortie en compagnie
//                stocSortie = stocSortie.add(gsc.getStockSortie());//on ajout autres sortie enfin d etablir le nouveau stock final de la compagnie
//                stockFinal = (gsc.getStockInitial().add(gsc.getStockEntree())).subtract(stocSortie);
//                gsc.setStockFinal(stockFinal);
//                gsc.setStockSortie(stocSortie);
//                gsc.setDateModification(new Date());
//                gsc.setModifier_par(nomComplet);
//                em.merge(gsc);
//                em.persist(gestionCompagnieAgence);
//
//            } else {
//                stockInitial = gestionCompagnieAgence.getStockFinal();
//                OrclassGestionCompagnieAgence newGestionCompagnieAgence = null;
//                newGestionCompagnieAgence = gestionCompagnieAgenceDao.finKey(e, gsc, effet, op, ndoc, tdoc, agence);
//                if (newGestionCompagnieAgence == null || newGestionCompagnieAgence.getId() == null) {
//                    newGestionCompagnieAgence = new OrclassGestionCompagnieAgence();
//
//                    newGestionCompagnieAgence.setDateEffet(effet);
//                    newGestionCompagnieAgence.setDebut_serie(debut_serie);
//                    newGestionCompagnieAgence.setFin_serie(fin_serie);
//                    newGestionCompagnieAgence.setIdEntreprise(e);
//                    newGestionCompagnieAgence.setIdGestionStockCompagnie(gsc);
//                    newGestionCompagnieAgence.setIdIntermediaire(agence);
//                    newGestionCompagnieAgence.setIdNatureDocument(ndoc);
//                    newGestionCompagnieAgence.setIdOperationStock(op);
//                    newGestionCompagnieAgence.setIdTypeDocument(tdoc);
//                    newGestionCompagnieAgence.setDebut_serie(debut_serie);
//                    newGestionCompagnieAgence.setFin_serie(fin_serie);
//                    newGestionCompagnieAgence.setStockEntree(fin_serie.subtract(debut_serie));
//                    newGestionCompagnieAgence.setStockSortie(BigInteger.ZERO);
//                    newGestionCompagnieAgence.setStockInitial(stockInitial);
//                    newGestionCompagnieAgence.setStockFinal((newGestionCompagnieAgence.getStockEntree().add(newGestionCompagnieAgence.getStockInitial())).subtract(newGestionCompagnieAgence.getStockSortie()));
//                    newGestionCompagnieAgence.setSaisir_par(nomComplet);
//                    stocSortie = gestionCompagnieAgence.getStockEntree();//le stocke entre en agence est la quantite de stock sortie en compagnie
//                    stocSortie = stocSortie.add(gsc.getStockSortie());//on ajout autres sortie enfin d etablir le nouveau stock final de la compagnie
//                    stockFinal = (gsc.getStockInitial().add(gsc.getStockEntree())).subtract(stocSortie);
//                    gsc.setStockSortie(stocSortie);
//                    gsc.setStockFinal(stockFinal);
//                    gsc.setDateModification(new Date());
//                    gsc.setModifier_par(nomComplet);
//                    em.merge(gsc);
//                    em.persist(newGestionCompagnieAgence);
//
//                }
//            }
//            tx.commit();
//        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException ex) {
//            try {
//                tx.rollback();
//                resultat = Boolean.FALSE;
//                throw ex;
//            } catch (Exception exc) {
//
//            }
//
//        }
//        return resultat;
//    }
//
//    @Override
//    public Boolean approvisionnementAgenceByAgence(OrclassEntreprises e, OrclassGestionCompagnieAgence gsca, OrclassIntermediaires agenceReceptrice, OrclassOperationStock op, OrclassTypeDocument tdoc, OrclassNatureDocument ndoc, Date effet, BigInteger debut_serie, BigInteger fin_serie, Entite_Emmettrice entite_Emmettrice, OrclassUtilisateurs u) {
//        try {
//            tx.begin();
//
//            tx.commit();
//        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException ex) {
//            try {
//                tx.rollback();
//                throw ex;
//            } catch (Exception exc) {
//
//            }
//
//        }
//        return Boolean.TRUE;
//    }
//}
