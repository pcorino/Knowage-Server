package it.eng.spagobi.functions.metadata;

import java.util.UUID;

import it.eng.spagobi.analiticalmodel.document.metadata.SbiObjects;
import it.eng.spagobi.commons.metadata.SbiHibernateModel;

public class SbiObjFunction extends SbiHibernateModel {

	private static final long serialVersionUID = -329164608772260158L;

	private Integer biObjFunctionId;
	private SbiObjects sbiObject;
	private UUID functionUuid;

	/**
	 * default constructor.
	 */
	public SbiObjFunction() {
		this.biObjFunctionId = -1;

	}

	/**
	 * constructor with id.
	 */
	public SbiObjFunction(Integer biObjFunctionId) {
		this.biObjFunctionId = biObjFunctionId;
	}

	public Integer getBiObjFunctionId() {
		return biObjFunctionId;
	}

	public void setBiObjFunctionId(Integer biObjFunctionId) {
		this.biObjFunctionId = biObjFunctionId;
	}

	public SbiObjects getSbiObject() {
		return sbiObject;
	}

	public void setSbiObject(SbiObjects sbiObject) {
		this.sbiObject = sbiObject;
	}

	public UUID getFunctionUuid() {
		return functionUuid;
	}

	public void setFunctionUuid(UUID functionUuid) {
		this.functionUuid = functionUuid;
	}
}
