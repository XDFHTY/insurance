package com.cjkj.insurance.mapper;

import com.cjkj.insurance.entity.*;
import com.cjkj.insurance.entity.other.OrderInfo;
import com.cjkj.insurance.entity.other.RespOrder;
import com.cjkj.insurance.entity.other.RespOrderItme;
import com.cjkj.insurance.utils.ApiResult;
import com.cjkj.insurance.utils.domain.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    int insertAdmin(Admin admin);

    /**
     * 查询管理员
     * @param adminName
     * @return
     */
    Admin findAdmin(String adminName);

    /**
     * 删除管理员  非物理删除
     * @param admin
     * @return
     */
    int updateAdmin(Admin admin);

    /**
     * 用户个人查询订单列表
     * @param userId
     * @return
     */
    List<Order> findOrder(Integer userId);

    //=============================================================================================

    /**
     * 查询订单列表
     */
    public List<List<?>> findAllOrderBypager(Pager pager);


    //查询管理员列表
    public List<List<?>> findAllAdminByPager(Pager pager);

    //根据Id查询admin
    public Admin findAdminById(Long adminId);

    //修改管理员信息，修改自己密码，修改他人密码
    int updateByPrimaryKeySelective(Admin record);

    //根据adminName查询管理员
    public Admin findAdminByName(String adminName);

    //添加管理员
    int insertSelective(Admin record);

    //查询订单详情
    public OrderInfo findOrderInfo(Map map);

}

