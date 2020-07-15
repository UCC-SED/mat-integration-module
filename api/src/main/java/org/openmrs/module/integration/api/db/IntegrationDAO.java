/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration.api.db;

import java.util.List;

public interface IntegrationDAO {

    List getUserRegistration(String id);
    List getUserPrescription(String id);
}