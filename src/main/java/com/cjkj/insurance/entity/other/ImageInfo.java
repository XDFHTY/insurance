package com.cjkj.insurance.entity.other;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图像信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageInfo {

    /**
     * 影像类型
     * 影像编码
     */
    private String imageType;

    /**
     * 影像描述
     */
    private String imageName;

    /**
     * 图片格式
     */
    private String imageMode;

    /**
     * 图片URL
     */
    private String imageUrl;

    /**
     * 图片内容
     */
    private String imageContent;
}
