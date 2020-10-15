package com.vanthan99.batdongsan.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vanthan99.batdongsan.entities.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@JsonIgnoreProperties({
        "posts",
        "password",
        "accountReports"
})
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String address;

    @Column(length = 10)
    private String phoneNumber;

    @Column(length = 50)
    private String email;

    @Column(length = 60)
    private String password;

    @Column
    private String avatar;

    @Column
    private boolean status = true;

    @Column
    private boolean activated;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    @JsonManagedReference
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    private Set<AccountReport> accountReports;

    public void addAccountReport(AccountReport accountReport){
        if (accountReports == null){
            accountReports = new HashSet<>();
        }
        accountReports.add(accountReport);
    }
}
