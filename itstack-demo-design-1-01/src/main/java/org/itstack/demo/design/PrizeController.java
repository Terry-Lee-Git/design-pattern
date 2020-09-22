package org.itstack.demo.design;

import com.alibaba.fastjson.JSON;
import org.itstack.demo.design.card.IQiYiCardService;
import org.itstack.demo.design.coupon.CouponResult;
import org.itstack.demo.design.coupon.CouponService;
import org.itstack.demo.design.goods.DeliverReq;
import org.itstack.demo.design.goods.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模拟发奖服务
 */
public class PrizeController {
    //打印日志
    private Logger logger = LoggerFactory.getLogger(PrizeController.class);

    public AwardRes awardToUser(AwardReq req) {
        //将json转化为string
        String reqJson = JSON.toJSONString(req);
        AwardRes awardRes = null;
        try {
            logger.info("奖品发放开始{}。req:{}", req.getuId(), reqJson);
            // 按照不同类型方法商品[1优惠券、2实物商品、3第三方兑换卡(爱奇艺)]
            //第一种情况 优惠券
            if (req.getAwardType() == 1) {
                //new 优惠券发送的service对象
                CouponService couponService = new CouponService();
                //使用service里对发送信息的方法(用户id，优惠券号，随机id)
                CouponResult couponResult = couponService.sendCoupon(req.getuId(), req.getAwardNumber(), req.getBizId());
                //判断返回结果 info：描述
                if ("0000".equals(couponResult.getCode())) {
                    awardRes = new AwardRes("0000", "发放成功");
                } else {
                    awardRes = new AwardRes("0001", couponResult.getInfo());
                }
            //第二种情况 实体商品
            } else if (req.getAwardType() == 2) {
                //new 实物goods的service对象，内含发送方法
                GoodsService goodsService = new GoodsService();
                //new 实物的请求对象
                DeliverReq deliverReq = new DeliverReq();
                //为对象赋值，不能直接获取的通过id从数据库获取
                deliverReq.setUserName(queryUserName(req.getuId()));
                deliverReq.setUserPhone(queryUserPhoneNumber(req.getuId()));
                deliverReq.setSku(req.getAwardNumber());
                deliverReq.setOrderId(req.getBizId());
                //收货人姓名 从map中使用key获取值
                deliverReq.setConsigneeUserName(req.getExtMap().get("consigneeUserName"));
                //收货人电话
                deliverReq.setConsigneeUserPhone(req.getExtMap().get("consigneeUserPhone"));
                //收货人地址
                deliverReq.setConsigneeUserAddress(req.getExtMap().get("consigneeUserAddress"));
                //使用实物service的发送方法，参数是封装的对象
                Boolean isSuccess = goodsService.deliverGoods(deliverReq);
                //判断返回结果
                if (isSuccess) {
                    awardRes = new AwardRes("0000", "发放成功");
                } else {
                    awardRes = new AwardRes("0001", "发放失败");
                }
            //第三种情况 第三方兑换卡
            } else if (req.getAwardType() == 3) {
                //使用id 查找数据库拿到电话号
                String bindMobileNumber = queryUserPhoneNumber(req.getuId());
                //new兑换卡的处理方案
                IQiYiCardService iQiYiCardService = new IQiYiCardService();
                //使用方法，传入手机号和卡号
                iQiYiCardService.grantToken(bindMobileNumber, req.getAwardNumber());
                //没有返回结果，能往下走就是成功，编写返回信息
                awardRes = new AwardRes("0000", "发放成功");
            }
            //自定义日志参数
            logger.info("奖品发放完成{}。", req.getuId());
        } catch (Exception e) {
            //如果出现异常，则会走这里
            logger.error("奖品发放失败{}。req:{}", req.getuId(), reqJson, e);
            //并将封装的返回结果进行返回
            awardRes = new AwardRes("0001", e.getMessage());
        }

        return awardRes;
    }

    /**
     * 查询方法，可能通过id从数据库中获取对应的name
     * @param uId
     * @return
     */
    private String queryUserName(String uId) {
        return "花花";
    }

    /**
     * 查询方法，可能通过id从数据库中获取对应的手机号
     * @param uId
     * @return
     */
    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }

}
