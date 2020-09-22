package org.itstack.demo.design.store;

import java.util.Map;

public interface ICommodity {
    /**
     * 抽象方法，封装所有所需参数
     * @param uId
     * @param commodityId
     * @param bizId
     * @param extMap
     * @throws Exception
     */
    void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception;

}
