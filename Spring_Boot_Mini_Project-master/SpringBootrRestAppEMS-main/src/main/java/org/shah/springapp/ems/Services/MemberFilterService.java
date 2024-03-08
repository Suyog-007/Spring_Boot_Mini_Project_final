package org.shah.springapp.ems.Services;

import org.shah.springapp.ems.Domains.Member;
import org.shah.springapp.ems.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberFilterService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> filterMembers(String employeeId, String firstName, String lastName,
                                      Date dateOfJoining, String location, Integer experience,
                                      String status, String positionLevel) {
        Specification<Member> spec = createSpecification(employeeId, firstName, lastName, dateOfJoining, location,
                experience, status, positionLevel);
        return memberRepository.findAll(spec);
    }

    private Specification<Member> createSpecification(String employeeId, String firstName, String lastName,
                                                      Date dateOfJoining, String location, Integer experience,
                                                      String status, String positionLevel) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (employeeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("employeeId"), employeeId));
            }

            if (firstName != null) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), firstName));
            }

            if (lastName != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), lastName));
            }

            if (dateOfJoining != null) {
                predicates.add(criteriaBuilder.equal(root.get("dateOfJoining"), dateOfJoining));
            }

            if (location != null) {
                predicates.add(criteriaBuilder.equal(root.get("location"), location));
            }

            if (experience != null) {
                predicates.add(criteriaBuilder.equal(root.get("experience"), experience));
            }

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            if (positionLevel != null) {
                predicates.add(criteriaBuilder.equal(root.get("positionLevel"), positionLevel));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
