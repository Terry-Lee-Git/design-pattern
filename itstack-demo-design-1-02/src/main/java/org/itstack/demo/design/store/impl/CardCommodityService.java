package org.itstack.demo.design.store.impl;

import com.alibaba.fastjson.JSON;
import org.itstack.demo.design.card.IQiYiCardService;
import org.itstack.demo.design.store.ICommodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CardCommodityService implements ICommodity {
    /**
     * 打印日志所需配置
     */
    private Logger logger = LoggerFactory.getLogger(CardCommodityService.class);

    /**
     * 模拟注入
     * 私有 new使用方法
     */
    private IQiYiCardService iQiYiCardService = new IQiYiCardService();

    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        //设置参数
        String mobile = queryUserMobile(uId);
        //调用方法
        iQiYiCardService.grantToken(mobile, bizId);
        //打印日志 请求参数，测试结果
        logger.info("请求参数[爱奇艺兑换卡] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        logger.info("测试结果[爱奇艺兑换卡]：success");
    }

    /**
     * 通过id查询数据库获取手机号
     * @param uId
     * @return
     */
    private String queryUserMobile(String uId) {
        return "15200101232";
    }

}
