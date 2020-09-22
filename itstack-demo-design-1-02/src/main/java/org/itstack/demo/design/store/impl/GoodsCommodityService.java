package org.itstack.demo.design.store.impl;

import com.alibaba.fastjson.JSON;
import org.itstack.demo.design.goods.DeliverReq;
import org.itstack.demo.design.goods.GoodsService;
import org.itstack.demo.design.store.ICommodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GoodsCommodityService implements ICommodity {
    /**
     * 打印日志所需配置
     */
    private Logger logger = LoggerFactory.getLogger(GoodsCommodityService.class);
    /**
     * new处理方法
     */
    private GoodsService goodsService = new GoodsService();

    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        //封装参数
        DeliverReq deliverReq = new DeliverReq();
        deliverReq.setUserName(queryUserName(uId));
        deliverReq.setUserPhone(queryUserPhoneNumber(uId));
        deliverReq.setSku(commodityId);
        deliverReq.setOrderId(bizId);
        deliverReq.setConsigneeUserName(extMap.get("consigneeUserName"));
        deliverReq.setConsigneeUserPhone(extMap.get("consigneeUserPhone"));
        deliverReq.setConsigneeUserAddress(extMap.get("consigneeUserAddress"));
        //调用方法，返回结果哦
        Boolean isSuccess = goodsService.deliverGoods(deliverReq);
        //打印参数
        logger.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSON.toJSON(extMap));
        logger.info("测试结果[优惠券]：{}", isSuccess);
        //判断返回值
        if (!isSuccess) throw new RuntimeException("实物商品发放失败");
    }

    /**
     * 通过Id查询数据库获取name
     * @param uId
     * @return
     */
    private String queryUserName(String uId) {
        return "花花";
    }

    /**
     * 通过id查询数据库获取电话
     * @param uId
     * @return
     */
    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }

}
