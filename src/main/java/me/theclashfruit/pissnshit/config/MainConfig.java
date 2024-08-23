package me.theclashfruit.pissnshit.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "pissnshit")
public class MainConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    public int configVersion = 1;

    @ConfigEntry.Gui.Excluded
    public boolean hasShownDisclaimer = false;

    @ConfigEntry.Gui.CollapsibleObject
    public ShittingMechanics shittingMechanics = new ShittingMechanics();

    @ConfigEntry.Gui.CollapsibleObject
    public PissingMechanics pissingMechanics = new PissingMechanics();

    public static class ShittingMechanics {
        public boolean isMandatory = true;

        public int maxIntervalTicks = 72000;
        public int minIntervalTicks = 12000;
    }

    public static class PissingMechanics {
        public boolean isMandatory = true;

        public int maxIntervalTicks = 24000;
        public int minIntervalTicks = 3000;
    }
}
