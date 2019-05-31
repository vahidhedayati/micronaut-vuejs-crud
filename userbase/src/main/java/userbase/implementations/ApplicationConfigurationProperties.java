package userbase.implementations;

import io.micronaut.context.annotation.ConfigurationProperties;


@ConfigurationProperties("application")
public class ApplicationConfigurationProperties implements MyApplicationConfiguration{

    protected final Integer DEFAULT_MAX = 10;

    private Integer max = DEFAULT_MAX;

    @Override
    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        if(max != null) {
            this.max = max;
        }
    }
}
