///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package test_file;
//
//import enums.LienParente;
//import enums.Sexe;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.faces.context.FacesContext;
//import modele.OrclassImageFamilleRisque;
//import modele.OrclassImageRisque;
//import modele.OrclassProfession;
//import modele.OrclasseRefGroupe;
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
// * @author JIATOU FRANCK
// */
//public class FonctionChargeFile {
//
//    public static void chargeFileExcellProduction(InputStream file, String fileName) throws IOException {
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
//        String matricule = null, nom, sexe, date, parente;
//        Date dates = null;
//        OrclassImageFamilleRisque imageFamilleRisque = null;
//        OrclassImageRisque imageRisque = null;
//         FormulaEvaluator formulaEvaluator = null;
//
//
//        switch (ext) {
//            case "xls":
//                HSSFWorkbook workbook = null;
//                workbook = new HSSFWorkbook(file);
//                try {
//                    sheet = workbook.getSheetAt(indiceClasseur);
//                    formulaEvaluator=workbook.getCreationHelper().createFormulaEvaluator();
//                    int lastRow = sheet.getLastRowNum()+1;
//                    System.out.println("lastRow: " + lastRow);
//                    while (debutLigne < lastRow) {
//                        row = sheet.getRow(debutLigne);
//                        cell = row.getCell(1);
//                        // recuperation du matricule 
//                        switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
//                            case Cell.CELL_TYPE_NUMERIC:
//                                 matricule = ""+cell.getNumericCellValue();
//                                System.out.print(cell.getNumericCellValue() + "\t\t");
//                                break;
//                            case Cell.CELL_TYPE_STRING:
//                                 matricule = cell.getStringCellValue();
//                                System.out.print(cell.getStringCellValue() + "\t");
//                                break;
//                            case Cell.CELL_TYPE_BLANK:
//                                matricule="";
//                        }
//                       
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
////                            imageRisque.setIdGroup(refGroupe);
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
////                    this.setListeImageFamilleRisque(colImageFamilleRisque);
//                    for (OrclassImageFamilleRisque fr : colImageFamilleRisque) {
//                        System.out.println("membre: " + fr.getNom_membre());
//                    }
//                    for (OrclassImageRisque a : colImageRisque) {
//                        System.out.println("assure: " + a.getLibelle());
//                    }
//                     System.out.println("lastRow: " + lastRow);
//                      System.out.println("debutLigne: " + debutLigne);
////                    this.setListeImageRisque(colImageRisque);
//
//                } catch (Exception e) {
//                    System.out.println("Une erreur : " + debutLigne);
//                    System.out.println("debutLigne: " + debutLigne);
//
//                }
//                break;
//        }
//    }
//
//    public static List<OrclassImageRisque> readFile(File file) {
//
//        List<OrclassImageRisque> result = new ArrayList<>();
//        FileReader fr;
//        BufferedReader br = null;
//        try {
//            fr = new FileReader(file);
//            br = new BufferedReader(fr);
//            int i = 1;
//            for (String line = br.readLine(); line != null; line = br.readLine()) {
//                System.out.println(" valeur " + line);
//                System.out.println(" valeur i" + i++);
////                result.add(""+line);
//            }
//            br.close();
//            fr.close();
//        } catch (Exception e) {
//
//        }
//
//        return result;
//    }
//
//}
