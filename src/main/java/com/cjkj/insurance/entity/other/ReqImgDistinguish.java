package com.cjkj.insurance.entity.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 影像识别请求信息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqImgDistinguish {

    private ImageInfo imageInfo;
}
