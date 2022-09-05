package com.used.lux.response;

import com.used.lux.dto.ImageDto;

public record ImageResponse(
        Long id,
        String img1,
        String img2,
        String img
) {

    public static ImageResponse of(Long id, String img1, String img2, String img) {
        return new ImageResponse(id, img1, img2, img);
    }

    public static ImageResponse from(ImageDto imageDto) {
        return new ImageResponse(
                imageDto.id(),
                imageDto.img1(),
                imageDto.img2(),
                imageDto.img3()
        );
    }

}
