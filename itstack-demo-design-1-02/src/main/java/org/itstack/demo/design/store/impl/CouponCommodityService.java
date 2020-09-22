package org.itstack.demo.design.store.impl;

import com.alibaba.fastjson.JSON;
import org.itstack.demo.design.coupon.CouponResult;
import org.itstack.demo.design.coupon.CouponService;
import org.itstack.demo.design.store.ICommodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CouponCommodityService implements ICommodity {
    /**
     * 日志记录
     */
    private Logger logger = LoggerFactory.getLogger(CouponCommodityService.class);
    //new处理对象
    private CouponService couponService = new CouponService();

    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        //使用方法，传入参数
        CouponResult couponResult = couponService.sendCoupon(uId, commodityId, bizId);
        //答应日志 请求参数和测试结果
        logger.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        logger.info("测试结果[优惠券]：{}", JSON.toJSON(couponResult));
        //判断返回值 是否成功发送
        if (!"0000".equals(couponResult.getCode())) throw new RuntimeException(couponResult.getInfo());
    }

}
