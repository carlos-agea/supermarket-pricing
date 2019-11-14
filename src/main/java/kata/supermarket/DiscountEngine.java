package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DiscountEngine implements BasketDiscounter {
	private List<DiscountStrategy> discounts;
	
	public DiscountEngine() {
		discounts = new ArrayList<DiscountStrategy>();
	}
	
	/**
	 * Add discount strategies to be applied.
	 * @param discount is the strategy to be applied.
	 */
	@Override
	public void addDiscount(DiscountStrategy discount) {
		discounts.add(discount);
	}
	
	/**
	 * Calculate a discount applicable to list of items.
	 * @param items belonging to the basket from where
	 * the discount is calculated
	 * 
	 * @return discount with 2 decimals
	 */
	@Override
	public BigDecimal calculate(List<Item> items) {
		return discounts.stream()
			.map(s -> s.getDiscount(items))
			.reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO)
            .setScale(2, RoundingMode.HALF_UP);
	}
}
