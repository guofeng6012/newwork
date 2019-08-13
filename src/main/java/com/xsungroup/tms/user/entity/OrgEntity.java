package com.xsungroup.tms.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xsungroup.tms.core.supper.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 组织表
 * </p>
 *
 * @author Alex
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_org")
public class OrgEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 组织ID
     */
    @TableId(value = "org_id", type = IdType.UUID)
    private String orgId;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * groupID
     */
    private String groupId;

    /**
     *  所属公司ID
     */
//    private String companyId;


    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 企业角色
     */
    private Integer orgRole;

    /**
     * '组织类型 0:公司 1:网点'
     */
    private Integer orgType;

    /**
     * 来源  0:注册 1:创建
     */
    private Integer orgSource;

    /**
     * 0:企业 1:个人货主
     */
    private Integer bizType;

    /**
     * 法人组织 0:未选中  1:选中
     */
    private Integer isLegal;

    /**
     * 核算组织 0:未选中  1:选中
     */
    private Integer isAccountant;

    /**
     * 运营组织 0:未选中  1:选中
     */
    private Integer isOperation;

    /**
     * 是否道路运输许可
     */
    private Integer isRoadTrans;

    /**
     * 是否危险品运输
     */
    private Integer isDangerTrans;

    /**
     * 是否普货经营
     */
    private Integer isRegularGoods;

    /**
     * 是否冷链经营
     */
    private Integer isColdChainGoods;

    /**
     * 是否危化品经营
     */
    private Integer isDangerousGoods;

    /**
     * 是否集装箱经营
     */
    private Integer isContainerGoods;

    /**
     * 审核状态 0:待审核  1:审核拒绝  2:审核通过
     */
    private Integer auditStatus;

}
