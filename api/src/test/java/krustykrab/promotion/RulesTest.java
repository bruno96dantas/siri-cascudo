package krustykrab.promotion;


import krustykrab.rules.Rule;
import krustykrab.rules.RuleLight;
import krustykrab.rules.RuleMuitaCarne;
import krustykrab.rules.RuleMuitoQueijo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@RunWith(MockitoJUnitRunner.class)
public class RulesTest {

    private List<Rule> rules;

    @Before
    public void setUp() {
        rules = Stream.of(new RuleLight(), new RuleMuitoQueijo(), new RuleMuitaCarne())
                .collect(toList());

    }

    @Test
    public void ruleLight() {

    }

    @Test
    public void ruleMuitaCarne() {

    }

    @Test
    public void ruleMuitoQueijo() {

    }


}
