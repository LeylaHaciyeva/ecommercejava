package demo.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    //    @Column(unique = true)
    private String mail;
    private String firstName;
    private String middleName;
    private String postCode;

    private Boolean isActive;
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<UserDetails> userDetails;



    public Users(Long id, String mail, String firstName, String middleName, String postCode, Boolean isActive) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.postCode = postCode;
        this.isActive = isActive;

    }

    public Users(String mail, String firstName, String middleName, String postCode, Boolean isActive) {
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.postCode = postCode;
        this.isActive = isActive;
    }

    public Users() {
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
    public Set<UserDetails> getUserDetails() {
        return userDetails;
    }
}
