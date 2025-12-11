package org.example.repository;

import org.example.model.Group;
import org.example.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findByGroupId(Long groupId);

    List<Membership> findByUserId(Long userId);

    Optional<Membership> findByUserIdAndGroupId(Long userId, Long groupId);

    boolean existsByUserIdAndGroupId(Long userId, Long groupId);

    // ГРУПИ, В ЯКИХ УЧАСНИК ЦЕЙ КОРИСТУВАЧ
    @Query("""
       SELECT g
       FROM Group g
       JOIN Membership m ON m.groupId = g.id
       WHERE m.userId = :userId
       """)
    List<Group> findGroupsByUserId(@Param("userId") Long userId);



}
