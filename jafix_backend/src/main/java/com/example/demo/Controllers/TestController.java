package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


class Response{
    Response(String t){
        this.t = t;
    }
    private String t;

    public String getS() {
        return t;
    }

    public void setS(String s) {
        this.t = t;
    }
}
@CrossOrigin
@RestController
public class TestController {
    char c = 10;
    final String MESSAGE_TEXT = "       T        T"+ c +
                                  "     T  T      T  T  " + c +
                                  "    T    TTTTTT    T" + c +
                                  "   T     O     O    T" + c +
                                  "  T         T        T" + c +
                                  "   T                T" + c +
                                  "     TTTTTTTTTTTTTT";
    @CrossOrigin
    @GetMapping("/message")
    public Response message(){
        return new Response(MESSAGE_TEXT);
    }

}
