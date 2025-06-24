package com.example.temptconfig;

import net.fabricmc.api.ClientModInitializer;

public class TemptConfigClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Tempt Config Client initialized");
        // Cloth Config UI would be registered here in a full version
    }
}