package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateResourceRequest;
import org.example.model.Resource;
import org.example.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public List<Resource> listResources(Long groupId) {
        return resourceRepository.findByGroupId(groupId);
    }

    public Resource createResource(Long userId, Long groupId, CreateResourceRequest req) {

        Resource r = new Resource(
                groupId,
                userId,
                req.getTitle(),
                req.getType(),
                req.getPathOrUrl(),
                LocalDateTime.now()
        );

        return resourceRepository.save(r);
    }

    public void deleteResource(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }
}
