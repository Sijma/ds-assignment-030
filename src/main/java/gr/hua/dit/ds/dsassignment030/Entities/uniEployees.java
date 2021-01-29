package gr.hua.dit.ds.dsassignment030.Entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Uni_Employees")
public class uniEployees {
    @Id
    @Column(name = "personellID", nullable = false)
    private int personellID;
    @Column(name = "User_name", nullable = false)
    private String username;
    @Column(name = "pass_word", nullable = false)
    private String password;
    @Column(name = "authority", nullable = false)
    private String authority;

    public int getPersonellID() {
        return personellID;
    }

    public void setPersonellID(int personellID) {
        this.personellID = personellID;
    }

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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}