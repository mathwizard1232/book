package com.nyancoin.ABCBooks.Book;

// This class will contain various I/O utility helper values / functions
public class UIO {
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
		return "<br><br><hr><br><br>\n";
	}

	// Rest is Form functionality, pulled into UIO temporarily (changing BookApplication to go through new UIO rather than Form for overall I/O functionality)
	public static final String basic_book_input = "<form><label for=\"author\">Author</label><br><input type=\"text\" id=\"author\" name=\"author\" value=\"\" autofocus><br><label for=\"title\">Title</label><br><input type=\"text\" id=\"title\" name=\"title\" value=\"\"><br><br><input type=\"submit\" value=\"Submit\"></form>";

	public static String BookInputWithTitleSet(String title)
	{
		String result = "";
		result += "<form><label for=\"author\">Author</label><br><input type=\"text\" id=\"author\" name=\"author\" value=\"\" autofocus><br><label for=\"title\">Title</label><br><input type=\"text\" id=\"title\" name=\"title\" value=\"";
		result += title;
		result += "\"><br><br><input type=\"submit\" value=\"Submit\"></form>";
		return result;
	}

	public static String BookInputWithAuthorSet(String author)
	{
		String result = "";
		result += "<form><label for=\"author\">Author</label><br><input type=\"text\" id=\"author\" name=\"author\" value=\"";
		result += author;
		result += "\"><br><label for=\"title\">Title</label><br><input type=\"text\" id=\"title\" name=\"title\" value=\"\" autofocus><br><br><input type=\"submit\" value=\"Submit\"></form>";
		return result;
	}
}