/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.ModuleActivator;

public class IntegrationActivator implements ModuleActivator {
	
	protected Log log = LogFactory.getLog(getClass());

	public void willRefreshContext() {
		this.log.info((Object)"Refreshing MAT Module");
	}

	public void contextRefreshed() {
		this.log.info((Object)"Refreshing MAT Module");
	}

	public void willStart() {
		this.log.info((Object)"MAT Module has Stated");
	}

	public void started() {
		this.log.info((Object)"MAT Module has  started");
	}

	public void willStop() {
		this.log.info((Object)"Stopping  MAT Module");
	}

	public void stopped() {
		this.log.info((Object)"MAT Module has stopped");
	}
}
