package com.vanthan99.batdongsan.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vanthan99.batdongsan.entities.audit.DateAudit;
import javafx.geometry.Pos;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String content;

    @Column
    private boolean status = true;

    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reply> replies;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;
}
