package com.itany.interceptor;

import com.itany.config.AuthorityCacheManager;
import com.itany.constants.AppExceptionMsgEnum;
import com.itany.dto.ManagerUserDTO;
import com.itany.exception.AppException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * LoginInterceptor.
 *
 * @author Thou
 * @date 2022/10/30
 */
public class LoginInterceptor implements HandlerInterceptor {

    public static final String[] PUBLIC_URL_PREFIXES = new String[] {
            "/error/404",
            "/manager/index",
            "/manager/areas",
            "/manager/examines",
            "/manager/managerUsers",
            "/manager/messages",
            "/manager/notices",
            "/manager/permissions",
            "/manager/roles",
            "/manager/serverCompanies",
            "/manager/servers",
            "/manager/types",
            "/manager/users",
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ManagerUserDTO loginUser = (ManagerUserDTO)request.getSession().getAttribute("loginUser");
        if (Objects.isNull(loginUser)) {
            response.sendRedirect("/manager/login");
            return false;
        }
        String uri = request.getRequestURI();
        for (String prefix : PUBLIC_URL_PREFIXES) {
            if (uri.startsWith(prefix)) {
                return true;
            }
        }
        List<Integer> userRoleIds = AuthorityCacheManager.USER_ROLE_MAP.get(loginUser.getId());
        if (CollectionUtils.isEmpty(userRoleIds)) {
            throw new AppException(AppExceptionMsgEnum.USER_ACCESS_NOT_ALLOWED);
        }
        userRoleIds.forEach(rid -> {
            List<String> uris = AuthorityCacheManager.ROLE_URI_MAP.get(rid);
            if (null == uris || uris.isEmpty()) {
                throw new AppException(AppExceptionMsgEnum.USER_ACCESS_NOT_ALLOWED);
            }
            if (!uris.contains(uri)) {
                throw new AppException(AppExceptionMsgEnum.USER_ACCESS_NOT_ALLOWED);
            }
        });
        return true;
    }
}
