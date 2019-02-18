package krustykrab.services;

import krustykrab.model.Cardapio;
import krustykrab.model.Ingrediente;
import krustykrab.model.Lanche;
import krustykrab.utils.IngredienteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class LancheService {

    @Autowired
    private DiscountService discountService;


    public BigDecimal calculatePrice(Lanche lanche) {

        BigDecimal lanchePrice = lanche.getTotalPrice();

        BigDecimal totalDiscount = discountService.getDiscount(lanche);

        // -1 means that that totalDiscount is greater then lanchePrice
        if (lanchePrice.compareTo(totalDiscount) < 0) {
            throw new RuntimeException("We got more discount than the price");
        }

        return lanchePrice.subtract(totalDiscount);
    }

    public List<Lanche> getLanchesInCardapio() {
        return asList(Cardapio.XBACON.getLanche(),
                Cardapio.XBURGER.getLanche(),
                Cardapio.XEGG.getLanche(),
                Cardapio.XEGGBACON.getLanche());
    }

    public BigDecimal calculatePrice(List<Ingrediente> ingredientes) {

        BigDecimal totalPrice = IngredienteUtils.getTotalPrice(ingredientes);

        BigDecimal discount = discountService.getDiscount(ingredientes);

        // -1 means that that totalDiscount is greater then lanchePrice
        if (totalPrice.compareTo(discount) < 0) {
            throw new RuntimeException("We got more discount than the price");
        }

        return totalPrice.subtract(discount);
    }


}
