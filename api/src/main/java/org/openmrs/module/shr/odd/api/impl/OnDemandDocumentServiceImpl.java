package org.openmrs.module.shr.odd.api.impl;

import java.util.Date;
import java.util.List;

import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.ClinicalDocument;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.shr.odd.api.OnDemandDocumentService;
import org.openmrs.module.shr.odd.api.db.OnDemandDocumentDAO;
import org.openmrs.module.shr.odd.generator.DocumentGenerator;
import org.openmrs.module.shr.odd.model.OnDemandDocumentEncounterLink;
import org.openmrs.module.shr.odd.model.OnDemandDocumentRegistration;
import org.openmrs.module.shr.odd.model.OnDemandDocumentType;

/**
 * The On-demand document service implementation
 */
public class OnDemandDocumentServiceImpl extends BaseOpenmrsService implements OnDemandDocumentService {
	
	// Dao
	private OnDemandDocumentDAO dao;

	/**
	 * Save an on-demand document
	 * @see org.openmrs.module.shr.odd.api.OnDemandDocumentService#saveOnDemandDocument(org.openmrs.module.shr.odd.model.OnDemandDocumentRegistration)
	 */
	@Override
    public OnDemandDocumentRegistration saveOnDemandDocument(OnDemandDocumentRegistration registrationEntry) {
		registrationEntry.setDateChanged(new Date());
		registrationEntry.setChangedBy(Context.getAuthenticatedUser());
		return this.dao.saveOnDemandDocumentRegistration(registrationEntry);
    }

	/**
	 * Generate an on-demand document
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @see org.openmrs.module.shr.odd.api.OnDemandDocumentService#generateOnDemandDocument(org.openmrs.module.shr.odd.model.OnDemandDocumentRegistration)
	 */
	@Override
    public ClinicalDocument generateOnDemandDocument(OnDemandDocumentRegistration registrationEntry) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		// Validate
		if(registrationEntry == null)
			throw new IllegalArgumentException("registrationEntry must not be null");
		else if(registrationEntry.getType() == null)
			throw new IllegalStateException("registrationEntry must carry type");
		
		// Get the class listed in the registration of type
        @SuppressWarnings("unchecked")
        Class<? extends DocumentGenerator> clazz = (Class<? extends DocumentGenerator>)Class.forName(registrationEntry.getType().getJavaClassName());

        // Now instantiate and generate
        DocumentGenerator generatorInstance = clazz.newInstance();
        return generatorInstance.generateDocument(registrationEntry);
		
    }

	/**
	 * Determine if the ODD is registered
	 * @see org.openmrs.module.shr.odd.api.OnDemandDocumentService#isOnDemandDocumentRegistered(java.lang.String)
	 */
	@Override
    public boolean isOnDemandDocumentRegistered(String accessionNumber) {
		return this.dao.getOnDemandDocumentRegistrationsByAccessionNumber(accessionNumber, true).size() != 0;
    }

	/**
	 * Get the on demand document registration by id
	 * @see org.openmrs.module.shr.odd.api.OnDemandDocumentService#getOnDemandDocumentRegistrationById(java.lang.Integer)
	 */
	@Override
    public OnDemandDocumentRegistration getOnDemandDocumentRegistrationById(Integer id) {
		return this.dao.getOnDemandDocumentRegistrationById(id);
    }

	/**
	 * Get on demand document registration by uuid
	 * @see org.openmrs.module.shr.odd.api.OnDemandDocumentService#getOnDemandDocumentRegistrationByUuid(java.lang.String)
	 */
	@Override
    public OnDemandDocumentRegistration getOnDemandDocumentRegistrationByUuid(String uuid) {
		return this.dao.getOnDemandDocumentRegistrationByUuid(uuid);
    }

	/**
	 * Get on demand document registration by accession number
	 * @see org.openmrs.module.shr.odd.api.OnDemandDocumentService#getOnDemandDocumentRegistrationByAccessionNumber(java.lang.String)
	 */
	@Override
    public List<OnDemandDocumentRegistration> getOnDemandDocumentRegistrationsByAccessionNumber(String accessionNumber) {
		return this.dao.getOnDemandDocumentRegistrationsByAccessionNumber(accessionNumber, false);
    }

	/**
	 * Get on demand document registrations by patient
	 * @see org.openmrs.module.shr.odd.api.OnDemandDocumentService#getOnDemandDocumentRegistrationsByPatient(org.openmrs.Patient)
	 */
	@Override
    public List<OnDemandDocumentRegistration> getOnDemandDocumentRegistrationsByPatient(Patient patient) {
		return this.dao.getOnDemandDocumentRegistrationsByPatient(patient, false);
    }

	/**
	 * Get an on-demand document registration type by uuid
	 * @see org.openmrs.module.shr.odd.api.OnDemandDocumentService#getOnDemandDocumentTypeByUud(java.lang.String)
	 */
	@Override
    public OnDemandDocumentType getOnDemandDocumentTypeByUud(String uuid) {
		return this.dao.getOnDemandDocumentTypeByUuid(uuid);
    }

	/**
	 * Save an on-demand document type
	 * @see org.openmrs.module.shr.odd.api.OnDemandDocumentService#saveOnDemandDocumentType(org.openmrs.module.shr.odd.model.OnDemandDocumentType)
	 */
	@Override
    public OnDemandDocumentType saveOnDemandDocumentType(OnDemandDocumentType documentType) {
		documentType.setDateChanged(new Date());
		documentType.setChangedBy(Context.getAuthenticatedUser());
		return this.dao.saveOnDemandDocumentType(documentType);
    }

	/**
	 * Get encounters related to an on-demand document registration
	 * @see org.openmrs.module.shr.odd.api.OnDemandDocumentService#getOnDemandDocumentEncounters(org.openmrs.module.shr.odd.model.OnDemandDocumentRegistration)
	 */
	@Override
    public List<OnDemandDocumentEncounterLink> getOnDemandDocumentEncounters(OnDemandDocumentRegistration oddRegistration) {
		return this.dao.getOnDemandDocumentEncounterLinks(oddRegistration, false);
    }

	
    /**
     * @param dao the dao to set
     */
    public void setDao(OnDemandDocumentDAO dao) {
    	this.dao = dao;
    }
	
	
	
}
