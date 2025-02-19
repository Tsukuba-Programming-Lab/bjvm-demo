package dev.itsu.bjvmdemo;

import dev.itsu.bjvm.*;
import dev.itsu.dom.html.*;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from Main.java!");

        var posts = (HTMLDivElement) $.document.getElementById("posts");
        var newPost = (HTMLTextareaElement) $.document.getElementById("newPost");
        var name = (HTMLInputElement) $.document.getElementById("name");
        var textCount = (HTMLPElement) $.document.getElementById("textCount");
        var postButton = (HTMLButtonElement) $.document.getElementById("postButton");

        newPost.addEventListener("input", new EventListener() {
            @Override
            public void handleEvent(Event evt) {
                var length = String.valueOf(newPost.getValue().length());
                textCount.setTextContent(length);
            }
        });

        postButton.addEventListener("click", new EventListener() {
            @Override
            public void handleEvent(Event evt) {
                var length = newPost.getValue().length();
                if (length == 0) {
                    $.alert("Post cannot be empty!");
                    return;
                }

                var nameLength = name.getValue().length();
                if (nameLength == 0) {
                    $.alert("Name cannot be empty!");
                    return;
                }

                System.out.println(name.getValue() + ": " + newPost.getValue());
            }
        });
    }
}
