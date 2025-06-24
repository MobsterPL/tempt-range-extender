package com.example.temptconfig;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.*;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityEvents;

import java.util.List;

public class TemptConfigMod implements ModInitializer {
    public static float TEMPT_RANGE = 64.0f;
    public static double TEMPT_SPEED = 2.5;

    @Override
    public void onInitialize() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof AnimalEntity animal) {
                if (animal instanceof CowEntity || animal instanceof PigEntity || animal instanceof SheepEntity
                        || animal instanceof ChickenEntity || animal instanceof GoatEntity
                        || animal instanceof HorseEntity || animal instanceof DonkeyEntity || animal instanceof MuleEntity) {
                    removeTemptGoals(animal);
                    animal.goalSelector.add(3, new TemptGoal(animal, TEMPT_SPEED,
                            Ingredient.ofItems(Items.WHEAT, Items.CARROT, Items.HAY_BLOCK), false));
                }
            }
        });
        System.out.println("Tempt Range Extender initialized.");
    }

    private void removeTemptGoals(AnimalEntity animal) {
        List<net.minecraft.entity.ai.goal.Goal> goalsToRemove = animal.goalSelector.getGoals().stream()
            .filter(goal -> goal.getGoal() instanceof TemptGoal)
            .map(net.minecraft.entity.ai.goal.GoalSelector.Entry::getGoal)
            .toList();
        goalsToRemove.forEach(animal.goalSelector::remove);
    }
}