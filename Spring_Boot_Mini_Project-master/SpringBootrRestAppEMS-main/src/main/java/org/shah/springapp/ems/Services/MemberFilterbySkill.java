package org.shah.springapp.ems.Services;


import org.shah.springapp.ems.Domains.Demand;
import org.shah.springapp.ems.Domains.Member;
import org.shah.springapp.ems.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberFilterbySkill {
    @Autowired
    private MemberFilterService memberFilterService;

    public List<Member> filterMembers(String employeeId, String firstName, String lastName,
                                      Date dateOfJoining, String location, Integer experience,
                                      String status, String positionLevel,String skill){
        List<Member>memberList = memberFilterService.filterMembers(employeeId, firstName, lastName, dateOfJoining, location,
                experience, status, positionLevel);
        if(skill != null){
            List<Member> res = new ArrayList<>();
            String[] skillarr = skill.split("-");
            for(Member m : memberList){
                for(String s : skillarr){
                    if(m.getSkills().containsKey(s)){
                        res.add(m);
                    }
                }
            }
            return res;
        }
        return memberList;
    }
}
