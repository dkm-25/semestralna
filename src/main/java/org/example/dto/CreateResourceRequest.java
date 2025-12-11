package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResourceRequest {
    private String title;
    private String type;
    private String pathOrUrl;
}
