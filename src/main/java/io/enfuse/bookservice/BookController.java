package io.enfuse.bookservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/booksAPI")
public class BookController {

    static private List<String> allBooks = new ArrayList<String>() {{
        add("GOT");
        add("GOT");
        add("GOT-II");
        add("GOT-III");
        add("GOT-IV");
        add("Harry-Potter-I");
        add("Harry-Potter-II");
    }};
    @GetMapping
    public List<String> findAll(@RequestHeader(value="User-Agent") String userAgent) {
        return allBooks;
    }

    @RequestMapping(
            value = "/book",
            method = GET,
            headers = "Accept=application/json")
    public String getSingleBook(@RequestParam int idx) {
        if(allBooks.size() <= idx || idx < 0) return "Not Found";
        return allBooks.get(idx);
    }

    @DeleteMapping()
    @RequestMapping(
            method = DELETE,
            produces = {"application/json","application/xml"})
    public String deleteBook(@RequestParam int idx) {
        if(allBooks.size() >= idx || idx < 0) return "Not Found";
        String deleted = allBooks.remove(idx);
        return deleted;
    }

    @PostMapping()
    public String checkout(@RequestParam String bookTitle) {
        System.out.println(bookTitle);
        allBooks.add(bookTitle);
        return bookTitle;
    }


}
