/**
 *
 * UCC:George Yusto
 *
 */
package org.openmrs.module.integration;

import org.openmrs.BaseOpenmrsObject;
import java.io.Serializable;

public class Integration extends BaseOpenmrsObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
}