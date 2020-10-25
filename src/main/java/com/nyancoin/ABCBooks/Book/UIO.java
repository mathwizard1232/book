package com.nyancoin.ABCBooks.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// This class will contain various I/O utility helper values / functions
@Component
public class UIO {
	private Form form;

	@Autowired
	public UIO(final Form form) {
		this.form = form;
	}

	public Integer integerFromString(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return -1;
		}
	}

	public String br2()
	{
		return "\n<br><br>\n";
	}

	public String ahref(String target, String anchor)
	{
		return "<a href=\"" + target + "\">" + anchor + "</a>\n";
	}

	public String getHeader()
	{
		return getHeader("ABC Books");
	}

	public String getHeader(String title) {
		return "<html>\n<head>\n<title>" + title + "</title>\n</head>\n<body style=\"background-image:url('/books.jpg'); padding-left: 20%; padding-right: 40%\">\n\n<div style=\"background-image:url('/scroll.jpg'); background-size: 100% 100%; padding:20%\">\n\n";
	}

	public String getFooter()
	{
		return "</div>\n\n</body>\n</html>";
	}

	public String basicPage(String body)
	{
		return getHeader() + body + getFooter();
	}

	public String basicPageWithTitle(String body, String title)
	{
		return getHeader(title) + body + getFooter();
	}

	public String h3(String content)
	{
		return "<h3>" + content + "</h3>\n";
	}

	public String strong(String content)
	{
		return "<strong>" + content + "</strong>";
	}

	public String spanColor(String content, String color) {
		return "<span style=\"background-color:" + color + "\">" + content + "</span>\n";
	}

	public String hr()
	{
		return "\n<br><br><hr><br><br>\n\n";
	}

	// This is a UIO wrapper on core Form functionality; we want Application to go through UIO rather than Form
	public String basicBookInput(String boxid)
	{
		return h3("Next LegacyBook:") + form.GenerateBasicBookInput(boxid);
	}

	public String basicBoxInput()
	{
		return h3("New box:") + form.GenerateBasicBoxInput();
	}

	public String bookInputWithTitleSet(String title, String boxid) {
		return h3("Next LegacyBook:") + form.GenerateBookInputWithTitleSet(title, boxid);
	}

	public String bookInputWithAuthorSet(String author, String boxid) {
		return h3("Next LegacyBook:") + form.GenerateBookInputWithAuthorSet(author, boxid);
	}
}