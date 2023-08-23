///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Excell;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//import javax.ejb.Local;
//import modele.OrclassActions;
//import modele.OrclassActivite;
//import modele.OrclassCaracteristiques;
//import modele.OrclassDuree;
//import modele.OrclassFamillePrestation;
//import modele.OrclassImageFamilleRisque;
//import modele.OrclassImageRisque;
//import modele.OrclassPrestation;
//import modele.OrclassProfession;
//import modele.OrclassQualite;
//import modele.OrclassRefGaranties;
//import modele.OrclassRubrique;
//import modele.OrclasseRefGroupe;
//import modele.Pays;
//
///**
// *
// * @author hp
// */
//@Local
//public interface IExcell {
//
//    /*
//    recupere les reference garantie à partier d un fichier systeme
//     */
//    public List<OrclassRefGaranties> recuperListeRefrenceGarantieByExcell(InputStream file, String fileName) throws IOException;
//
//    public List<OrclassFamillePrestation> recuperListeFamillePrestationByExcell(InputStream file, String fileName) throws IOException;
//
//    public List<OrclassPrestation> recuperListePrestationByExcell(InputStream file, String fileName) throws IOException;
//
//    public List<OrclassRubrique> recuperListeRubriqueByExcell(InputStream file, String fileName) throws IOException;
//
//    public List<OrclassCaracteristiques> recuperListeCaracterisqueByExcell(InputStream file, String fileName) throws IOException;
//
//    public List<OrclassDuree> recuperListeDureeByExcell(InputStream file, String fileName) throws IOException;
//    public void chargeFileExcellProduction(InputStream file, String fileName,OrclasseRefGroupe refGroupe) throws IOException;
//
//    public int lastRowRead();
//
//    public int lastRow();
//
//    public List<OrclassActivite> recuperListActiviteByExcell(InputStream file, String fileName) throws IOException;
//
//    public List<OrclassQualite> recuperListQualiteByExcell(InputStream file, String fileName) throws IOException;
//
//    public List<Pays> recuperListPaysByExcell(InputStream file, String fileName) throws IOException;
//
//    public List<OrclassProfession> recuperListProfessionsByExcell(InputStream file, String fileName) throws IOException;
//   public List<OrclassImageFamilleRisque> listeImageFamilleRisque();
//    public List<OrclassImageRisque> listeImageRisque();
//}
