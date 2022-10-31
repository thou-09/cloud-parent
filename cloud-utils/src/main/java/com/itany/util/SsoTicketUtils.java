package com.itany.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * SsoTicketUtils.
 *
 * @author Thou
 * @date 2022/10/31
 */
public class SsoTicketUtils {

    /**
     * 一次性签证存放处
     */
    private static final List<String> ONCE_TICKET_LIST = new ArrayList<>();

    /**
     * 分发一次性签证
     *
     * @return java.lang.String
     * @author Thou
     * @date 2022/10/31
     */
    public static String generateOnceTicket() {
        String ticket = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        ONCE_TICKET_LIST.add(ticket);
        return ticket;
    }

    /**
     * 验证签证是否合法，是否由 SSO 分发
     *
     * @param ticket -
     * @return boolean
     * @author Thou
     * @date 2022/10/31
     */
    public static boolean isLegal(String ticket) {
        if (null == ticket) {
            return false;
        }
        return ONCE_TICKET_LIST.contains(ticket);
    }

    /**
     * 销毁签证，验证完需要销毁
     *
     * @param ticket -
     * @author Thou
     * @date 2022/10/31
     */
    public static void destroy(String ticket) {
        ONCE_TICKET_LIST.removeIf(s -> s.equals(ticket));
    }
}
