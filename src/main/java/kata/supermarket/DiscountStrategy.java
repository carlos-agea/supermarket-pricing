package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountStrategy {
	BigDecimal getDiscount(List<Item> items);
}
