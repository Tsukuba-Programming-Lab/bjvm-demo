package dev.itsu.bjvmdemo;

import dev.itsu.bjvm.*;
import dev.itsu.bjvm.apis.fetch.RequestInit;
import dev.itsu.dom.html.*;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Main {

    private static final HashMap<String, String> HEADERS = new HashMap<>() {{
        put("Content-Type", "application/x-www-form-urlencoded");
    }};

    private static final HashMap<String, Post> postMap = new HashMap<>();
    private static int sId = 0;

    private static HTMLDivElement posts = null;
    private static HTMLTextareaElement newPost = null;
    private static HTMLInputElement name = null;
    private static HTMLPElement textCount = null;
    private static HTMLButtonElement postButton = null;

    static {
        posts = $.document.<HTMLDivElement>getElementByIdG("posts");
        newPost = $.document.<HTMLTextareaElement>getElementByIdG("newPost");
        name = $.document.<HTMLInputElement>getElementByIdG("name");
        textCount = $.document.<HTMLPElement>getElementByIdG("textCount");
        postButton = $.document.<HTMLButtonElement>getElementByIdG("postButton");
    }

    public static void main(String[] args) {
        System.out.println("Hello from Main.java!");

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
                    $.alert("1 文字以上入力してください！");
                    return;
                }

                var nameValue = name.getValue();
                var nameLength = nameValue.length();
                if (nameLength == 0) {
                    $.alert("名前は空にできません！");
                    return;
                }

                System.out.println("投稿中...");

//                var post = $.fetch("http://localhost:8080/api/v1/post", RequestInit.builder()
//                        .method("PUT")
//                        .body("name=" + nameValue + "&text=" + textValue)
//                        .headers(HEADERS)
//                        .build()
//                ).json(Post.class);

                var post = Post.builder()
                        .text(textValue)
                        .name(nameValue)
                        .date(String.valueOf(System.currentTimeMillis()))
                        .sId(String.valueOf(sId))
                        .build();

                postMap.put(String.valueOf(sId), post);
                var postElement = createPostElement(post);
                posts.appendChild(postElement);

                System.out.println("投稿完了：" + post.getSId());

                sId++;
            }
        });

        updatePosts();
    }

    private static void updatePosts() {
        System.out.println("投稿を取得中...");

        int i = 0;
        for (var entry : postMap.entrySet()) {
            var post = entry.getValue();
            if (post.getText().equals("testdate")) {
                i++;
                continue;
            }
            var postElement = createPostElement(post);
            posts.appendChild(postElement);
        }

        System.out.println((postMap.size() - i) + "件を取得しました！");
    }

    private static HTMLElement createPostElement(Post post) {
        var imgElement = $.document.<HTMLImgElement>createElementG("img");
        imgElement.setSrc("images/monster11.png");
        imgElement.setAlt("User Icon");

        var nameElement = $.document.<HTMLPElement>createElementG("p");
        nameElement.setClassName("name");
        nameElement.setTextContent(post.getName());

        var divElement = $.document.<HTMLDivElement>createElementG("div");
        divElement.appendChild(imgElement);
        divElement.appendChild(nameElement);

        var dateElement = $.document.<HTMLPElement>createElementG("p");
        dateElement.setClassName("date");

        // To make error for demo
        if (post.getText().equals("testdate")) {
            try {
                var date = new SimpleDateFormat().parse(post.getDate());
                var formatedDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
                dateElement.setTextContent(formatedDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            dateElement.setTextContent(post.getDate());
        }

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
