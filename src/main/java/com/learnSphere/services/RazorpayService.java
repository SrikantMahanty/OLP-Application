package com.learnSphere.services;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learnSphere.entity.Course;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
@Service
public class RazorpayService {
    @Value("${razorpay.keyId}")
    private String razorpayKeyId;
    
    @Value("${razorpay.keySecret}")
    private String razorpayKeySecret;
    
    private Map<String, Course> orderIdToCourseMap = new HashMap<>();
    
    public String createOrder(int amount) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
        
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // Amount in paisa
        orderRequest.put("currency", "INR");
        
        try {
            Order order = razorpayClient.orders.create(orderRequest);
            return order.get("id");
        } catch (RazorpayException e) {
            e.printStackTrace();
            // Handle exception
            return null;
        }
    }

    public boolean verifyPaymentSignature(String paymentId, String orderId, String receivedSignature) {
        String secret = razorpayKeySecret; // Replace with your actual Razorpay API key secret
        
        String expectedSignature = generateSignature(paymentId, orderId, secret);
        
        
        return receivedSignature.equals(expectedSignature);
    }
    
    private String generateSignature(String paymentId, String orderId, String secret) {
        try {
            String data = orderId + "|" + paymentId;
            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            
            StringBuilder result = new StringBuilder();
            for (byte b : rawHmac) {
                result.append(String.format("%02x", b));
            }
            
            return result.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
    public void addCourseOrderIdMapping(String orderId, Course course) {
        orderIdToCourseMap.put(orderId, course);
    }

    public Course fetchCourseByOrderId(String orderId) {
        return orderIdToCourseMap.get(orderId);
    }
    

}
