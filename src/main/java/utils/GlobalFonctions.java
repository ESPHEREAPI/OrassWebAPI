package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
//import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.XlsxReportConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sante.SousCaracteristique;

public class GlobalFonctions implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(GlobalFonctions.class);

    public static final String ETAB_ACTIF = "etabActif";

    public static final String SESSION_USER = "sessionuser";

    public static final String SESSION_CONFIG = "sessionconfig";

    public static final String ENTREPRISE_ACTIF = "entrepriseActif";

    public static final String CONNECTION_CONNTROLLER = "connectionController";

    public static final String PATH_TO_FILE = "path_to_file";

    public static final String LICENCE_STATUT = "licenceStatut";

    public static final String MOIS = "mois";

    public static final String TOUT = "tout";

    public static final String MOIS_DEJA_PAYE = "0";

    public static final int[] numeroJour = new int[]{
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
        21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
        31};

    public static final String MENUS_USER = "menususer";

    private static JRMapCollectionDataSource dataSource;

    private static String summary;

    private static String msgdetail;

    private static JasperPrint jasperPrint;

    private static JasperReport jasperReport;

    private static JRBeanCollectionDataSource beanCollectionDataSource;

    private static final String userBd = "root";

    private static final String pwd = "root";

    private static final String bd = "paie";

    public static final String salaire = "salaire";

    public static final char espace = ' ';

    public static final String separator = "/";

    public static final Double nombreJourByAnnee = Double.valueOf(365.0D);

    public static final String dossierBulletin = "C:\\Report";

    public static final String dossierCnps = "C:\\Report\\Cnps";

    private static final String motsNonDesiree = "vostfr-1280x720p-1280x720-720p-1080p-480p-x264-XVID-MP3-AAC-HDTV-ReEnc";

    private static final String CaracNonDesiree = "_-*/+;:!|,/?";

    private static final String currentFolder = "/photos";

    public static final String dossierPrincipal = "e-sphere";

    public static final String dossierContratSante = "sante/production/contrat";
    public static final String dossierContratAuto = "auto/production/contrat";
    public static final String dossierAvenant = "sante/avenant";

    public static final String dossierDefaut = "default";
    public static final String dossierDefauts = "defaults";

    public static final String discipline = "discipline";

    public static final String etude = "etude";

    public static final String examen = "examen";

    public static final String payement = "payement";

    public static final String scolarite = "scolarite";

    public static final String dossierSira = "sira";

    public static final String dossierBudget = "budget";

    private static final int DEFAULT_BUFFER_SIZE = 10240;
    private static List<SousCaracteristique> listscdp = new ArrayList<>();

    public static String replaceASpecialCharWithAscii(String stringOr, int nbre) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < stringOr.length(); i++) {
            char x = stringOr.charAt(i);
            int cast = x;
            int codePoint = String.valueOf(x).codePointAt(0);
            char again = (char) codePoint;
            if (codePoint != nbre) {
                str.append(x);
            }
        }
        return str.toString();
    }

    public static String valueObject(Object t) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();
        if (t != null) {
            return LocaleHelper.getLocaleString("langue.welcome", t.toString(), null, myLoc);
        }
        return "";
    }

    public static double getNumberNet(Double d) {
        System.out.println("valeur entree :" + d);
        BigDecimal pi = new BigDecimal(d.doubleValue());
        pi = pi.setScale(2, 4);
        System.out.println("valeur arroundir 2chiffres :" + pi);
        int i = pi.intValue();
        System.out.println("valeur entier :" + i);
        String valeur = pi.toString();
        System.out.println("valeur Stingr :" + valeur);
        System.out.println("valeur different :" + (d.doubleValue() - d.intValue()));
        String j = "" + i;
        int l = j.length();
        char c = valeur.charAt(l + 1);
        System.out.println("valeur apres lavirgule:" + c);
        String sc = "" + c;
        Long stringSc = new Long(sc);
        int chiffre = stringSc.intValue();
        double nombre = 0.0D;
        System.out.println("valeur exact :" + chiffre);
        if (chiffre < 5) {
            nombre = Math.floor(d.doubleValue());
        } else if (chiffre > 5 || chiffre == 5) {
            nombre = Math.ceil(d.doubleValue());
        }
        System.out.println("valeur exact :" + nombre);
        return nombre;
    }

    public static double valeuMillierInferieur(double valeur) {
        double nbre = valeur / 1000.0D;
        return Math.floor(nbre) * 1000.0D;
    }

    public static int getNumberNetForInt(Double d) {
        System.out.println("valeur entree :" + d);
        BigDecimal pi = new BigDecimal(d.doubleValue());
        pi = pi.setScale(2, 4);
        System.out.println("valeur arroundir 2chiffres :" + pi);
        int i = pi.intValue();
        System.out.println("valeur entier :" + i);
        String valeur = pi.toString();
        System.out.println("valeur Stingr :" + valeur);
        System.out.println("valeur different :" + (d.doubleValue() - d.intValue()));
        String j = "" + i;
        int l = j.length();
        char c = valeur.charAt(l + 1);
        System.out.println("valeur apres lavirgule:" + c);
        String sc = "" + c;
        Long stringSc = new Long(sc);
        int chiffre = stringSc.intValue();
        Double nombre = Double.valueOf(0.0D);
        System.out.println("valeur exact :" + chiffre);
        if (chiffre < 5) {
            nombre = Double.valueOf(Math.floor(d.doubleValue()));
        } else if (chiffre > 5 || chiffre == 5) {
            nombre = Double.valueOf(Math.ceil(d.doubleValue()));
        }
        System.out.println("valeur exact :" + nombre);
        return nombre.intValue();
    }

    public static double nombreAnciennete(Date recrutement) {
        Date today = new Date();
        long differrent = today.getTime() - recrutement.getTime();
        double diff;
        return diff = (differrent / 86400000L);
    }

    public static double nombreAnciennete(Date recrutement, Date system) {
        Date today = system;
        long differrent = today.getTime() - recrutement.getTime();
        double diff;
        return diff = (differrent / 86400000L);
    }

    public static double getDoubleAfterVirgule(Double d) {
        int i = d.intValue();
        String valeur = d.toString();
        String j = "" + i;
        int l = j.length();
        char c = valeur.charAt(l + 1);
        char c1 = '0';
        if (d.toString().length() > l + 2) {
            c1 = valeur.charAt(l + 2);
        }
        String sc = "." + c + "" + c1;
        j = j + sc;
        Double nombre = new Double(j);
        System.out.println("valeur exact :" + nombre);
        return nombre.doubleValue();
    }

    public static void displayMessages(String idComponent, FacesContext ctx, FacesMessage.Severity typeSeverity, String nameClass, String codeMessage, Object[] parameters) {
        Locale myLoc = ctx.getViewRoot().getLocale();
        String[] entete = {LocaleHelper.getLocaleString("langue.welcome", nameClass, null, myLoc)};
        String[] detail = {entete[0], codeMessage};
        summary = LocaleHelper.getLocaleString("langue.welcome", codeMessage, parameters, myLoc);
        msgdetail = LocaleHelper.getLocaleString("langue.welcome", codeMessage, (Object[]) detail, myLoc);
        ctx.addMessage(idComponent, new FacesMessage(typeSeverity, summary, msgdetail));
    }

    public static String formatNumberGeneral(long number) {
        DecimalFormat format = new DecimalFormat();
        DecimalFormatSymbols s = format.getDecimalFormatSymbols();
        s.setGroupingSeparator(' ');
        format.setDecimalFormatSymbols(s);
        return format.format(number);
    }

    public static String formatDate(Date d) {
        if (d != null) {
            int jour = IdleDate.getDayMonth(d);
            int mois = IdleDate.getMonth(d);
            int annee = IdleDate.getYear(d);
            String jr = "" + jour;
            String m = "" + mois;
            if (jr.length() == 1) {
                jr = "0" + jour;
            }
            if (m.length() == 1) {
                m = "0" + mois;
            }
            return "" + jr + "/" + m + "/" + annee;
        }
        return null;
    }

    public static void addSuccessMessages(String codeMessage, Object[] parameters) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();
        summary = LocaleHelper.getLocaleString("langue.welcome", codeMessage, parameters, myLoc);
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));
    }

    public static void addErrorMessages(String codeMessage, Object[] parameters) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale myLoc = ctx.getViewRoot().getLocale();
        summary = LocaleHelper.getLocaleString("langue.welcome", codeMessage, parameters, myLoc);
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null));
    }

    public static void print(String fileName, Collection<?> data, Map<String, Object> parameters) throws IOException, JRException {
        ServletOutputStream servletOutputStream = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        Locale myLoc = fc.getViewRoot().getLocale();
        beanCollectionDataSource = new JRBeanCollectionDataSource(data);
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            String reportLocation = fc.getExternalContext().getRealPath("WEB-INF");
            String pathFile = reportLocation + "/Report/" + fileName;
            String reportPathjrxml = pathFile + ".jrxml";
            String reportPathjasper = pathFile + ".jasper";
            File f = new File(reportPathjasper);
            if (!f.exists()) {
                JasperCompileManager.compileReportToFile(reportPathjrxml, reportPathjasper);
            }
            FileInputStream fis = new FileInputStream(reportPathjasper);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            String filePath = reportLocation + "/Report/bundle/i18nReport_" + myLoc + ".properties";
            FileInputStream fis1 = new FileInputStream(filePath);
            ResourceBundle bundle = new PropertyResourceBundle(fis1);
            parameters.put("REPORT_RESOURCE_BUNDLE", bundle);
            parameters.put("REPORT_LOCALE", myLoc);
            jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, (JRDataSource) beanCollectionDataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            response.setContentLength(baos.size());
            baos.writeTo((OutputStream) servletOutputStream);
            fis.close();
            bufferedInputStream.close();
        } finally {
            servletOutputStream.flush();
            servletOutputStream.close();
            baos.close();
            fc.responseComplete();
        }
    }

    public static void printWithSql(String fileName, Map<String, Object> parameters) throws IOException, JRException {
        ServletOutputStream servletOutputStream = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        Locale myLoc = fc.getViewRoot().getLocale();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType("application/pdf");
        servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            String reportLocation = fc.getExternalContext().getRealPath("WEB-INF");
            String pathFile = reportLocation + "/Report/" + fileName;
            String reportPathjrxml = pathFile + ".jrxml";
            String reportPathjasper = pathFile + ".jasper";
            File f = new File(reportPathjasper);
            if (!f.exists()) {
                JasperCompileManager.compileReportToFile(reportPathjrxml, reportPathjasper);
            }
            FileInputStream fis = new FileInputStream(reportPathjasper);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            String filePath = reportLocation + "/Report/bundle/i18nReport_" + myLoc + ".properties";
            FileInputStream fis1 = new FileInputStream(filePath);
            ResourceBundle bundle = new PropertyResourceBundle(fis1);
            parameters.put("REPORT_RESOURCE_BUNDLE", bundle);
            parameters.put("REPORT_LOCALE", myLoc);
            jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            response.setContentLength(baos.size());
            baos.writeTo((OutputStream) servletOutputStream);
            fis.close();
            bufferedInputStream.close();
        } finally {
            servletOutputStream.flush();
            servletOutputStream.close();
            baos.close();
            fc.responseComplete();
        }
    }

    public static void printPdf(String fileName, Collection<?> data, Map<String, Object> parameters, String langue) throws IOException, JRException {
        Locale myLoc;
        ServletOutputStream servletOutputStream = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        if (langue == null) {
            myLoc = fc.getViewRoot().getLocale();
        } else {
            myLoc = new Locale(langue);
        }
        try {
            beanCollectionDataSource = new JRBeanCollectionDataSource(data);
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            servletOutputStream = response.getOutputStream();
            String reportLocation = fc.getExternalContext().getRealPath("WEB-INF");
            String pathFile = reportLocation + "/Report/" + fileName;
            String reportPathjasper = pathFile + ".jasper";
            String filePath = reportLocation + "/Report/bundle/i18nReport_" + myLoc + ".properties";
            FileInputStream fis1 = new FileInputStream(filePath);
            ResourceBundle bundle = new PropertyResourceBundle(fis1);
            parameters.put("REPORT_RESOURCE_BUNDLE", bundle);
            parameters.put("REPORT_LOCALE", myLoc);

            jasperPrint = JasperFillManager.fillReport(reportPathjasper, parameters, (JRDataSource) beanCollectionDataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, (OutputStream) servletOutputStream);
        } finally {
            fc.responseComplete();
        }
    }

    /*
    impression etat avec sous raport
     */
    public static void printPdfHaveSubReport(String fileName, Collection<?> data, Map<String, Object> parameters, String langue) throws IOException, JRException {
        Locale myLoc;
        ServletOutputStream servletOutputStream = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        if (langue == null) {
            myLoc = fc.getViewRoot().getLocale();
        } else {
            myLoc = new Locale(langue);
        }
        try {
            beanCollectionDataSource = new JRBeanCollectionDataSource(data);
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            servletOutputStream = response.getOutputStream();
            String reportLocation = fc.getExternalContext().getRealPath("WEB-INF");
            String pathFile = reportLocation + "/Report/" + fileName;
            String reportPathjasper = pathFile + ".jasper";
            String filePath = reportLocation + "/Report/bundle/i18nReport_" + myLoc + ".properties";
            String filePathSubReport = reportLocation + "/Report/" + dossierPrincipal + "/" + dossierContratSante + "/" + dossierDefaut + "/";
            FileInputStream fis1 = new FileInputStream(filePath);
            ResourceBundle bundle = new PropertyResourceBundle(fis1);
            Map<String, Object> map_subreport_Garantie = new HashMap<>();
            map_subreport_Garantie.put("path_file", filePathSubReport);
            map_subreport_Garantie.put("scpd", listscdp);
            parameters.put("REPORT_RESOURCE_BUNDLE", bundle);
            parameters.put("REPORT_LOCALE", myLoc);
            parameters.put("SUBREPORT_DIR", filePathSubReport);// source is path your subreport 
            parameters.put("path", map_subreport_Garantie);

            jasperPrint = JasperFillManager.fillReport(reportPathjasper, parameters, (JRDataSource) beanCollectionDataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, (OutputStream) servletOutputStream);
        } finally {
            fc.responseComplete();
        }
    }

    public static void retourneListe(List<SousCaracteristique> list) {
        listscdp = list;
    }

    public static void printPdfHaveSubReportAuto(String fileName, Collection<?> data, Map<String, Object> parameters, String langue) throws IOException, JRException {
        Locale myLoc;
        ServletOutputStream servletOutputStream = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        if (langue == null) {
            myLoc = fc.getViewRoot().getLocale();
        } else {
            myLoc = new Locale(langue);
        }
        try {
            beanCollectionDataSource = new JRBeanCollectionDataSource(data);
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            servletOutputStream = response.getOutputStream();
            String reportLocation = fc.getExternalContext().getRealPath("WEB-INF");
            String pathFile = reportLocation + "/Report/" + fileName;
            String reportPathjasper = pathFile + ".jasper";
            String filePath = reportLocation + "/Report/bundle/i18nReport_" + myLoc + ".properties";
            String filePathSubReport = reportLocation + "/Report/" + dossierPrincipal + "/" + dossierContratAuto + "/" + dossierDefaut + "/";
            FileInputStream fis1 = new FileInputStream(filePath);
            ResourceBundle bundle = new PropertyResourceBundle(fis1);
            parameters.put("REPORT_RESOURCE_BUNDLE", bundle);
            parameters.put("REPORT_LOCALE", myLoc);
            parameters.put("SUBREPORT_DIR", filePathSubReport);// source is path your subreport 
            jasperPrint = JasperFillManager.fillReport(reportPathjasper, parameters, (JRDataSource) beanCollectionDataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, (OutputStream) servletOutputStream);
        } finally {
            fc.responseComplete();
        }
    }

    public static void printDipe(String fileName, Collection<?> data, Map<String, Object> parameters) throws IOException, JRException {
        ServletOutputStream servletOutputStream = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        Locale myLoc = fc.getViewRoot().getLocale();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            beanCollectionDataSource = new JRBeanCollectionDataSource(data);
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            servletOutputStream = response.getOutputStream();
            response.setContentType("plain/text");
            String reportLocation = fc.getExternalContext().getRealPath("WEB-INF");
            String pathFile = reportLocation + "/Report/" + fileName;
            String reportPathjrxml = pathFile + ".jrxml";
            String reportPathjasper = pathFile + ".jasper";
            File f = new File(reportPathjasper);
            if (!f.exists()) {
                JasperCompileManager.compileReportToFile(reportPathjrxml, reportPathjasper);
            }
            String filePath = reportLocation + "/Report/bundle/i18nReport_" + myLoc + ".properties";
            FileInputStream fis1 = new FileInputStream(filePath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(reportPathjasper));
            ResourceBundle bundle = new PropertyResourceBundle(fis1);
            parameters.put("REPORT_RESOURCE_BUNDLE", bundle);
            parameters.put("REPORT_LOCALE", myLoc);
            jasperPrint = JasperFillManager.fillReport(reportPathjasper, parameters, (JRDataSource) beanCollectionDataSource);
            JRTextExporter exporter = new JRTextExporter();
            exporter.setParameter(JRTextExporterParameter.CHARACTER_ENCODING, "UTF-8");
            exporter.setParameter((JRExporterParameter) JRTextExporterParameter.CHARACTER_HEIGHT, Float.valueOf(5.0F));
            exporter.setParameter((JRExporterParameter) JRTextExporterParameter.CHARACTER_WIDTH, Float.valueOf(3.0F));
            exporter.setParameter(JRTextExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
            exporter.setParameter(JRTextExporterParameter.OUTPUT_FILE_NAME, "C:\\A.txt");
            exporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM, servletOutputStream);
            exporter.setParameter(JRTextExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter((JRExporterParameter) JRTextExporterParameter.LINE_SEPARATOR, "\r\n");
            exporter.exportReport();
            fis1.close();
            bufferedInputStream.close();
        } finally {
            servletOutputStream.flush();
            servletOutputStream.close();
            fc.responseComplete();
        }
    }

    public static void downloadFile(File file) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        String type = facesContext.getExternalContext().getMimeType(file.getName());
        if (type == null) {
            type = "application/octet-stream";
        }
        response.setBufferSize(10240);
        response.setContentType(type);
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        response.setContentLength((int) file.length());
        FileInputStream input = null;
        try {
            int i = 0;
            input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            while ((i = input.read(buffer)) != -1) {
                response.getOutputStream().write(buffer);
                response.getOutputStream().flush();
            }
            facesContext.responseComplete();
            facesContext.renderResponse();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printTicket(String fileName, Collection<?> data, Map<String, Object> parameters) throws IOException, JRException {
        ServletOutputStream servletOutputStream = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        Locale myLoc = fc.getViewRoot().getLocale();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            beanCollectionDataSource = new JRBeanCollectionDataSource(data);
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            servletOutputStream = response.getOutputStream();
            response.setContentType("plain/text");
            String reportLocation = fc.getExternalContext().getRealPath("WEB-INF");
            String pathFile = reportLocation + "/Report/" + fileName;
            String reportPathjrxml = pathFile + ".jrxml";
            String reportPathjasper = pathFile + ".jasper";
            File f = new File(reportPathjasper);
            if (!f.exists()) {
                JasperCompileManager.compileReportToFile(reportPathjrxml, reportPathjasper);
            }
            String filePath = reportLocation + "/Report/bundle/i18nReport_" + myLoc + ".properties";
            FileInputStream fis1 = new FileInputStream(filePath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(reportPathjasper));
            ResourceBundle bundle = new PropertyResourceBundle(fis1);
            parameters.put("REPORT_RESOURCE_BUNDLE", bundle);
            parameters.put("REPORT_LOCALE", myLoc);
            jasperPrint = JasperFillManager.fillReport(reportPathjasper, parameters, (JRDataSource) beanCollectionDataSource);
            JRTextExporter exporter = new JRTextExporter();
            exporter.setParameter(JRTextExporterParameter.CHARACTER_ENCODING, "UTF-8");
            exporter.setParameter((JRExporterParameter) JRTextExporterParameter.CHARACTER_HEIGHT, Float.valueOf(5.0F));
            exporter.setParameter((JRExporterParameter) JRTextExporterParameter.CHARACTER_WIDTH, Float.valueOf(3.0F));
            exporter.setParameter(JRTextExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
            exporter.setParameter(JRTextExporterParameter.OUTPUT_FILE_NAME, "C:\\A.txt");
            exporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM, servletOutputStream);
            exporter.setParameter(JRTextExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter((JRExporterParameter) JRTextExporterParameter.LINE_SEPARATOR, "\r\n");
            exporter.exportReport();
            fis1.close();
            bufferedInputStream.close();
        } finally {
            servletOutputStream.flush();
            servletOutputStream.close();
            fc.responseComplete();
        }
    }

    //**exportationsur format excelle
    public static void printExcell(String fileName, Collection<?> data, Map<String, Object> parameters, String nameFileExcelle) throws IOException, JRException {

        ServletOutputStream servletOutputStream = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        Locale myLoc = fc.getViewRoot().getLocale();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            beanCollectionDataSource = new JRBeanCollectionDataSource(data);
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            servletOutputStream = response.getOutputStream();
//            response.setContentType("plain/text");
            String reportLocation = fc.getExternalContext().getRealPath("WEB-INF");
            String pathFile = reportLocation + "/Report/" + fileName;
            String reportPathjrxml = pathFile + ".jrxml";
            String reportPathjasper = pathFile + ".jasper";
            File f = new File(reportPathjasper);
            if (!f.exists()) {
                JasperCompileManager.compileReportToFile(reportPathjrxml, reportPathjasper);
            }
            String filePath = reportLocation + "/Report/bundle/i18nReport_" + myLoc + ".properties";
            FileInputStream fis1 = new FileInputStream(filePath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(reportPathjasper));
            ResourceBundle bundle = new PropertyResourceBundle(fis1);
            parameters.put("REPORT_RESOURCE_BUNDLE", bundle);
            parameters.put("REPORT_LOCALE", myLoc);
            jasperPrint = JasperFillManager.fillReport(reportPathjasper, parameters, (JRDataSource) beanCollectionDataSource);
//            System.out.println("chemin de sauvegarde ..." + path_to_fil.getPath());
//            JasperExportManager.exportReportToPdfFile(jasperPrint, path_to_fil.getPath());
            generateReportExcel(jasperPrint, nameFileExcelle, response);

        } finally {
            servletOutputStream.flush();
            servletOutputStream.close();
            fc.responseComplete();

        }
    }

    //java method to export to excel file
    public static void generateReportExcel(JasperPrint jasperPrint, String fileName, HttpServletResponse response) throws FileNotFoundException, IOException, JRException {

        ServletOutputStream outputStream = null;
        response.setHeader("Content-Disposition", "attachment;filename" + fileName + ".xlsx");
        response.setContentType("application/octet-stream");
        response.setContentLength(4096);
        outputStream = response.getOutputStream();
        //creation chemin de sauvegarde
        File path_to_fil;
        String path_to_file_save = "C:\\reporte_jasper";
        path_to_fil = new File("C:\\reporte_jasper");
        if (path_to_fil.exists() == false) {
            path_to_fil.mkdirs();

        }
        path_to_file_save += "\\" + fileName + "" + new Date().getTime() + ".xlsx";

//        XlsxReportConfiguration configuration = new 
//        configuration.setOnePagePerSheet(true);
//        configuration.setDetectCellType(true); // Detect cell types (date and etc.)
//        configuration.setWhitePageBackground(false); // No white background!
//        configuration.setFontSizeFixEnabled(false);
//
//        // No spaces between rows and columns 
//        configuration.setRemoveEmptySpaceBetweenRows(true);
//        configuration.setRemoveEmptySpaceBetweenColumns(true);
        // If you want to name sheets then uncomment this line
        // configuration.setSheetNames(new String[] { "Data" });
//         JRXlsExporter exporter = new JRXlsExporter();
        JRXlsxExporter exporter = new JRXlsxExporter();
//        exporter.setConfiguration(configuration);
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

//        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
//        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName + ".xlsx");
        try ( ByteArrayOutputStream excelStream = new ByteArrayOutputStream();  FileOutputStream fos = new FileOutputStream("" + path_to_file_save)) {
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(excelStream);
            exporter.setExporterOutput(exporterOutput);
            exporter.exportReport();

            excelStream.writeTo(fos);
            downloadFile(new File(path_to_file_save));
        }
    }

    public static void printByMapDatasource(String fileName, Collection<Map<String, ?>> reportRows, Map<String, Object> parameters) throws IOException, JRException {
        ServletOutputStream servletOutputStream = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        Locale myLoc = fc.getViewRoot().getLocale();
        try {
            dataSource = new JRMapCollectionDataSource(reportRows);
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            servletOutputStream = response.getOutputStream();
            String reportLocation = fc.getExternalContext().getRealPath("WEB-INF");
            String pathFile = reportLocation + "/Report/" + fileName;
            String reportPathjasper = pathFile + ".jasper";
            jasperPrint = JasperFillManager.fillReport(reportPathjasper, parameters, (JRDataSource) dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, (OutputStream) servletOutputStream);
        } finally {
            servletOutputStream.flush();
            servletOutputStream.close();
            fc.responseComplete();
        }
    }

    public static String createImageByLibellePhotos(String libelle, byte[] image) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extContext = ctx.getExternalContext();
        String imagePath = extContext.getRealPath("") + libelle;
        File dossiers = null;
        try {
            String nameFile = libelle.split("/")[2];
            String ext = "";
            if (nameFile.contains(".jpg") || nameFile.contains(".jpeg")) {
                ext = "jpg";
            } else if (nameFile.contains(".png")) {
                ext = "png";
            } else if (nameFile.contains(".gif")) {
                ext = "gif";
            }
            ByteArrayInputStream bis = new ByteArrayInputStream(image);
            Iterator<?> readers = ImageIO.getImageReadersByFormatName(ext);
            ImageReader reader = (ImageReader) readers.next();
            Object source = bis;
            ImageInputStream iis = ImageIO.createImageInputStream(source);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Image images = reader.read(0, param);
            BufferedImage bufferedImage = new BufferedImage(images.getWidth(null), images.getHeight(null), 1);
            Graphics2D g2 = bufferedImage.createGraphics();
            g2.drawImage(images, null, null);
            dossiers = new File(imagePath);
            ImageIO.write(bufferedImage, "jpg", dossiers);
        } catch (IOException e) {
            logger.error("Erreur de creation  d image dans le dossier " + dossiers);
            System.err.println("Erreur de creation  d image dans le dossier " + dossiers);
        }
        return libelle;
    }

    public static File retourneDossierPaie(String dossier) {
        File file = new File(dossier);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static String supprimerDoubleEspaces(String NouveauNom) {
        int i = 0;
        while (true) {
            i = NouveauNom.indexOf("  ");
            NouveauNom = NouveauNom.replaceAll("  ", " ");
            if (i == -1) {
                return NouveauNom;
            }
        }
    }

    public static String supprimerEspacesForVide(String NouveauNom) {
        int i = 0;
        while (true) {
            i = NouveauNom.indexOf(" ");
            NouveauNom = NouveauNom.replaceAll(" ", "");
            if (i == -1) {
                return NouveauNom;
            }
        }
    }

    public static String supprimerEspaces(String NouveauNom) {
        int i = 0;
        while (true) {
            i = NouveauNom.indexOf(" ");
            NouveauNom = NouveauNom.replaceAll(" ", "_");
            if (i == -1) {
                return NouveauNom;
            }
        }
    }

    public static String supprimerMotsNonDesiree(String NouveauNom) {
        String[] buffer = "vostfr-1280x720p-1280x720-720p-1080p-480p-x264-XVID-MP3-AAC-HDTV-ReEnc".split("-");
        for (String v : buffer) {
            NouveauNom = NouveauNom.replace(v, " ");
        }
        return NouveauNom;
    }

    public static String supprimerMotsNonDesiree2(String NouveauNom) {
        int i = 0, j = 0;
        String lowerMots = "vostfr-1280x720p-1280x720-720p-1080p-480p-x264-XVID-MP3-AAC-HDTV-ReEnc".toLowerCase();
        String[] buffer = lowerMots.split("-");
        String lowerNom = NouveauNom.toLowerCase();
        for (String v : buffer) {
            if (lowerNom.contains(v)) {
                do {
                    i = lowerNom.indexOf(v);
                    if (i == -1) {
                        continue;
                    }
                    if (i != 0) {
                        NouveauNom = "" + NouveauNom.substring(0, i) + "" + NouveauNom.substring(i + v.length(), NouveauNom.length());
                        lowerNom = "" + lowerNom.substring(0, i) + "" + lowerNom.substring(i + v.length(), lowerNom.length());
                    } else {
                        NouveauNom = "" + NouveauNom.substring(i + v.length(), NouveauNom.length());
                        lowerNom = "" + lowerNom.substring(i + v.length(), lowerNom.length());
                    }
                } while (i != -1);
            }
        }
        return NouveauNom;
    }

    public static String supprimerCaractereNonDesiree(String NouveauNom) {
        for (int i = 0; i < "_-*/+;:!|,/?".length(); i++) {
            NouveauNom = NouveauNom.replace("" + "_-*/+;:!|,/?".charAt(i), "");
        }
        return NouveauNom;
    }

    public static String supprimerCaractereNonDesireeParEspace(String NouveauNom) {
        for (int i = 0; i < "_-*/+;:!|,/?".length(); i++) {
            NouveauNom = NouveauNom.replace("" + "_-*/+;:!|,/?".charAt(i), " ");
        }
        return NouveauNom;
    }

    public static String supprimerCaractereEntreCrochet(String NouveauNom) {
        NouveauNom = supprimerCaractereEntreCrochet(NouveauNom, '[', ']');
        NouveauNom = supprimerCaractereEntreCrochet(NouveauNom, '(', ')');
        return supprimerCaractereEntreCrochet(NouveauNom, '{', '}');
    }

    public static String supprimerCaractereEntreCrochet(String NouveauNom, char avant, char apres) {
        int i;
        int j;
        do {
            i = NouveauNom.indexOf(avant);
            j = NouveauNom.indexOf(apres);
            if (i != -1 && j != -1 && i < j) {
                if (i != 0) {
                    NouveauNom = "" + NouveauNom.substring(0, i) + "" + NouveauNom.substring(j + 1);
                } else {
                    NouveauNom = NouveauNom.substring(j + 1);
                }
            } else if (i != -1 && j != -1 && i > j) {
                String temp = "";
                for (int o = 0; o < NouveauNom.length(); o++) {
                    if (o == j) {
                        temp = temp + " ";
                    } else {
                        temp = temp + "" + NouveauNom.charAt(o);
                    }
                }
                NouveauNom = temp;
            }
        } while (i != -1 || j != -1);
        return NouveauNom;
    }

    public static String supprimerPointGardeExtension(String NouveauNom) {
        int i = -1;
        i = NouveauNom.lastIndexOf(".");
        if (i != -1) {
            String temp = NouveauNom.substring(i);
            NouveauNom = "" + supprimerPoint(NouveauNom.substring(0, i)) + "" + temp;
        }
        return NouveauNom;
    }

    public static String supprimerPoint(String NouveauNom) {
        return NouveauNom.replace(".", " ");
    }

    public static String supprimerEspacesPremier(String NouveauNom) {
        if (NouveauNom.charAt(0) == ' ') {
            NouveauNom = NouveauNom.substring(1);
        }
        return NouveauNom;
    }

    public static String supprimerEspacesDernier(String NouveauNom) {
        int i = NouveauNom.lastIndexOf(".");
        if (i != -1 && i != 0
                && NouveauNom.charAt(i - 1) == ' ') {
            NouveauNom = "" + NouveauNom.substring(0, i - 1) + "" + NouveauNom.substring(i, NouveauNom.length());
        }
        return NouveauNom;
    }

    public static String intvaleurExact(String valeur) {
        if (valeur.contains(".")) {
            String[] t = valeur.split("\\.");
            return t[0];
        }
        return valeur;
    }

    public static int tailleEnregistrement(int taille) {
        String nbre = "" + taille;
        int v = nbre.length();
        switch (taille) {
            case 9:
                v = nbre.length() + 1;
                break;
            case 99:
                v = nbre.length() + 2;
                break;
            case 999:
                v = nbre.length() + 3;
                break;
            case 9999:
                v = nbre.length() + 4;
                break;
            case 99999:
                v = nbre.length() + 5;
                break;
        }
        return v;
    }

    public static void openFilTxt(String nameFile, String pathFile) {
        ServletOutputStream servletOutputStream = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        response.setContentType("plain/text");
        response.setHeader("" + pathFile, "attachment; filename=" + nameFile);
    }

    public static String formatDayAndMonth(int valeur) {
        switch (valeur) {
            case 1:
                return "01";
            case 2:
                return "02";
            case 3:
                return "03";
            case 4:
                return "04";
            case 5:
                return "05";
            case 6:
                return "06";
            case 7:
                return "07";
            case 8:
                return "08";
            case 9:
                return "09";
        }
        return "" + valeur;
    }
}
