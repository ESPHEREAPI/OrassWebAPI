package droitAcces;

import dao.AbstractJpaDAO;
import dao.OrclassFonctionnalitesDao;
import dao.OrclassMenusDao;
import dao.OrclassModulesDao;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import modele.OrclassActions;
import modele.OrclassFonctionnalites;
import modele.OrclassMenus;
import modele.OrclassModules;
import modele.OrclassUtilisateurs;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServiceDroitAcces implements IDroitAcces {

    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;

    @Resource
    UserTransaction tx;

    @EJB
    OrclassMenusDao menusDao;

    @EJB
    OrclassModulesDao modulesDao;

    @EJB
    OrclassFonctionnalitesDao fonctionnalitesDao;

    @Override
    public Boolean accesToModuleAdministration(OrclassUtilisateurs u) {
        Query q = this.em.createQuery("SELECT m FROM OrclassModules m JOIN m.orclassEntreprisesModulesList me WHERE me.orclassModules.code= :code").setParameter("code", "mod.admin");
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        if (!q.getResultList().isEmpty()) {
            Query q1 = this.em.createQuery("SELECT DISTINCT m FROM OrclassModules m JOIN m.orclassUtilisateursAccesList ua where ua.idModule.code = :code and ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_principal=true or ua.autorisation_principal=1)").setParameter("code", "mod.admin").setParameter("iduser", u.getIdUtilisateur());
            if (!q1.getResultList().isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean accesToModuleAssureIrd(OrclassUtilisateurs u) {
        Query q = this.em.createQuery("SELECT m FROM OrclassModules m JOIN m.orclassEntreprisesModulesList me WHERE me.orclassModules.code= :code").setParameter("code", "mod.assure.ird");
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        if (!q.getResultList().isEmpty()) {
            q = this.em.createQuery("SELECT distinct m FROM OrclassModules m JOIN m.orclassUtilisateursAccesList ua where ua.idModule.code = :code and ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_principal=true or ua.autorisation_principal=1)").setParameter("code", "mod.assure.ird").setParameter("iduser", u.getIdUtilisateur());
            if (!q.getResultList().isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean accesToModuleTransport(OrclassUtilisateurs u) {
        Query q = this.em.createQuery("SELECT m FROM OrclassModules m JOIN m.orclassEntreprisesModulesList me WHERE me.orclassModules.code= :code").setParameter("code", "mod.transp");
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        if (!q.getResultList().isEmpty()) {
            q = this.em.createQuery("SELECT distinct m FROM OrclassModules m JOIN m.orclassUtilisateursAccesList ua where ua.idModule.code = :code and ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_principal=true or ua.autorisation_principal=1)").setParameter("code", "mod.transp").setParameter("iduser", u.getIdUtilisateur());
            if (!q.getResultList().isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean accesToModuleCautionCredit(OrclassUtilisateurs u) {
        Query q = this.em.createQuery("SELECT m FROM OrclassModules m JOIN m.orclassEntreprisesModulesList me WHERE me.orclassModules.code= :code").setParameter("code", "mod.caution.credit");
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        if (!q.getResultList().isEmpty()) {
            q = this.em.createQuery("SELECT distinct m FROM OrclassModules m JOIN m.orclassUtilisateursAccesList ua where ua.idModule.code = :code and ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_principal=true or ua.autorisation_principal=1)").setParameter("code", "mod.caution.credit").setParameter("iduser", u.getIdUtilisateur());
            if (!q.getResultList().isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean accesToModuleAgriclote(OrclassUtilisateurs u) {
        Query q = this.em.createQuery("SELECT m FROM OrclassModules m JOIN m.orclassEntreprisesModulesList me WHERE me.orclassModules.code= :code").setParameter("code", "mod.agr");
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        if (!q.getResultList().isEmpty()) {
            q = this.em.createQuery("SELECT distinct m FROM OrclassModules m JOIN m.orclassUtilisateursAccesList ua where ua.idModule.code= :code and ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_principal=true or ua.autorisation_principal=1)").setParameter("code", "mod.agr").setParameter("iduser", u.getIdUtilisateur());
            if (!q.getResultList().isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean accesToModuleAssureAdp(OrclassUtilisateurs u) {
        Query q = this.em.createQuery("SELECT m FROM OrclassModules m JOIN m.orclassEntreprisesModulesList me WHERE me.orclassModules.code= :code").setParameter("code", "mod.assure.adp");
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        if (!q.getResultList().isEmpty()) {
            q = this.em.createQuery("SELECT distinct m FROM OrclassModules m JOIN m.orclassUtilisateursAccesList ua where ua.idModule.code = :code and ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_principal=true or ua.autorisation_principal=1) and ua.idProfil.actif= :value").setParameter("code", "mod.assure.adp").setParameter("value", Boolean.TRUE).setParameter("iduser", u.getIdUtilisateur());
            if (!q.getResultList().isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean accesToModuleGestionAssurer(OrclassUtilisateurs u) {
        Query q = this.em.createQuery("SELECT m FROM OrclassModules m JOIN m.orclassEntreprisesModulesList me WHERE me.orclassModules.code= :code").setParameter("code", "mod.ges.ass");
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        if (!q.getResultList().isEmpty()) {
            q = this.em.createQuery("SELECT distinct m FROM OrclassModules m JOIN m.orclassUtilisateursAccesList ua where ua.idModule.code = :code and ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_principal=true or ua.autorisation_principal=1)").setParameter("code", "mod.ges.ass").setParameter("iduser", u.getIdUtilisateur());
            if (!q.getResultList().isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean accesComptabiliteGeneral(OrclassUtilisateurs u) {
        Query q = this.em.createQuery("SELECT m FROM OrclassModules m JOIN m.orclassEntreprisesModulesList me WHERE me.orclassModules.code= :code").setParameter("code", "mod.compt.gene");
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        if (!q.getResultList().isEmpty()) {
            q = this.em.createQuery("SELECT distinct m FROM OrclassModules m JOIN m.orclassUtilisateursAccesList ua where ua.idModule.code = :code and ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_principal=true or ua.autorisation_principal=1)").setParameter("code", "mod.compt.gene").setParameter("iduser", u.getIdUtilisateur());
            if (!q.getResultList().isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean accesAssuranceAuto(OrclassUtilisateurs u) {
        Query q = this.em.createQuery("SELECT m FROM OrclassModules m JOIN m.orclassEntreprisesModulesList me WHERE me.orclassModules.code= :code").setParameter("code", "mod.ass.auto");
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        if (!q.getResultList().isEmpty()) {
            q = this.em.createQuery("SELECT distinct m FROM OrclassModules m JOIN m.orclassUtilisateursAccesList ua where ua.idModule.code= :code and ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_principal=true or ua.autorisation_principal=1)").setParameter("code", "mod.ass.auto").setParameter("iduser", u.getIdUtilisateur());
            if (!q.getResultList().isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean accessMenuByAdministration(OrclassUtilisateurs u, String code) {
        Collection<OrclassMenus> colMenu = new ArrayList<>();
        Query q = this.em.createQuery("SELECT DISTINCT m.idMenu FROM OrclassMenusAcces m JOIN m.idFonctionnalite.orclassUtilisateursAccesList u where u.idUtilisateur.idUtilisateur= :iduser and m.idMenu.code= :code and (u.autorisation_principal=true or u.autorisation_principal=1)  and (u.autorisation_fonctionnalite=true or u.autorisation_fonctionnalite=1)").setParameter("iduser", u.getIdUtilisateur()).setParameter("code", code);
        colMenu = q.getResultList();
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean accessModule(OrclassUtilisateurs u, String code) {
        Query q = this.em.createQuery("SELECT m FROM OrclassModules m JOIN m.orclassEntreprisesModulesList me WHERE me.orclassModules.code= :code").setParameter("code", code);
        if (q.getResultList().isEmpty()) {
            return Boolean.FALSE;
        }
        if (!q.getResultList().isEmpty()) {
            q = this.em.createQuery("SELECT distinct m FROM OrclassModules m JOIN m.orclassUtilisateursAccesList ua where ua.idModule.code = :code and ua.idUtilisateur.idUtilisateur= :iduser and (ua.autorisation_principal=true or ua.autorisation_principal=1) and ua.idProfil.actif= :value").setParameter("code", code).setParameter("value", Boolean.TRUE).setParameter("iduser", u.getIdUtilisateur());
            if (!q.getResultList().isEmpty()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean accessAction(OrclassUtilisateurs user, OrclassActions act, OrclassMenus m) {
        Boolean etat = Boolean.FALSE;
        Query q;
        try {
            q = this.em.createQuery("SELECT m  FROM OrclassUtilisateursAcces m  WHERE m.idAction.idAction= :idAction AND m.idFonctionnalite.idFonctionnalite= :idFonctionnalite  and m.autorisation_principal= :value and m.autorisation_fonctionnalite= :value and m.autorisation_action= :value   and m.idModule.idModule= :idModule and m.idUtilisateur.idUtilisateur= :idUtilisateur")
                    .setParameter("value", Boolean.TRUE)
                    .setParameter("idAction", act.getIdAction())
                    .setParameter("idFonctionnalite", act.getIdFonctionnalite().getIdFonctionnalite())
                    .setParameter("idModule", act.getIdFonctionnalite().getIdModule().getIdModule())
                    .setParameter("idUtilisateur", user.getIdUtilisateur());
            etat = Boolean.valueOf((q.getResultList().size() != 0));
            System.out.println("droitAcces.ServiceDroitAcces.accessAction()" + etat);
        } catch (Exception e) {
            return Boolean.FALSE;
        }

        return etat;
    }

    @Override
    public OrclassMenus getMenuByCode(String code) {
        return (OrclassMenus) this.menusDao.findEntityHavingValue("code", code);
    }

    @Override
    public OrclassModules getModuleByCode(String code) {
        return (OrclassModules) this.modulesDao.findEntityHavingValue("code", code);
    }

    @Override
    public OrclassFonctionnalites getFonctionnaliteByCode(String code) {
        return (OrclassFonctionnalites) this.fonctionnalitesDao.findEntityHavingValue("code", code);
    }

    @Override
    public OrclassActions getActionByMenuAccess(OrclassFonctionnalites f, OrclassMenus m, OrclassModules mod) {
        Query q = this.em.createQuery("SELECT ma FROM OrclassMenusAcces ma WHERE ma.idFonctionnalite.idFonctionnalite= :idFonctionnalite AND ma.idMenu.idMenu= :idMenu AND ma.idFonctionnalite.idModule.idModule= :idModule").setParameter("idFonctionnalite", f.getIdFonctionnalite()).setParameter("idMenu", m.getIdMenu()).setParameter("idModule", mod.getIdModule());
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (OrclassActions) q.getResultList().toArray()[0];
    }

    @Override
    public OrclassActions getActionByCodeByFonctionnalit(String code, OrclassFonctionnalites f) {
        Query q = this.em.createQuery("SELECT a FROM OrclassActions a where a.code= :code and a.idFonctionnalite.idFonctionnalite= :idFonctionnalite").setParameter("code", code).setParameter("idFonctionnalite", f==null ? null:f.getIdFonctionnalite());
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (OrclassActions) q.getResultList().toArray()[0];
    }
}
