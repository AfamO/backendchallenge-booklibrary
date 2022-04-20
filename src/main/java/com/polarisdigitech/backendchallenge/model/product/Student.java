package com.polarisdigitech.backendchallenge.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "enrolledStudents", fetch = FetchType.EAGER)
    private Set<Subject> subjects = new HashSet<>();


    private LocalDateTime createdDate=LocalDateTime.now();

    public Student(String name) {
        this.name = name;
    }

}
