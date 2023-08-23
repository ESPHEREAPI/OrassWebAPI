/*    */ package dao;
/*    */ 
/*    */ import javax.ejb.Stateless;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.PersistenceContext;
/*    */ import modele.SeverMessagerie;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Stateless
/*    */ public class SeverMessagerieDao
/*    */   extends AbstractJpaDAO<SeverMessagerie>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */       
    @PersistenceContext(unitName=AbstractJpaDAO.PersistanceUnit)
    private EntityManager em;
/*    */   
/*    */   protected Class<SeverMessagerie> getEntityClass() {
/* 30 */     return SeverMessagerie.class;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected EntityManager getEntityManager() {
/* 36 */     return this.em;
/*    */   }
/*    */ }


/* Location:              C:\Users\E-SPHERE\Desktop\frank\globalpaie162530259009271860\WEB-INF\classes\!\dao\SeverMessagerieDao.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */