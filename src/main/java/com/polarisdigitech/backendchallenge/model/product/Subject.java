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
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@EqualsAndHashCode.Exclude
    private Long id;
    @EqualsAndHashCode.Exclude
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
            )

    @EqualsAndHashCode.Exclude
    private Set<Student> enrolledStudents = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Teacher teacher;
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdDate=LocalDateTime.now();

    public Subject(String name) {
        this.name = name;
    }

    public void assignTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    public void enrollStudent(Student student) {
        this.enrolledStudents.add(student);
    }
}
