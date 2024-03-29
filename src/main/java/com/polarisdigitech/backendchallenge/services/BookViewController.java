
package com.polarisdigitech.backendchallenge.services;

import com.polarisdigitech.backendchallenge.helpers.Constants;
import com.polarisdigitech.backendchallenge.model.book.Book;
import com.polarisdigitech.backendchallenge.request.BookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping(Constants.API_PATH+"view")
public class BookViewController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String showWelcomePage(Model model){
        log.info("Received incoming requests for BookViewController");
        return "welcome";
    }
    @GetMapping("/uploadForm")
    public String showUploadFormPage(Model model){
        log.info("Received incoming requests for uploadForm BookViewController");
        return "uploadForm";
    }

    @GetMapping("/add_book")
    public String showaddBookPage(Model model){
        log.info("Received incoming requests for BookViewController: Add Books");
        model.addAttribute("book", new Book());
        return "add_book";
    }

    @PostMapping("/addBook")
    public String addBook(@Validated BookRequest bookRequest, BindingResult bindingResult) {
        log.info("Attempting to add a book with isbn: {} ",bookRequest.getIsbn());
        if(bindingResult.hasErrors()){
            return "add_book";

        }
        bookService.addBooks(bookRequest);
        return "redirect:/view-books";
    }

    @GetMapping("/view-books")
    public String showBooksPage(ModelMap modelMap){
        Sort sort = Sort.by(Sort.Direction.ASC,"author");
        Pageable pageable = PageRequest.of(0,4,sort);
        modelMap.addAttribute("books", bookService.getAllBooks(pageable));
        return "list-books";
    }

    @GetMapping("/edit/{isbn}")
    public String showEditBookPage(ModelMap modelMap,@PathVariable("isbn") String isbn){
        modelMap.addAttribute("book", bookService.findABook(isbn).getData());
        return "edit_book";
    }

    @PutMapping("/updateBook")
    public String updateBook(@Validated BookRequest bookRequest, BindingResult bindingResult) {
        log.info("Attempting to update a book with isbn: {} ",bookRequest.getIsbn());
        if(bindingResult.hasErrors()){
            return "edit_book";

        }
        this.bookService.updateBook(bookRequest);
        return "redirect:/view-books";
    }

    @DeleteMapping("/delete/{isbn}")
    public String deleteBook(ModelMap modelMap,@PathVariable("isbn") String isbn){
        bookService.deleteABook(isbn);
        return "redirect:/view-books";
    }
}
