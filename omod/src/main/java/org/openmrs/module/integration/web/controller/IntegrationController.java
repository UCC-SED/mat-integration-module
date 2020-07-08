/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import static java.nio.file.Files.createTempFile;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/integration")
public class IntegrationController {
	
	protected final Log log;
	//private static final Log log = LogFactory.getLog(TempFile.class);
	protected  Path baseDir = FileSystems.getDefault().getPath("/media/sf_mat_temp");
	protected  String prefix = "MAT_";
	protected  String suffix = ".tmp";
	protected  Path tmpFile = null;
	public IntegrationController() {
		this.log = LogFactory.getLog((Class)this.getClass());
	}
	@RequestMapping(value = "test", method = RequestMethod.GET)
	@ResponseBody
	public void testing() {
		try {
			log.info((Object)"MAT:Temp file Init");
			tmpFile = createTempFile(baseDir, prefix, suffix);
			BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile.toFile()));
			bw.write("This is the temporary file content");
			bw.close();
			log.info((Object)"MAT:Temp file Created");
		} catch (IOException e) {
		}
		log.info((Object)"API worked: tested");
	}

	@RequestMapping(value = "test2", method = RequestMethod.GET)
	@ResponseBody
	public  String testing2(@RequestParam("test") String test1 ) throws Exception {
		try {
			log.info((Object)"MAT:Temp file Init");
			tmpFile = createTempFile(baseDir, prefix, suffix);
			BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile.toFile()));
			bw.write("This is the temporary file content");
			bw.close();
			log.info((Object)"MAT:Temp file Created");
			
		} catch (IOException e) {
		}
		log.info((Object)"API worked: tested");
		return "worked"+test1;
	}

}
