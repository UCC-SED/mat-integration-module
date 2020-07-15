/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api;

import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IntegrationService extends OpenmrsService {
	List getUserRegistration(String id);
	List getUserPrescription(String id);
}