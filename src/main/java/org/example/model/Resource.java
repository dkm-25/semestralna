package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;
    private Long createdBy;

    private String title;
    private String type;
    private String pathOrUrl;

    private LocalDateTime createdAt;

    public Resource(Long groupId, Long createdBy,
                    String title, String type,
                    String pathOrUrl, LocalDateTime createdAt) {

        this.groupId = groupId;
        this.createdBy = createdBy;
        this.title = title;
        this.type = type;
        this.pathOrUrl = pathOrUrl;
        this.createdAt = createdAt;
    }
}
