package gr.hua.dit.ds.assignment030.Entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "supervising_proffesor")
@DynamicInsert
@DynamicUpdate
public class Professors
{
    @Id
    @Column(name = "personellID", nullable = false)
    private String personellID;

    @OneToOne
    @JoinColumn(name = "User_name", referencedColumnName = "username")
    private Users user;

    @Column(name = "First_name", nullable = false)
    private String fname;

    @Column(name = "Last_name", nullable = false)
    private String lname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private List<Candidates> candidatesList;

    public String getPersonellID() {
        return personellID;
    }

    public void setPersonellID(String personellID) {
        this.personellID = personellID;
    }

    public Users getUser()
    {
        return user;
    }

    public void setUser(Users user)
    {
        this.user = user;
    }

    public List<Candidates> getCandidatesList()
    {
        return candidatesList;
    }

    public void setCandidatesList(List<Candidates> candidatesList)
    {
        this.candidatesList = candidatesList;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
