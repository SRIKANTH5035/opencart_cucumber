package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                //features= {".//features/LoginDDT.feature"},
                features = {"./features/login.feature","./features/AccountRegistration.feature" },
                //features = {"./features/AccountRegistration.feature"},
                glue="stepDefinations",
                dryRun = false
        )

public class testrun {
}