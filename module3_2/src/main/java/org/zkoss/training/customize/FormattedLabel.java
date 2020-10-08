package org.zkoss.training.customize;

import org.zkoss.zul.Label;

/**
 * A label that displays a float upon the specified "format" attribute.
 * 
 */
public class FormattedLabel extends Label {

	private static final long serialVersionUID = 7740185613644063144L;
	private String format = "";

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getValue() {
		if (format.length() > 0)
			return String.format(format, Float.valueOf(super.getValue()));
		
		return super.getValue();
	}

}
