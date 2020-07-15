/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.integration.api.db.IntegrationDAO;
import org.openmrs.module.integration.api.model.Registration;

import java.util.List;

public class HibernateDAO implements IntegrationDAO {
	protected Log log;
    private DbSessionFactory sessionFactory;

    public HibernateDAO() {
        this.log = LogFactory.getLog((Class)this.getClass());
    }

    public void setSessionFactory(final DbSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DbSessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @Override
    public List getUserRegistration(String id) {
        this.log.info((Object)("MAT testing logs "));
        String sql ="SELECT  pi.identifier as 'Mat_id',pn.given_name,pn.middle_name,pn.family_name, DATE_FORMAT(FROM_DAYS(DATEDIFF(now(),p.birthdate)), '%Y')+0 as 'age',p.gender FROM person p inner join person_name pn on p.person_id=pn.person_id inner join patient_identifier pi on p.person_id=pi.patient_id where pi.identifier='"+id+"';";
        DbSession session =this.sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Registration.class));
        List results = query.list();
        this.log.info((Object)("MAT sql: " + sql));
        if (results!=null){
            return results;
        }else {
            return null;
        }
    }

    @Override
    public List getUserPrescription(String id) {
        return null;
    }

}