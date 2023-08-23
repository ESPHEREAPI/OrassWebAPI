/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import controllers.ConnectionController;
import enums.TypeUtilisateur;

import java.util.HashMap;
import java.util.Map;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import modele.OrclassUtilisateurs;
import utils.GlobalFonctions;

@WebListener
public class MonPhaseListener implements PhaseListener, ServletRequestListener {

    private static final long serialVersionUID = 1L;
    int i = 0;
    @Inject
    ConnectionController connectionController;
    OrclassUtilisateurs utilisateurs;

    public MonPhaseListener() {
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        if (pe.getPhaseId() == PhaseId.RESTORE_VIEW) {
            System.out.println("Nouvelle requête en cours ...");
        }
        System.out.println("avant - " + pe.getPhaseId().toString());
 
    }

    @Override
    public void afterPhase(PhaseEvent pe) {

        //        System.out.println("après - " + pe.getPhaseId().toString());
        if (pe.getPhaseId() == PhaseId.RESTORE_VIEW) {
            System.out.println("Fin d’analyse de la requête.\n");

            FacesContext fc = pe.getFacesContext();
            String currentPage = fc.getViewRoot().getViewId();
            System.out.println(currentPage);
            //teste que c la page de login
            boolean loginPage = (currentPage.contains("connection.xhtml") || currentPage.contains("licence.xhtml") || currentPage.contains("createnewcompte.xhtml"));
            //teste si c'est une page xhtml
            boolean xhtmlPage = (currentPage.contains(".xhtml"));
//            ConnectionController connectController = null;
//             utilisateurs = null;
            //si c'est une page differente de login
            if (!loginPage && xhtmlPage) {
                boolean isLogged = false;
//                 int status=-1;
                int j = 0;
                utilisateurs = (OrclassUtilisateurs) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(GlobalFonctions.SESSION_USER);
                //je recupere la classe session de controller de connection
//                connectController = (ConnectionController) fc.getExternalContext().getSessionMap().get("connectionController");
//                if (connectController == null) {
//                    connectController = (ConnectionController) fc.getExternalContext().getApplicationMap().get("connectionController");
//                }
                NavigationHandler nh = fc.getApplication().getNavigationHandler();
                if (utilisateurs != null) {
                    System.out.println("Connection OK");
                    isLogged = utilisateurs.getConnecter();
                    int status = utilisateurs.getStatut();

//                    j = utilisateurs.getJ();
//                    utilisateurs.setJ(2);
                    switch (status) {
                        case ConnectionController.GO_TO_CONFIG:
                            //bloque la redirection multiple ici
//                            nh.handleNavigation(fc, null, "configuration");
                            break;
                        case ConnectionController.GO_TO_CONNECT:
                            nh.handleNavigation(fc, null, "client.deconnect");
                            break;
//                        case ConnectionController.GO_TO_LICENCE:
//                            nh.handleNavigation(fc, null, "license");
//                            break;
                        case ConnectionController.GO_TO_PAGE:
                            if (utilisateurs != null && utilisateurs.getIdUtilisateur() != null) {
                                if (utilisateurs.getTypeUtilisateur().equals(TypeUtilisateur.UTILISATEUR_ENTITE)) {
                                    if (utilisateurs.getStatut() == utilisateurs.getJ()) {
                                        this.checkSecurePage(currentPage, fc);

                                    }

                                }
                            }

//                            connectController.afficheLibellePage();
                            break;
                    }
                } else {
                    System.out.println("Connection NULL");
                    nh.handleNavigation(fc, null, "client.deconnect");
                }

            }
        }

    }

    public void checkSecurePage(String currentPage, FacesContext fc) {
        NavigationHandler nh = fc.getApplication().getNavigationHandler();
        //c'est bien mais verifions que c   le menu qui lui appartient
        String[] myDecomposePage = new String[2];
        myDecomposePage = currentPage.split("\\.");
        String chemin = myDecomposePage[0];
        Map<String, String> colMenu = (HashMap<String, String>) fc.getExternalContext().getSessionMap().get(GlobalFonctions.MENUS_USER);

        if (colMenu != null && !colMenu.isEmpty()) {
            String page = colMenu.get(chemin);
            System.out.println("ma page=" + page);

//            fc.getExternalContext().getRequestMap().put("page", page);
            //enlevons principal et pagerror /acceuil
            if (!chemin.equalsIgnoreCase("/notfound") && !chemin.equalsIgnoreCase("/createnewcompte") && !chemin.equalsIgnoreCase("/accueil") && !chemin.equalsIgnoreCase("/licence") && !chemin.equalsIgnoreCase("/administration/entreprise") && !chemin.equalsIgnoreCase("/principal") && !chemin.equalsIgnoreCase("/errorpage") && !chemin.equalsIgnoreCase("/administration/traduction") && !chemin.equalsIgnoreCase("/administration/template")) {
                if (colMenu.containsKey(chemin) == false) {
                    nh.handleNavigation(fc, null, "pageerror");
                } else {
                    fc.getExternalContext().getRequestMap().put("page", page);
                }
            }
            utilisateurs.setJ(0);
            fc.getExternalContext().getSessionMap().put(GlobalFonctions.SESSION_USER, utilisateurs);
        } else {
            System.out.println("Pas de Menus Operationelles");
            nh.handleNavigation(fc, null, "client.deconnect");
        }

    }

    public PhaseEvent getPPhaseEvent(PhaseEvent p) {
        return p;
    }

    @Override
    public PhaseId getPhaseId() {
        //je prend juste les vues finales
        return PhaseId.RESTORE_VIEW;
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre
    ) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre
    ) {
    }
}
