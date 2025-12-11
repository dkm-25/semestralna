package org.example.dto;     // дуже важливо, щоб було саме так!

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WsMessage {
    private String type;
    private Object payload;
}
