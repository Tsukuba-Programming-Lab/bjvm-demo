package dev.itsu.bjvmdemo;

import dev.itsu.bjvm.*;
import dev.itsu.dom.html.*;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

public class Main {

    private static int id = 0;

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
                var textValue = newPost.getValue();
                var textLength = textValue.length();
                if (textLength == 0) {
                    $.alert("Post cannot be empty!");
                    return;
                }

                var nameValue = name.getValue();
                var nameLength = nameValue.length();
                if (nameLength == 0) {
                    $.alert("Name cannot be empty!");
                    return;
                }

                var post = new Post(id++, nameValue, "2025/2/15", textValue);
//                var postElement = createPostElement(post);
//                posts.appendChild(postElement);

                System.out.println(post);

                $.alert("Posted!");
            }
        });
    }

    private static HTMLElement createPostElement(Post post) {
        var imgElement = (HTMLImageElement) $.document.createElement("img");
        imgElement.setSrc("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg-u61ZQPWv4uospSzPDLLBmr_3DeqacUAZarynzB9pJYxUoxPakMDw6RY-0wodVerLcJaaPnVojNRAsVucke_E46aLieQKp-iaXrl0fRB3rZNO7E4tdw_v7RS5UPELxoOjyZNVTXW0kMI/s170/animal_kuma.png");
        imgElement.setAlt("User Icon");

        var nameElement = (HTMLPElement) $.document.createElement("img");
        nameElement.setClassName("name");
        nameElement.setTextContent(post.getName());

        var divElement = (HTMLDivElement) $.document.createElement("div");
        divElement.appendChild(imgElement);
        divElement.appendChild(nameElement);

        var dateElement = (HTMLPElement) $.document.createElement("p");
        dateElement.setClassName("date");
        dateElement.setTextContent(post.getDate());

        var headerElement = (HTMLHeaderElement) $.document.createElement("header");
        headerElement.appendChild(divElement);
        headerElement.appendChild(dateElement);

        var textElement = (HTMLPElement) $.document.createElement("p");
        textElement.setClassName("text");
        textElement.setTextContent(post.getText());

        var mainElement = (HTMLMainElement) $.document.createElement("main");
        mainElement.appendChild(textElement);

        var postElement = (HTMLArticleElement) $.document.createElement("article");
        postElement.setClassName("post");
        postElement.appendChild(headerElement);
        postElement.appendChild(mainElement);

        return postElement;
    }

}
