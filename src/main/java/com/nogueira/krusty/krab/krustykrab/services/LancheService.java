package com.nogueira.krusty.krab.krustykrab.services;

import com.nogueira.krusty.krab.krustykrab.model.Ingrediente;
import com.nogueira.krusty.krab.krustykrab.model.QuantidadeIngrediente;
import com.nogueira.krusty.krab.krustykrab.promotion.IngredienteContext;
import com.nogueira.krusty.krab.krustykrab.repositories.LancheRepository;
import com.nogueira.krusty.krab.krustykrab.rules.RuleLight;
import com.nogueira.krusty.krab.krustykrab.rules.RuleMain;
import com.nogueira.krusty.krab.krustykrab.rules.RuleMuitaCarne;
import com.nogueira.krusty.krab.krustykrab.rules.RuleMuitoQueijo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class LancheService {

    @Autowired
    private LancheRepository lancheRepository;

    private Function<Set<QuantidadeIngrediente>, Map<Ingrediente, Integer>> mapSupplier = (x) -> x.stream()
            .collect(toMap(QuantidadeIngrediente::getIngrediente, QuantidadeIngrediente::getQuantity));

    @PostConstruct
    @Transactional
    public void createDefaultLanches() {

//        QuantidadeIngrediente build = QuantidadeIngrediente.builder().quantity(2).ingrediente(Ingrediente.ALFACE).build();
//
//        Lanche teste = Lanche.builder().ingredientes(ImmutableSet.of(build)).name("teste").price(1.4).build();
//
//        Lanche save = lancheRepository.save(teste)
    }


    // TODO: verificar se vou ter problemas utilizando uma entity salva... apesar que acho que não...
    public Double calculatePrice(Double totalPrice, Set<QuantidadeIngrediente> ingredientes) {

        Map<Ingrediente, Integer> contextMap = mapSupplier.apply(ingredientes);

        IngredienteContext context = IngredienteContext.builder()
                .context(contextMap)
                .build();

        List<RuleMain> rules = Stream.of(new RuleLight(), new RuleMuitoQueijo(), new RuleMuitaCarne())
                .collect(toList());

        Double totalDiscount = rules.stream()
                .map(rule -> rule.getDiscount(context, totalPrice))
                .mapToDouble(Double::doubleValue)
                .sum();

        if (totalDiscount > totalPrice) {
            throw new RuntimeException("Well this should not happen. Sorry.");
        }

        return totalPrice - totalDiscount;
    }
}
