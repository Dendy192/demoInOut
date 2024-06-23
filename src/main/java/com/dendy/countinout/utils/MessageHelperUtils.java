package com.dendy.countinout.utils;

import com.dendy.countinout.vo.MessageVo;

public class MessageHelperUtils {

    public static MessageVo mustLoginFirst(){
        MessageVo vo = new MessageVo();
        vo.setContent(LabelUtils.loginContenMustLogin);
        vo.setType(LabelUtils.typeError);
        vo.setCssClass(LabelUtils.cssError);
        return vo;
    }

    public static MessageVo successUpdateData(){
        MessageVo vo = new MessageVo();
        vo.setContent(LabelUtils.successUpdateData);
        vo.setType(LabelUtils.typeSuccess);
        vo.setCssClass(LabelUtils.cssSuccess);
        return vo;
    }
}
