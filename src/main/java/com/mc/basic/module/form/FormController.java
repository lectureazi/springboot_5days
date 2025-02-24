package com.mc.basic.module.form;

import com.mc.basic.module.form.request.FormRequest;
import com.mc.basic.module.form.response.FormResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("form")
public class FormController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String getForm(
            FormRequest request, // @ModelAttribute 암묵적 바인드
            Model model
    ){
        log.info("form : {}", request);

        FormResponse response = generateFormResponse(request);
        model.addAttribute("response", response);

        log.info("model : {}", model);
        return "spring/example";
    }

    private FormResponse generateFormResponse(FormRequest request) {
        return new FormResponse(request.id(), request.email(), request.name(), LocalDateTime.now());
    }
}
