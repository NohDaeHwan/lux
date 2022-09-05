package com.used.lux.dto;

import com.used.lux.domain.Image;

public record ImageDto(
        Long id,
        String img1,
        String img2,
        String img3
) {

    public static ImageDto of(Long id, String img1, String img2, String img3) {
        return new ImageDto(id, img1, img2, img3);
    }

    public static ImageDto from(Image entity) {
        return new ImageDto(
                entity.getId(),
                entity.getImg1(),
                entity.getImg2(),
                entity.getImg3()
        );
    }
    public Image toEntity() {
        return Image.of(img1, img2, img3);
    }

}
