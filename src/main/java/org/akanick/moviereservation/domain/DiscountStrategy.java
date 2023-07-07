package org.akanick.moviereservation.domain;

public abstract class DiscountStrategy {

    public Money calculateDiscountFee(Showing showing) {
        for (Rule each : rules) {
            if (each.isSatisfiedBy(showing)) {
                return getDiscountFee(showing);
            }
        }

        return Money.ZERO;
    }

    protected abstract Money getDiscountFee(Showing showing);

}
