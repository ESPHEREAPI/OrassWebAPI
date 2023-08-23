/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sante;

import java.math.BigInteger;

/**
 *
 * @author JIATOU FRANCK
 */
public class CaracterisqueGroupeMaladie {
    private int nombre_membres=0;
    private int  nombre_adherent=0;
    private int nombre_adherent_membre=0;
    private BigInteger prime_base_adherent=BigInteger.ZERO;

    public CaracterisqueGroupeMaladie() {
    }

    public int getNombre_membres() {
        return nombre_membres;
    }

    public void setNombre_membres(int nombre_membres) {
        this.nombre_membres = nombre_membres;
    }

    public int getNombre_adherent() {
        return nombre_adherent;
    }

    public void setNombre_adherent(int nombre_adherent) {
        this.nombre_adherent = nombre_adherent;
    }

    public int getNombre_adherent_membre() {
        return nombre_adherent_membre;
    }

    public void setNombre_adherent_membre(int nombre_adherent_membre) {
        this.nombre_adherent_membre = nombre_adherent_membre;
    }

    public BigInteger getPrime_base_adherent() {
        return prime_base_adherent;
    }

    public void setPrime_base_adherent(BigInteger prime_base_adherent) {
        this.prime_base_adherent = prime_base_adherent;
    }
    
}
