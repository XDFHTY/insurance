package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.*;
import com.cjkj.insurance.entity.other.RespOrder;
import com.cjkj.insurance.entity.other.RespOrderItme;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    /**
     * 登陆
     * @param admin
     * @return
     */
    Admin login(Admin admin);

    /**
     * 修改密码
     * @param admin
     * @return
     */
    Admin findUser(Admin admin);
    int change(Admin admin1);

    /**
     * 查询订单列表
     * @return
     */
    List<RespOrder> findOrderList();


    /**
     * 查询订单详情
     * @param userID
     * @param taskId
     * @param prvId
     * @param taskState
     * @return
     */
    List<RespOrderItme> findOrderItmesById(@Param("userId") Integer userID,
                                           @Param("taskId") String taskId,
                                           @Param("prvId") String prvId,
                                           @Param("taskState") String taskState);

    /**
     * 查询保险配置信息
     * @param taskId
     * @param prvId
     * @param taskState
     * @return
     */
    List<InsureInfo> findInsureInfoById(@Param("taskId") String taskId,
                                        @Param("prvId") String prvId,
                                        @Param("taskState") String taskState);

    /**
     * 查询险种信息
     * @param taskId
     * @param prvId
     * @param taskState
     * @return
     */
    List<RiskKinds> findRiskKindsById(@Param("taskId") String taskId,
                                      @Param("prvId") String prvId,
                                      @Param("taskState") String taskState);

}

