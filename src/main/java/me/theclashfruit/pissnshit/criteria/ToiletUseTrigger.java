package me.theclashfruit.pissnshit.criteria;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Optional;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class ToiletUseTrigger extends AbstractCriterion<ToiletUseTrigger.Conditions> {
    public static final Identifier ID = new Identifier(MOD_ID, "toilet_used");

    @Override
    protected Conditions conditionsFromJson(JsonObject obj, LootContextPredicate playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new Conditions();
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    public void trigger(PlayerEntity player) {
        trigger((ServerPlayerEntity) player, Conditions::requirementsMet);
    }

    public static class Conditions extends AbstractCriterionConditions {
        public Conditions() {
            super(ID, LootContextPredicate.EMPTY);
        }

        boolean requirementsMet() {
            return true;
        }
    }
}
