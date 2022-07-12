package com.polarisdigitech.backendchallenge;

import com.polarisdigitech.backendchallenge.algorithms.*;
import com.polarisdigitech.backendchallenge.aop.Operation;
import com.polarisdigitech.backendchallenge.exceptions.CustomAsyncExceptionHandler;
import com.polarisdigitech.backendchallenge.helpers.Helpers;
import com.polarisdigitech.backendchallenge.helpers.StorageProperties;
import com.polarisdigitech.backendchallenge.model.book.Book;
import com.polarisdigitech.backendchallenge.model.product.Student;
import com.polarisdigitech.backendchallenge.model.product.Subject;
import com.polarisdigitech.backendchallenge.model.product.Product;
import com.polarisdigitech.backendchallenge.regex.RegularExpressions;
import com.polarisdigitech.backendchallenge.repository.book.BookRepository;
import com.polarisdigitech.backendchallenge.repository.product.ProductRepository;
import com.polarisdigitech.backendchallenge.services.StorageService;
import com.polarisdigitech.backendchallenge.services.StudentService;
import com.polarisdigitech.backendchallenge.services.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.DefaultManagedTaskExecutor;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executor;

@SpringBootApplication
@Slf4j
@EnableConfigurationProperties(StorageProperties.class)
public class BackendChallengeApplication implements CommandLineRunner {


	private final BookRepository bookRepository;
	private final ProductRepository productRepository;
	private final StudentService studentService;
	private final SubjectService subjectService;

	public BackendChallengeApplication(BookRepository bookRepository, ProductRepository productRepository, StudentService studentService, SubjectService subjectService, Operation operation) {
		this.bookRepository = bookRepository;
		this.productRepository = productRepository;
		this.studentService = studentService;
		this.subjectService = subjectService;
	}


	public void invokeAOP(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		Operation operation = (Operation) applicationContext.getBean("opBean");
		log.info("Calling msg");
		operation.msg();
		log.info("Calling m");
		operation.m();
		log.info("Calling k");
		operation.k();

	}



	public static void main(String[] args) {
		SpringApplication.run(BackendChallengeApplication.class, args);
		Helpers helpers = new Helpers();

		List<Book> bookList = Arrays.asList(new Book("012-123-4567","How to program","Deitel Deitel","female","Deitel Inc","Nigeria"),
				new Book("012-123-4567,89","How to Laugh","Dickson Jack","male","Jackson Publishers Inc","US"));
		List<BookDto> dtos = helpers.mapList(bookList,BookDto.class);
		log.info("My BookDtos == {}",dtos);
		helpers.myMapping();
	}
	public void createStudentAndSubject(Subject subject, Student student){
		log.info("Creating Student->{}",studentService.createOrUpdateStudent(student));
		log.info("Creating Subject->{}",subjectService.createOrUpdateSubject(subject));
	}
	public void enrollStudents(Long studentId, Long subjectId){
		log.info("Enrolled Students -> {}",subjectService.enrollStudent(studentId,subjectId));
	}
	@Override
	public void run(String... args) throws Exception {
		//List<Book> bookList = Arrays.asList(new Book("012-123-4567","How to program","Deitel Deitel"),
		//		new Book("012-123-4567,89","How to program","Deitel Deitel"));
		//log.info("Saved Another Book {}",bookRepository.save(new Book("012-123-456700","Problem of Pain","CS Lewis")));
		//log.info("Saved BookRepository =={}",bookRepository.saveAll(bookList));
		log.info("Retrieved Single Book=={}",bookRepository.selectByIsbnAndGender("012-123-4567,89","female"));
		Page<Book> listPage = bookRepository.selectBookByCountry("Nigeria", PageRequest.of(0,5));
		log.info("Pageable Results =={}",listPage.getContent());
		log.info("Retrieved Book List =={}",bookRepository.selectMyBookByGender("male"));
		//log.info("Update Result =={}",bookRepository.updateCount("Criterion Publishers","012-123-456700"));
		log.info("Retrieved Book Instance By Country and Isbn =={}",bookRepository.selectByIsbnAndCountry("012-123-456700","England"));
		log.info("Retrieved Books By Gender:{}",bookRepository.selectBooksHavingParticularGender(Arrays.asList("female")));
		log.info("Retrieved product =={}",productRepository.findOne(Product.class,1L));
		log.info("Retrieved item =={}",productRepository.findOne(Subject.class,2L));
		log.info("Retrieved item =={}",productRepository.findOne(Subject.class,3L));
		log.info("Retrieved result =={}",productRepository.findAllByIds(Subject.class,Arrays.asList(1L,2L,5L,6L)));
		//Item item = productRepository.save(new Item("Refrigerator",5000D));
		List bulkProducts = Arrays.asList(new Product("Printer",530d),new Product("Mouse",160d),new Product("Bag",30d));
		//List<Product> product = productRepository.save(bulkProducts);
		//log.info("Saving product :::{}",product);
		log.info("Results from CriteriaQuery For Item table=={}",productRepository.searchDBForGivenDateRange(Subject.class,LocalDateTime.parse("2022-03-21T15:40:56"), LocalDateTime.parse("2022-03-21T15:42:52")));
		log.info("Results from CriteriaQuery for Product table =={} ",productRepository.searchDBForGivenDateRange(Product.class,LocalDateTime.parse("2022-03-22T09:30:48"), LocalDateTime.parse("2022-03-22T09:44:47")));
		log.info("Results fro CriteriaQuery Searching Item=={}",productRepository.findInTableWithNameLike(Subject.class,"kett"));
		//createStudentAndSubject(new Subject("Geography"), new Student("John Dada Jr"));
		//enrollStudents(1L,167L);
		log.info("My Created URI =={}", URI.create("/1"));
		int [] left = {1,2,3};
		log.info("Printing ArrayLst>>>");
		Arrays.asList(left).stream().forEach(System.out::println);
		productRepository.findCarByYearWithNamedStored(2015);
		log.info("The createdAndNonStored storedProcedure queried result =={}",productRepository.findCarByYearWithNoNameStoredProcedure(2010));
		log.info("The createdAndStoredProcedure queried result =={}",productRepository.countTotalProductsGivenAPrice(200));
		//invokeAOP();
		Solutions algorithmSolutions = new Solutions();
		algorithmSolutions.testOtherAlgortihms();

	}

	@Bean
	CommandLineRunner init(StorageService storageService){
		return ((args) -> {
			//storageService.deleteAll();
			//storageService.init();
		});
	}
}
