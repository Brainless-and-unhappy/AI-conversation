package com.zjweu.websocket;

import com.zjweu.service.impl.CustomChatGpt;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket服务
 */
@Component
@ServerEndpoint("/ws/{sid}")
public class WebSocketServer {

    //存放会话对象
    private static Map<String, Session> sessionMap = new HashMap();

    final String apiKey = "sk-4jQ65kVe8tzjFfhJ91A3Bc4377114d708e4dA84e88C0Ff2a";
    CustomChatGpt customChatGpt = new CustomChatGpt(apiKey);

    CloseableHttpClient httpClient ;



    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        System.out.println("客户端：" + sid + "建立连接");
        httpClient = HttpClients.createDefault();
        // 根据自己的网络设置吧
        customChatGpt.setResponseTimeout(50000);
        sessionMap.put(sid, session);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {

        long start = System.currentTimeMillis();
        String answer = customChatGpt.getAnswer(httpClient, message);
        long end = System.currentTimeMillis();
        System.out.println("该回答花费时间为：" + (end - start) / 1000.0 + "秒");
        sendToAllClient(answer,sid);
        System.out.println("收到来自客户端：" + sid + "的信息:" + message);
    }

    /**
     * 连接关闭调用的方法
     *
     * @param sid
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) throws IOException {
        System.out.println("连接断开:" + sid);
        httpClient.close();
        sessionMap.remove(sid);
    }

    /**
     * 群发
     *
     * @param message
     */
    public void sendToAllClient(String message,String sid) {
        Session session = sessionMap.get(sid);
        //Collection<Session> sessions = sessionMap.values();
//        for (Session session : sessions) {
            try {
                //服务器向客户端发送消息
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }
    }

}
