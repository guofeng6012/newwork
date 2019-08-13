package com.xsungroup.tms.user.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author GF
 * @Date 2019-7-30 11:12:29
 * @Description 企业校验字段
 **/
public class OrgEnum{

    @Getter
    @AllArgsConstructor
    public enum OrgCheckEnum {

        CKECK_ENTERPRISE_ORG_NAME("orgName","license_org_name","营业执照-企业名称"),
        CKECK_ENTERPRISE_ORG_CODE("orgCode","license_org_code","营业执照-社会统一信用代码"),
        CKECK_ENTERPRISE_ORG_NO("orgNo","road_trans_no","道路运输许可证-许可号");

        private final String key;

        private final String code;

        private final String description;

        public static   OrgCheckEnum getOrgCheckEnum(String key){
            for(OrgCheckEnum orgCheckEnum : OrgCheckEnum.values()){
                if(orgCheckEnum.key.equals(key)){
                    return orgCheckEnum;
                }
            }
            return null;
        }
    }


    @Getter
    @AllArgsConstructor
    public enum  OrgChooseStatusEnum {

        Org_Choose_Status_off(0,"未选中"),
        Org_Choose_Status_on(1,"选中");

        private final Integer key;

        private final String description;
    }


    @Getter
    @AllArgsConstructor
    public enum OrgRoleEnum {
        ORG_ROLE_TYPE_0(0,"承运商"),
        ORG_ROLE_TYPE_1(1,"货主"),
        ORG_ROLE_TYPE_2(2,"两者都是");

        private final int type;

        private final String description;
    }

    @Getter
    @AllArgsConstructor
    public enum  OrgSourceEnum {
        ORG_SOURCE_0(0,"注册"),
        ORG_SOURCE_1(1,"创建");

        private final int type;

        private final String description;

    }

    @Getter
    @AllArgsConstructor
    public enum OrgStatusEnum {

        ORG_STATUS_0(0,"待审核"),
        ORG_STATUS_1(1,"审核拒绝"),
        ORG_STATUS_2(2,"审核通过");

        private final int type;

        private final String description;
    }

    @Getter
    @AllArgsConstructor
    public enum OrgTypeEnum {
        ORG_TYPE_0(0,"公司"),

        ORG_TYPE_1(1,"网点");

        private final int type;

        private final String description;

    }

    @Getter
    @AllArgsConstructor
    public enum OrgBizTypeEnum {
        ORG_BIZ_TYPE_0(0,"企业"),

        ORG_BIZ_TYPE_1(1,"个人");

        private final int type;

        private final String description;

    }

    @Getter
    @AllArgsConstructor
    public enum OrgCheckTypeEnum {
        ORG_BIZ_TYPE_0(0,"查看"),

        ORG_BIZ_TYPE_1(1,"创建");

        private final int type;

        private final String description;

    }
}


