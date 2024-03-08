package org.shah.springapp.ems.Services;


import org.shah.springapp.ems.Repository.DemandRepository;
import org.shah.springapp.ems.Repository.MemberRepository;
import org.shah.springapp.ems.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestApproval {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private MemberRepository memberRepository;


}
