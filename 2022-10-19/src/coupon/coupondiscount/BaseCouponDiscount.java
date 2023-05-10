package coupon.coupondiscount;

import coupon.couponlimitation.BaseCouponLimitation;
import orderitem.OrderItem;

public abstract class BaseCouponDiscount{

    private BaseCouponLimitation couponLimitation;

    /**
     * @param orderItem
     */
    public void compute(OrderItem orderItem) {

    }

    /**
     * 计算并且设置具体的折扣类型
     * @param orderItem
     */
    public abstract void calcAndSedDiscount(OrderItem orderItem);

    public BaseCouponDiscount getInstance(String jsonString){

        return null;
    }
}
