package com.epam.library.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.library.entity.Client;
import com.epam.library.entity.User;

public class UserOnlineTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5483121634401816855L;

	public int doStartTag() throws JspException {
		try {
			User user = (User) pageContext.getSession().getAttribute("user");
			Client client = (Client) pageContext.getSession().getAttribute(
					"client");
			pageContext.getOut().print(
					"Hello " + client.getName() + " (" + user.getLogin() + ")");
		} catch (Exception ex) {
			throw new JspTagException("SimpleTag: " + ex.getMessage());
		}
		return SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

}
