package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.PatientSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonathan on 17/07/14.
 */
@DatabaseTable()
public class PatientDto implements Serializable, Parcelable {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer patientId;
    @DatabaseField()
    private Integer personId;
    private PersonDto person;
    @DatabaseField()
    private Integer maritalStatusId;
    private ConceptDto maritalStatus;
    @DatabaseField()
    private String ocupation;
    @DatabaseField()
    private Integer userTypeId;
    private ConceptDto userType;
    @DatabaseField()
    private Integer afiliateTypeId;
    private ConceptDto afiliateType;
    @DatabaseField()
    private Integer externalCauseId;
    private ConceptDto externalCause;
    @DatabaseField()
    private String momId;
    @DatabaseField()
    private String momName;
    @DatabaseField()
    private String dadId;
    @DatabaseField()
    private String dadName;
    @DatabaseField()
    private String responsableName;
    @DatabaseField()
    private String responsableFamily;
    @DatabaseField()
    private String responsablePhone;
    @DatabaseField()
    private String responsablePhone2;
    @DatabaseField()
    private String responsableKin;
    @DatabaseField()
    private Boolean hasAttendat;
    @DatabaseField()
    private String attendatName;
    @DatabaseField()
    private String attendatFamily;
    @DatabaseField()
    private String attendatPhone;
    @DatabaseField()
    private String attendatKin;
    @DatabaseField()
    private Integer preferredIdentifierId;
    private PatientIdentifierDto preferredIdentifier;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;
    @DatabaseField()
    private Integer creator;
    @DatabaseField()
    private Date dateCreated;

