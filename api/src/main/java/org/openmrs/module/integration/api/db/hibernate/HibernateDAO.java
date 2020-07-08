/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.integration.api.db.IntegrationDAO;

public class HibernateDAO implements IntegrationDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
	    return sessionFactory;
    }
}