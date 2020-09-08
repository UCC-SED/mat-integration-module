
package org.openmrs.module.integration.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.integration.api.IntegrationService;
import org.openmrs.module.integration.api.db.IntegrationDAO;

import java.text.ParseException;


public class IntegrationServiceImpl extends BaseOpenmrsService implements IntegrationService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	private IntegrationDAO dao;
    public void setDao(IntegrationDAO dao) {
	    this.dao = dao;
    }

    public IntegrationDAO getDao() {
	    return dao;
    }
    public String getUserPrescription(String OrderNumber,String actionStatus) throws ParseException {return this.dao.getUserPrescription(OrderNumber,actionStatus);}
}