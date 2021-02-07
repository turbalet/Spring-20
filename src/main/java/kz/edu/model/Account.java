package kz.edu.model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity(name = "AccountEntity")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "account_number")
    @CreditCardNumber(message = "Not a valid card number")
    private String accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "balance")
    private double balance;

    @Column(name = "created")
    private Date created;

    @Column(name = "expires")
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String expires;

    @OneToMany(mappedBy = "receiverId", cascade=CascadeType.ALL)
    private Set<TransactionHistory> receivers;

    @OneToMany(mappedBy = "senderId", cascade=CascadeType.ALL)
    private Set<TransactionHistory> senders;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;






}