    public static final Parcelable.Creator<PatientDto> CREATOR = new Parcelable.Creator<PatientDto>() {
        public PatientDto createFromParcel(Parcel in) {
            return new PatientDto(in);
        }

        public PatientDto[] newArray(int size) {
            return new PatientDto[size];
        }
    };
    public PatientDto(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public PatientDto(Parcel patient) {
        id=(Integer)patient.readValue(null);
        patientId = (Integer)patient.readValue(Integer.class.getClassLoader());
        person = (PersonDto)patient.readValue(PersonDto.class.getClassLoader());
        maritalStatus = (ConceptDto)patient.readValue(ConceptDto.class.getClassLoader());
        ocupation = (String)patient.readValue(null);
        userType = (ConceptDto)patient.readValue(ConceptDto.class.getClassLoader());
        afiliateType = (ConceptDto)patient.readValue(ConceptDto.class.getClassLoader());
        externalCause = (ConceptDto)patient.readValue(ConceptDto.class.getClassLoader());
        momId = (String)patient.readValue(null);
        momName = (String)patient.readValue(null);
        dadId = (String)patient.readValue(null);
        dadName = (String)patient.readValue(null);
        responsableName = (String)patient.readValue(null);
        responsableFamily = (String)patient.readValue(null);
        responsablePhone = (String)patient.readValue(null);
        responsablePhone2 = (String)patient.readValue(null);
        responsableKin = (String)patient.readValue(null);
        hasAttendat = (Boolean) patient.readValue(Boolean.class.getClassLoader());
        attendatName = (String)patient.readValue(null);
        attendatFamily = (String)patient.readValue(null);
        attendatPhone = (String)patient.readValue(null);
        attendatKin = (String)patient.readValue(null);
        preferredIdentifier = (PatientIdentifierDto)patient.readValue(PatientIdentifierDto.class.getClassLoader());
        isSynchronized =(Boolean)patient.readValue(null);

        maritalStatusId=(Integer)patient.readValue(null);
        userTypeId=(Integer)patient.readValue(null);
        afiliateTypeId=(Integer)patient.readValue(null);
        externalCauseId=(Integer)patient.readValue(null);
        preferredIdentifierId=(Integer)patient.readValue(null);
    }

    public PatientDto(PatientSpringDto patient) {

        patientId = patient.getPatientId();
        if(patient.getPerson() != null)
        person = new PersonDto(patient.getPerson());
        if(patient.getMaritalStatus() != null)
        maritalStatus = new ConceptDto(patient.getMaritalStatus(), ConceptDto.TYPE_MARITAL_STATUS_ID);
        ocupation = patient.getOcupation();
        if(patient.getUserType() != null)
        userType = new ConceptDto(patient.getUserType(), ConceptDto.TYPE_USER_TYPE_ID);
        if(patient.getAfiliateType() != null)
        afiliateType = new ConceptDto(patient.getAfiliateType(), ConceptDto.TYPE_AFFILIATE_TYPE_ID);
        if(patient.getExternalCause() != null)
        externalCause = new ConceptDto(patient.getExternalCause(), ConceptDto.TYPE_CAUSA_EXTERNA_ID);
        momId = patient.getMomId();
        momName = patient.getMomName();
        dadId = patient.getDadId();
        dadName = patient.getDadName();
        responsableName = patient.getResponsableName();
        responsableFamily = patient.getResponsableFamily();
        responsablePhone = patient.getResponsablePhone();
        responsablePhone2 = patient.getResponsablePhone2();
        responsableKin = patient.getResponsableKin();
        hasAttendat = patient.getHasAttendat();
        attendatName = patient.getAttendatName();
        attendatFamily = patient.getAttendatFamily();
        attendatPhone = patient.getAttendatPhone();
        attendatKin =patient.getAttendatKin();
        if(patient.getPreferredIdentifier() != null)
        preferredIdentifier =new PatientIdentifierDto(patient.getPreferredIdentifier());
        isSynchronized = Boolean.TRUE;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public ConceptDto getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(ConceptDto maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }

    public ConceptDto getUserType() {
        return userType;
    }

    public void setUserType(ConceptDto userType) {
        this.userType = userType;
    }

    public ConceptDto getAfiliateType() {
        return afiliateType;
    }

    public void setAfiliateType(ConceptDto afiliateType) {
        this.afiliateType = afiliateType;
    }

    public ConceptDto getExternalCause() {
        return externalCause;
    }

    public void setExternalCause(ConceptDto externalCause) {
        this.externalCause = externalCause;
    }

    public String getMomId() {
        return momId;
    }

    public void setMomId(String momId) {
        this.momId = momId;
    }

    public String getMomName() {
        return momName;
    }

    public void setMomName(String momName) {
        this.momName = momName;
    }

    public String getDadId() {
        return dadId;
    }

    public void setDadId(String dadId) {
        this.dadId = dadId;
    }

    public String getDadName() {
        return dadName;
    }

    public void setDadName(String dadName) {
        this.dadName = dadName;
    }

    public String getResponsableName() {
        return responsableName;
    }

    public void setResponsableName(String responsableName) {
        this.responsableName = responsableName;
    }

    public String getResponsableFamily() {
        return responsableFamily;
    }

    public void setResponsableFamily(String responsableFamily) {
        this.responsableFamily = responsableFamily;
    }

    public String getResponsablePhone() {
        return responsablePhone;
    }

    public void setResponsablePhone(String responsablePhone) {
        this.responsablePhone = responsablePhone;
    }

    public String getResponsablePhone2() {
        return responsablePhone2;
    }

    public void setResponsablePhone2(String responsablePhone2) {
        this.responsablePhone2 = responsablePhone2;
    }

    public String getResponsableKin() {
        return responsableKin;
    }

    public void setResponsableKin(String responsableKin) {
        this.responsableKin = responsableKin;
    }

    public Boolean getHasAttendat() {
        return hasAttendat;
    }

    public void setHasAttendat(Boolean hasAttendat) {
        this.hasAttendat = hasAttendat;
    }

    public String getAttendatName() {
        return attendatName;
    }

    public void setAttendatName(String attendatName) {
        this.attendatName = attendatName;
    }

    public String getAttendatFamily() {
        return attendatFamily;
    }

    public void setAttendatFamily(String attendatFamily) {
        this.attendatFamily = attendatFamily;
    }

    public String getAttendatPhone() {
        return attendatPhone;
    }

    public void setAttendatPhone(String attendatPhone) {
        this.attendatPhone = attendatPhone;
    }

    public String getAttendatKin() {
        return attendatKin;
    }

    public void setAttendatKin(String attendatKin) {
        this.attendatKin = attendatKin;
    }

    public PatientIdentifierDto getPreferredIdentifier() {
        return preferredIdentifier;
    }

    public void setPreferredIdentifier(PatientIdentifierDto preferredIdentifier) {
        this.preferredIdentifier = preferredIdentifier;
    }

    public Boolean getIsSynchronized() {
        return isSynchronized;
    }

    public void setIsSynchronized(Boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(patientId);
        dest.writeValue(person);
        dest.writeValue(maritalStatus);
        dest.writeValue(ocupation);
        dest.writeValue(userType);
        dest.writeValue(afiliateType);
        dest.writeValue(externalCause);
        dest.writeValue(momId);
        dest.writeValue(momName);
        dest.writeValue(dadId);
        dest.writeValue(dadName);
        dest.writeValue(responsableName);
        dest.writeValue(responsableFamily);
        dest.writeValue(responsablePhone);
        dest.writeValue(responsablePhone2);
        dest.writeValue(responsableKin);
        dest.writeValue(hasAttendat);
        dest.writeValue(attendatName);
        dest.writeValue(attendatFamily);
        dest.writeValue(attendatPhone);
        dest.writeValue(attendatKin);
        dest.writeValue(preferredIdentifier);
        dest.writeValue(isSynchronized);

        dest.writeValue(maritalStatusId);
        dest.writeValue(userTypeId);
        dest.writeValue(afiliateTypeId);
        dest.writeValue(externalCauseId);
        dest.writeValue(preferredIdentifierId);
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Integer maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Integer getAfiliateTypeId() {
        return afiliateTypeId;
    }

    public void setAfiliateTypeId(Integer afiliateTypeId) {
        this.afiliateTypeId = afiliateTypeId;
    }

    public Integer getExternalCauseId() {
        return externalCauseId;
    }

    public void setExternalCauseId(Integer externalCauseId) {
        this.externalCauseId = externalCauseId;
    }

    public Integer getPreferredIdentifierId() {
        return preferredIdentifierId;
    }

    public void setPreferredIdentifierId(Integer preferredIdentifierId) {
        this.preferredIdentifierId = preferredIdentifierId;
    }

    public void setupId(PatientDto patient)
    {
        id = patient.getId();
        if(person != null)
        {
            person.setupId(patient.getPerson());
        }
        if(preferredIdentifier != null)
        {
            preferredIdentifier.setupId(patient.getPreferredIdentifier());
        }
    }
}
