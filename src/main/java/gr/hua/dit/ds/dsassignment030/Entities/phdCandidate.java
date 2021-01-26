package gr.hua.dit.ds.dsassignment030.Entities;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "PhD_Candidate")

public class phdCandidate {


    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "personellID", nullable = false)
    private int personellID;
    @Column(name = "User_name", nullable = false)
    private String username;
    @Column(name = "pass_word", nullable = false)
    private String password;
    @Column(name = "authority", nullable = false)
    private String authority;
    @Column(name = "First_name", nullable = false)
    private String fname;
    @Column(name = "Last_name", nullable = false)
    private String lname;
    @Column(name = "SurveilancePoints" )
   private   float surveilancePoints ;
    @Column(name = "targetSurveilancePoints" )
        private float tsp;
    @Column(name = "CorrectionPoints" )
    private float cp;
    @Column(name = "targetCorrectionPoints" )
       private float tcp;
    @Column(name = "TeachingPoints" )
    private float tp;
    @Column(name = "targetTeachingPoints" )
    private float ttp ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public float getSurveilancePoints() {
        return surveilancePoints;
    }

    public void setSurveilancePoints(float surveilancePoints) {
        this.surveilancePoints = surveilancePoints;
    }

    public float getTsp() {
        return tsp;
    }

    public void setTsp(float tsp) {
        this.tsp = tsp;
    }

    public float getCp() {
        return cp;
    }

    public void setCp(float cp) {
        this.cp = cp;
    }

    public float getTcp() {
        return tcp;
    }

    public void setTcp(float tcp) {
        this.tcp = tcp;
    }

    public float getTp() {
        return tp;
    }

    public void setTp(float tp) {
        this.tp = tp;
    }

    public float getTtp() {
        return ttp;
    }

    public void setTtp(float ttp) {
        this.ttp = ttp;
    }
}