package com.nyancoin.ABCBooks.Book;

// This class has magic values and helper methods to generate HTML forms.
// Currently folded into UIO; keeping file as will likely split back out again later once UIO is more established
public class Form {		
	public static final String form_start = "<form>\n";

	// userLabel - label to show user describing field
	// fieldLabel - name/id for input to name requestparameter
	// focused - start with the focus on this input when page loads
	// maxlength - maximum allowed number of characters in input
	// defaultValue - starting value
	// hidden - hidden inputs are used to pass along prior entries in forms
	public static String LabeledInput(String userLabel, 
	String fieldLabel, boolean focused, Integer maxlength,
	String defaultValue, boolean hidden) {
		// For a long input, switch to multiline
		boolean textarea = maxlength > 50;

		String result = "";
		
		if (!hidden) {
			result += "<label for=\"";
			result += fieldLabel;
			result += "\"><strong>";
			result += userLabel;
			result += "</strong></label>\n";
			result += "<br><br>\n";
		}
		
		if (!textarea) {
			result += "<input type=\"text\" id=\"";
		} else {
			result += "<textarea cols=\"50\" rows=\"4\" id=\"";
		}		
		
		result += fieldLabel;
		result += "\" name=\"";
		result += fieldLabel;
		result += "\" style=\"";
		
		if (hidden) {
			result += "display:none;";
		}

		result += "width:";
		// We aren't going to go wider than 50, just taller
		result += Math.min(50, maxlength); // this is the width of the input; try to visually match length limit
		result += "ch\" maxlength=\"";
		result += maxlength; // this is convenience limit for user's browser's awareness (that is, be aware user devices can disregard; this is for user experience rather than security)
		result += "\" value=\"";
		result += defaultValue;
		result += "\"";

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

	// default to 50 characters
	private static Integer defaultMaxLength = 50;

	// default to not having focus
	private static boolean defaultFocus = false;

	public static String LabeledInput(String userLabel, String fieldLabel, boolean focused, Integer maxlength) {
		// default to no default value and visible input
		return LabeledInput(userLabel, fieldLabel, focused, maxlength, "", false);
	}

	public static String LabeledInput(String userLabel, String fieldLabel, boolean focused) {
		
		return LabeledInput(userLabel, fieldLabel, focused, defaultMaxLength);
	}

	public static String LabeledInput(String userLabel, String fieldLabel)
	{
		// default to not having focus
		return LabeledInput(userLabel, fieldLabel, defaultFocus);
	}

	public static String FocusedLabeledInput(String userLabel, String fieldLabel)
	{
		return LabeledInput(userLabel, fieldLabel, true);
	}

	public static String LabeledInputWithDefaultValue(String userLabel, String fieldLabel, String defaultValue) {
		// Here we're setting the default value we're given and also noting visible (no extra overload to not need final false)
		return LabeledInput(userLabel, fieldLabel, defaultFocus, defaultMaxLength, defaultValue, false);
	}

	public static String HiddenInput(String fieldLabel, String defaultValue) {
		// Don't bother labeling for user since we're hiding it anyhow
		return LabeledInput("",fieldLabel,false, defaultMaxLength, defaultValue, true);
	}

	public static String LabeledSubmit(String label)
	{
		return "<br>\n<input type=\"submit\" value=\"" + label + "\" style=\"height:2em; width:25vmin\">\n</form>\n\n";
	}

	public static String GenerateBasicBookInput(String boxid)
	{
		String result = form_start;
		result += FocusedLabeledInput("Author", "author");
		result += LabeledInput("Title", "title");
		result += HiddenInput("boxid", boxid);
		result += LabeledSubmit("Add Book");
		return result;
	}
	
	public static String GenerateBasicBoxInput()
	{
		String result = form_start;
		result += FocusedLabeledInput("Box Title", "boxtitle");
		// Setting to 200 characters max; input not focused
		result += LabeledInput("Box Description", "boxdescription", false, 200);		
		result += LabeledSubmit("Add Box");
		return result;
	}

	public static String GenerateBookInputWithTitleSet(String title, String boxid)
	{
		String result = form_start;
		result += FocusedLabeledInput("Author", "author");
		result += LabeledInputWithDefaultValue("Title", "title",title);
		result += HiddenInput("boxid", boxid);
		result += LabeledSubmit("Add Book");
		return result;
	}

	public static String GenerateBookInputWithAuthorSet(String author, String boxid)
	{
		String result = form_start;
		result += LabeledInputWithDefaultValue("Author", "author", author);
		result += FocusedLabeledInput("Title", "title");
		result += HiddenInput("boxid", boxid);
		result += LabeledSubmit("Add Book");
		return result;
	}

	public static final String basic_book_input = "<form><label for=\"author\">Author</label><br><input type=\"text\" id=\"author\" name=\"author\" value=\"\" autofocus><br><label for=\"title\">Title</label><br><input type=\"text\" id=\"title\" name=\"title\" value=\"\"><br><br><input type=\"submit\" value=\"Submit\"></form>";

}