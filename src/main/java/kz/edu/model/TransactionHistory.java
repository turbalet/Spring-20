package kz.edu.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TransactionHistoryEntity")
@Table(name = "transaction_log")
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "transaction_date")
    private Date transactionDate;

//    @ManyToOne
//    @JoinColumn(name = "sender_id")
//    private Account senderId;
//
//    @ManyToOne
//    @JoinColumn(name = "receiver_id")
//    private Account receiverId;

    @Column(name = "sender_id")
    private int senderId;

    @Column(name = "receiver_id")
    private int receiverId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TransactionType type;

    public TransactionHistory(Date transactionDate, int senderId, int receiverId, double amount, String comments, TransactionType type) {
        this.transactionDate = transactionDate;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.comments = comments;
        this.type = type;
    }

    public TransactionHistory(Date transactionDate, int senderId, double amount) {
        this.transactionDate = transactionDate;
        this.senderId = senderId;
        this.amount = amount;
    }
}
