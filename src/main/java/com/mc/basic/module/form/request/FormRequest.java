package com.mc.basic.module.form.request;

import java.time.LocalDateTime;

// record
// 1. immutable
// 2. getter를 속성명으로 자동생성
public record FormRequest(
        Integer id,
        String email,
        String name,
        LocalDateTime createdAt
) {

}
