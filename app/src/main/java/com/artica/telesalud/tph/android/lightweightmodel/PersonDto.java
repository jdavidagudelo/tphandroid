package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.PersonSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jonathan on 17/07/14.
 */
@DatabaseTable()
public class PersonDto implements Serializable, Parcelable{
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField()
    private Integer personId;
    @DatabaseField()
    private String gender;
    @DatabaseField()
    private Date birthdate;
    @DatabaseField()
    private Boolean birthdateEstimated;
    @DatabaseField()
    private Integer specialty;
    @DatabaseField()
    private String identification;
    @DatabaseField()
    private Boolean dead;
    @DatabaseField()
    private Date deathDate;
    @DatabaseField()
    private Integer causeOfDeathId;
    private ConceptDto causeOfDeath;
    @DatabaseField()
    private Integer age;
    @DatabaseField()
    private String registro;
    @DatabaseField()
    private String firma;
    @DatabaseField()
    private Integer erpId;
    private ConceptDto erp;

    @DatabaseField()
    private Integer nivelId;
    private ConceptDto nivel;
    @DatabaseField()
    private Integer controlAphId;
    private ConceptDto controlAph;
    @DatabaseField()
    private Integer preferredNameId;
    private PersonNameDto preferredName;
    @DatabaseField()
    private Integer preferredAddressId;
    private PersonAddressDto preferredAddress;
    @DatabaseField()
    private Boolean isSynchronized = Boolean.FALSE;
    @DatabaseField()
    private Integer creator;
    @DatabaseField()
    private Date dateCreated;

    public static final Parcelable.Creator<PersonDto> CREATOR = new Parcelable.Creator<PersonDto>() {
        public PersonDto createFromParcel(Parcel in) {
            return new PersonDto(in);
        }

        public PersonDto[] newArray(int size) {
            return new PersonDto[size];
        }
    };
public PersonDto(){

}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonDto(Parcel in) {
        id =(Integer)in.readValue(null);
        personId = (Integer)in.readValue(Integer.class.getClassLoader());
        gender = (String)in.readValue(null);
        birthdate = (Date) in.readValue(Date.class.getClassLoader());
        birthdateEstimated = (Boolean) in.readValue(Date.class.getClassLoader());
        specialty = (Integer)in.readValue(Integer.class.getClassLoader());
        identification = (String)in.readValue(null);
        dead = (Boolean) in.readValue(Boolean.class.getClassLoader());
        deathDate = (Date) in.readValue(Date.class.getClassLoader());
        causeOfDeath = (ConceptDto)in.readValue(ConceptDto.class.getClassLoader());
        age = (Integer)in.readValue(Integer.class.getClassLoader());
        registro = (String)in.readValue(null);
        firma = (String)in.readValue(null);
        erp = (ConceptDto)in.readValue(ConceptDto.class.getClassLoader());
        nivel = (ConceptDto)in.readValue(ConceptDto.class.getClassLoader());
        controlAph = (ConceptDto)in.readValue(ConceptDto.class.getClassLoader());
        preferredName = (PersonNameDto)in.readValue(PersonNameDto.class.getClassLoader());
        preferredAddress = (PersonAddressDto)in.readValue(PersonAddressDto.class.getClassLoader());
        isSynchronized = (Boolean)in.readValue(null);

        causeOfDeathId=(Integer)in.readValue(Integer.class.getClassLoader());

        erpId= (Integer)in.readValue(Integer.class.getClassLoader());

        nivelId= (Integer)in.readValue(Integer.class.getClassLoader());
        controlAphId= (Integer)in.readValue(Integer.class.getClassLoader());
         preferredNameId= (Integer)in.readValue(Integer.class.getClassLoader());
        preferredAddressId= (Integer)in.readValue(Integer.class.getClassLoader());
    }

