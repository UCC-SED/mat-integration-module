/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.integration.api.IntegrationService;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/integration")
public class IntegrationController {

	protected final Log log;
	public IntegrationController() {
		this.log = LogFactory.getLog((Class)this.getClass());
	}

	@RequestMapping(value = "prescription", method = RequestMethod.GET)
	@ResponseBody
	public String getUserPrescription(@RequestParam("orderNumber") String OrderNumber,@RequestParam("action") String action ) throws Exception{
		log.info("MAT server");
		IntegrationService service =Context.getService(IntegrationService.class);

		return service.getUserPrescription(OrderNumber,action);
	}


}
