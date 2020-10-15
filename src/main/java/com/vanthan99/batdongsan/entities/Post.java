package com.vanthan99.batdongsan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vanthan99.batdongsan.entities.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@JsonIgnoreProperties({
        "comments",
        "viewCountStatistics"
})
public class Post extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column
    private boolean enable = true;

    @Column(length = 100)
    private String address;

    @Column
    private Double area;

    @Column
    private Long price;

    @Column
    private boolean deal = false;

    @Column
    private Long viewCount;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private boolean status = true;

    @Column
    private boolean approved;

    @Column
    private boolean customerType = true;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_type_id")
    private PostType postType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "direction_id")
    private Direction direction;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "post")
    @JsonManagedReference
    private List<PostImage> postImages;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "post")
    @JsonManagedReference
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "post")
    @JsonManagedReference
    private List<ViewCountStatistic> viewCountStatistics;
}
