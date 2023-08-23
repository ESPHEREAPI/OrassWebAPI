/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Licence;

import java.io.File;
import javax.swing.filechooser.FileFilter;
/**
 * This is purely for usability on the UI end.  
 * We can provide the JFileChooser this filter and it will find only our license files.
 * @author fabrice
 */
public class LicenseFileFilter extends FileFilter {
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(FileExtension.ZIP);
    }

    public String getDescription() {
        return "License files";
    }
}