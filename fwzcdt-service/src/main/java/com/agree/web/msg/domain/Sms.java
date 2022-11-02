package com.agree.web.msg.domain;

import lombok.Data;

@Data
public class Sms {
    private String phoneNumber;
    private String code;
    private int min;
}


