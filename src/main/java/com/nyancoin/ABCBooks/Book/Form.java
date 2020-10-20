package com.nyancoin.ABCBooks.Book;

// This class has magic values and helper methods to generate HTML forms.
// Currently folded into UIO; keeping file as will likely split back out again later once UIO is more established
public class Form {		
	public static final String form_start = "<form>\n";

	public static String LabeledInput(String userLabel, String fieldLabel, boolean focused) {
		String result = "<label for=\"";
		result += fieldLabel;
		result += "\"><strong>";
		result += userLabel;
		result += "</strong></label>\n";
		result += "<br><br>\n";
		result += "<input type=\"text\" id=\"";
		result += fieldLabel;
		result += "\" name=\"";
		result += fieldLabel;
		result += "\" style=\"width:50ch\" maxlength=\"50\" value=\"\"";
		if (focused)
		{
			result += " autofocus";
		}
		result += ">\n<br><br>\n";
		return result;
	}

	public static String LabeledInput(String userLabel, String fieldLabel)
	{
		return LabeledInput(userLabel, fieldLabel, false);
	}

	public static String FocusedLabeledInput(String userLabel, String fieldLabel)
	{
		return LabeledInput(userLabel, fieldLabel, true);
	}

	public static String LabeledSubmit(String label)
	{
		return "<br>\n<input type=\"submit\" value=\"" + label + "\" style=\"height:2em; width:25vmin\">\n</form>\n\n";
	}

	public static String GenerateBasicBookInput()
	{
		String result = form_start;
		result += FocusedLabeledInput("Author", "author");
		result += LabeledInput("Title", "title");
		result += LabeledSubmit("Add Book");
		return result;
	}

	public static final String basic_book_input = "<form><label for=\"author\">Author</label><br><input type=\"text\" id=\"author\" name=\"author\" value=\"\" autofocus><br><label for=\"title\">Title</label><br><input type=\"text\" id=\"title\" name=\"title\" value=\"\"><br><br><input type=\"submit\" value=\"Submit\"></form>";

}