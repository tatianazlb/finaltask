package epam.zlobich.task6.tag;

import epam.zlobich.task6.entity.entitybd.UserBd;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;


import epam.zlobich.task6.entity.entitybd.UserBd;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class LocaleTag extends TagSupport {
    @Override

    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            String temp =  "<div align=\"right\"><form name=\"test\" method=\"post\" action=\"/locale\"> " +
                    "<div><button type=\"submit\" name=\"lbutton\" value=\"ru\">" +
                    ((ResourceBundle)pageContext.getSession().getAttribute("bundle")).getString("ru")+
                    "</button> <button type=\"submit\"name=\"lbutton\" value=\"en\">" +
                    ((ResourceBundle)pageContext.getSession().getAttribute("bundle")).getString("en")+
                    "</button></div> </form><div>";

            //String string = "<label><input type=\"radio\" checked name=\"ru\"/><%= RB.getString(\"ru\") %></label> <label><input type=\"radio\" name=\"dva\"/><%= RB.getString(\"en\") %></label>"

            out.write(temp);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

    @Override
    public int doAfterBody() throws JspTagException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspTagException {
        return EVAL_PAGE;
    }
}

