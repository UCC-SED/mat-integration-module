/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api.db;

import java.text.ParseException;

public interface IntegrationDAO {

    String getUserPrescription(String OrderNumber, String actionStatus) throws ParseException;
}