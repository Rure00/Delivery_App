package com.delivery.app.Delivery.data.dto.response.review;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
public class GetMarketReviewsResponseDto implements BaseResponseDto {
    private ArrayList<Long> id;
    private ArrayList<String> comment;
    private ArrayList<Float> score;
    private ArrayList<LocalDateTime> date;

    public GetMarketReviewsResponseDto() {
        id = new ArrayList<>();
        comment = new ArrayList<>();
        score = new ArrayList<>();
        date = new ArrayList<>();
    }
    public boolean isEmpty() {
        return id.isEmpty();
    }
    public void addElement(Long id, String comment, Float score, LocalDateTime date) {
        this.id.add(id);
        this.comment.add(comment);
        this.score.add(score);
        this.date.add(date);
    }
}
