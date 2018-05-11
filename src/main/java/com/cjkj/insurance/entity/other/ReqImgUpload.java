package com.cjkj.insurance.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 影像上传请求信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqImgUpload {

    /**
     * 任务号
     */
    private String taskId;

    /**
     * 影像集合
     */
    private List<ImageInfo> imageInfos;


}
