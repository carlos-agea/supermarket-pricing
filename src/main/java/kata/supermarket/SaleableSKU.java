package kata.supermarket;

import java.math.BigDecimal;

public interface SaleableSKU extends SKU {
	BigDecimal unityPrice();
}
