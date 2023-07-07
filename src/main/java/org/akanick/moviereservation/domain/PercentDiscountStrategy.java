package org.akanick.moviereservation.domain;

public class PercentDiscountStrategy extends DiscountStrategy {

    @Override
    protected Money getDiscountFee(Showing showing) {
        return showing.getFixedFee().times(percent);
    }
}
