package com.nogueira.krusty.krab.krustykrab.services;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.model.Lanche;
import com.nogueira.krusty.krab.krustykrab.promotion.IngredienteContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class LancheService {

    @Autowired
    private RulesService rulesService;


    public BigDecimal calculatePrice(Lanche lanche) {

        Map<Ingrediente, Integer> contextMap = lanche.getIngredienteQuantity();

        IngredienteContext context = IngredienteContext.builder()
                .context(contextMap)
                .build();

        BigDecimal lanchePrice = lanche.getTotalPrice();

        double totalDiscount = rulesService.getDiscount(lanchePrice, context);

        if (totalDiscount > lanchePrice.doubleValue()) {
            throw new RuntimeException("Well this should not happen. Sorry.");
        }

        return lanchePrice.subtract(BigDecimal.valueOf(totalDiscount));
    }


}
