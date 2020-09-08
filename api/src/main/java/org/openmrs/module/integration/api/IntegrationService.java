/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api;

import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Transactional
public interface IntegrationService extends OpenmrsService {
	String getUserPrescription(String OrderNumber, String actionStatus) throws ParseException;
}