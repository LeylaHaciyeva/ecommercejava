package demo.model;

import jakarta.persistence.*;

@Entity
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
//    @Column(unique = true)
    private String  mail;
    private String firstName;
    private String middleName;
    private String postCode;

    private Boolean isActive;


    public UserInformation(Long id, String mail, String firstName, String middleName, String postCode,Boolean isActive) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.postCode = postCode;
        this.isActive=isActive;
    }

    public UserInformation(String mail, String firstName, String middleName, String postCode,Boolean isActive) {
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.postCode = postCode;
        this.isActive=isActive;
    }

    public UserInformation() {
    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPostCode() {
        return postCode;
    }
    public Boolean getActive() {
        return isActive;
    }

}
