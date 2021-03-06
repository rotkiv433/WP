package mk.ukim.finki.wp.lab.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter
public class CourseFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        Long courseId = (Long) request.getSession().getAttribute("courseId");
        if(courseId==null && (!"/courses".equals(path)
                    && !path.contains("/courses/add")
                    && !path.contains("/courses/delete")
                    && !path.contains("/courses/edit"))){
            response.sendRedirect("/courses");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
