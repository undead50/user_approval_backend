package com.ebl.userapproval.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private String from;
    private String to;
    private String[] cc;
    private String title;
    private String subject;
    private String message;
    private Map<String,Object> extraParameter;
}