package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public interface BasketDiscounter {
	void addDiscount(DiscountStrategy discount);
	BigDecimal calculate(List<Item> items);
}
