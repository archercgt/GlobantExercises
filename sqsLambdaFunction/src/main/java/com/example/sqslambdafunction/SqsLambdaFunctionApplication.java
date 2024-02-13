package com.example.sqslambdafunction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.function.Function;

@SpringBootApplication
public class SqsLambdaFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqsLambdaFunctionApplication.class, args);
    }

    //Function that receives the SQS messages as an event and extracts the SNS subject and body content.
    @Bean
    public Function<String,String> handler() {
        return (s) -> {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray recordsArray = jsonObject.getJSONArray("Records");
            JSONObject record = recordsArray.getJSONObject(0);
            JSONObject body = new JSONObject(record.getString("body"));

            String subject = body.getString("Subject");
            String message = body.getString("Message");

            System.out.println("Subject: " + subject);
            System.out.println("Message: " + message);
            return s;
        };
    }
}
