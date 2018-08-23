package epam.zlobich.task6.tag;

import epam.zlobich.task6.entity.entitybd.UserBd;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;

@SuppressWarnings("serial")
public class UserTag extends TagSupport{
    @Override

    public int doStartTag() throws JspTagException {

        if(((UserBd)pageContext.getSession().getAttribute("user")).isRole()) return SKIP_BODY;

        return EVAL_BODY_INCLUDE;
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

