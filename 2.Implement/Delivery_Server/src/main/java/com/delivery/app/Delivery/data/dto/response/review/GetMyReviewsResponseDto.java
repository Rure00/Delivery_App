package com.delivery.app.Delivery.data.dto.response.review;

import com.delivery.app.Delivery.data.dto.response.BaseResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
public class GetMyReviewsResponseDto implements BaseResponseDto {
    private ArrayList<Long> id;
    private ArrayList<Long> marketId;
    private ArrayList<String> marketName;
    private ArrayList<String> comment;
    private ArrayList<Float> score;
    private ArrayList<LocalDateTime> date;

    public GetMyReviewsResponseDto() {
        id = new ArrayList<>();
        marketId = new ArrayList<>();
        marketName = new ArrayList<>();
        comment = new ArrayList<>();
        score = new ArrayList<>();
        date = new ArrayList<>();
    }
    public boolean isEmpty() {
        return id.isEmpty();
    }
    public void addElement(Long id, Long marketId, String marketName, String comment, Float score, LocalDateTime date) {
        this.id.add(id);
        this.marketId.add(marketId);
        this.marketName.add(marketName);
        this.comment.add(comment);
        this.score.add(score);
        this.date.add(date);
    }
}
