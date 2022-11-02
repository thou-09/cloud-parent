package com.itany.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocketService.
 *
 * @author Thou
 * @date 2022/11/2
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{userid}")
public class WebSocketService {

    /**
     * 保存连接信息
     */
    private static final Map<String, Session> CLIENTS = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("userid") String userid, Session session) {
        log.warn("[onOpen] " + session.getRequestURI().getPath() + " 打开连接开始 ==> sessionId::" + session.getId());
        // 连接已存在，关闭
        if (CLIENTS.containsKey(userid)) {
            onClose(userid, session);
        }
        CLIENTS.put(userid, session);
        log.warn("[onOpen] " + session.getRequestURI().getPath() + " 打开连接完成 ==> 连接数::" + CLIENTS.size());
    }

    @OnClose
    public void onClose(@PathParam("userid") String userid, Session session) {
        log.warn("[onClose] " + session.getRequestURI().getPath() + " 关闭连接开始 ==> sessionId::" + session.getId());
        CLIENTS.remove(userid);
        log.warn("[onClose] " + session.getRequestURI().getPath() + " 关闭连接完成 ==> 连接数::" + CLIENTS.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.warn("[onMessage] 前台发送消息 ==> " + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("[onError] 连接发生错误 ==> " + error.getMessage());
    }

    public void onClose(Session session) {
        try {
            session.close();
        } catch (IOException e) {
            log.error("[onClose] 关闭连接发生错误 ==> " + e.getMessage());
        }
    }

    public void sendMessage(String message) {
        log.warn("[sendMessage] 推送群体消息 ==> " + message);
        CLIENTS.forEach((s, session) -> {
            session.getAsyncRemote().sendText(message);
        });
    }

    public void sendMessage(String message, String userid) {
        log.warn("[sendMessage] 推送单体消息 ==> " + userid + "::" + message);
        Session session = CLIENTS.get(userid);
        session.getAsyncRemote().sendText(message);
    }
}
