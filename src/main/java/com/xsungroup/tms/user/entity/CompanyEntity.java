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
 * 公司表
 * </p>
 *
 * @author Alex
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_company")
public class CompanyEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "company_id", type = IdType.UUID)
    private String companyId;

    /**
     * 上级组织
     */
    private String groupId;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 来源  0:注册 1:创建
     */
    private String source;

    /**
     * 税号
     */
    private String companyCode;

    /**
     * 企业类型  0:承运商 1:货主 2:两者都是
     */
    private String companyType;

    /**
     * 单位地址
     */
    private String companyAddress;

    /**
     * 开户行名称
     */
    private String bankName;

    /**
     * 公司银行账号
     */
    private String bankCode;

    /**
     * 法人组织 0:未选中  1:选中
     */
    private String legalPerson;

    /**
     * 核算组织  0:未选中  1:选中
     */
    private String accountant;

    /**
     * 运营组织  0:未选中  1:选中
     */
    private String operation;

    /**
     * 0:企业 1:个人 2:司机
     */
    private String businessType;

    /**
     * 社会统一信用代码
     */
    private String businessCode;

    /**
     * 企业名称
     */
    private String businessName;

    /**
     * 注册资本
     */
    private String registeredCapital;

    /**
     * 资本类型
     */
    private String capitalType;

    /**
     * 企业地址
     */
    private String businessAddress;

    /**
     * 有效期开始时间
     */
    private String validTimeStart;

    /**
     * 有效期结束时间
     */
    private String validTimeEnd;

    /**
     * 发证机关
     */
    private String licensingCompanyaniz;

    /**
     * 营业执照url
     */
    private String businessUrl;

    /**
     * 法人身份证
     */
    private String legalPersonIdCard;

    /**
     * 道路运输许可证
     */
    private String roadTransport;

    /**
     * 危险品运输
     */
    private String dangerTransport;

    /**
     * 授权涵访问地址
     */
    private String warrant;

    /**
     * 审核状态 0:待审核  1:审核拒绝  2:审核通过
     */
    private String auditStatus;

    /**
     * 联系人
     */
    private String contactMan;

    /**
     * 公司联系号码
     */
    private String contactNo;

    /**
     * 公司邮箱
     */
    private String companyEmail;

    /**
     * 公司传真
     */
    private String companyFax;

    /**
     * 首次提交时间
     */
    private String firstSubmitTime;

    /**
     * 最近提交时间
     */
    private String lastSubmitTime;

    /**
     * 首次审核时间
     */
    private String firstAuditTime;

    /**
     * 最新审核时间
     */
    private String lastAuditTime;

    /**
     * 审核方式 0:自动  1:人工
     */
    private String auditMode;

    /**
     * 审核人pk
     */
    private String auditUser;

    /**
     * 创建人
     */
    private String createUser;


    /**
     * 修改人
     */
    private String modifyUser;



    /**
     * 普货（存放普货中勾选的小类）
     */
    private String generalGoods;

    /**
     * 冷链（存放冷链中勾选的小类）
     */
    private String coldChainGoods;

    /**
     * 危险品（存放危险品中勾选的小类）
     */
    private String dangerousGoods;

    /**
     * 集装箱（存放集装箱中勾选的小类）
     */
    private String containerGoods;

    /**
     * 框架协议（存路劲）
     */
    private String agreement;

  }
