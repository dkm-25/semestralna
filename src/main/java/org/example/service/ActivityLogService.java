package org.example.service;

import org.example.model.ActivityLog;
import org.example.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityLogService {

    private final ActivityLogRepository repo;

    public ActivityLogService(ActivityLogRepository repo) {
        this.repo = repo;
    }

    public void log(Long userId, String action, String details) {
        ActivityLog entry = new ActivityLog(userId, action, details);
        repo.save(entry);
    }
}
