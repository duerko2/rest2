package com.example.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Before;
import org.junit.runner.RunWith;

/* Important:
for Cucumber tests to be recognized by Maven, the class name has to have
either the word Test in the beginning or at the end.
For example, the class name CucumberTests (Test with an s) will be ignored by
Maven.
*/

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "summary",
        publish = false,
        features = "features"// directory of the feature files
)
public class CucumberTest {

}
