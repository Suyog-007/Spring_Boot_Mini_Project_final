package org.shah.springapp.ems.Domains;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "RequestDemands")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="demand_id")
    private Long Demand_id;

    @Column(name = "project_id")
    private String Project_Id;

    @Column(name = "project_name")
    private String Project_Name;

    @Column(name = "project_location")
    private  String project_location;

    @Column(name = "manger_id")
    private String Manger_Id;

    @Column(name = "manger_name")
    private String Manger_Name;

    @Column(name ="member_id")
    private long member_id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "member_name")
    private String Member_Name;

    @Column(name = "employee_location")
    private  String Member_location;

    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDemand_id() {
        return Demand_id;
    }

    public void setDemand_id(Long demand_id) {
        Demand_id = demand_id;
    }

    public String getProject_Id() {
        return Project_Id;
    }

    public void setProject_Id(String project_Id) {
        Project_Id = project_Id;
    }

    public String getProject_Name() {
        return Project_Name;
    }

    public void setProject_Name(String project_Name) {
        Project_Name = project_Name;
    }

    public String getProject_location() {
        return project_location;
    }

    public void setProject_location(String project_location) {
        this.project_location = project_location;
    }

    public String getManger_Id() {
        return Manger_Id;
    }

    public void setManger_Id(String manger_Id) {
        Manger_Id = manger_Id;
    }

    public String getManger_Name() {
        return Manger_Name;
    }

    public void setManger_Name(String manger_Name) {
        Manger_Name = manger_Name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getMember_Name() {
        return Member_Name;
    }

    public void setMember_Name(String member_Name) {
        Member_Name = member_Name;
    }

    public String getMember_location() {
        return Member_location;
    }

    public void setMember_location(String member_location) {
        Member_location = member_location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }
}
