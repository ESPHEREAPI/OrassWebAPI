///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Excell;
//
//import dao.OrclassCaracteristiquesDao;
//import dao.OrclassDureeDao;
//import dao.OrclassFamillePrestationDao;
//import dao.OrclassPrestationDao;
//import dao.OrclassProfessionDao;
//import dao.OrclassRefGarantiesDao;
//import dao.OrclassRubriqueDao;
//import dao.OrclassType_CaracteristiqueDao;
//import dao.OrclassUnite_CaracteristiqueDao;
//import enums.LienParente;
//import enums.Sexe;
//import enums.TypeDuree;
//import enums.UniteDuree;
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.faces.context.FacesContext;
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
//import modele.OrclassType_Caracteristique;
//import modele.OrclassUnite_Caracteristique;
//import modele.OrclasseRefGroupe;
//import modele.Pays;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.FormulaEvaluator;
//import org.primefaces.shaded.commons.io.FilenameUtils;
//import utils.IdleDate;
//
///**
// *
// * @author hp
// */
//@Stateless
//
//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//
//public class ExcellService implements IExcell {
//
////    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
////    private EntityManager em;
////    @Resource
////    UserTransaction tx;
//    @EJB
//    OrclassRefGarantiesDao refGarantiesDao;
//    @EJB
//    OrclassFamillePrestationDao famillePrestationDao;
//    @EJB
//    OrclassPrestationDao prestationDao;
//    @EJB
//    OrclassRubriqueDao rubriqueDao;
//    @EJB
//    OrclassCaracteristiquesDao caracteristiquesDao;
//    @EJB
//    OrclassType_CaracteristiqueDao type_CaracteristiqueDao;
//    @EJB
//    OrclassUnite_CaracteristiqueDao unite_CaracteristiqueDao;
//    @EJB
//    OrclassDureeDao dureeDao;
//    @EJB
//    OrclassProfessionDao professionDao;
//    private int lastRow = 0;
//    private int lastRowRead = 0;
//
//    List<OrclassImageFamilleRisque> listeImageFamilleRisque = new ArrayList<>();
//    List<OrclassImageRisque> listeImageRisque = new ArrayList<>();
//
//    @Override
//    public List<OrclassRefGaranties> recuperListeRefrenceGarantieByExcell(InputStream file, String fileName) throws IOException {
//        //importer le fichier excell
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        OrclassRefGaranties refGarentie;
//        List<OrclassRefGaranties> colRefGarantie = new ArrayList<>();
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String code, libelle, famille;
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    int lastRow = sheet.getLastRowNum();
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne < lastRow) {
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(0); // recuperation du code 
//                        code = cell.getStringCellValue();
//                        if (code != null && !"".equals(code)) {
//                            refGarentie = refGarantiesDao.findEntityHavingValue("code", code);
//                            if (refGarentie == null) {
//                                cell = row.getCell(1);
//                                libelle = cell.getStringCellValue();
//                                cell = row.getCell(2);
//                                famille = cell.getStringCellValue();
//                                refGarentie = new OrclassRefGaranties(code, libelle, famille);
//                                colRefGarantie.add(refGarentie);
//                            }
//                        }
//                        debutLigne++;
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//                    return null;
//                }
//                break;
//        }
//        return colRefGarantie;
//    }
//
//    // Add business logic below. (Right-click in editor and choose
//    // "Insert Code > Add Business Method")
//    @Override
//    public List<OrclassFamillePrestation> recuperListeFamillePrestationByExcell(InputStream file, String fileName) throws IOException {
//        //importer le fichier excell
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        OrclassFamillePrestation f;
//        List<OrclassFamillePrestation> colFamillePrestation = new ArrayList<>();
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String code, libelle, famille;
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    int lastRow = sheet.getLastRowNum();
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne < lastRow) {
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(0); // recuperation du code 
//                        code = cell.getStringCellValue();
//                        if (code != null && !"".equals(code)) {
//                            f = famillePrestationDao.findEntityHavingValue("code", code);
//                            if (f == null) {
//                                cell = row.getCell(1);
//                                libelle = cell.getStringCellValue();
//
//                                f = new OrclassFamillePrestation(code, libelle);
//                                colFamillePrestation.add(f);
//                            }
//                        }
//                        debutLigne++;
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//                    return null;
//                }
//                break;
//        }
//        return colFamillePrestation;
//
//    }
//
//    @Override
//    public List<OrclassPrestation> recuperListePrestationByExcell(InputStream file, String fileName) throws IOException {
//        //importer le fichier excell
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        OrclassPrestation prest;
//        List<OrclassPrestation> colPrestation = new ArrayList<>();
//        OrclassFamillePrestation famillePrestation = null;
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String code, libelle, famille, codeCle = null;
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    int lastRow = sheet.getLastRowNum();
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne < lastRow) {
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(0); // recuperation du code 
//                        code = cell.getStringCellValue();
//                        if (code != null && !"".equals(code)) {
//                            prest = prestationDao.findEntityHavingValue("code", code);
//                            if (prest == null) {
//                                cell = row.getCell(1);
//                                libelle = cell.getStringCellValue();
//                                cell = row.getCell(2);
//                                famille = cell.getStringCellValue();
//                                famillePrestation = famillePrestationDao.findEntityHavingValue("code", famille);
//                                try {
//                                    cell = row.getCell(3);
//                                    codeCle = cell.getStringCellValue();
//                                } catch (Exception e) {
//                                    if (row == null) {
//                                        codeCle = "";
//                                    }
//                                }
//
//                                if (famillePrestation != null) {
//                                    prest = new OrclassPrestation(code, libelle, codeCle);
//                                    prest.setIdFamillePrestation(famillePrestation);
//                                    colPrestation.add(prest);
//                                }
//
//                            }
//                        }
//                        debutLigne++;
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//                    return null;
//                }
//                break;
//        }
//        return colPrestation;
//    }
//
//    @Override
//    public List<OrclassRubrique> recuperListeRubriqueByExcell(InputStream file, String fileName) throws IOException {
//        //importer le fichier excell
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        OrclassRubrique rub;
//        List<OrclassRubrique> colRubrique = new ArrayList<>();
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String code, libelle, famille = null;
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    int lastRow = sheet.getLastRowNum();
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne < lastRow) {
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(0); // recuperation du code 
//                        code = cell.getStringCellValue();
//                        if (code != null && !"".equals(code)) {
//                            rub = rubriqueDao.findEntityHavingValue("code", code);
//                            if (rub == null) {
//                                cell = row.getCell(1);
//                                libelle = cell.getStringCellValue();
//
//                                try {
//                                    cell = row.getCell(2);
//                                    famille = cell.getStringCellValue();
//                                } catch (Exception e) {
//                                    if (row == null) {
//                                        famille = "";
//                                    }
//                                }
//
//                                rub = new OrclassRubrique(code, libelle, famille);
//                                colRubrique.add(rub);
//                            }
//                        }
//                        debutLigne++;
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//                    return null;
//                }
//                break;
//        }
//        return colRubrique;
//    }
//
//    @Override
//    public List<OrclassCaracteristiques> recuperListeCaracterisqueByExcell(InputStream file, String fileName) throws IOException {
//        //importer le fichier excell
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        OrclassCaracteristiques ca = null;
//        OrclassType_Caracteristique tc = null;
//        OrclassUnite_Caracteristique uc = null;
//        List<OrclassCaracteristiques> colRubrique = new ArrayList<>();
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String code, libelle, famille, type_Caract, unite_Caracteristique = null;
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    int lastRow = sheet.getLastRowNum();
////                    this.setLastRow(lastRow);
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne <= lastRow) {
////                        if (this.lastRowRead != 0) {
////                            debutLigne = lastRowRead - 1;
////                        }
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(0); // recuperation du code 
//                        code = cell.getStringCellValue();
//
//                        if (code != null && !"".equals(code)) {
//                            try {
//                                ca = caracteristiquesDao.findEntityHavingValue("code", code);
////                                ca = finKeyCaracteristiqueForAllCompagnie(code);
//                            } catch (Exception ex) {
//                                ca = null;
//                            }
//
//                            if (ca == null) {
//                                cell = row.getCell(1);
//                                libelle = cell.getStringCellValue();
//                                try {
//                                    cell = row.getCell(2);
//                                    type_Caract = cell.getStringCellValue();
//                                    tc = type_CaracteristiqueDao.findEntityHavingValue("code", type_Caract);
//
//                                } catch (Exception e) {
////                                     if (row == null) {
//                                    tc = null;
////                                    }
//                                }
//
//                                try {
//                                    cell = row.getCell(3);
//                                    unite_Caracteristique = cell.getStringCellValue();
//                                    uc = unite_CaracteristiqueDao.findEntityHavingValue("code", unite_Caracteristique);
//                                } catch (Exception e) {
////                                    if (row == null) {
//                                    unite_Caracteristique = null;
////                                    }
//                                }
//
//                                ca = new OrclassCaracteristiques(code, libelle);
//                                if (tc != null) {
//                                    ca.setType_Caracteristique(tc);
//                                }
//                                if (uc != null) {
//                                    ca.setUnite_Caracteristique(uc);
//                                }
//                                colRubrique.add(ca);
//                                ca = null;
//                            }
//                        }
//                        debutLigne++;
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//                    System.out.println("Erreur Liste taille list : " + colRubrique.size());
//                    System.err.println("message erreur :" + e.getMessage() + "|" + e.getLocalizedMessage());
//                    this.setLastRowRead(debutLigne);
//                    file.close();
//
////                    return null;
//                    return colRubrique;
//                }
//                break;
//        }
//        this.setLastRowRead(0);
//        return colRubrique;
//
//    }
//
////    public OrclassCaracteristiques finKeyCaracteristiqueForAllCompagnie(String code) {
////        Query q;
////        q = em.createQuery("SELECT c FROM OrclassCaracteristiques c WHERE c.code= :code and c.showAllCompagnies= :value and c.idEntreprise IS NULL")
////                .setParameter("code", code)
////                .setParameter("value", Boolean.TRUE);
////        if (q.getResultList().isEmpty()) {
////            return null;
////        }
////        return (OrclassCaracteristiques) q.getResultList().toArray()[0];
////    }
//    public int getLastRow() {
//        return lastRow;
//    }
//
//    public void setLastRow(int lastRow) {
//        this.lastRow = lastRow;
//    }
//
//    public int getLastRowRead() {
//        return lastRowRead;
//    }
//
//    public void setLastRowRead(int lastRowRead) {
//        this.lastRowRead = lastRowRead;
//    }
//
//    @Override
//    public int lastRowRead() {
//        return lastRowRead;
//    }
//
//    @Override
//    public int lastRow() {
//        return lastRow;
//    }
//
//    @Override
//    public List<OrclassDuree> recuperListeDureeByExcell(InputStream file, String fileName) throws IOException {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        OrclassDuree d = null;
//        OrclassType_Caracteristique tc = null;
//        OrclassUnite_Caracteristique uc = null;
//        List<OrclassDuree> colDuree = new ArrayList<>();
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String code, libelle, famille, type_Caract, unite_Caracteristique = null;
//        Double valeur;
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    int lastRow = sheet.getLastRowNum();
////                    this.setLastRow(lastRow);
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne <= lastRow) {
////                        if (this.lastRowRead != 0) {
////                            debutLigne = lastRowRead - 1;
////                        }
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(0); // recuperation du code 
//                        code = cell.getStringCellValue();
//
//                        if (code != null && !"".equals(code)) {
//                            try {
//                                d = dureeDao.findEntityHavingValue("code", code);
////                                ca = finKeyCaracteristiqueForAllCompagnie(code);
//                            } catch (Exception ex) {
//                                d = null;
//                            }
//
//                            if (d == null) {
//                                d = new OrclassDuree();
//                                d.setCode(code);
//                                cell = row.getCell(1);
//                                libelle = cell.getStringCellValue();
//                                d.setLibelle(libelle);
//                                cell = row.getCell(2);
//                                valeur = cell.getNumericCellValue();
//                                d.setMinDuree(BigInteger.valueOf(valeur.longValue()));
//                                cell = row.getCell(3);
//                                if (cell != null && "J".equals(cell.getStringCellValue())) {
//                                    d.setUniteDuree(UniteDuree.jours);
//                                }
//                                if (cell != null && "S".equals(cell.getStringCellValue())) {
//                                    d.setUniteDuree(UniteDuree.semaines);
//                                }
//                                if (cell != null && "M".equals(cell.getStringCellValue())) {
//                                    d.setUniteDuree(UniteDuree.mois);
//                                }
//                                cell = row.getCell(4);
//                                if (cell != null) {
//                                    valeur = cell.getNumericCellValue();
//                                    d.setMaxDuree(BigInteger.valueOf(valeur.longValue()));
//                                }
//                                cell = row.getCell(5);
//                                if (cell != null && "V".equals(cell.getStringCellValue())) {
//                                    d.setTypeDuree(TypeDuree.variable);
//                                }
//                                if (cell != null && "F".equals(cell.getStringCellValue())) {
//                                    d.setTypeDuree(TypeDuree.fin_annee);
//                                }
//                                cell = row.getCell(6);
//                                if (cell != null) {
//                                    valeur = cell.getNumericCellValue();
//                                    d.setBaseProrata(BigInteger.valueOf(valeur.longValue()));
//
//                                }
//
//                            }
//                        }
//                        debutLigne++;
//                        if (debutLigne == 46) {
//                            debutLigne++;
//                        }
//                        colDuree.add(d);
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//                    System.out.println("Erreur Liste taille list : " + colDuree.size());
//                    System.err.println("message erreur :" + e.getMessage() + "|" + e.getLocalizedMessage());
//                    this.setLastRowRead(debutLigne);
//                    file.close();
//
//                    return colDuree;
////             return colRubrique;
//                }
//                break;
//        }
//
//        return colDuree;
//    }
//
//    @Override
//    public List<OrclassActivite> recuperListActiviteByExcell(InputStream file, String fileName) throws IOException {
//
//        //importer le fichier excell
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        OrclassActivite act;
//        List<OrclassActivite> colActivite = new ArrayList<>();
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String code, libelle;
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    int lastRow = sheet.getLastRowNum();
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne < lastRow) {
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(0); // recuperation du code 
//                        code = "" + cell.getNumericCellValue();
//                        if (code != null && !"".equals(code)) {
//                            cell = row.getCell(1);
//                            libelle = cell.getStringCellValue();
//
//                            act = new OrclassActivite(code, libelle);
//                            colActivite.add(act);
//
//                        }
//                        debutLigne++;
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//                    return null;
//                }
//                break;
//        }
//        return colActivite;
//    }
//
//    @Override
//    public List<OrclassQualite> recuperListQualiteByExcell(InputStream file, String fileName) throws IOException {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. //importer le fichier excell
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        OrclassQualite q;
//        List<OrclassQualite> colqualite = new ArrayList<>();
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String code, libelle;
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    int lastRow = sheet.getLastRowNum();
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne < lastRow) {
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(0); // recuperation du code 
//                        code = "" + cell.getNumericCellValue();
//                        if (code != null && !"".equals(code)) {
//                            cell = row.getCell(1);
//                            libelle = cell.getStringCellValue();
//
//                            q = new OrclassQualite(code, libelle);
//                            colqualite.add(q);
//
//                        }
//                        debutLigne++;
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//                    return null;
//                }
//                break;
//        }
//        return colqualite;
//    }
//
//    @Override
//    public List<Pays> recuperListPaysByExcell(InputStream file, String fileName) throws IOException {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        Pays p;
//        List<Pays> colPays = new ArrayList<>();
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String code, libelle;
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    int lastRow = sheet.getLastRowNum();
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne < lastRow) {
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(0); // recuperation du code 
//                        code = cell.getStringCellValue();
//                        if (code != null && !"".equals(code)) {
//                            cell = row.getCell(1);
//                            libelle = cell.getStringCellValue();
//
//                            p = new Pays(code, libelle, "", "");
//                            colPays.add(p);
//
//                        }
//                        debutLigne++;
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//                    return null;
//                }
//                break;
//        }
//        return colPays;
//    }
//
//    @Override
//    public List<OrclassProfession> recuperListProfessionsByExcell(InputStream file, String fileName) throws IOException {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        OrclassProfession pf;
//        List<OrclassProfession> colProfession = new ArrayList<>();
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String code, libelle;
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    int lastRow = sheet.getLastRowNum();
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne < lastRow) {
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(0); // recuperation du code 
//                        code = "" + cell.getNumericCellValue();
//                        if (code != null && !"".equals(code)) {
//                            pf = professionDao.findEntityHavingValue("code", code);
//                            if (pf == null) {
//                                cell = row.getCell(1);
//                                libelle = cell.getStringCellValue();
//                                pf = new OrclassProfession(code, libelle);
//                                colProfession.add(pf);
//                            }
//
//                        }
//                        debutLigne++;
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//                    return null;
//                }
//                break;
//        }
//        return colProfession;
//    }
//
//    @Override
//    public void chargeFileExcellProduction(InputStream file, String fileName, OrclasseRefGroupe refGroupe) throws IOException {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        Map<String, Object> params = new HashMap<>();
//        String ext = FilenameUtils.getExtension(fileName);
//        System.out.println("extension du fichier lu:" + ext);
//        OrclassProfession pf;
//        List<OrclassImageFamilleRisque> colImageFamilleRisque = new ArrayList<>();
//        List<OrclassImageRisque> colImageRisque = new ArrayList<>();
//        int debutLigne = 1;
//        Boolean etat = true;
//        int indiceClasseur = 0;
//        HSSFSheet sheet = null;
//        //Lecture des champs excel
//        HSSFRow row = null;
//        HSSFCell cell = null;
//        String matricule="", nom, sexe, date, parente;
//        Date dates = null;
//        OrclassImageFamilleRisque imageFamilleRisque = null;
//        OrclassImageRisque imageRisque = null;
//        FormulaEvaluator formulaEvaluator = null;
//
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
//                    int lastRow = sheet.getLastRowNum()+1;
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne < lastRow) {
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(1);
//// recuperation du matricule 
//                        switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
//                            case Cell.CELL_TYPE_NUMERIC:
//                                matricule = "" + cell.getNumericCellValue();
//                                System.out.print(cell.getNumericCellValue() + "\t\t");
//                                break;
//                            case Cell.CELL_TYPE_STRING:
//                                matricule = cell.getStringCellValue();
//                                System.out.print(cell.getStringCellValue() + "\t");
//                                break;
//                            case Cell.CELL_TYPE_BLANK:
//                                matricule = "";
//                        }
//                        cell = row.getCell(2); // recuperation du nom complet
//                        nom = cell.getStringCellValue();
//                        cell = row.getCell(3); // recuperation de la date
//                        try {
//                            date = cell.getStringCellValue();
//
////                            if (!"".equals(date) || date != null) {
//                            dates = IdleDate.parseString(date, "dd/MM/yyy");
////                            }
//
//                        } catch (Exception e) {
//                            dates = cell.getDateCellValue();
//                        }
//
//                        cell = row.getCell(4); // recuperation de la sexe
//                        sexe = cell.getStringCellValue();
//                        cell = row.getCell(5); // recuperation de la sexe
//                        parente = cell.getStringCellValue();
//                        if ("S".equals(parente)) {
//                            imageRisque = new OrclassImageRisque();
//                            imageRisque.setIdGroup(refGroupe);
//                            imageRisque.setMatricule(matricule);
//                            imageRisque.setLibelle(nom);
//                            if ("M".equals(sexe)) {
//                                imageRisque.setSexe(Sexe.M);
//                            } else {
//                                imageRisque.setSexe(Sexe.F);
//                            }
////                            imageRisque.setDateNaissance(IdleDate.parseString(date, "dd/MM/yyy"));
//                            imageRisque.setDateNaissance(dates);
//                            colImageRisque.add(imageRisque);
//                        } else {
//                            imageFamilleRisque = new OrclassImageFamilleRisque();
//                            imageFamilleRisque.setMatricule(matricule);
//                            imageFamilleRisque.setNom_membre(nom);
//                            if ("M".equals(sexe)) {
//                                imageFamilleRisque.setSexe(Sexe.M);
//                            } else {
//                                imageFamilleRisque.setSexe(Sexe.F);
//                            }
////                            imageFamilleRisque.setDateNaissance(IdleDate.parseString(date, "dd/MM/yyy"));
//                            imageFamilleRisque.setDateNaissance(dates);
//                            if ("C".equals(parente)) {
//                                imageFamilleRisque.setLienParente(LienParente.conjoint);
//                            } else if ("E".equals(parente)) {
//                                imageFamilleRisque.setLienParente(LienParente.enfant);
//                            }
//                            colImageFamilleRisque.add(imageFamilleRisque);
//
//                        }
//                        debutLigne++;
//                    }
//                    this.setListeImageFamilleRisque(colImageFamilleRisque);
//                    this.setListeImageRisque(colImageRisque);
//
//                } catch (Exception e) {
//                    System.out.println("debutLigne: " + debutLigne);
//
//                }
//                break;
//        }
//    }
//
//    public List<OrclassImageFamilleRisque> getListeImageFamilleRisque() {
//        return listeImageFamilleRisque;
//    }
//
//    public void setListeImageFamilleRisque(List<OrclassImageFamilleRisque> listeImageFamilleRisque) {
//        this.listeImageFamilleRisque = listeImageFamilleRisque;
//    }
//
//    public List<OrclassImageRisque> getListeImageRisque() {
//        return listeImageRisque;
//    }
//
//    public void setListeImageRisque(List<OrclassImageRisque> listeImageRisque) {
//        this.listeImageRisque = listeImageRisque;
//    }
//
//    @Override
//    public List<OrclassImageFamilleRisque> listeImageFamilleRisque() {
//        return listeImageFamilleRisque;
//    }
//
//    @Override
//    public List<OrclassImageRisque> listeImageRisque() {
//        return listeImageRisque;
//    }
//
//}
