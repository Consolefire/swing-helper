/**
 * 
 */
package com.consolefire.swing.helper.model.syntax;

/**
 * @author sabuj.das
 *
 */
public class StyleConfigurationHelper {

	public static WordStyle getWordStyleByType(
			String language, String type, StyleConfiguration configuration){
		SyntaxStyle syntaxStyle = configuration.getStyleByLanguage(language);
		if(syntaxStyle != null){
			return syntaxStyle.getWordStyleList().getStyleByType(type);
		}
		return null;
	}
	
}
