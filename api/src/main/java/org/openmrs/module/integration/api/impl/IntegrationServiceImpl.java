
package org.openmrs.module.integration.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.integration.api.IntegrationService;
import org.openmrs.module.integration.api.db.IntegrationDAO;
import java.util.List;


public class IntegrationServiceImpl extends BaseOpenmrsService implements IntegrationService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	private IntegrationDAO dao;
    public void setDao(IntegrationDAO dao) {
	    this.dao = dao;
    }

    public IntegrationDAO getDao() {
	    return dao;
    }
    public  List getUserRegistration(String id){
        return this.dao.getUserRegistration(id);
    }
}