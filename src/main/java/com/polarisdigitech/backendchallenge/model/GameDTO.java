package com.polarisdigitech.backendchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameDTO {
    private long id;
    private  String name;
    private Long creationTime;

}
