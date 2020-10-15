package com.vanthan99.batdongsan.entities;

import lombok.Data;

import javax.persistence.*;

@Table(name = "account_report")
@Entity
@Data
public class AccountReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @Column
    private boolean status = true;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
