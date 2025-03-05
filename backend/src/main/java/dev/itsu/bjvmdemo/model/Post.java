package dev.itsu.bjvmdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
public class Post {

    @JsonProperty("sId")
    String sId;

    String name;

    String date;

    String text;

}
