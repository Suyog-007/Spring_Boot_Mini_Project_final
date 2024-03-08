package org.shah.springapp.ems.Services;


import org.shah.springapp.ems.Domains.Demand;
import org.shah.springapp.ems.Domains.Member;
import org.shah.springapp.ems.Domains.Request;
import org.shah.springapp.ems.Repository.DemandRepository;
import org.shah.springapp.ems.Repository.MemberRepository;
import org.shah.springapp.ems.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestCreator {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Request createRequest(long demand_id,long member_id){
        Demand d = demandRepository.getReferenceById(demand_id);
        Member m = memberRepository.getReferenceById(member_id);

        if(m.getStatus().equalsIgnoreCase("ASSIGNED")){
            throw new IllegalArgumentException("Member is already Assigned");
        }

        if(d.getStatus().equalsIgnoreCase(("FULLFILLED"))){
            throw new IllegalArgumentException("Demand already fullfilled , cannot be requested");
        }
        Request r = new Request();
        r.setDemand_id(d.getId());
        r.setManger_Id(d.getManager_Id());
        r.setManger_Name(d.getMangerName());
        r.setMember_location(m.getLocation());
        r.setProject_Id(d.getProjectId());
        r.setProject_Name(d.getProjectName());
        r.setEmployeeId(m.getEmployeeId());
        r.setProject_location(d.getCity());
        r.setStatus("REQUESTED");
        r.setMember_id(m.getId());
        r.setMember_Name((m.getFirstName() + " " +m.getLastName()));

        return r;
    }
}
