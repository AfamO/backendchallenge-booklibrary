package com.polarisdigitech.backendchallenge.model.product;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(exclude = {"id","name","subjects", "createdDate"})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@EqualsAndHashCode.Exclude
    private Long id;
    //@EqualsAndHashCode.Exclude
    private String name;

    @ToString.Exclude
    //@EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "enrolledStudents", fetch = FetchType.EAGER)
    private Set<Subject> subjects = new HashSet<>();

    //@EqualsAndHashCode.Exclude
    private LocalDateTime createdDate=LocalDateTime.now();

    public Student(String name) {
        this.name = name;
    }

}
