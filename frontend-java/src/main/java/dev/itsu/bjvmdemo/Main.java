package dev.itsu.bjvmdemo;

import dev.itsu.bjvm.$;
import org.w3c.dom.html.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from Main.java!");

        var posts = (HTMLDivElement) $.document.getElementById("posts");
        var newPost = (HTMLTextAreaElement) $.document.getElementById("newPost");
        var name = (HTMLInputElement) $.document.getElementById("name");
        var textCount = (HTMLParagraphElement) $.document.getElementById("textCount");
        var postButton = (HTMLButtonElement) $.document.getElementById("postButton");

    }
}
