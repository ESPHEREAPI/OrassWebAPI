///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package auto;
//
//import dao.AbstractJpaDAO;
//import dao.OrclassAssureDao;
//import dao.OrclassAttestationAssuranceDao;
//import dao.OrclassPoliceCaracteristiqueDao;
//import dao.OrclassPoliceCommissionApporteurDao;
//import dao.OrclassPoliceDao;
//import dao.OrclassPoliceGarantieDao;
//import dao.Orclass_ConducteurDao;
//import enums.NatureRisque;
//import exception.GlobalException;
//import java.math.BigInteger;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//import javax.annotation.Resource;
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionManagement;
//import javax.ejb.TransactionManagementType;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.HeuristicMixedException;
//import javax.transaction.HeuristicRollbackException;
//import javax.transaction.NotSupportedException;
//import javax.transaction.RollbackException;
//import javax.transaction.SystemException;
//import javax.transaction.UserTransaction;
//import modele.OrclassAssure;
//import modele.OrclassAttestationAssurance;
//import modele.OrclassAttestationConducteur;
//import modele.OrclassConducteur;
//import modele.OrclassEntreprises;
//import modele.OrclassGestionCompagnieAgence;
//import modele.OrclassIntermediaires;
//import modele.OrclassPolice;
//import modele.OrclassPoliceCaracteristique;
//import modele.OrclassPoliceCommissionApporteur;
//import modele.OrclassPoliceGarantie;
//import modele.OrclassQuitance;
//import modele.OrclassRisque;
//import modele.OrclassUtilisateurs;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// *
// * @author JIATOU FRANCK
// */
//@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
//public class Auto implements IAuto {
//
//    @Resource
//    private UserTransaction tx;
//    @PersistenceContext(unitName = AbstractJpaDAO.PersistanceUnit)
//    private EntityManager em;
//    @EJB
//    OrclassAssureDao assureDao;
//    @EJB
//    OrclassPoliceDao policeDao;
//    Logger logger = LoggerFactory.getLogger(Auto.class);
//    @EJB
//    OrclassPoliceCaracteristiqueDao policeCaracteristiqueDao;
//    @EJB
//    OrclassPoliceGarantieDao policeGarantieDao;
//    @EJB
//    Orclass_ConducteurDao conducteurDao;
//    @EJB
//    OrclassAttestationAssuranceDao attestationAssuranceDao;
//    @EJB
//    OrclassPoliceCommissionApporteurDao policeCommissionApporteurDao;
//
//    @Override
//    public Boolean addProjetAutoMono(OrclassAssure assure, OrclassPolice police, OrclassRisque risque, List<OrclassConducteur> conducteur, List<OrclassPoliceCaracteristique> policeCaracteristiques, List<OrclassPoliceGarantie> policeGaranties, OrclassQuitance quitance, List<OrclassPoliceCommissionApporteur> commissionApporteurs, OrclassEntreprises entreprises, String numero_attestation_ass, OrclassAttestationAssurance attestationAssurance, OrclassGestionCompagnieAgence gscag, OrclassUtilisateurs user, Boolean enregistrer, Boolean valider, Boolean print) {
//
//        String code;
//        try {
//            tx.begin();
//            OrclassPolice newPolice;
//            OrclassAssure newAssuerer;
//            OrclassRisque newrisque;
//            OrclassConducteur newconducteur;
//            OrclassAttestationConducteur newattestationConducteur = null;
//            OrclassAttestationAssurance newattestaion_ass;
//            //insertion de l assureure
//            if (assureDao.finKeyAssure(assure.getNom(), entreprises) == null) {
//                code = this.codeAssurer(police.getIdIntermediaire(), entreprises);
//                System.out.println("Code Assure:" + code);
//                newAssuerer = new OrclassAssure();
//                newAssuerer.setCode(code);
//                newAssuerer.setNom(assure.getNom().toUpperCase());
//                newAssuerer.setPrenom(assure.getPrenom());
//                newAssuerer.setAdresse(assure.getAdresse());
//                newAssuerer.setDateCreation(new Date());
//                newAssuerer.setDate_naissance(assure.getDate_naissance());
//                newAssuerer.setDate_saisie(police.getDate_saisie());
//                newAssuerer.setIdActivite(assure.getIdActivite());
//                newAssuerer.setIdEntreprise(entreprises);
//                newAssuerer.setIdIntermediaire(police.getIdIntermediaire());
//                newAssuerer.setIdProfession(assure.getIdProfession());
//                newAssuerer.setIdQualite(assure.getIdQualite());
//                newAssuerer.setIdVille(assure.getIdVille());
//                newAssuerer.setLieu_naissance(assure.getLieu_naissance());
//                newAssuerer.setNum_cni(assure.getNum_cni());
//                newAssuerer.setNumero_piece(assure.getNumero_piece());
//
//                newAssuerer.setSexe(assure.getSexe());
//                newAssuerer.setTypePieces(assure.getTypePieces());
////                newAssuerer.setDate_saisie(assure.getDate_saisie());
//                newAssuerer.setSaisir_par(user.getNom() + " " + user.getPrenom());
//                em.persist(newAssuerer);
//            } else {
//                newAssuerer = assureDao.finKeyAssure(assure.getNom(), entreprises);
//            }
//            // creation de la police
//            if (policeDao.finKey(police.getIdIntermediaire(), police.getNumero_police(), entreprises, police.getIdCategories()) != null) {
//                throw new GlobalException("THE POLICE NUMBER EXISTS TRY A NEW PROJECT OR CONSULT YOUR ADMINISTRATOR " + " FATAL ERROR");
//
//            }
//            newPolice = new OrclassPolice();
////            OrclassPolicePK policePK = new OrclassPolicePK();
////            newPolice.setPolicePK(policePK);
//            newPolice.setNumero_police(police.getNumero_police());
//            newPolice.setAccessoir(police.getAccessoir());
//            newPolice.setContrat(police.getContrat());
//            newPolice.setCoursDevise(police.getCoursDevise());
//            newPolice.setCoursDeviseApplique(police.getCoursDeviseApplique());
//            newPolice.setCptg(police.getCptg());
//            newPolice.setDateCreation(new Date());
//            newPolice.setDate_cours_devise(police.getDate_cours_devise());
//            newPolice.setDate_echeance(police.getDate_echeance());
//            newPolice.setDate_effet(police.getDate_effet());
//            newPolice.setDate_saisie(police.getDate_saisie());
//            newPolice.setDate_souscription(police.getDate_souscription());
//            if (Objects.equals(valider, Boolean.TRUE)) {
//                newPolice.setValidation(valider);
//                newPolice.setValider_par(user.getNom() + " " + user.getPrenom());
//                newPolice.setDate_validation(new Date());
//
//            }
//            if (Objects.equals(print, Boolean.TRUE)) {
//                newPolice.setImprimer(print);
//
//            }
//            newPolice.setIdApporteur(police.getIdApporteur());
//            newPolice.setIdAssure(newAssuerer);
//            newPolice.setIdCategories(police.getIdCategories());
//            newPolice.setIdConvention(police.getIdConvention());
//            newPolice.setIdDevise(police.getIdDevise());
//            newPolice.setIdEntreprises(entreprises);
//            newPolice.setIdExoneration(police.getIdExoneration());
//            newPolice.setIdFractionnementCategories(police.getIdFractionnementCategories());
//            newPolice.setIdIntermediaire(police.getIdIntermediaire());
//            newPolice.setIdMajorationDuree(police.getIdMajorationDuree());
//            newPolice.setIdReduction(police.getIdReduction());
//            newPolice.setIdTimbre(police.getIdTimbre());
//            newPolice.setIdTimbreDimension(police.getIdTimbreDimension());
//            newPolice.setIdTypeTarif(police.getIdTypeTarif());
//            newPolice.setMontantaccessoir(police.getMontantaccessoir());
//            newPolice.setNatureContrat(police.getNatureContrat());
//            newPolice.setNombre_timbre(police.getNombre_timbre());
//            newPolice.setNumero_Borderau(police.getNumero_Borderau());
//            newPolice.setObservation(police.getObservation());
//            newPolice.setRef_intermediaire(police.getRef_intermediaire());
//            newPolice.setSaisir_par(user.getNom() + " " + user.getPrenom());
//            newPolice.setTaux_echange(police.getTaux_echange());
//            newPolice.setTitre(police.getTitre());
//            newPolice.setUniteDuree(police.getUniteDuree());
//            newPolice.setValeurDuree(police.getValeurDuree());
//            newPolice.setValeur_Devis(police.getValeur_Devis());
//            newPolice.setValeur_timbre(police.getValeur_timbre());
//
//            newPolice.setIdUtilisateur(user);
//
//            em.persist(newPolice);
//
//            /*
//                creation du risque
//             */
//            if (risque != null && risque.getId() == null) {
//                newrisque = new OrclassRisque();
//                newrisque.setDateCreation(new Date());
//                newrisque.setGenre_vehicule(risque.getGenre_vehicule());
//                newrisque.setUsage_vehicule(risque.getUsage_vehicule());
//                newrisque.setZone(risque.getZone());
//                newrisque.setIdZoneTransport(risque.getIdZoneTransport());
//                newrisque.setIdSousUsageTPV(risque.getIdSousUsageTPV());
//                newrisque.setMarque_vehicule(risque.getMarque_vehicule());
//                newrisque.setTypeVehicule(risque.getTypeVehicule());
//                newrisque.setRefer_Marq_vehic(risque.getRefer_Marq_vehic());
//                newrisque.setNum_immatricul(risque.getNum_immatricul());
//                newrisque.setDateMec(risque.getDateMec());
//                newrisque.setEnergie(risque.getEnergie());
//                newrisque.setTurbo(risque.getTurbo());
//                newrisque.setAvec_remoque(risque.getAvec_remoque());
//                newrisque.setMatiere_inflamable(risque.getMatiere_inflamable());
//                newrisque.setNumero_chassis(risque.getNumero_chassis());
//
//                newrisque.setNumero_moteur(risque.getNumero_moteur());
//                newrisque.setIdCarrosserie(risque.getIdCarrosserie());
//                newrisque.setPuissance_vehicule(risque.getPuissance_vehicule());
//                newrisque.setPoids_vehicule(risque.getPoids_vehicule());
//                newrisque.setCylindre_vehicule(risque.getCylindre_vehicule());
//                newrisque.setNombre_place(risque.getNombre_place());
//                newrisque.setValeur_catalogue(risque.getValeur_catalogue());
//                risque.setValeur_declaree(risque.getValeur_declaree());
//                newrisque.setIdPolice(newPolice);
////                newrisque.setMatricule(risque.getMatricule());
//                newrisque.setDateEntree(risque.getDateEntree());
////                newrisque.setDateNaissance(risque.getDateNaissance());
//                newrisque.setIdEntreprise(entreprises);
//
//                newrisque.setLibelle(NatureRisque.vehicule.toString());
//
//                newrisque.setIdVille(assure.getIdVille());
//                newrisque.setSaisir_par(user.getNom() + " " + user.getPrenom());
//
//                em.persist(newrisque);
//
//                for (OrclassConducteur cd : conducteur) {
//                    if ("".equals(cd.getNumero_permis()) || "".equals(cd.getNomComplet())) {
//                        continue;
//                    }
//                    try {
//                        if (conducteurDao.findEntityHavingValue("numero_permis", cd.getNumero_permis()) == null) {
//                            newattestationConducteur = new OrclassAttestationConducteur();
//                            newattestationConducteur.setAdresse(cd.getIdTestationConducteur().getAdresse());
//                            newattestationConducteur.setIdVille(cd.getIdTestationConducteur().getIdVille());
//                            newattestationConducteur.setNomCmplet(cd.getIdTestationConducteur().getNomCmplet());
//                            newattestationConducteur.setProfession(cd.getIdTestationConducteur().getProfession());
//                            em.persist(newattestationConducteur);
//                            newconducteur = new OrclassConducteur();
//                            newconducteur.setNumero_permis(cd.getNumero_permis());
//                            newconducteur.setNomComplet(cd.getNomComplet());
//                            newconducteur.setDateDeliPerm(cd.getDateDeliPerm());
//                            newconducteur.setDateSaisir(cd.getDateSaisir());
//                            newconducteur.setDate_Naissance(cd.getDate_Naissance());
//                            newconducteur.setDurre_conduite(cd.getDurre_conduite());
//                            newconducteur.setIdEntreprise(entreprises);
//                            newconducteur.setIdRisque(newrisque);
//                            newconducteur.setLieu_deli_permis(cd.getLieu_deli_permis());
//                            newconducteur.setIdTestationConducteur(newattestationConducteur);
//                            newconducteur.setPermis(cd.getPermis());
//                            newconducteur.setSexe(cd.getSexe());
//                            em.persist(newconducteur);
//
//                        }
//
//                    } catch (GlobalException gex) {
//                        throw new GlobalException("Erreur Sur le conducteur ... verifier son numero de permis ");
//                    }
//
//                }
//                // attestation assurance
//                if (gscag != null && gscag.getId() != null && numero_attestation_ass != null && !"".equals(numero_attestation_ass) && attestationAssurance != null && attestationAssurance.getIdDocumentCategories() != null) {
//                    try {
//                        if (attestationAssuranceDao.findEntityHavingValue("numumero_Attestation", numero_attestation_ass) == null) {
//                            newattestaion_ass = new OrclassAttestationAssurance();
//                            newattestaion_ass.setDateCreation(new Date());
//                            newattestaion_ass.setDate_affectation(attestationAssurance.getDate_affectation());
//                            newattestaion_ass.setDate_echeance(attestationAssurance.getDate_echeance());
//                            newattestaion_ass.setDate_effet(attestationAssurance.getDate_effet());
//                            newattestaion_ass.setNumumero_Attestation(numero_attestation_ass);
//                            newattestaion_ass.setIdDocumentCategories(attestationAssurance.getIdDocumentCategories());
//                            newattestaion_ass.setIdEntreprises(entreprises);
//                            newattestaion_ass.setIdPolice(newPolice);
//                            newattestaion_ass.setIdRisque(newrisque);
//                            newattestaion_ass.setNatureRisque(attestationAssurance.getNatureRisque());
//                            newattestaion_ass.setNum_logique(attestationAssurance.getNum_logique());
//                            newattestaion_ass.setSaisir_par(user.getNom() + " " + user.getPrenom());
//                            em.persist(newattestaion_ass);
//                            BigInteger sortie = gscag.getStockSortie();
//                            gscag.setStockSortie(sortie.add(BigInteger.ONE));
//                            BigInteger stockFinala = gscag.getStockFinal();
//                            gscag.setStockFinal(stockFinala.subtract(BigInteger.ONE));
//                            em.merge(gscag);
//
////                        newattestaion_ass.setImprimer(print);
//                        }
//                    } catch (Exception e) {
//                        throw new GlobalException("Erreur Sur le numero attestation ... verifier  le numero d attestaion ");
//                    }
//
//                }
//
//            }
//            OrclassPoliceCaracteristique newPoliceCaracteristique;
//            for (OrclassPoliceCaracteristique pc : policeCaracteristiques) {
//                if (pc.getIdCaracteristiques() == null || pc.getIdCaracteristiques().getId() == null) {
//                    continue;
//                }
//                if (pc.getIdCaracteristiques() == null || pc.getIdCaracteristiques().getId() == null || pc.getIdCaracteristiques().getOrclass_Objet() != null) {
//                    continue;
//                }
//                if (pc.getValeurBoolean() == null && pc.getDateValeur() == null && pc.getValeurCaracteristique() == null && pc.getValeurCode() == null && (pc.getValeurNumerique() == null || pc.getValeurNumerique().intValue() == 0) && pc.getValeurTexte() == null) {
//                    continue;
//                }
//
////                if (policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprises, newPolice, pc.getIdGroup()) != null) {
////
////                    continue;
////                }
//                newPoliceCaracteristique = new OrclassPoliceCaracteristique();
//
//                newPoliceCaracteristique.setIdCaracteristiques(pc.getIdCaracteristiques());
//                newPoliceCaracteristique.setIdPolice(newPolice);
//                newPoliceCaracteristique.setIdEntreprise(entreprises);
//                if (pc.getDateValeur() != null) {
//                    newPoliceCaracteristique.setDateValeur(pc.getDateValeur());
//                }
//                if (pc.getValeurBoolean() != null) {
//                    newPoliceCaracteristique.setValeurBoolean(pc.getValeurBoolean());
//                }
//                if (pc.getValeurCode() != null) {
//                    newPoliceCaracteristique.setValeurCode(pc.getValeurCode());
//                }
//                if (pc.getValeurCaracteristique() != null) {
//                    pc.setValeurCaracteristique(pc.getValeurCaracteristique());
//                }
//                if (pc.getValeurTexte() != null) {
//                    newPoliceCaracteristique.setValeurTexte(pc.getValeurTexte());
//                }
//                if (pc.getValeurNumerique() != null && pc.getValeurNumerique() != null && pc.getValeurNumerique().intValue() != 0) {
//                    newPoliceCaracteristique.setValeurNumerique(pc.getValeurNumerique());
//                }
//                newPoliceCaracteristique.setIdCategories(police.getIdCategories());
////                newPoliceCaracteristique.setIdGroup(pc.getIdGroup());
//                em.persist(newPoliceCaracteristique);
//
//            }
//
//            /*
//                enregistrement des garenties polices
//             */
//            OrclassPoliceGarantie newPoliceGarantie;
//
////            OrclassGroupeGarantiePolice groupeGarantiePolice;
//            for (OrclassPoliceGarantie pg : policeGaranties) {
//                if (pg.getIdGarantie() == null || pg.getIdGarantie().getId() == null) {
//                    continue;
//                }
//                if ((pg.getPrime() == null || pg.getPrime().intValue() == 0) && Objects.equals(pg.getIdGarantie().getGratuit(), Boolean.FALSE)) {
//                    continue;
//                }
////                if (policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprises, newPolice, pg.getIdGroup()) != null) {
////                    continue;
////                }
//                newPoliceGarantie = new OrclassPoliceGarantie();
//                newPoliceGarantie.setCapital(pg.getCapital());
//                newPoliceGarantie.setIdEntreprise(entreprises);
//                newPoliceGarantie.setIdGarantie(pg.getIdGarantie());
//                newPoliceGarantie.setIdPolice(newPolice);
//                newPoliceGarantie.setIdGroup(pg.getIdGroup());
//                newPoliceGarantie.setPourcentage(pg.getPourcentage());
//                newPoliceGarantie.setPrime(pg.getPrime());
//                newPoliceGarantie.setProrata(pg.getProrata());
//                newPoliceGarantie.setPrimeAnnuelle(pg.getPrimeAnnuelle());
//                newPoliceGarantie.setPrime_sans_reduction_ou_operation(pg.getPrime_sans_reduction_ou_operation());
//                newPoliceGarantie.setTaux_Majoration_reduction(pg.getTaux_Majoration_reduction());
//                newPoliceGarantie.setTaux(pg.getTaux());
//                em.persist(newPoliceGarantie);
//            }
//
//
//            /*
//                creation quittance
//             */
//            OrclassQuitance newQuittance = new OrclassQuitance();
//            newQuittance.setAccessoirTaxe(quitance.getAccessoirTaxe());
//            newQuittance.setDateCreation(new Date());
//            newQuittance.setDateEmission(quitance.getDateEmission());
//
//            newQuittance.setDate_saisie(quitance.getDate_saisie());
//            if (Objects.equals(valider, Boolean.TRUE)) {
//                newQuittance.setValidation(valider);
//                newQuittance.setDate_validation(quitance.getDate_validation());
//                newQuittance.setValider_par(user.getNom() + " " + user.getPrenom());
//
//            }
//            newQuittance.setDate_echeance(quitance.getDate_echeance());
//            newQuittance.setDate_effet(quitance.getDate_effet());
//            newQuittance.setIdDevise(police.getIdDevise());
//            newQuittance.setIdEntreprise(entreprises);
//            newQuittance.setIdPolice(newPolice);
//            newQuittance.setIdTimbreDimension(quitance.getIdTimbreDimension());
//            newQuittance.setTypQuittance(quitance.getTypQuittance());
//            newQuittance.setMontantApport(quitance.getMontantApport());
//            newQuittance.setMontantCommision(quitance.getMontantCommision());
//            newQuittance.setMontantGestion(quitance.getMontantGestion());
//            newQuittance.setMontant_Accessoire(quitance.getMontant_Accessoire());
//            newQuittance.setPrimeNette(quitance.getPrimeNette());
//            newQuittance.setPrimeTaxe(quitance.getPrimeTaxe());
//            newQuittance.setTaxePrime(quitance.getTaxePrime());
//            newQuittance.setTaxe_tva(quitance.getTaxe_tva());
//            newQuittance.setTaxe_tva_acc(quitance.getTaxe_tva_acc());
//            newQuittance.setTaxe_asac_fga(quitance.getTaxe_asac_fga());
//            newQuittance.setTaxe_tva_sur_asac(quitance.getTaxe_tva_sur_asac());
//            newQuittance.setTimbr_Gradue__vignette(quitance.getTimbr_Gradue__vignette());
//            newQuittance.setTimbr_Gradue_cp(quitance.getTimbr_Gradue_cp());
//            newQuittance.setTimbreDimension(quitance.getTimbreDimension());
//            newQuittance.setRedMajDonneeSpecial(quitance.getRedMajDonneeSpecial());
//            newQuittance.setReduction_tarif1(quitance.getReduction_tarif1());
//            newQuittance.setReduction_tarif2(quitance.getReduction_tarif2());
//            newQuittance.setReduction_tarif3(quitance.getReduction_tarif3());
//            newQuittance.setMatiere_inflamable(quitance.getMatiere_inflamable());
//            newQuittance.setMajoration_permis(quitance.getMajoration_permis());
//            newQuittance.setMajoration_age(quitance.getMajoration_age());
//            newQuittance.setRedMajDonneeSpecial(quitance.getRedMajDonneeSpecial());
//            newQuittance.setBonus(quitance.getBonus());
//            newQuittance.setReduction_commercial(quitance.getReduction_commercial());
//            newQuittance.setReduction(quitance.getReduction());
//
//            newQuittance.setSaisir_par(user.getNom() + " " + user.getPrenom());
//            newQuittance.setTaxeAccessoir(quitance.getTaxeAccessoir());
//            newQuittance.setTaxeCommision(quitance.getTotalCommision());
//            newQuittance.setTimbreDimension(quitance.getTimbreDimension());
//            newQuittance.setTimbreGradue(quitance.getTimbreGradue());
//            newQuittance.setTotalCommision(quitance.getTotalCommision());
//            newQuittance.setTotalTaxes(quitance.getTotalTaxes());
//            newQuittance.setTotalTimb(quitance.getTotalTimb());
//            newQuittance.setTotal_a_paye(quitance.getTotal_a_paye());
////            newQuittance.setTaxesAccessoires(quitance.getTaxesAccessoires());
//            newQuittance.setTypQuittance(quitance.getTypQuittance());
//            newQuittance.setTaux_apport(quitance.getTaux_apport());
//            newQuittance.setTaux_gestion(quitance.getTaux_gestion());
//            em.persist(newQuittance);
//
//            /*
//               commission prime apporteur
//             */
//            OrclassPoliceCommissionApporteur apporteur = null;
//            for (OrclassPoliceCommissionApporteur commissionApporteur : commissionApporteurs) {
//                apporteur = new OrclassPoliceCommissionApporteur();
//                apporteur.setIdCommission_Prime_Apporteur(commissionApporteur.getIdCommission_Prime_Apporteur());
//                apporteur.setTaux_apport(commissionApporteur.getTaux_apport());
//                apporteur.setTaux_gestion(commissionApporteur.getTaux_gestion());
//                apporteur.setIdEntreprise(entreprises);
//                apporteur.setIdPolice(newPolice);
//                em.persist(apporteur);
//
//            }
//
//            tx.commit();
//            valider = Boolean.TRUE;
//        } catch (GlobalException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException ex) {
//            try {
//                tx.rollback();
//                throw ex;
//            } catch (Exception e) {
//            }
//            valider = Boolean.FALSE;
//        }
//        return valider;
//
//    }
//
//    @Override
//    public Boolean updateProjetAutoMono(OrclassAssure assure, OrclassPolice police,
//            OrclassRisque risque, List<OrclassConducteur> conducteur,
//            List<OrclassPoliceCaracteristique> policeCaracteristiques, List<OrclassPoliceGarantie> policeGaranties,
//            OrclassQuitance quitance, List<OrclassPoliceCommissionApporteur> commissionApporteurs,
//            OrclassEntreprises entreprises, String numero_attestation_ass, OrclassAttestationAssurance attestationAssurance,
//            OrclassGestionCompagnieAgence gscag, OrclassUtilisateurs user
//    ) {
//        Boolean resultat = Boolean.FALSE;
//
//        try {
//            tx.begin();
//            OrclassPolice newPolice = null;
//            OrclassAssure newAssuerer;
//            OrclassRisque newrisque;
//            OrclassConducteur newconducteur;
//            OrclassAttestationConducteur newattestationConducteur = null;
//            OrclassAttestationAssurance newattestaion_ass;
//
//            //insertion de l assureure
//            if (assure != null && assure.getId() != null) {
//                assure.setDateModification(new Date());
//                assure.setModifier_par(user.getNom() + " " + user.getPrenom());
//                newAssuerer = em.merge(assure);
//
//            }
//            if (police != null && police.getId() != null) {
//                police.setDateModification(new Date());
//                police.setModifier_par(user.getNom() + " " + user.getPrenom());
//
//                newPolice = em.merge(police);
//            }
//
//
//            /*
//                creation du risque
//             */
//            if (risque != null && risque.getId() != null) {
//                risque.setDateModification(new Date());
//                risque.setModifier_par(user.getNom() + " " + user.getPrenom());
//                newrisque = em.merge(risque);
//            }
//
//            for (OrclassConducteur cd : conducteur) {
//                try {
//                    if (cd != null && cd.getId() != null) {
//                        newattestationConducteur = em.merge(cd.getIdTestationConducteur());
//                        newconducteur = em.merge(cd);
//                        em.merge(newconducteur);
//
//                    }
//
//                } catch (GlobalException gex) {
//                    throw new GlobalException("Erreur Sur le conducteur ... verifier et ressayer ");
//                }
//
//            }
//
//            OrclassPoliceCaracteristique newPoliceCaracteristique;
//            for (OrclassPoliceCaracteristique pc : policeCaracteristiques) {
//                if (pc.getIdCaracteristiques() == null || pc.getIdCaracteristiques().getId() == null) {
//                    continue;
//                }
//                if (pc.getIdCaracteristiques() == null || pc.getIdCaracteristiques().getId() == null || pc.getIdCaracteristiques().getOrclass_Objet() != null) {
//                    continue;
//                }
//                if (pc.getValeurBoolean() == null && pc.getDateValeur() == null && pc.getValeurCaracteristique() == null && pc.getValeurCode() == null && (pc.getValeurNumerique() == null || pc.getValeurNumerique().intValue() == 0) && pc.getValeurTexte() == null) {
//                    continue;
//                }
//
////                if (policeCaracteristiqueDao.listeCaracteristiqueByPoliceNotHaveAvenant(entreprises, newPolice, pc.getIdGroup()) != null) {
////
////                    continue;
////                }
//                if (pc != null && pc.getId() != null) {
//                    em.merge(pc);
//                    continue;
//                }
//                newPoliceCaracteristique = new OrclassPoliceCaracteristique();
//
//                newPoliceCaracteristique.setIdCaracteristiques(pc.getIdCaracteristiques());
//                newPoliceCaracteristique.setIdPolice(newPolice);
//                newPoliceCaracteristique.setIdEntreprise(entreprises);
//                if (pc.getDateValeur() != null) {
//                    newPoliceCaracteristique.setDateValeur(pc.getDateValeur());
//                }
//                if (pc.getValeurBoolean() != null) {
//                    newPoliceCaracteristique.setValeurBoolean(pc.getValeurBoolean());
//                }
//                if (pc.getValeurCode() != null) {
//                    newPoliceCaracteristique.setValeurCode(pc.getValeurCode());
//                }
//                if (pc.getValeurCaracteristique() != null) {
//                    pc.setValeurCaracteristique(pc.getValeurCaracteristique());
//                }
//                if (pc.getValeurTexte() != null) {
//                    newPoliceCaracteristique.setValeurTexte(pc.getValeurTexte());
//                }
//                if (pc.getValeurNumerique() != null && pc.getValeurNumerique() != null && pc.getValeurNumerique().intValue() != 0) {
//                    newPoliceCaracteristique.setValeurNumerique(pc.getValeurNumerique());
//                }
//                newPoliceCaracteristique.setIdCategories(police.getIdCategories());
////                newPoliceCaracteristique.setIdGroup(pc.getIdGroup());
//                em.persist(newPoliceCaracteristique);
//
//            }
//
//            /*
//                enregistrement des garenties polices
//             */
//            OrclassPoliceGarantie newPoliceGarantie;
//
////            OrclassGroupeGarantiePolice groupeGarantiePolice;
//            for (OrclassPoliceGarantie pg : policeGaranties) {
//                if (pg.getIdGarantie() == null || pg.getIdGarantie().getId() == null) {
//                    continue;
//                }
//                if ((pg.getPrime() == null || pg.getPrime().intValue() == 0) && Objects.equals(pg.getIdGarantie().getGratuit(), Boolean.FALSE)) {
//                    continue;
//                }
////                if (policeGarantieDao.listeGarantiesByPoliceNotHaveAvenant(entreprises, newPolice, pg.getIdGroup()) != null) {
////                    continue;
////                }
//                if (pg != null && pg.getId() != null) {
//                    newPoliceGarantie = em.merge(pg);
//                    continue;
//                }
//                newPoliceGarantie = new OrclassPoliceGarantie();
//                newPoliceGarantie.setCapital(pg.getCapital());
//                newPoliceGarantie.setIdEntreprise(entreprises);
//                newPoliceGarantie.setIdGarantie(pg.getIdGarantie());
//                newPoliceGarantie.setIdPolice(newPolice);
//                newPoliceGarantie.setIdGroup(pg.getIdGroup());
//                newPoliceGarantie.setPourcentage(pg.getPourcentage());
//                newPoliceGarantie.setPrime(pg.getPrime());
//                newPoliceGarantie.setProrata(pg.getProrata());
//                newPoliceGarantie.setPrimeAnnuelle(pg.getPrimeAnnuelle());
//                newPoliceGarantie.setPrime_sans_reduction_ou_operation(pg.getPrime_sans_reduction_ou_operation());
//                newPoliceGarantie.setTaux_Majoration_reduction(pg.getTaux_Majoration_reduction());
//                newPoliceGarantie.setTaux(pg.getTaux());
//                em.persist(newPoliceGarantie);
//            }
//
//
//            /*
//                creation quittance
//             */
//            if (quitance != null && quitance.getId() != null) {
//                em.merge(quitance);
//            }
//
//
//            /*
//               commission prime apporteur
//             */
//            OrclassPoliceCommissionApporteur apporteur = null;
//            for (OrclassPoliceCommissionApporteur commissionApporteur : commissionApporteurs) {
//                if (policeCommissionApporteurDao.findKey(police, commissionApporteur.getIdCommission_Prime_Apporteur().getIdApporteur(), entreprises) != null) {
//                    continue;
//                }
//                apporteur = new OrclassPoliceCommissionApporteur();
//                apporteur.setIdCommission_Prime_Apporteur(commissionApporteur.getIdCommission_Prime_Apporteur());
//                apporteur.setTaux_apport(commissionApporteur.getTaux_apport());
//                apporteur.setTaux_gestion(commissionApporteur.getTaux_gestion());
//                apporteur.setIdEntreprise(entreprises);
//                apporteur.setIdPolice(newPolice);
//                em.persist(apporteur);
//
//            }
//
//            tx.commit();
//            resultat = Boolean.TRUE;
//        } catch (GlobalException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException ex) {
//
//            try {
//                tx.rollback();
//
//                throw ex;
//            } catch (Exception e) {
//                
//            }
//
//        }
//        return resultat;
//
//    }
//
//// Add business logic below. (Right-click in editor and choose
//// "Insert Code > Add Business Method")
//    public String codeAssurer(OrclassIntermediaires agence, OrclassEntreprises e) {
//        BigInteger valeur = BigInteger.ZERO;
//        BigInteger nombre = BigInteger.ZERO;
//        int taille = 4;
//        StringBuilder genCode = new StringBuilder();
//        Long nbre = assureDao.nbreLigne(agence, e);
//        genCode.append(e.getRaisonSociale().substring(0, 3));
//        genCode.append(agence.getIdRefIntermediaire().getCode());
//        genCode.append("-");
//        if (nbre == 0L) {
////            valeur = BigInteger.ONE;
//
//            for (int i = 0; i < 4; i++) {
//                genCode.append(0);
//            }
//            genCode.append(BigInteger.ONE);
//            nombre = BigInteger.ONE;
//            while (assureDao.findEntityHavingValue("code", genCode.toString()) != null) {
//                nombre = nombre.add(BigInteger.ONE);
//                genCode = new StringBuilder();
//                for (int i = 0; i < 4; i++) {
//                    genCode.append(0);
//                }
//                genCode.append(nombre.toString());
//            }
////            genCode = new StringBuilder();
////             for (int i = 0; i < 4; i++) {
////                genCode.append(0);
////            }
////              genCode.append(nombre.toString());
//
//        } else {
////            Double stringValeur = Double.parseDouble(a.getCode());
//            valeur = BigInteger.valueOf(nbre);
//
//            valeur = valeur.add(BigInteger.ONE);
//            for (int i = 0; i < 4; i++) {
//                genCode.append(0);
//            }
//            genCode.append(valeur.toString());
//            nombre = valeur;
//            while (assureDao.findEntityHavingValue("code", genCode.toString()) != null) {
//                nombre = nombre.add(BigInteger.ONE);
//                genCode = new StringBuilder();
//                for (int i = 0; i < 4; i++) {
//                    genCode.append(0);
//                }
//                genCode.append(nombre.toString());
//            }
//
//        }
//        //generation du numero
//
//        return genCode.toString();
//
//    }
//
//}
