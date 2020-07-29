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
import org.openmrs.module.integration.api.util.TempFile;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/integration")
public class IntegrationController {

	protected final Log log;
	@Autowired
	private TempFile tempService;
	public IntegrationController() {
		this.log = LogFactory.getLog((Class)this.getClass());
	}

	@RequestMapping(value = "prescription", method = RequestMethod.GET)
	@ResponseBody
	public String getUserPrescription(@RequestParam("details") String OrderNumber,String action ) throws Exception{
		IntegrationService service =Context.getService(IntegrationService.class);
		return tempService.createTemp(service.getUserPrescription(OrderNumber,action));
	}
}