    public PersonDto(PersonSpringDto person) {
        personId = person.getPersonId();
        gender = person.getGender();
        birthdate = person.getBirthdate();
        birthdateEstimated = person.getBirthdateEstimated();
        specialty = person.getSpecialty();
        identification = person.getIdentification();
        dead = person.getDead();
        deathDate = person.getDeathDate();
        if(person.getCauseOfDeath() != null) {
            causeOfDeath = new ConceptDto(person.getCauseOfDeath(), ConceptDto.TYPE_CAUSA_MUERTE_ID);
        }
        age = person.getAge();
        registro = person.getRegistro();
        firma = person.getFirma();
        if(person.getErp() != null)
        erp = new ConceptDto(person.getErp(), ConceptDto.TYPE_ERP_ID);
        if(person.getNivel() != null)
        nivel = new ConceptDto(person.getNivel(), ConceptDto.TYPE_NIVEL_ID);
        if(person.getControlAph() != null)
        controlAph = new ConceptDto(person.getControlAph(), ConceptDto.TYPE_CONTROL_APH_ID);
        if(person.getPreferredName() != null)
        preferredName = new PersonNameDto(person.getPreferredName());
        if(person.getPreferredAddress() != null)
        preferredAddress = new PersonAddressDto(person.getPreferredAddress());
        isSynchronized = Boolean.TRUE;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Boolean getBirthdateEstimated() {
        return birthdateEstimated;
    }

    public void setBirthdateEstimated(Boolean birthdateEstimated) {
        this.birthdateEstimated = birthdateEstimated;
    }

    public Integer getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Integer specialty) {
        this.specialty = specialty;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Boolean getDead() {
        return dead;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public ConceptDto getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(ConceptDto causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public ConceptDto getErp() {
        return erp;
    }

    public void setErp(ConceptDto erp) {
        this.erp = erp;
    }

    public ConceptDto getNivel() {
        return nivel;
    }

    public void setNivel(ConceptDto nivel) {
        this.nivel = nivel;
    }

    public ConceptDto getControlAph() {
        return controlAph;
    }

    public void setControlAph(ConceptDto controlAph) {
        this.controlAph = controlAph;
    }

    public PersonNameDto getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(PersonNameDto preferredName) {
        this.preferredName = preferredName;
    }

    public PersonAddressDto getPreferredAddress() {
        return preferredAddress;
    }

    public void setPreferredAddress(PersonAddressDto preferredAddress) {
        this.preferredAddress = preferredAddress;
    }

    public Boolean getIsSynchronized() {
        return isSynchronized;
    }

    public void setIsSynchronized(Boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }

    public Integer getCauseOfDeathId() {
        return causeOfDeathId;
    }

    public void setCauseOfDeathId(Integer causeOfDeathId) {
        this.causeOfDeathId = causeOfDeathId;
    }

    public Integer getErpId() {
        return erpId;
    }

    public void setErpId(Integer erpId) {
        this.erpId = erpId;
    }

    public Integer getNivelId() {
        return nivelId;
    }

    public void setNivelId(Integer nivelId) {
        this.nivelId = nivelId;
    }

    public Integer getControlAphId() {
        return controlAphId;
    }

    public void setControlAphId(Integer controlAphId) {
        this.controlAphId = controlAphId;
    }

    public Integer getPreferredNameId() {
        return preferredNameId;
    }

    public void setPreferredNameId(Integer preferredNameId) {
        this.preferredNameId = preferredNameId;
    }

    public Integer getPreferredAddressId() {
        return preferredAddressId;
    }

    public void setPreferredAddressId(Integer preferredAddressId) {
        this.preferredAddressId = preferredAddressId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(personId);
        dest.writeValue(gender);
        dest.writeValue(birthdate);
        dest.writeValue(birthdateEstimated);
        dest.writeValue(specialty);
        dest.writeValue(identification);
        dest.writeValue(dead);
        dest.writeValue(deathDate);
        dest.writeValue(causeOfDeath);
        dest.writeValue(age);
        dest.writeValue(registro);
        dest.writeValue(firma);
        dest.writeValue(erp);
        dest.writeValue(nivel);
        dest.writeValue(controlAph);
        dest.writeValue(preferredName);
        dest.writeValue(preferredAddress);
        dest.writeValue(isSynchronized);

        dest.writeValue(causeOfDeathId);

        dest.writeValue(erpId);

        dest.writeValue(nivelId);
        dest.writeValue(controlAphId);
        dest.writeValue(preferredNameId);
        dest.writeValue(preferredAddressId);
    }
    public void setupId(PersonDto person)
    {
        id = person.getId();
        if(preferredAddress != null)
        {
            preferredAddress.setupId(person.getPreferredAddress());
        }
        if(preferredName != null)
        {
            preferredName.setupId(person.getPreferredName());
        }
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
}
