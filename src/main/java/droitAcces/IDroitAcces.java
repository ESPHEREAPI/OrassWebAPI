package droitAcces;

import javax.ejb.Local;
import modele.OrclassActions;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassModules;
import modele.OrclassUtilisateurs;

@Local
public interface IDroitAcces {
  Boolean accesToModuleAdministration(OrclassUtilisateurs paramOrclassUtilisateurs);
  
  Boolean accesToModuleAssureIrd(OrclassUtilisateurs paramOrclassUtilisateurs);
  
  Boolean accesToModuleTransport(OrclassUtilisateurs paramOrclassUtilisateurs);
  
  Boolean accesToModuleCautionCredit(OrclassUtilisateurs paramOrclassUtilisateurs);
  
  Boolean accesToModuleAgriclote(OrclassUtilisateurs paramOrclassUtilisateurs);
  
  Boolean accesToModuleAssureAdp(OrclassUtilisateurs paramOrclassUtilisateurs);
  
  Boolean accesToModuleGestionAssurer(OrclassUtilisateurs paramOrclassUtilisateurs);
  
  Boolean accesComptabiliteGeneral(OrclassUtilisateurs paramOrclassUtilisateurs);
  
  Boolean accessModule(OrclassUtilisateurs paramOrclassUtilisateurs, String paramString);
  
  Boolean accesAssuranceAuto(OrclassUtilisateurs paramOrclassUtilisateurs);
  
  Boolean accessMenuByAdministration(OrclassUtilisateurs paramOrclassUtilisateurs, String paramString);
  
  Boolean accessAction(OrclassUtilisateurs paramOrclassUtilisateurs, OrclassActions paramOrclassActions, OrclassMenus paramOrclassMenus);
  
  OrclassMenus getMenuByCode(String paramString);
  
  OrclassModules getModuleByCode(String paramString);
  
  OrclassFonctionnalites getFonctionnaliteByCode(String paramString);
  
  OrclassActions getActionByMenuAccess(OrclassFonctionnalites paramOrclassFonctionnalites, OrclassMenus paramOrclassMenus, OrclassModules paramOrclassModules);
  
  OrclassActions getActionByCodeByFonctionnalit(String paramString, OrclassFonctionnalites paramOrclassFonctionnalites);
}
