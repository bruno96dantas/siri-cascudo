package com.nogueira.krusty.krab.krustykrab.services;

import com.nogueira.krusty.krab.krustykrab.model.Cardapio;
import com.nogueira.krusty.krab.krustykrab.model.Lanche;
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


}
