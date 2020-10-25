package com.nyancoin.ABCBooks.Book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class BookApplication extends SpringBootServletInitializer {
//	private LegacyAuthor authorService;
//	private LegacyBook legacyBookService;
//	private LegacyBox boxService;
	// Utility class for I/O
	// private UIO uio;

	public static void main(String[] args) {		
		SpringApplication.run(BookApplication.class, args);
	}

	/*
	@Autowired // This Spring annotation means it will automatically inject the requires dependencies
	public BookApplication(final UIO uio){//}, final LegacyAuthor authorService, final LegacyBook legacyBookService) {
		this.uio = uio;
//		this.authorService = authorService;
//		this.legacyBookService = legacyBookService;
	}
	*/

/*	@RequestMapping("/review")
	public String review() {
		String result = uio.getHeader("Review Collection");

		result += authorService.DisplayAuthors();

		result += uio.getFooter();

		return result;
	}

	@RequestMapping("/addbox")
	public String addbox(@RequestParam(value = "boxtitle", defaultValue = "") String boxtitle,
						 @RequestParam(value = "boxdescription", defaultValue = "") String description) {
		String result = uio.getHeader("Add a LegacyBox of Books");
		String form = uio.basicBoxInput();
		
		if (boxtitle.isEmpty() && description.isEmpty()) {
			result += form;
		} else {
			Integer boxid = boxService.LookupOrAdd(boxtitle, description);
			result += uio.h3("LegacyBox Added:");
			result += boxtitle + uio.br2() + description + uio.br2();
			result += uio.ahref("addbook?boxid="+boxid,"Add books to this box.");
		}	

		result += uio.getFooter();

		return result;
	}

	// TODO: Add completion steps to adding books to a box - review / confirm / edit types of options to add
	@RequestMapping("/addbook")
	public String addbook(@RequestParam(value = "author", defaultValue = "") String author,
						  @RequestParam(value = "title", defaultValue = "") String title,
						  @RequestParam(value = "boxid", defaultValue = "") String boxid) {
		String result = uio.getHeader("Add a LegacyBook");
		String spanColor = "#D6CCA9";
		String box_title = "";
		Integer nBoxId = uio.integerFromString(boxid);

		if (! boxid.isEmpty()) {
			box_title = boxService.GetTitleById(nBoxId);
			result += "Adding into box titled: " + box_title + uio.br2();
		}

		String form = uio.basicBookInput(boxid);
		
		if (author.isEmpty() && title.isEmpty()) {
			result += form;
		} else if (!(author.isEmpty()) && !(title.isEmpty())) {
			Integer aid = authorService.lookupOrAdd(author);
			Integer book_id = legacyBookService.LookupOrAdd(aid, title);
			if (! boxid.isEmpty()) {
				boxService.LookupOrAdd(nBoxId, book_id);
			}

			result += uio.h3("LegacyBook Added:");
			result += uio.strong("LegacyAuthor: ") + uio.spanColor(author,spanColor);
			result += "<br><br>\n";
			result += uio.strong("Title: ") + uio.spanColor(title,spanColor);

			result += uio.hr() + form;
		} else {
			if (author.isEmpty()) {
				result += "Please specify a name of an author as well as the title.<br>";
				result += uio.bookInputWithTitleSet(title, boxid);
			} else if (title.isEmpty()) {
				result += "Please specify the title of the work as well as its author.<br>";
				result += uio.bookInputWithAuthorSet(author, boxid);
			} else {
				// I believe I have already covered all possible cases.
				result = "Unexpected case reached internally.<br>";
				result += form;
			}
		}

		result += uio.getFooter();

		return result;
	}
*/
}
