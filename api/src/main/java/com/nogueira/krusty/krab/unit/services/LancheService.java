package com.nogueira.krusty.krab.unit.services;

import com.nogueira.krusty.krab.model.Cardapio;
import com.nogueira.krusty.krab.model.Ingrediente;
import com.nogueira.krusty.krab.model.Lanche;
import com.nogueira.krusty.krab.model.utils.IngredienteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.nogueira.krusty.krab.model.Ingrediente.*;
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

    public List<Ingrediente> getIngredientes(){
        return asList(ALFACE, HAMBURGER, QUEIJO, BACON, OVO);
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
