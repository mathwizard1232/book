package com.nyancoin.ABCBooks.Book;

// This class has magic values and helper methods to generate HTML forms.
// Currently folded into UIO; keeping file as will likely split back out again later once UIO is more established
public class Form {		
	public static final String form_start = "<form>\n";

	public static String LabeledInput(String userLabel, String fieldLabel, boolean focused, Integer maxlength) {
		// For a long input, switch to multiline
		boolean textarea = maxlength > 50;
		
		String result = "<label for=\"";
		result += fieldLabel;
		result += "\"><strong>";
		result += userLabel;
		result += "</strong></label>\n";
		result += "<br><br>\n";

		
		if (!textarea) {
			result += "<input type=\"text\" id=\"";
		} else {
			result += "<textarea cols=\"50\" rows=\"4\" id=\"";
		}		
		
		result += fieldLabel;
		result += "\" name=\"";
		result += fieldLabel;
		result += "\" style=\"width:";
		// We aren't going to go wider than 50, just taller
		result += Math.min(50, maxlength); // this is the width of the input; try to visually match length limit
		result += "ch\" maxlength=\"";
		result += maxlength; // this is convenience limit for user's browser's awareness (that is, be aware user devices can disregard; this is for user experience rather than security)
		result += "\" value=\"\"";

		if (focused)
		{
			result += " autofocus";
		}

		result += ">"; // end of input or textarea opening tag

		if (textarea) {
			result += "</textarea>\n"; // unlike <input>, <textarea> needs closure.
		}

		
		result += "\n<br><br>\n";
		return result;
	}

	public static String LabeledInput(String userLabel, String fieldLabel, boolean focused) {
		return LabeledInput(userLabel, fieldLabel, focused, 50);
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

	public static String GenerateBasicBoxInput()
	{
		String result = form_start;
		result += FocusedLabeledInput("Box Title", "boxtitle");
		result += LabeledInput("Box Description", "boxdescription", false, 200);
		result += LabeledSubmit("Add Box");
		return result;
	}

	public static final String basic_book_input = "<form><label for=\"author\">Author</label><br><input type=\"text\" id=\"author\" name=\"author\" value=\"\" autofocus><br><label for=\"title\">Title</label><br><input type=\"text\" id=\"title\" name=\"title\" value=\"\"><br><br><input type=\"submit\" value=\"Submit\"></form>";

}