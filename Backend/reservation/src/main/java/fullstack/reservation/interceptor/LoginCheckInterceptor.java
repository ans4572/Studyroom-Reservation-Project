package fullstack.reservation.interceptor;

import fullstack.reservation.exception.InterceptorException;
import fullstack.reservation.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        String requestURI = request.getRequestURI();

        if(request.getMethod().equals("OPTIONS")) {
            log.info("option 테스트 제발 되어라");
            return true;
        }

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("requestURI = {}", requestURI);
            throw new InterceptorException("인터셉터 오류");
        }


        return true;
    }
}
