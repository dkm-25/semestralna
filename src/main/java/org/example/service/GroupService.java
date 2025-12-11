package org.example.service;

import org.example.dto.CreateGroupRequest;
import org.example.model.Group;
import org.example.model.Membership;
import org.example.repository.GroupRepository;
import org.example.repository.MembershipRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;

    public GroupService(GroupRepository groupRepository,
                        MembershipRepository membershipRepository) {
        this.groupRepository = groupRepository;
        this.membershipRepository = membershipRepository;
    }

    // ---------------------------
    // CREATE GROUP
    // ---------------------------
    public Group createGroup(Long creatorId, CreateGroupRequest req) {

        // створюємо групу
        Group g = new Group(req.getName(), req.getDescription(), creatorId);
        g = groupRepository.save(g);

        // додаємо творця групи як ADMIN
        Membership m = new Membership(creatorId, g.getId(), "ADMIN");
        membershipRepository.save(m);

        return g;
    }

    // ---------------------------
    // LIST GROUPS FOR USER
    // ---------------------------
    public List<Group> listGroupsForUser(Long userId) {
        return membershipRepository.findGroupsByUserId(userId);
    }


    // повернення всіх груп (не для UI користувача)
    public List<Group> listGroups() {
        return groupRepository.findAll();
    }

    // ---------------------------
    // MEMBERS OF GROUP
    // ---------------------------
    public List<Membership> listMembers(Long groupId) {
        return membershipRepository.findByGroupId(groupId);
    }

    // ---------------------------
    // JOIN GROUP (ADD USER)
    // ---------------------------
    public void joinGroup(Long userId, Long groupId) {

        // перевірка — чи вже є користувач у групі
        if (membershipRepository.existsByUserIdAndGroupId(userId, groupId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already joined");
        }

        // додаємо як MEMBER
        Membership m = new Membership(userId, groupId, "MEMBER");
        membershipRepository.save(m);
    }

    // ---------------------------
    // FIND ONE GROUP
    // ---------------------------
    public Group getGroup(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }
}
