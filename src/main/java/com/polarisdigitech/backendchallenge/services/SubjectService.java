package com.polarisdigitech.backendchallenge.services;

import com.polarisdigitech.backendchallenge.helpers.IdNotFoundException;
import com.polarisdigitech.backendchallenge.model.product.Student;
import com.polarisdigitech.backendchallenge.model.product.Subject;
import com.polarisdigitech.backendchallenge.model.product.Teacher;
import com.polarisdigitech.backendchallenge.repository.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class SubjectService implements ProductService {

    private final ProductRepository productRepository;

    public SubjectService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public <T> T saveProductEntity(T type) {
        return productRepository.save(type);
    }

    @Override
    public <T> T findOne(Class<T> tClass, Long id) {
        T one = productRepository.findOne(tClass, id);
        if (one == null)
            throw new IdNotFoundException(HttpStatus.NOT_FOUND,"The requested id not found");
        return one;
    }

    public Subject  assignSubjectToTeacher(Long teacherId, Long subjectId) {
        Teacher teacher = this.findOne(Teacher.class,teacherId);

        Subject subject = this.findOne(Subject.class,subjectId);
        subject.assignTeacher(teacher);

       return (Subject) saveProductEntity(subject);
    }

    public  Subject enrollStudent(Long studentId, Long subjectId){
        Subject subject = this.findOne(Subject.class,subjectId);
        Student student = this.findOne(Student.class, studentId);
        subject.enrollStudent(student);

        return (Subject)saveProductEntity(subject);
    }

    public List<Subject> createSubject(List<Subject> subjects){
        //List<Subject> subjects = Arrays.asList(new Subject("English"),new Subject("Igbo"));
        return this.saveProductEntity(subjects);
    }

    public Subject createOrUpdateSubject(Subject subject){
        return this.saveProductEntity(subject);
    }
}
