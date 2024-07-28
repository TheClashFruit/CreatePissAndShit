package me.theclashfruit.pissnshit.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "pissnshit")
public class MainConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    int configVersion = 1;

    @ConfigEntry.Gui.CollapsibleObject
    ShittingMechanics shittingMechanics = new ShittingMechanics();

    @ConfigEntry.Gui.CollapsibleObject
    PissingMechanics pissingMechanics = new PissingMechanics();

    static class ShittingMechanics {
        boolean isMandatory = true;

        int maxIntervalTicks = 72000;
        int minIntervalTicks = 12000;
    }

    static class PissingMechanics {
        boolean isMandatory = true;

        int maxIntervalTicks = 24000;
        int minIntervalTicks = 3000;
    }
}
