///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gestionStock;
//
//import enums.Entite_Emmettrice;
//import java.math.BigInteger;
//import java.util.Date;
//import modele.OrclassEntreprises;
//import modele.OrclassGestionCompagnieAgence;
//import modele.OrclassGestionStockCompagnie;
//import modele.OrclassIntermediaires;
//import modele.OrclassNatureDocument;
//import modele.OrclassOperationStock;
//import modele.OrclassTypeDocument;
//import modele.OrclassUtilisateurs;
//
///**
// *
// * @author JIATOU FRANCK
// */
//public interface IGestionStock {
//
//    public Boolean approvisionnementCompagnie(OrclassEntreprises e, OrclassOperationStock op, OrclassTypeDocument tdoc, OrclassNatureDocument ndoc, Date effet, BigInteger debut_serie, BigInteger fin_serie, Entite_Emmettrice entite_Emmettrice, OrclassUtilisateurs u);
//
//    public Boolean approvisionnementAgenceByCompagnie(OrclassEntreprises e, OrclassGestionStockCompagnie gsc, OrclassIntermediaires agence, OrclassOperationStock op, OrclassTypeDocument tdoc, OrclassNatureDocument ndoc, Date effet, BigInteger debut_serie, BigInteger fin_serie, Entite_Emmettrice entite_Emmettrice, OrclassUtilisateurs u);
//
//    public Boolean approvisionnementAgenceByAgence(OrclassEntreprises e, OrclassGestionCompagnieAgence gsca, OrclassIntermediaires agenceReceptrice, OrclassOperationStock op, OrclassTypeDocument tdoc, OrclassNatureDocument ndoc, Date effet, BigInteger debut_serie, BigInteger fin_serie, Entite_Emmettrice entite_Emmettrice, OrclassUtilisateurs u);
//}
