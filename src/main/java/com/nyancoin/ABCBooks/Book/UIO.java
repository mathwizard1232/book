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

	public String GetHeader()
	{
		return GetHeader("ABC Books");
	}

	public String GetHeader(String title) {
		return "<html>\n<head>\n<title>" + title + "</title>\n</head>\n<body style=\"background-image:url('books.jpg'); padding-left: 20%; padding-right: 40%\">\n\n<div style=\"background-image:url('scroll.jpg'); background-size: 100% 100%; padding:20%\">\n\n";
	}

	public String GetFooter()
	{
		return "</div>\n\n</body>\n</html>";
	}

	public String BasicPage(String body)
	{
		return GetHeader() + body + GetFooter();
	}

	public String BasicPageWithTitle(String body, String title)
	{
		return GetHeader(title) + body + GetFooter();
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
	public String BasicBookInput(String boxid)
	{
		return h3("Next Book:") + form.GenerateBasicBookInput(boxid);
	}

	public String BasicBoxInput()
	{
		return h3("New box:") + form.GenerateBasicBoxInput();
	}

	public String BookInputWithTitleSet(String title, String boxid) {
		return h3("Next Book:") + form.GenerateBookInputWithTitleSet(title, boxid);
	}

	public String BookInputWithAuthorSet(String author, String boxid) {
		return h3("Next Book:") + form.GenerateBookInputWithAuthorSet(author, boxid);
	}
}