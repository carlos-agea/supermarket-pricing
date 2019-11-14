package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DiscountEngine {
	private List<DiscountStrategy> discounts;
	
	public DiscountEngine() {
		discounts = new ArrayList<DiscountStrategy>();
	}
	
	public void addDiscount(DiscountStrategy discount) {
		discounts.add(discount);
	}
	
	public BigDecimal calculate(List<Item> items) {
		return discounts.stream()
			.map(s -> s.getDiscount(items))
			.reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO)
            .setScale(2, RoundingMode.HALF_UP);
	}
}
