/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.web.controller;

import com.google.gson.Gson;
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

import java.nio.file.FileSystems;
import java.nio.file.Path;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/integration")
public class IntegrationController {

	protected final Log log;
	protected  Path baseDir = FileSystems.getDefault().getPath("/media/sf_mat_temp");
	protected  String prefix = "MAT_";
	protected  String suffix = ".tmp";
	protected  Path tmpFile = null;
	@Autowired
	private TempFile tempService;
	public IntegrationController() {
		this.log = LogFactory.getLog((Class)this.getClass());
	}
	@RequestMapping(value = "test", method = RequestMethod.GET)
	@ResponseBody
	public String testing(@RequestParam("test") String test1 ) throws Exception{
		tempService.createTemp();
		return "API worked: "+test1;
	}

	@RequestMapping(value = "patient", method = RequestMethod.GET)
	@ResponseBody
	public String getUserRegistration(@RequestParam("details") String Id ) throws Exception{
		return new Gson().toJson(Context.getService(IntegrationService.class).getUserRegistration(Id));

	}

}
