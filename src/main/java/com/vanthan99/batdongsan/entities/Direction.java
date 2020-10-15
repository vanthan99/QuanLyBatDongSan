package com.vanthan99.batdongsan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "direction")
@Data
@JsonIgnoreProperties({
        "posts"
})
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "direction", cascade = CascadeType.ALL)
    private List<Post> posts;
}
