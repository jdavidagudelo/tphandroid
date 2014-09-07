package com.artica.telesalud.tph.android.lightweightmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.artica.telesalud.tph.android.model.UserSpringDto;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable()
public class UserDto implements Serializable, Parcelable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @DatabaseField(id=true)
    private Integer userId;
    @DatabaseField(index = true)
    private String userName;
    @DatabaseField(index = true)
    private String personName;
    @DatabaseField(index = true)
    private String roleName;
    @DatabaseField(index = true)
    private Integer role;
    public static final Parcelable.Creator<UserDto> CREATOR = new Parcelable.Creator<UserDto>() {
        public UserDto createFromParcel(Parcel in) {
            return new UserDto(in);
        }

        public UserDto[] newArray(int size) {
            return new UserDto[size];
        }
    };
    public UserDto(Parcel p)
    {
        userId = (Integer)p.readValue(null);
        userName = (String)p.readValue(null);
        personName = (String)p.readValue(null);
        roleName = (String)p.readValue(null);
        role = (Integer)p.readValue(null);
    }
    public UserDto(UserSpringDto user)
    {

        userId = user.getUserId();
        userName = user.getUserName();
        personName = user.getPersonName();
        roleName = user.getRoleName();
        role = user.getRole();
    }
    public UserDto()
    {

    }
    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return the personName
     */
    public String getPersonName() {
        return personName;
    }
    /**
     * @param personName the personName to set
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }
    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**
     * @return the role
     */
    public Integer getRole() {
        return role;
    }
    /**
     * @param role the role to set
     */
    public void setRole(Integer role) {
        this.role = role;
    }
    @Override
    public void writeToParcel(Parcel parcel, int args) {
        parcel.writeValue(userId);
        parcel.writeValue(userName);
        parcel.writeValue(personName);
        parcel.writeValue(roleName);
        parcel.writeValue(role);
    }
    @Override
    public int describeContents() {

        return 0;
    }
}