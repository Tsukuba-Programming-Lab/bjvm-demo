package dev.itsu.bjvmdemo;

import lombok.*;

@Value
@Builder
public class Post {

    String sId;

    @NonNull
    String name;

    @NonNull
    String date;

    @NonNull
    String text;

}
