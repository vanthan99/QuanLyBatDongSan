package com.vanthan99.batdongsan.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vanthan99.batdongsan.entities.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "post_image")
@Getter
@Setter
public class PostImage extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String image;

    @Column
    private boolean status = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;
}
