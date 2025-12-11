package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.CreateGroupRequest;
import org.example.model.Group;
import org.example.model.Membership;
import org.example.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // GET /groups
    @GetMapping
    public List<Group> listGroups() {
        return groupService.listGroups();
    }

    // POST /groups
    @PostMapping
    public Group createGroup(@RequestParam Long userId, @RequestBody @Valid CreateGroupRequest req) {
        return groupService.createGroup(userId, req);
    }

    // POST /groups/{id}/join
    @PostMapping("/{id}/join")
    public void joinGroup(@PathVariable Long id, @RequestParam Long userId) {
        groupService.joinGroup(userId, id);
    }

    // GET /groups/{id}/members
    @GetMapping("/{id}/members")
    public List<Membership> listMembers(@PathVariable Long id) {
        return groupService.listMembers(id);
    }

    @GetMapping("/list")
    public List<Group> listGroupsForUser(@RequestParam Long userId) {
        return groupService.listGroupsForUser(userId);
    }

    @GetMapping("/{id}")
    public Group getGroup(@PathVariable Long id) {
        return groupService.getGroup(id);
    }

}
