package kz.edu.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "UserEntity")
@Table(name = "users")
public class User implements Serializable
{
    private long user_id;
    private String email;
    private String password;
    private Role role;


    private String firstName;

    private String lastName;

    private Date birthday;

    private String phoneNumber;

    private Set<Account> accounts;

    private Set<Favourite> favourites;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public long getId()
    {
        return this.user_id;
    }
    public void setId(long user_id)
    {
        this.user_id = user_id;
    }

    @Column(name = "email")
    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {this.email = email;}

    @Column(name = "password")
    public String getPassword()
    {
        return this.password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    public Role getRole()
    {
        return role;
    }
    public void setRole(Role role)
    {
        this.role = role;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public Set<Account> getAccounts() { return accounts; }
    public void setAccounts(Set<Account> accounts) { this.accounts = accounts; }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public Set<Favourite> getFavourites() { return favourites; }
    public void setFavourites(Set<Favourite> favourites) { this.favourites = favourites; }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "birthday")
    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}