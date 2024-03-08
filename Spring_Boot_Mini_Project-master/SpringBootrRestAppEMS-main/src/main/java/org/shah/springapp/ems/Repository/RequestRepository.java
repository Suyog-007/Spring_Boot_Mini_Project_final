package org.shah.springapp.ems.Repository;


import org.shah.springapp.ems.Domains.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
