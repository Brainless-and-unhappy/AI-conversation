package com.zjweu.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptResponseParameter {

    String id;
    String object;
    String created;
    String model;
    Usage usage;
    List<Choices> choices;

    String system_fingerprint;
}

