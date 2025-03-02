package dev.itsu.bjvmdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
//@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;

    @NonNull
    String name;

    @NonNull
    String text;

    @Column(name = "date", updatable = false)
    @CreatedDate
    Date date;

    // String型のIDを扱うためのフィールド（実際のカラムには存在しない）
    @Transient
    @JsonProperty("sId")
    private String sId;

    public void setId(Long id) {
        this.id = id;
        // IntegerのIDからStringのIDを自動設定
        if (id != null) {
            this.sId = id.toString();
        }
    }

    public String getSId() {
        if (sId == null && id != null) {
            sId = id.toString();
        }
        return sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
        // StringからIntegerへの変換（必要な場合）
        try {
            if (sId != null) {
                this.id = Long.parseLong(sId);
            }
        } catch (NumberFormatException e) {
            // 変換エラー処理
        }
    }

}

