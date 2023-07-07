package org.akanick.moviereservation.domain;

public class NonDiscountStrategy extends DiscountStrategy{

    @Override
    protected Money getDiscountFee(Showing showing) {
        return Money.ZERO;
    }
}
