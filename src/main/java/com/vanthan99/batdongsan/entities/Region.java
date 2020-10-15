package com.vanthan99.batdongsan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "region")
@Data
@JsonIgnoreProperties({
        "posts"
})
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "region",cascade = CascadeType.ALL)
    private List<Post> posts;

}
