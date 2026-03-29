package org.mcphase.base;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

/**
 * Provides Regex verification for InputVerifiers.
 * @author Till Hoffmann
 */
public class RegexVerifier extends InputVerifier {

    // <editor-fold defaultstate="collapsed" desc="Properties">
    private String pattern;

    /**
     * Returns the pattern.
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Sets the pattern.
     * @param pattern the pattern to set
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new instance of RegexVerifier.
     */
    public RegexVerifier() {
        pattern = ".*";
    }

    /**
     * Creates a new instance of RegexVerifier.
     * @param pattern the pattern
     */
    public RegexVerifier(String pattern) {
        this.pattern = pattern;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Implementation">
    private static final Color invalidColor = new Color(255, 153, 153);

    /**
     * Verifies whether the specified text matches the pattern.
     * @param text the text to match against the pattern
     * @return <code>true</code> if the text matches, <code>false</code> otherwise
     */
    public boolean verify(String text)
    {
        return text.matches(pattern);
    }

    @Override
    public boolean verify(JComponent input) {
        if(input instanceof JTextComponent)
            if(verify(((JTextComponent)input).getText())){
                input.setBackground(Color.white);
                return true;
            }
            else{
                input.setBackground(invalidColor);
                return false;
            }
        else
            return true;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Library">
    /**
     * Creates a regex based verifier for scientific numbers allowing for empty strings.
     * @return a regex based verifier for scientific numbers allowing for empty strings.
     */
    public static RegexVerifier getScientificVerifier() {
        return getScientificVerifier(true);
    }

    /**
     * Creates a regex based verifier for scientific numbers.
     * @param allowEmpty specifies whether an empty string is considered to be valid
     * @return a regex based verifier for scientific numbers.
     */
    public static RegexVerifier getScientificVerifier(boolean allowEmpty){
//        return allowEmpty ? new RegexVerifier("(^[+-]?((\\b[0-9]+)?\\.)?\\b[0-9]+([eE][-+]?[0-9]+)?\\b$)?"):
        return allowEmpty ? new RegexVerifier("(^[+-]?+((\\b[0-9]+)?+\\.)?+\\b[0-9]++([eE][-+]?+[0-9]++)?+\\b$)?+"):
//            new RegexVerifier("^[+-]?((\\b[0-9]+)?\\.)?\\b[0-9]+([eE][-+]?[0-9]+)?\\b$");
            new RegexVerifier("^[+-]?+((\\b[0-9]+)?+\\.)?+\\b[0-9]++([eE][-+]?+[0-9]++)?+\\b$");
    }
    // </editor-fold>
}
