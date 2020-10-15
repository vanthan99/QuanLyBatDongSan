package com.vanthan99.batdongsan.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "view_count_statistic")
@Data
public class ViewCountStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,updatable = false)
    private Date dateStatistic;

    @Column(nullable = false)
    private Long viewCount = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;
}
