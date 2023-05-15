package demo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private  String city;
    private  String address;
    private  String country;
    private  String postCode;
    private  String phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private  Users users;

    public UserDetails(Long id, String city, String address, String country, String postCode, String phoneNumber, Users users) {
        this.id = id;
        this.city = city;
        this.address = address;
        this.country = country;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.users = users;
    }

    public UserDetails(String city, String address, String country, String postCode, String phoneNumber, Users users) {
        this.city = city;
        this.address = address;
        this.country = country;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.users = users;
    }

    public UserDetails(String city, String address, String country, String postCode, String phoneNumber) {
        this.city = city;
        this.address = address;
        this.country = country;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
    }

    public UserDetails() {

    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Users getUsers() {
        return users;
    }
}
