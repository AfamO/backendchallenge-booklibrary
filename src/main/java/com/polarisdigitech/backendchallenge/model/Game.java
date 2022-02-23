package com.polarisdigitech.backendchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Game {
    private long id;
    private  String name;
    public Long timestamp;
    private List<String> stringList;
    private Map<Integer, String> integerStringMap;
}
