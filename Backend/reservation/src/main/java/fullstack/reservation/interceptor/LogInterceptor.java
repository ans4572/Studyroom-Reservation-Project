package fullstack.reservation.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private final String LOG_ID = "logId";

    //컨트롤러 호출전에 호출된다.
    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, uuid);

        log.info("REQUEST : [{}][{}][{}]", uuid, requestURI, handler);

        response.setHeader("Set-Cookie", "JSESSIONID=" + request.getRequestedSessionId() +
                "; path=/; SameSite=None");
        return true;
    }

    //컨트롤러 호출 후에 호출된다.
    //예외 발생 시 호출되지 않는다.
    @Override
    public void postHandle(HttpServletRequest request
            , HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("postHandle [{}]", handler);
    }
    
    //뷰가 렌더링 된 후 호출
    //항상 호출된다.
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        String requestURI = request.getRequestURI();
        String logId = (String)request.getAttribute(LOG_ID);

        log.info("RESPONSE [{}][{}]", logId, requestURI);

        if (ex != null) {
            log.error("afterCompletion error!!", ex);
        }
    }
}
