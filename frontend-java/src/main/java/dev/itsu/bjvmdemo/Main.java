package dev.itsu.bjvmdemo;

import dev.itsu.bjvm.*;
import dev.itsu.bjvm.apis.fetch.RequestInit;
import dev.itsu.dom.html.*;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import java.util.HashMap;

public class Main {

    private static int id = 0;
    private static final HashMap<String, String> HEADERS = new HashMap<>() {{
        put("Content-Type", "application/x-www-form-urlencoded");
    }};

    public static void main(String[] args) {
        System.out.println("Hello from Main.java!");

        var posts = $.document.<HTMLDivElement>getElementByIdG("posts");
        var newPost = $.document.<HTMLTextareaElement>getElementByIdG("newPost");
        var name = $.document.<HTMLInputElement>getElementByIdG("name");
        var textCount = $.document.<HTMLPElement>getElementByIdG("textCount");
        var postButton = $.document.<HTMLButtonElement>getElementByIdG("postButton");

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
                var postElement = createPostElement(post);
                posts.appendChild(postElement);

                var result = $.fetch("api/v1/post", RequestInit.builder()
                        .method("PUT")
                        .body("userName=" + nameValue + "&text=" + textValue)
                        .headers(HEADERS)
                        .build()
                );

                System.out.println(post);
            }
        });
    }

    private static HTMLElement createPostElement(Post post) {
        var imgElement = $.document.<HTMLImgElement>createElementG("img");
        imgElement.setSrc("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg-u61ZQPWv4uospSzPDLLBmr_3DeqacUAZarynzB9pJYxUoxPakMDw6RY-0wodVerLcJaaPnVojNRAsVucke_E46aLieQKp-iaXrl0fRB3rZNO7E4tdw_v7RS5UPELxoOjyZNVTXW0kMI/s170/animal_kuma.png");
        imgElement.setAlt("User Icon");

        var nameElement = $.document.<HTMLPElement>createElementG("p");
        nameElement.setClassName("name");
        nameElement.setTextContent(post.getName());

        var divElement =  $.document.<HTMLDivElement>createElementG("div");
        divElement.appendChild(imgElement);
        divElement.appendChild(nameElement);

        var dateElement = $.document.<HTMLPElement>createElementG("p");
        dateElement.setClassName("date");
        dateElement.setTextContent(post.getDate());

        var headerElement = $.document.<HTMLHeaderElement>createElementG("header");
        headerElement.appendChild(divElement);
        headerElement.appendChild(dateElement);

        var textElement = $.document.<HTMLPElement>createElementG("p");
        textElement.setClassName("text");
        textElement.setTextContent(post.getText());

        var mainElement = $.document.<HTMLMainElement>createElementG("main");
        mainElement.appendChild(textElement);

        var postElement = $.document.<HTMLArticleElement>createElementG("article");
        postElement.setClassName("post");
        postElement.appendChild(headerElement);
        postElement.appendChild(mainElement);

        return postElement;
    }

}
