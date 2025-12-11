package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateResourceRequest;
import org.example.model.Resource;
import org.example.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups/{groupId}/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping
    public List<Resource> list(@PathVariable Long groupId) {
        return resourceService.listResources(groupId);
    }

    @PostMapping
    public Resource create(
            @PathVariable Long groupId,
            @RequestParam Long userId,
            @RequestBody CreateResourceRequest req
    ) {
        return resourceService.createResource(userId, groupId, req);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long groupId,
            @PathVariable Long id
    ) {
        resourceService.deleteResource(id);
    }
}
