package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.*;
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
     * 查询车辆信息表
     * @param userID
     * @return
     */
    List<CarInfo> findCarInfoById(Integer userID);

    /**
     * 根据任务号查询回掉信息
     * @param taskId
     * @return
     */
    List<RespMsg> findRespMsg(String taskId);

    /**
     * 根据用户id查询车主信息
     * @param userID
     * @return
     */
    List<CarOwner> findCarOwnerById(Integer userID);

    /**
     * 根据任务号查询险种信息
     * @param taskId
     * @return
     */
    List<InsureInfo> findInsureInfoByTaskId(@Param("taskId") String taskId,@Param("prvId") String prvId);

    /**
     * 根据任务号和供应商id查询险种信息
     * @param taskId
     * @param prvId
     * @return
     */
    List<RiskKinds> findRiskKinds(@Param("taskId") String taskId,@Param("prvId") String prvId);

    /**
     * 根据用户id查询影像信息
     * @param userID
     * @return
     */
    List<UserImg> findUserImgById(Integer userID);

    /**
     * 根据任务号和供应商id查询配送信息
     * @param taskId
     * @param prvId
     * @return
     */
    List<Delivery> findDelivery(@Param("taskId") String taskId,@Param("prvId") String prvId);

    /**
     * 根据任务号和供应商id查询核保补充数据项
     * @param taskId
     * @param prvId
     * @return
     */
    List<InsureSupplys> findInsureSupplys(@Param("taskId") String taskId,@Param("prvId") String prvId);

    /**
     * 根据任务号和供应商id查询业务评级信息
     * @param taskId
     * @param prvId
     * @return
     */
    List<ScoreRate> findScoreRate(@Param("taskId") String taskId,@Param("prvId") String prvId);
}

