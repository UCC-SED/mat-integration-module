/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api.db;

public interface IntegrationDAO {

    String getUserPrescription(String OrderNumber, String actionStatus);
}