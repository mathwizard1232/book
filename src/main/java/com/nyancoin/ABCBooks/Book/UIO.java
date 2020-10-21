package com.nyancoin.ABCBooks.Book;

// This class will contain various I/O utility helper values / functions
public class UIO {
	private static Form form;

	public Integer IntFromString(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return -1;
		}
	}

	public static String br2()
	{
		return "\n<br><br>\n";
	}

	public static String ahref(String target, String anchor)
	{
		return "<a href=\"" + target + "\">" + anchor + "</a>\n";
	}

	public static String GetHeader()
	{
		return GetHeader("ABC Books");
	}

	public static String GetHeader(String title)
	{
		return "<html>\n<head>\n<title>" + title + "</title>\n</head>\n<body style=\"background-image:url('books.jpg'); padding-left: 20%; padding-right: 40%\">\n\n<div style=\"background-image:url('scroll.jpg'); background-size: 100% 100%; padding:20%\">\n\n";
	}

	public static String GetFooter()
	{
		return "</div>\n\n</body>\n</html>";
	}

	public static String BasicPage(String body)
	{
		return GetHeader() + body + GetFooter();
	}

	public static String BasicPageWithTitle(String body, String title)
	{
		return GetHeader(title) + body + GetFooter();
	}

	public static String h3(String content)
	{
		return "<h3>" + content + "</h3>\n";
	}

	public static String strong(String content)
	{
		return "<strong>" + content + "</strong>";
	}

	public static String spanColor(String content, String color)
	{
		return "<span style=\"background-color:" + color + "\">" + content + "</span>\n";
	}

	public static String hr()
	{
		return "\n<br><br><hr><br><br>\n\n";
	}

	// This is a UIO wrapper on core Form functionality; we want Application to go through UIO rather than Form
	public static String BasicBookInput(String boxid)
	{
		return h3("Next Book:") + form.GenerateBasicBookInput(boxid);
	}

	public static String BasicBoxInput()
	{
		return h3("New box:") + form.GenerateBasicBoxInput();
	}

	public static String BookInputWithTitleSet(String title, String boxid)
	{
		return h3("Next Book:") + form.GenerateBookInputWithTitleSet(title, boxid);
	}

	public static String BookInputWithAuthorSet(String author, String boxid)
	{
		return h3("Next Book:") + form.GenerateBookInputWithAuthorSet(author, boxid);
	}
}