package coupon.couponlimitation;

import orderitem.OrderItem;

/**
 * 这是折扣的抽象类
 *        折扣通常由折扣规则和折扣价格组成，因此按照一般的设计，折扣类应该包含这两个部分，
 *    但是折扣的类型有很多，折扣的规则也有很多，为了增强折扣系统的灵活性，达到高内聚
 *    低耦合的效果。ocp原则，这里就采用了虚化的方法，将折扣一分为二，分别是折扣方式
 *    和折扣条件
 *        然后折扣方式和折扣条件都是一个父类，具体的折扣都是由其子类完成的
 */
public abstract class BaseCouponLimitation {

    /**
     * 商品是否满足折扣规则
     * @param orderItem
     * @return
     */
   public abstract boolean pass(OrderItem orderItem);

    public abstract void calcAndSedDiscount(OrderItem orderItem);

    public BaseCouponLimitation getInstance(String jsonString){

        return null;
    }
}
