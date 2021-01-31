package gr.hua.dit.ds.assignment030.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phd_candidate")
public class Candidates {

    @Id
    @Column(name = "ID", nullable = false)
    private int candidateId;

    @Column(name = "personellID", nullable = false)
    private String supervisorId;

    @Column(name = "User_name", nullable = false)
    private String username;

    @Column(name = "First_name", nullable = false)
    private String fname;

    @Column(name = "Last_name")
    private String lname;

    @Column(name = "SurveilancePoints", columnDefinition = "float default 0")
    private float sp;

    @Column(name = "targetSurveilancePoints", columnDefinition = "float default 0")
    private float tsp;

    @Column(name = "CorrectionPoints", columnDefinition = "float default 0")
    private float cp;

    @Column(name = "targetCorrectionPoints", columnDefinition = "float default 0")
    private float tcp;

    @Column(name = "TeachingPoints", columnDefinition = "float default 0")
    private float tp;

    @Column(name = "targetTeachingPoints", columnDefinition = "float default 0")
    private float ttp ;

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateid) {
        this.candidateId = candidateid;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String personellID) {
        this.supervisorId = personellID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public float getSp() {
        return sp;
    }

    public void setSp(float surveilancePoints) {
        this.sp = surveilancePoints;
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