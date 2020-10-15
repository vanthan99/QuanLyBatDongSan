package com.vanthan99.batdongsan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post_type")
@Data
@JsonIgnoreProperties({
        "posts"
})
public class PostType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column
    private boolean status = true;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "postType",cascade = CascadeType.ALL)
    private List<Post> posts;
}
