package com.zjweu;

import com.google.gson.Gson;
import com.zjweu.constant.TraningConstant;
import com.zjweu.po.Score;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test {

    public static void main(String[] args) throws IOException {
        String str = "哟，瞧瞧这服务态度，连解释都懒得给了是吧？怪不得你们这菜里能吃出虫卵来。行啊，这事儿今天我可不跟你善罢甘休，你等着被曝光吧！看你们这店还怎么做生意！\n" +
                "\n" +
                "对话结束。\n" +
                "\n" +
                "{\"totalScore\": 15, \"listenScore\": 0, \"apologyScore\": 0, \"satisfactionScore\": 5, \"thanksScore\": 10}\n" +
                "\n" +
                "评分说明：\n" +
                "\n" +
                "Listen Score (0/25): 你根本没有听取顾客的投诉，反而直接粗暴回应，毫无倾听和理解顾客需求的意图。\n" +
                "\n" +
                "Apology Score (0/25): 完全没有道歉的意思，甚至恶语相向，导致问题升级。\n" +
                "\n" +
                "Satisfaction Score (5/25): 顾客完全不满意你的回应，情绪更加激动，问题未得到任何解决。\n" +
                "\n" +
                "Thanks Score (10/25): 你没有表示感谢，反而直接挑衅顾客，严重缺乏礼貌。\n" +
                "\n" +
                "改进建议：\n" +
                "\n" +
                "态度: 面对投诉时应保持冷静和专业，避免使用粗暴语言。\n" +
                "倾听: 尝试认真倾听顾客的反馈，并以诚恳的态度进行回应。\n" +
                "道歉: 即使问题较为严重，依然应该先道歉，以缓和顾客情绪，再提出解决方案。\n" +
                "专业性: 保持职业操守，即便顾客情绪激动，也应尽量安抚对方，而非火上浇油。";
        if (str.contains("对话结束")) {
            // 定义正则表达式，匹配大括号中的内容
            Pattern pattern = Pattern.compile("\\{[^{}]*\\}");
            Matcher matcher = pattern.matcher(str);

            if (matcher.find()) {
                // 提取匹配的部分
                String jsonString = matcher.group();
                System.out.println(jsonString);
                Gson gson = new Gson();
                Score score = gson.fromJson(jsonString, Score.class);
                // 输出各个字段的值
                System.out.println("Total Score: " + score.getTotalScore());
                System.out.println("Listen Score: " + score.getListenScore());
                System.out.println("Apology Score: " + score.getApologyScore());
                System.out.println("Satisfaction Score: " + score.getSatisfactionScore());
                System.out.println("Thanks Score: " + score.getThanksScore());

            } else {
                System.out.println("没有找到有效的 JSON 部分");


            }
        }

        String s = TraningConstant.above + "1111" + TraningConstant.below;
        System.out.println(s);

    }

}
