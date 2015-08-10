package skyline.util;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-24
 * Time: ÉÏÎç9:53
 * To change this template use File | Settings | File Templates.
 */

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }
}