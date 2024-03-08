package org.shah.springapp.ems.Domains;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import org.shah.springapp.ems.Services.SkillConvertor;

import java.io.IOException;
import java.util.*;

@Entity
@Data
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_joining")
    private String dateOfJoining;

    @Column(name = "location")
    private String location;

    @Column(name = "experience")
    private Integer experience;

    @Column(name ="skills")
@Convert(converter= SkillConvertor.class)
private Map<String,Integer> Skills = new HashMap<>();

    public void setSkills(Map<String, Integer> skills) {
        Skills = skills;
    }

//    public enum status_enum{
//        AVAILABLE,ASSIGNED
//    }
//    @Column(name = "status")
//    @Enumerated(EnumType.STRING)
//    private status_enum status;

    @Column(name ="status")
    private String status;
//    public enum positionLevel_enum{
//        A00, A01, A02, A03,
//        AS1, AS2,
//        P01, P02, P03, P04,
//        PS1,PS2, PS3, PS4,
//        M01, M02,M03,
//        E01,E02,E03,E04,
//        MS1,MS2,
//        ES1,ES2;
//    }
//    @Column(name = "position_level")
//    @Enumerated(EnumType.STRING)
//    private positionLevel_enum positionLevel;

    @Column(name ="position_level")
    private String positionLevel;

    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public positionLevel_enum getPositionLevel() {
//        return positionLevel;
//    }
//
//    public void setPositionLevel(positionLevel_enum positionLevel) {
//        this.positionLevel = positionLevel;
//    }

//    public status_enum getStatus() {
//        return status;
//    }
//
//    public void setStatus(status_enum status) {
//        this.status = status;
//    }


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

    public Map<String, Integer> getSkills() {
        return Skills;
    }

//    public void setStatus(String status) {
//        this.status = status;
//    }
public void setStatus(String status) {
    ArrayList<String> stats = new ArrayList<>(Arrays.asList("AVAILABLE","ASSIGNED"));
    if(stats.contains(status)){
        this.status = status;
    }else{
        throw new IllegalArgumentException("Invalid status: " + status);
    }

}
    public String getStatus() {
        return status;
    }
}
