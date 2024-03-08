package org.shah.springapp.ems.Repository;

import org.shah.springapp.ems.Domains.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAll(Specification<Member> spec);

    List<Member> findAll(Specification<Member> spec, Sort dateOfJoining);


}