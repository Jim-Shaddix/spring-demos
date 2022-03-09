package com.example.websockets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class WebSocketOutputMessage {
    private String from;
    private String text;
    private String time;
}
