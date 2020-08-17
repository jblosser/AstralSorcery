package hellfirepvp.astralsorcery.common.registry;

import hellfirepvp.astralsorcery.AstralSorcery;
import hellfirepvp.astralsorcery.common.data.research.PlayerProgress;
import hellfirepvp.astralsorcery.common.lib.PerkAttributeTypesAS;
import hellfirepvp.astralsorcery.common.perk.modifier.PerkAttributeModifier;
import hellfirepvp.astralsorcery.common.perk.type.ModifierType;
import net.minecraft.entity.player.PlayerEntity;

import static hellfirepvp.astralsorcery.common.lib.PerkCustomModifiersAS.*;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: RegistryPerkCustomModifiers
 * Created by HellFirePvP
 * Date: 14.08.2020 / 17:11
 */
public class RegistryPerkCustomModifiers {

    private RegistryPerkCustomModifiers() {}

    public static void init() {
        FOCUS_GELU = register(new PerkAttributeModifier(AstralSorcery.key("focus_gelu"),
                PerkAttributeTypesAS.ATTR_TYPE_INC_PERK_EFFECT, ModifierType.ADDED_MULTIPLY, 0.03F) {
            @Override
            protected void initModifier() {
                super.initModifier();

                this.setAbsolute();
            }

            @Override
            public float getValue(PlayerEntity player, PlayerProgress progress) {
                return getRawValue() * (progress.getAppliedPerks().size() - progress.getSealedPerks().size());
            }

            @Override
            public boolean hasDisplayString() {
                return false;
            }
        });
        FOCUS_ULTERIA = register(new PerkAttributeModifier(AstralSorcery.key("focus_ulteria"),
                PerkAttributeTypesAS.ATTR_TYPE_INC_PERK_EXP, ModifierType.STACKING_MULTIPLY, 1F + 0.05F) {
            @Override
            protected void initModifier() {
                super.initModifier();

                this.setAbsolute();
            }

            @Override
            public float getValue(PlayerEntity player, PlayerProgress progress) {
                return 1F + (0.05F * progress.getAvailablePerkPoints(player));
            }

            @Override
            public boolean hasDisplayString() {
                return false;
            }
        });
        FOCUS_VORUX = register(new PerkAttributeModifier(AstralSorcery.key("focus_vorux"),
                PerkAttributeTypesAS.ATTR_TYPE_INC_PERK_EFFECT, ModifierType.ADDED_MULTIPLY, 0.01F) {
            @Override
            protected void initModifier() {
                super.initModifier();

                this.setAbsolute();
            }

            @Override
            public float getValue(PlayerEntity player, PlayerProgress progress) {
                return getRawValue() * (progress.getAppliedPerks().size() - progress.getSealedPerks().size());
            }

            @Override
            public boolean hasDisplayString() {
                return false;
            }
        });
    }

    private static <T extends PerkAttributeModifier> T register(T modifier) {
        AstralSorcery.getProxy().getRegistryPrimer().register(modifier);
        return modifier;
    }
}