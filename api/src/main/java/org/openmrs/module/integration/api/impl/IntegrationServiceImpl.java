
package org.openmrs.module.integration.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.integration.api.IntegrationService;
import org.openmrs.module.integration.api.db.IntegrationDAO;

/**
 * It is a default implementation of {@link IntegrationService}.
 */

public class IntegrationServiceImpl extends BaseOpenmrsService implements IntegrationService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private IntegrationDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(IntegrationDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public IntegrationDAO getDao() {
	    return dao;
    }
}