/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api;

import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import static org.junit.Assert.assertNotNull;


public class IntegrationServiceTest extends BaseModuleContextSensitiveTest {
	
	@Test
	public void shouldSetupContext() {
		assertNotNull(Context.getService(IntegrationService.class));
	}
}
