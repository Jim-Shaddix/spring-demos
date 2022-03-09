package com.example.websockets.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class WebSocketInputMessage {
    private String from;
    private String text;
}
