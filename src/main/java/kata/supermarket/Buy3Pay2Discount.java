package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public class Buy3Pay2Discount implements DiscountStrategy {

	private final SaleableSKU saleableSKU;
	
	public Buy3Pay2Discount(SaleableSKU saleableSKU) {
		this.saleableSKU = saleableSKU;
	}
	
	@Override
	public BigDecimal getDiscount(List<Item> items) {
		long n = items.stream().filter(x -> x.getSKU().equals(saleableSKU.getSKU())).count();
		return saleableSKU.unityPrice().multiply(new BigDecimal(n / 3));
	}

}
