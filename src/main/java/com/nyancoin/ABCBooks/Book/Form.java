package com.nyancoin.ABCBooks.Book;

// This class has magic values and helper methods to generate HTML forms.
public class Form {
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