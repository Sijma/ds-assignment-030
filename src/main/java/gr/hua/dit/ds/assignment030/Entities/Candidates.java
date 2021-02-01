package gr.hua.dit.ds.assignment030.Entities;

import javax.persistence.*;

@Entity
@Table(name = "phd_candidate")
public class Candidates {

    @Id
    @Column(name = "ID", nullable = false)
    private int candidateId;


    @ManyToOne
    @JoinColumn(name = "personellID")
    private Professors professor;

    @OneToOne
    @JoinColumn(name = "User_name", referencedColumnName = "username")
    private Users user;

    @Column(name = "First_name", nullable = false)
    private String fname;

    @Column(name = "Last_name", nullable = false)
    private String lname;

    @Column(name = "surveilance_points", columnDefinition = "float default 0")
    private float sp;

    @Column(name = "target_surveilance_points", columnDefinition = "float default 0")
    private float tsp;

    @Column(name = "correction_Points", columnDefinition = "float default 0")
    private float cp;

    @Column(name = "target_correction_points", columnDefinition = "float default 0")
    private float tcp;

    @Column(name = "Teaching_Points", columnDefinition = "float default 0")
    private float tp;

    @Column(name = "target_teaching_points", columnDefinition = "float default 0")
    private float ttp ;

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateid) {
        this.candidateId = candidateid;
    }

    public Professors getProfessor()
    {
        return professor;
    }

    public void setProfessor(Professors professor)
    {
        this.professor = professor;
    }

    public Users getUser()
    {
        return user;
    }

    public void setUser(Users user)
    {
        this.user = user;
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