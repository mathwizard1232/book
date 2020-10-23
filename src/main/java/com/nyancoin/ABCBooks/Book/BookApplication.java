package com.nyancoin.ABCBooks.Book;

import com.nyancoin.ABCBooks.Book.AuthorRepository;
import com.nyancoin.ABCBooks.Book.AuthorEntity;
import com.nyancoin.ABCBooks.Book.Author;

import com.nyancoin.ABCBooks.Book.Form; // Utility class with HTML form constants and helper methods.
import com.nyancoin.ABCBooks.Book.UIO; // I/O helper class

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@RestController
public class BookApplication {
	@Autowired // This Spring annotation means it will automatically construct / provide this component
	private Author authorService;

	@Autowired
	private Book bookService;

	@Autowired
	private Box boxService;

	// Utility class for I/O
	private UIO UIO;

	public static void main(String[] args) {		
		SpringApplication.run(BookApplication.class, args);
	}

	public BookApplication()
	{
		UIO = new UIO();
	}

	@RequestMapping("/")
	public String index() {
		return UIO.BasicPage("Greetings from ABC Books!<br><br><a href=\"addbox\">Add a box of books.</a><br><br><a href=\"addbook\">Add a book.</a>");
	}

	// Endpoint kept for nostalgia	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	// Will be refactored ; convenience / testing method for now
	@GetMapping(path="/all")
	public @ResponseBody Iterable<AuthorEntity> getAllAuthors() {
		return authorService.GetAll();			
	}

	@RequestMapping("/addbox")
	public String addbox(@RequestParam(value = "boxtitle", defaultValue = "") String boxtitle,
						 @RequestParam(value = "boxdescription", defaultValue = "") String description) {
		String result = UIO.GetHeader("Add a Box of Books");
		String form = UIO.BasicBoxInput();
		
		if (boxtitle.isEmpty() && description.isEmpty()) {
			result += form;
		} else {
			Integer boxid = boxService.LookupOrAdd(boxtitle, description);
			result += UIO.h3("Box Added:");
			result += boxtitle + UIO.br2() + description + UIO.br2();
			result += UIO.ahref("addbook?boxid="+boxid,"Add books to this box.");
		}	
		return result;
	}

	// TODO: Add completion steps to adding books to a box - review / confirm / edit types of options to add
	@RequestMapping("/addbook")
	public String addbook(@RequestParam(value = "author", defaultValue = "") String author,
						  @RequestParam(value = "title", defaultValue = "") String title,
						  @RequestParam(value = "boxid", defaultValue = "") String boxid) {
		String result = UIO.GetHeader("Add a Book");
		String spanColor = "#D6CCA9";
		String box_title = "";
		Integer nBoxId = UIO.IntFromString(boxid);

		if (! boxid.isEmpty()) {
			box_title = boxService.GetTitleById(nBoxId);
			result += "Adding into box titled: " + box_title + UIO.br2();
		}

		String form = UIO.BasicBookInput(boxid);
		
		if (author.isEmpty() && title.isEmpty()) {
			result += form;
		} else if (!(author.isEmpty()) && !(title.isEmpty())) {
			Integer aid = authorService.LookupOrAdd(author);
			Integer book_id = bookService.LookupOrAdd(aid, title);
			if (! boxid.isEmpty()) {
				boxService.LookupOrAdd(nBoxId, book_id);
			}

			result += UIO.h3("Book Added:");
			result += UIO.strong("Author: ") + UIO.spanColor(author,spanColor);
			result += "<br><br>\n";
			result += UIO.strong("Title: ") + UIO.spanColor(title,spanColor);

			result += UIO.hr() + form;
		} else {
			if (author.isEmpty()) {
				result += "Please specify a name of an author as well as the title.<br>";
				result += UIO.BookInputWithTitleSet(title, boxid);		
			} else if (title.isEmpty()) {
				result += "Please specify the title of the work as well as its author.<br>";
				result += UIO.BookInputWithAuthorSet(author, boxid);
			} else {
				// I believe I have already covered all possible cases.
				result = "Unexpected case reached internally.<br>";
				result += form;
			}
		}

		result += UIO.GetFooter();

		return result;
	}

}
