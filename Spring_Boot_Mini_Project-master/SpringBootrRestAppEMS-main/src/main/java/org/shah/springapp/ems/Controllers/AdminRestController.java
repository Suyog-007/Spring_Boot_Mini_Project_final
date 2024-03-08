package org.shah.springapp.ems.Controllers;

import org.shah.springapp.ems.Domains.Demand;
import org.shah.springapp.ems.Domains.Member;
import org.shah.springapp.ems.Domains.Request;
import org.shah.springapp.ems.Exceptions.MemberNotFoundException;
import org.shah.springapp.ems.Repository.DemandRepository;
import org.shah.springapp.ems.Repository.MemberRepository;
import org.shah.springapp.ems.Repository.RequestRepository;
import org.shah.springapp.ems.Services.MemberFilterbySkill;
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
@RequestMapping("/admin")
public class AdminRestController {

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
    @PostMapping("/members")
    public Member createMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @PutMapping ("/members/{id}")
    public ResponseEntity<Member> updateTutorial(@PathVariable long id, @RequestBody Member updatedMember) {
        Optional<Member> memberData = memberRepository.findById(id);
        if (memberData.isPresent()) {
            Member existingMember= memberData.get();
            existingMember.setDateOfJoining(updatedMember.getDateOfJoining());
            existingMember.setExperience(updatedMember.getExperience());
//          existingMember.setId(updatedMember.getId());
            existingMember.setLocation(updatedMember.getLocation());
            existingMember.setEmployeeId(updatedMember.getEmployeeId());
            existingMember.setFirstName(updatedMember.getFirstName());
            existingMember.setLastName((updatedMember.getLastName()));
            existingMember.setPositionLevel(updatedMember.getPositionLevel());
            existingMember.setStatus(updatedMember.getStatus());
            existingMember.setSkills(updatedMember.getSkills());
            return new ResponseEntity<>(memberRepository.save(existingMember), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<HttpStatus> deleteMember(@PathVariable long id) {
        try {
            memberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String orderby) {
        List<Member> res = memberFilterbySkill.filterMembers(employeeId, firstName, lastName, dateOfJoining, location,
                experience, status, positionLevel,skill);
        Comparator<Member> cmp =null;
        switch (orderby){
            case "id" :
                cmp = Comparator.comparing(Member:: getId);
                break;
                case "dateOfJoining":
                cmp = Comparator.comparing(Member:: getDateOfJoining);
                break;
            case "positionLevel":
                cmp = Comparator.comparing(Member:: getPositionLevel);
                break;
            default:
                throw new IllegalArgumentException("Invalid orderby Attribute : " + orderby);
        }
        if("desc".equalsIgnoreCase(sort)){
            cmp = cmp.reversed();
        }

        res.sort(cmp);

        return  res;
    }

    @Autowired
    private RequestRepository requestRepository;
    @GetMapping("/requests")
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @PutMapping ("/requests/approval/{id}")
    public ResponseEntity<Request> updateTutorial(@PathVariable long id, @RequestBody Request updateRequest) {

        Optional<Request> requestData = requestRepository.findById(id);
        if (requestData.isPresent()) {
            Request existingRequest= requestData.get();
            String s = updateRequest.getStatus();
            existingRequest.setStatus(s);
            Demand d = demandRepository.getReferenceById(existingRequest.getDemand_id());
            Member m = memberRepository.getReferenceById(existingRequest.getMember_id());
            if(s.equalsIgnoreCase("APPROVED")){
                d.setStatus("FULLFILLED");
                m.setStatus("ASSIGNED");
            }else{
                d.setStatus("NOT FULLFILLED");
                m.setStatus("AVAILABLE");
            }

            demandRepository.save(d);
            memberRepository.save(m);

            return new ResponseEntity<>(requestRepository.save(existingRequest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
