package org.shah.springapp.ems.Services;


import org.shah.springapp.ems.Domains.Demand;
import org.shah.springapp.ems.Repository.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DemandFilterbySkill {
    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private DemandFilterService demandFilterService;

    public List<Demand> getDemandsBySkill(String projectId, String projectName, String managerName,
                                          String managerId, String level, String city,
                                          Double duration, Date startDate, String status,String skill) {

        List<Demand> demandList  = demandFilterService.filterDemands(projectId, projectName, managerName, managerId,
                level, city, duration, startDate, status);
        if(skill != null){
            List<Demand> res = new ArrayList<>();
            String[] skillarr = skill.split("-");
            for(Demand d : demandList){
                for(String s : skillarr){
                    if(d.getSkills().contains(s)){
                        res.add(d);
                    }
                }
            }
            return res;
        }
        return demandList;

    }
}
