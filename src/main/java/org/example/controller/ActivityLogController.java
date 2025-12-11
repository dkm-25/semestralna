package org.example.controller;

import org.example.model.ActivityLog;
import org.example.repository.ActivityLogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class ActivityLogController {

    private final ActivityLogRepository repo;

    public ActivityLogController(ActivityLogRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/user/{id}")
    public List<ActivityLog> getLogs(@PathVariable Long id) {
        return repo.findByUserId(id);
    }
}
