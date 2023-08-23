package Entreprise;

import java.util.Date;
import java.util.List;
import modele.OrclassEntreprises;
import modele.OrclassModules;
import modele.OrclassUtilisateurs;
import modele.Societe;

public interface IEntreprise {
  Societe addEntreprise(List<OrclassModules> paramList, Societe societe, Date paramDate1, Date paramDate2, OrclassUtilisateurs paramOrclassUtilisateurs);
}
