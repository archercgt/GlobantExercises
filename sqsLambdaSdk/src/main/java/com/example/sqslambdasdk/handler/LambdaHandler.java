package com.example.sqslambdasdk.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import org.json.JSONObject;


public class LambdaHandler implements RequestHandler<SQSEvent, Void> {

    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {
        for (SQSEvent.SQSMessage msg : sqsEvent.getRecords()) {
            JSONObject jsonMessage = new JSONObject(msg.getBody());

            String subject = jsonMessage.getString("Subject");
            String body = jsonMessage.getString("Message");

            System.out.println("Subject: " + subject);
            System.out.println("Body: " + body);
        }
        return null;
    }
}
