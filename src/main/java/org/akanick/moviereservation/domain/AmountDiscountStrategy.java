package org.akanick.moviereservation.domain;

public class AmountDiscountStrategy extends DiscountStrategy{

    @Override
    protected Money getDiscountFee(Showing showing) {
        return discountAmount;
    }
}
