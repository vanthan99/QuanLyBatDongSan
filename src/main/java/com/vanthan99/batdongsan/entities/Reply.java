package com.vanthan99.batdongsan.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vanthan99.batdongsan.entities.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reply")
@Getter
@Setter
public class Reply extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column
    private boolean status = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    @JsonBackReference
    private Comment comment;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
