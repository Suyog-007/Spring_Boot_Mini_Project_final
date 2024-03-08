package org.shah.springapp.ems.Domains;

import jakarta.persistence.*;
import lombok.Data;
import org.shah.springapp.ems.Services.ArrayListConvertor;
import org.shah.springapp.ems.Services.SkillConvertor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name = "demands")
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id")
    private String ProjectId;

    @Column(name = "project_name")
    private String ProjectName;

    @Column(name = "manager_name")
    private String MangerName;

    @Column(name = "manager_id")
    private String Manager_Id;

//    public enum positionLevel_enum{
//        A00, A01, A02, A03,
//        AS1, AS2,
//        P01, P02, P03, P04,
//        PS1,PS2, PS3, PS4,
//        M01, M02,M03,
//        E01,E02,E03,E04,
//        MS1,MS2,
//        ES1,ES2
//    }
//    @Column(name = "position_level")
//    @Enumerated(EnumType.STRING)
//    private positionLevel_enum level;

    @Column(name = "position_level")
    private String positionLevel;

    @Column(name = "city")
    private String city;
//

    @Column(name ="skills")
    @Convert(converter= ArrayListConvertor.class)
    private ArrayList<String> Skills;

    public void setSkills(ArrayList<String> skills) {
        Skills = skills;
    }

    @Column(name = "duration")
    private double Duration;

    @Column(name = "start_date")
    private  String StartDate;

    @Column(name ="status")
    private String status;

    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getManager_Id() {
        return Manager_Id;
    }

    public void setManager_Id(String manager_Id) {
        Manager_Id = manager_Id;
    }

    public String getMangerName() {
        return MangerName;
    }

    public void setMangerName(String mangerName) {
        MangerName = mangerName;
    }

    public String getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(String positionLevel) {
        ArrayList<String> positionLevels = new ArrayList<>(Arrays.asList(
                "A00", "A01", "A02", "A03",
                "AS1", "AS2",
                "P01", "P02", "P03", "P04",
                "PS1", "PS2", "PS3", "PS4",
                "M01", "M02", "M03",
                "E01", "E02", "E03", "E04",
                "MS1", "MS2",
                "ES1", "ES2"
        ));
        if(positionLevels.contains(positionLevel)){
            this.positionLevel = positionLevel;
        }else{
            throw new IllegalArgumentException("Invalid positionLevel: " + positionLevel);
        }
    }

    public double getDuration() {
        return Duration;
    }

    public void setDuration(double duration) {
        Duration = duration;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public ArrayList<String> getSkills() {
        return Skills;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        ArrayList<String> stats = new ArrayList<>(Arrays.asList("FULLFILLED","NOT FULLFILLED","OPEN"));
        if(stats.contains(status)){
            this.status = status;
        }else{
            throw new IllegalArgumentException("Invalid status: " + status);
        }

    }


}
