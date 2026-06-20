package com.example.tntwarner;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("tntwarner")
public class TNTWarner {
    public TNTWarner() {
        // Регистрируем наш отрисовщик в шине событий Forge
        MinecraftForge.EVENT_BUS.register(new HUDRenderer());
    }
}
