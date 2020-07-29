/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api.db;

public interface IntegrationDAO {

    String getUserPrescription(String OrderNumber, String actionStatus);
    String addOrderLog(String orderNumber, String logType, String logMessage);
    
}