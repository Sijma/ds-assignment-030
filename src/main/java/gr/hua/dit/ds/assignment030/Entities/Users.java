package gr.hua.dit.ds.assignment030.Entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@DynamicInsert
@DynamicUpdate
public class Users
{

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "authority", nullable = false)
    private String authority;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Professors professor;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Candidates candidate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Professors getProfessor()
    {
        return professor;
    }

    public void setProfessor(Professors professor)
    {
        this.professor = professor;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() { return authority; }

    public void setAuthority(String authority) { this.authority = authority; }

    public Candidates getCandidate()
    {
        return candidate;
    }

    public void setCandidate(Candidates candidate)
    {
        this.candidate = candidate;
    }
}