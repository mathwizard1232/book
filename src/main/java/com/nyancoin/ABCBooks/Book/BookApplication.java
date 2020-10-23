package com.nyancoin.ABCBooks.Book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

@SpringBootApplication
@RestController
public class BookApplication {
	private LegacyAuthor authorService;
	private Book bookService;
	private Box boxService;
	// Utility class for I/O
	private UIO uio;

	public static void main(String[] args) {		
		SpringApplication.run(BookApplication.class, args);
	}

	@Autowired // This Spring annotation means it will automatically inject the requires dependencies
	public BookApplication(final UIO uio, final LegacyAuthor authorService, final Book bookService) {
		this.uio = uio;
		this.authorService = authorService;
		this.bookService = bookService;
	}

	@RequestMapping("/review")
	public String review()
	{
		String result = uio.GetHeader("Review Collection");

		result += authorService.DisplayAuthors();

		result += uio.GetFooter();

		return result;
	}

	@RequestMapping("/addbox")
	public String addbox(@RequestParam(value = "boxtitle", defaultValue = "") String boxtitle,
						 @RequestParam(value = "boxdescription", defaultValue = "") String description) {
		String result = uio.GetHeader("Add a Box of Books");
		String form = uio.BasicBoxInput();
		
		if (boxtitle.isEmpty() && description.isEmpty()) {
			result += form;
		} else {
			Integer boxid = boxService.LookupOrAdd(boxtitle, description);
			result += uio.h3("Box Added:");
			result += boxtitle + uio.br2() + description + uio.br2();
			result += uio.ahref("addbook?boxid="+boxid,"Add books to this box.");
		}	

		result += uio.GetFooter();

		return result;
	}

	// TODO: Add completion steps to adding books to a box - review / confirm / edit types of options to add
	@RequestMapping("/addbook")
	public String addbook(@RequestParam(value = "author", defaultValue = "") String author,
						  @RequestParam(value = "title", defaultValue = "") String title,
						  @RequestParam(value = "boxid", defaultValue = "") String boxid) {
		String result = uio.GetHeader("Add a Book");
		String spanColor = "#D6CCA9";
		String box_title = "";
		Integer nBoxId = uio.integerFromString(boxid);

		if (! boxid.isEmpty()) {
			box_title = boxService.GetTitleById(nBoxId);
			result += "Adding into box titled: " + box_title + uio.br2();
		}

		String form = uio.BasicBookInput(boxid);
		
		if (author.isEmpty() && title.isEmpty()) {
			result += form;
		} else if (!(author.isEmpty()) && !(title.isEmpty())) {
			Integer aid = authorService.lookupOrAdd(author);
			Integer book_id = bookService.LookupOrAdd(aid, title);
			if (! boxid.isEmpty()) {
				boxService.LookupOrAdd(nBoxId, book_id);
			}

			result += uio.h3("Book Added:");
			result += uio.strong("LegacyAuthor: ") + uio.spanColor(author,spanColor);
			result += "<br><br>\n";
			result += uio.strong("Title: ") + uio.spanColor(title,spanColor);

			result += uio.hr() + form;
		} else {
			if (author.isEmpty()) {
				result += "Please specify a name of an author as well as the title.<br>";
				result += uio.BookInputWithTitleSet(title, boxid);
			} else if (title.isEmpty()) {
				result += "Please specify the title of the work as well as its author.<br>";
				result += uio.BookInputWithAuthorSet(author, boxid);
			} else {
				// I believe I have already covered all possible cases.
				result = "Unexpected case reached internally.<br>";
				result += form;
			}
		}

		result += uio.GetFooter();

		return result;
	}

}
