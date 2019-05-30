package com.example.demo.code;

import java.util.Map;

public interface MailService {
    public void sendSimpleMail(String to, Map<String,StringBuilder> map);

    public void beginCapturePackage();
}
