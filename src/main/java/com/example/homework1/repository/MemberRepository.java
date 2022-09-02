package com.example.homework1.repository;

import com.example.homework1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.id = :id")
    Member findById(String id);

    @Query("select m from Member m where m.email = :email")
    Member findByEmail(String email);

    @Modifying
    @Query("delete from Member m where m.id = :id")
    void deleteById(String id);



}
