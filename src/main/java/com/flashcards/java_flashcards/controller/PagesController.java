package com.flashcards.java_flashcards.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @RequestMapping("/")
    public String welcome()
    {
        return "index";
    }

    @RequestMapping("/add_flashcard")
    public String flashcards()
    {
        return "add_flashcard";
    }
}
