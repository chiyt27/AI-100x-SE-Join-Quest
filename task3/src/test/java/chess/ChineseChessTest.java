package chess;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ChineseChess.feature")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "chess")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty,html:target/cucumber-reports,json:target/cucumber-reports-json/cucumber.json")
public class ChineseChessTest {
}