package com.nyancoin.ABCBooks.Book;

import org.springframework.stereotype.Component;

// This class has magic values and helper methods to generate HTML forms.
// Currently folded into UIO; keeping file as will likely split back out again later once UIO is more established
@Component
public class Form {
    public static final String form_start = "<form>\n";

    // userLabel - label to show user describing field
    // fieldLabel - name/id for input to name requestparameter
    // focused - start with the focus on this input when page loads
    // maxlength - maximum allowed number of characters in input
    // defaultValue - starting value
    // hidden - hidden inputs are used to pass along prior entries in forms
    public String LabeledInput(final String userLabel, final String fieldLabel, final boolean focused,
                                      final Integer maxlength, final String defaultValue, final boolean hidden) {
        // For a long input, switch to multiline
        final boolean textarea = maxlength > 50;

        final StringBuilder result = new StringBuilder();

        if (!hidden) {
            result.append("<label for=\"");
            result.append(fieldLabel);
            result.append("\"><strong>");
            result.append(userLabel);
            result.append("</strong></label>\n");
            result.append("<br><br>\n");
        }

        if (!textarea) {
            result.append("<input type=\"text\" id=\"");
        } else {
            result.append("<textarea cols=\"50\" rows=\"4\" id=\"");
        }

        result.append(fieldLabel);
        result.append("\" name=\"");
        result.append(fieldLabel);
        result.append("\" style=\"");

        if (hidden) {
            result.append("display:none;");
        }

        result.append("width:");
        // We aren't going to go wider than 50, just taller
        result.append(Math.min(50, maxlength)); // this is the width of the input; try to visually match length limit
        result.append("ch\" maxlength=\"");
        result.append(maxlength); // this is convenience limit for user's browser's awareness (that is, be aware user devices can disregard; this is for user experience rather than security)
        result.append("\" value=\"");
        result.append(defaultValue);
        result.append("\"");

        if (focused) {
            result.append(" autofocus");
        }

        result.append(">"); // end of input or textarea opening tag

        if (textarea) {
            result.append("</textarea>\n"); // unlike <input>, <textarea> needs closure.
        }


        result.append("\n<br><br>\n");
        return result.toString();
    }

    // default to 50 characters
    private static Integer defaultMaxLength = 50;

    // default to not having focus
    private static boolean defaultFocus = false;

    public String LabeledInput(String userLabel, String fieldLabel, boolean focused, Integer maxlength) {
        // default to no default value and visible input
        return LabeledInput(userLabel, fieldLabel, focused, maxlength, "", false);
    }

    public String LabeledInput(String userLabel, String fieldLabel, boolean focused) {

        return LabeledInput(userLabel, fieldLabel, focused, defaultMaxLength);
    }

    public String LabeledInput(String userLabel, String fieldLabel) {
        // default to not having focus
        return LabeledInput(userLabel, fieldLabel, defaultFocus);
    }

    public String FocusedLabeledInput(String userLabel, String fieldLabel) {
        return LabeledInput(userLabel, fieldLabel, true);
    }

    public String LabeledInputWithDefaultValue(final String userLabel, final String fieldLabel,
                                                      final String defaultValue) {
        // Here we're setting the default value we're given and also noting visible (no extra overload to not need final false)
        return LabeledInput(userLabel, fieldLabel, defaultFocus, defaultMaxLength, defaultValue, false);
    }

    public String HiddenInput(final String fieldLabel, final String defaultValue) {
        // Don't bother labeling for user since we're hiding it anyhow
        return LabeledInput("", fieldLabel, false, defaultMaxLength, defaultValue, true);
    }

    public String LabeledSubmit(final String label) {
        return "<br>\n<input type=\"submit\" value=\"" + label + "\" style=\"height:2em; width:25vmin\">\n</form>\n\n";
    }

    public String GenerateBasicBookInput(final String boxid) {
        final StringBuilder result = new StringBuilder(form_start);
        result.append(FocusedLabeledInput("LegacyAuthor", "author"));
        result.append(LabeledInput("Title", "title"));
        result.append(HiddenInput("boxid", boxid));
        result.append(LabeledSubmit("Add LegacyBook"));
        return result.toString();
    }

    public String GenerateBasicBoxInput() {
        final StringBuilder result = new StringBuilder(form_start);
        result.append(FocusedLabeledInput("LegacyBox Title", "boxtitle"));
        // Setting to 200 characters max; input not focused
        result.append(LabeledInput("LegacyBox Description", "boxdescription", false, 200));
        result.append(LabeledSubmit("Add LegacyBox"));
        return result.toString();
    }

    public String GenerateBookInputWithTitleSet(final String title, final String boxid) {
        final StringBuilder result = new StringBuilder(form_start);
        result.append(FocusedLabeledInput("LegacyAuthor", "author"));
        result.append(LabeledInputWithDefaultValue("Title", "title", title));
        result.append(HiddenInput("boxid", boxid));
        result.append(LabeledSubmit("Add LegacyBook"));
        return result.toString();
    }

    public String GenerateBookInputWithAuthorSet(final String author, final String boxid) {
        final StringBuilder result = new StringBuilder(form_start);
        result.append(LabeledInputWithDefaultValue("LegacyAuthor", "author", author));
        result.append(FocusedLabeledInput("Title", "title"));
        result.append(HiddenInput("boxid", boxid));
        result.append(LabeledSubmit("Add LegacyBook"));
        return result.toString();
    }

    public static final String basic_book_input = "<form><label for=\"author\">LegacyAuthor</label><br><input type=\"text\" id=\"author\" name=\"author\" value=\"\" autofocus><br><label for=\"title\">Title</label><br><input type=\"text\" id=\"title\" name=\"title\" value=\"\"><br><br><input type=\"submit\" value=\"Submit\"></form>";

}