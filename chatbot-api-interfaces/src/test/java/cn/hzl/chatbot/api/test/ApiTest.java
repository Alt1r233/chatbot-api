package cn.hzl.chatbot.api.test;


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class ApiTest {

    /**
     * @Author: ALT1R
     * @Description:  获取待我回答问题信息
     * @Date: 2023/3/12 15:45
     * @Parms: []
     * @ReturnType: void
     */
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //请求路径
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");
        //设置请求头
        get.addHeader("cookie", "zsxq_access_token=C60B117D-959A-88CA-5B4A-47333F592A15_217C79B3153B764E; sensorsdata2015jssdkcross={\"distinct_id\":\"581545848854154\",\"first_id\":\"186ce8a33cc407-0f46dd83f7da5b-65075b53-1350728-186ce8a33cd90c\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2Y2U4YTMzY2M0MDctMGY0NmRkODNmN2RhNWItNjUwNzViNTMtMTM1MDcyOC0xODZjZThhMzNjZDkwYyIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjU4MTU0NTg0ODg1NDE1NCJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"581545848854154\"},\"$device_id\":\"186ce8a33cc407-0f46dd83f7da5b-65075b53-1350728-186ce8a33cd90c\"}; abtest_env=product; zsxqsessionid=538e9c6f0e02bced4f350b134475a2d8");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
    /**
     * @Author: ALT1R
     * @Description:  回答问题方法
     * @Date: 2023/3/12 15:47
     * @Parms: []
     * @ReturnType: void
     */

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 自行申请 https://beta.openai.com/overview");

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}
