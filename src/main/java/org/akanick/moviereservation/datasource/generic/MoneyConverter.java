package org.akanick.moviereservation.datasource.generic;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.akanick.moviereservation.domain.Money;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {

    @Override
    public Long convertToDatabaseColumn(Money money) {
        return money.getAmount().longValue();
    }

    @Override
    public Money convertToEntityAttribute(Long amount) {
        return Money.wons(amount);
    }
}
