package epam.zlobich.task6.requestwrapper;

import epam.zlobich.task6.encrypte.EncrypterMD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ValidationRequest extends HttpServletRequestWrapper {
    public ValidationRequest(HttpServletRequest request) {
        super(request);
    }
    @Override
    public String getParameter(String name) {
        String parameter =  super.getParameter(name);
        if (parameter!=null) {
            if (name.equals("password")) return EncrypterMD5.cryptWithMD5(parameter);
            return parameter.replaceAll("[\\w]", "");
        }
        else return null;
    }
}
