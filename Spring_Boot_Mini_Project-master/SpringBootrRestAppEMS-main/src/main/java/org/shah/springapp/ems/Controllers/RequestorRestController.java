package org.shah.springapp.ems.Controllers;

import org.shah.springapp.ems.Domains.Demand;
import org.shah.springapp.ems.Domains.Member;
import org.shah.springapp.ems.Domains.Request;
import org.shah.springapp.ems.Repository.DemandRepository;
import org.shah.springapp.ems.Repository.MemberRepository;
import org.shah.springapp.ems.Repository.RequestRepository;
import org.shah.springapp.ems.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requestor")
public class RequestorRestController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DemandRepository demandRepository;

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") long id) {
        Optional<Member> memberData = memberRepository.findById(id);

        if (memberData.isPresent()) {
            return new ResponseEntity<>(memberData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/demands")
    public List<Demand> getAllDemands() {
        return demandRepository.findAll();
    }
    @GetMapping("/demands/{id}")
    public ResponseEntity<Demand> getDemandById(@PathVariable("id") long id) {
        Optional<Demand> demandData = demandRepository.findById(id);

        if (demandData.isPresent()) {
            return new ResponseEntity<>(demandData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/demands")
    public Demand createDemand(@RequestBody Demand demand) {
        return demandRepository.save(demand);
    }

    @PutMapping ("/demands/{id}")
    public ResponseEntity<Demand> updateTutorial(@PathVariable long id, @RequestBody Demand updatedMember) {
        Optional<Demand> memberData = demandRepository.findById(id);
        if (memberData.isPresent()) {
            Demand existingMember= memberData.get();
            existingMember.setProjectId(updatedMember.getProjectId());
            existingMember.setProjectName(updatedMember.getProjectName());
            existingMember.setMangerName(updatedMember.getMangerName());
            existingMember.setPositionLevel(updatedMember.getPositionLevel());
            existingMember.setCity(updatedMember.getCity());
            existingMember.setSkills(updatedMember.getSkills());
            existingMember.setDuration((updatedMember.getDuration()));
            existingMember.setStartDate(updatedMember.getStartDate());
            existingMember.setManager_Id(updatedMember.getManager_Id());
            existingMember.setStatus(updatedMember.getStatus());
            return new ResponseEntity<>(demandRepository.save(existingMember), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Autowired
    private MemberFilterService memberFilterService;

    @GetMapping("/members/filternotskill")
    public List<Member> filterMembers(
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfJoining,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer experience,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String positionLevel) {

        return memberFilterService.filterMembers(employeeId, firstName, lastName, dateOfJoining, location,
                experience, status, positionLevel);
    }

    @Autowired
    private DemandFilterService demandFilterService;

    @GetMapping("/demands/filternotskill")
    public List<Demand> filterDemands(
            @RequestParam(required = false) String projectId,
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) String managerName,
            @RequestParam(required = false) String managerId,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double duration,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) String status) {

        return demandFilterService.filterDemands(projectId, projectName, managerName, managerId,
                level, city, duration, startDate, status);
    }

     @Autowired
    private DemandMemberService demandMemberService;

    @GetMapping("/members/demands/{id}")
    public List<Member> getMemberForDemand(@PathVariable long id){
        return demandMemberService.findMemberForDemand(id);
    }

    @Autowired
    private DemandFilterbySkill demandFilterbySkill;
    @GetMapping("/demands/filter")
    public List<Demand> getDemandsBySkill(
            @RequestParam(required = false) String projectId,
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) String managerName,
            @RequestParam(required = false) String managerId,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double duration,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String orderby) {
        List<Demand> res = demandFilterbySkill.getDemandsBySkill(projectId, projectName, managerName, managerId,
                level, city, duration, startDate, status,skill);
        Comparator<Demand> cmp =null;
        switch (orderby){
            case "id" :
                cmp = Comparator.comparing(Demand:: getId);
                break;
            case "dateOfJoining":
                cmp = Comparator.comparing(Demand:: getStartDate);
                break;
            case "positionLevel":
                cmp = Comparator.comparing(Demand:: getPositionLevel);
                break;
            case "duration":
                cmp = Comparator.comparing(Demand:: getDuration);
                break;
            default:
                throw new IllegalArgumentException("Invalid orderby Attribute : " + orderby);
        }
        if("desc".equalsIgnoreCase(sort)){
            cmp = cmp.reversed();
        }

        res.sort(cmp);
        return res;
    }

    @Autowired
    private MemberFilterbySkill memberFilterbySkill;
    @GetMapping("/members/filter")
    public List<Member> getMembersBySkill(
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfJoining,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer experience,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String positionLevel,
            @RequestParam(required = false) String skill) {
        return memberFilterbySkill.filterMembers(employeeId, firstName, lastName, dateOfJoining, location,
                experience, status, positionLevel,skill);
    }

    @Autowired
    private RequestCreator requestCreator;

    @Autowired
    private RequestRepository requestRepository;

    @PostMapping("/members/{member_id}/demands/{demand_id}")
    public Request createRequestDemand(@PathVariable long demand_id, @PathVariable long member_id){
        Request r = requestCreator.createRequest(demand_id,member_id);
        return  requestRepository.save(r);
    }

}
