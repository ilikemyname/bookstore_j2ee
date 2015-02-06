/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Phat Huy
 */
public class UserDTO implements Serializable {

    private int id;
    private String loginName;
    private String email;
    private String password;
    private String phone;
    private String fullName;
    private Date birthDate;
    private Date createDate;

    public UserDTO() {
    }

    public UserDTO(int id, String loginName, String email, String password, String phone, String fullName, Date birthDate, Date createDate) {
        this.id = id;
        this.loginName = loginName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.createDate = createDate;
    }

    public UserDTO(String loginName, String email, String password,
            String phone, String fullName, Date birthDate, Date createDate) {
        this.loginName = loginName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.createDate = createDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public void setBirthDate(Calendar birthday) {
//        birthDate = birthday;
//    }
}
