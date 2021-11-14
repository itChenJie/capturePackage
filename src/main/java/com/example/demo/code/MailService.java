package com.example.demo.code;

import java.util.Map;

public interface MailService {
    void sendSimpleMail(String to, Map<String,StringBuilder> map);

    void beginCapturePackage();
}
