package net.timeboxing.webapp.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.vaadin.guice.annotation.UIScope;
import net.timeboxing.adapter.guice.AdapterModule;
import net.timeboxing.adapter.guice.AspectJAdaptModule;
import net.timeboxing.settings.guice.PropertiesSettingsModule;
import net.timeboxing.vaadin.guice.VaadinComponentEventModule;
import net.timeboxing.vaadin.guice.VaadinComponentModule;
import net.timeboxing.webapp.GreetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebappModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(WebappModule.class);

    @Override
    protected void configure() {
        super.configure();

        LOG.debug("Initializing");
        install(new AspectJAdaptModule());
        install(new AdapterModule("net.timeboxing"));
        install(new VaadinComponentModule("net.timeboxing"));
        install(new PropertiesSettingsModule(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "application.properties"));
        install(new VaadinComponentEventModule(UIScope.class));
        bind(GreetService.class).in(Scopes.SINGLETON);
    }
}
