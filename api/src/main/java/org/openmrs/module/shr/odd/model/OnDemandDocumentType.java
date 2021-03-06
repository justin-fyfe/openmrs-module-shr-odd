package org.openmrs.module.shr.odd.model;

import java.util.UUID;

import org.openmrs.BaseOpenmrsMetadata;

/**
 * Identifies a type of ODD
 */
public class OnDemandDocumentType extends BaseOpenmrsMetadata {

	// The Id of the ODD type
	private Integer typeId;
	// The name of the class 
	private String javaClassName;
	// The name of the type
	private String name;
	// Format code
	private String formatCode;
    /**
     * @return the id
     */
    public Integer getId() {

    	return typeId;
    }
	
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
    	this.typeId = id;
    }
	
    /**
     * @return the javaClassName
     */
    public String getJavaClassName() {
    	return javaClassName;
    }
	
    /**
     * @param javaClassName the javaClassName to set
     */
    public void setJavaClassName(String javaClassName) {
    	this.javaClassName = javaClassName;
    }
	
    /**
     * @return the name
     */
    public String getName() {
    	return name;
    }
	
    /**
     * @param name the name to set
     */
    public void setName(String name) {
    	this.name = name;
    }

	
    /**
     * @return the formatCode
     */
    public String getFormatCode() {
    	return formatCode;
    }

	
    /**
     * @param formatCode the formatCode to set
     */
    public void setFormatCode(String formatCode) {
    	this.formatCode = formatCode;
    }
	
	
	
}
