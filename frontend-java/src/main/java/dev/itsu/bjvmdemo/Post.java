package dev.itsu.bjvmdemo;

import lombok.*;

@Value
@Builder
public class Post {

    int id;

    @NonNull
    String name;

    @NonNull
    String date;

    @NonNull
    String text;

}
