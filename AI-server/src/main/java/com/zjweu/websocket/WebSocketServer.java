package com.zjweu.websocket;

import cn.hutool.core.bean.BeanUtil;
import com.google.gson.Gson;
import com.zjweu.constant.TraningConstant;
import com.zjweu.dto.AIDTO;
import com.zjweu.po.Scene;
import com.zjweu.po.Score;
import com.zjweu.po.TrainingDialogueRecord;
import com.zjweu.po.TrainingRecord;
import com.zjweu.service.SceneService;
import com.zjweu.service.ScoreService;
import com.zjweu.service.TrainingDialogueRecordService;
import com.zjweu.service.TrainingRecordService;
import com.zjweu.service.impl.CustomChatGpt;
import com.zjweu.service.impl.TrainingDialogueRecordServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WebSocket服务
 */
@Component
@ServerEndpoint("/ws/{sid}")
public class WebSocketServer {
    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }

    private TrainingDialogueRecordService trainingDialogueRecordService;

    private TrainingRecordService trainingRecordService;

    private SceneService sceneService;

    private ScoreService scoreService;

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
        this.trainingDialogueRecordService = applicationContext.getBean(TrainingDialogueRecordService.class);
        this.scoreService=applicationContext.getBean(ScoreService.class);
        this.trainingRecordService=applicationContext.getBean(TrainingRecordService.class);
        this.sceneService=applicationContext.getBean(SceneService.class);
        TrainingRecord trainingRecord = trainingRecordService.getById(sid);
        Scene scene = sceneService.getById(trainingRecord.getSceneId());
        String s = TraningConstant.above + scene.getPrompt() + TraningConstant.below;

        System.out.println("客户端：" + sid + "建立连接");
        httpClient = HttpClients.createDefault();
        // 根据自己的网络设置吧
        customChatGpt.setResponseTimeout(50000);
        sessionMap.put(sid, session);
        long start = System.currentTimeMillis();
        //发送信息
        String answer = customChatGpt.getAnswer(httpClient, s);
        long end = System.currentTimeMillis();
        System.out.println("该回答花费时间为：" + (end - start) / 1000.0 + "秒");

        //AI回答
        TrainingDialogueRecord trainingDialogueRecord=new TrainingDialogueRecord();
        trainingDialogueRecord.setTrainingId(Integer.parseInt(sid));
        trainingDialogueRecord.setSpeaker(Boolean.FALSE);
        trainingDialogueRecord.setContent(answer);
        this.trainingDialogueRecordService.insert(trainingDialogueRecord);

        sendToAllClient(answer,sid);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) throws IOException {
        System.out.println("收到来自客户端：" + sid + "的信息:" + message);

        //用户说话
        TrainingDialogueRecord trainingDialogueRecord=new TrainingDialogueRecord();
        System.out.println(sid);

        System.out.println(Integer.parseInt(sid));
        trainingDialogueRecord.setTrainingId(Integer.parseInt(sid));
        trainingDialogueRecord.setSpeaker(Boolean.TRUE);
        trainingDialogueRecord.setContent(message);
        System.out.println(trainingDialogueRecord);
        this.trainingDialogueRecordService.insert(trainingDialogueRecord);

        long start = System.currentTimeMillis();
        //发送信息
        String answer = customChatGpt.getAnswer(httpClient, message);
        long end = System.currentTimeMillis();
        System.out.println("该回答花费时间为：" + (end - start) / 1000.0 + "秒");

        if (answer.contains("对话结束")) {
            String[] parts = answer.split("对话结束");
            System.out.println(parts);
            sendToAllClient(parts[0],sid);

            //sendToAllClient("对话结束",sid);
            // 定义正则表达式，匹配大括号中的内容
            getScore(sid, answer);




            this.onClose(sid);
        } else {
            //AI回答
            trainingDialogueRecord=new TrainingDialogueRecord();
            trainingDialogueRecord.setTrainingId(Integer.parseInt(sid));
            trainingDialogueRecord.setSpeaker(Boolean.FALSE);
            trainingDialogueRecord.setContent(answer);
            this.trainingDialogueRecordService.insert(trainingDialogueRecord);

            sendToAllClient(answer,sid);

        }





    }

    /**
     * 连接关闭调用的方法
     *
     * @param sid
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) throws IOException {
        System.out.println("连接断开:" + sid);
        if (trainingRecordService.getById(sid).getScoreId() == null)
        {
            long start = System.currentTimeMillis();
            //发送信息
            String answer = customChatGpt.getAnswer(httpClient,"结束对话");
            long end = System.currentTimeMillis();
            System.out.println("该回答花费时间为：" + (end - start) / 1000.0 + "秒");

            getScore(sid, answer);


        }

        if (httpClient != null) {
            httpClient.close();
        }
        Session session = sessionMap.get(sid);
        if (session != null)
        {session.close();}
        sessionMap.remove(sid);
    }

    private void getScore(@PathParam("sid") String sid, String answer) {
        Pattern pattern = Pattern.compile("\\{[^{}]*\\}");
        Matcher matcher = pattern.matcher(answer);
        if (matcher.find()) {
            // 提取匹配的部分
            String jsonString = matcher.group();
            //System.out.println(jsonString);
            Gson gson = new Gson();
            AIDTO json = gson.fromJson(jsonString, AIDTO.class);
            System.out.println(json);

            Score score = BeanUtil.copyProperties(json, Score.class);
            scoreService.insert(score,sid);
            //获取反馈
            System.out.println(json.getFeedback());
            trainingRecordService.setFeedback(sid,json.getFeedback());

        } else {
            System.out.println("没有找到有效的 JSON 部分");
        }
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
