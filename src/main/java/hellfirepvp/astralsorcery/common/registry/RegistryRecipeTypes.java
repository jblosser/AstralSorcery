/*******************************************************************************
 * HellFirePvP / Astral Sorcery 2019
 *
 * All rights reserved.
 * The source code is available on github: https://github.com/HellFirePvP/AstralSorcery
 * For further details, see the License file there.
 ******************************************************************************/

package hellfirepvp.astralsorcery.common.registry;

import hellfirepvp.astralsorcery.AstralSorcery;
import hellfirepvp.astralsorcery.common.crafting.helper.IHandlerRecipe;
import hellfirepvp.astralsorcery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.astralsorcery.common.crafting.helper.ResolvingRecipeType;
import hellfirepvp.astralsorcery.common.crafting.recipe.LiquidInfusion;
import hellfirepvp.astralsorcery.common.crafting.recipe.SimpleAltarRecipe;
import hellfirepvp.astralsorcery.common.crafting.recipe.WellLiquefaction;
import hellfirepvp.astralsorcery.common.crafting.recipe.altar.effect.*;
import hellfirepvp.astralsorcery.common.util.NameUtil;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.IItemHandler;

import static hellfirepvp.astralsorcery.common.lib.AltarRecipeEffectsAS.*;
import static hellfirepvp.astralsorcery.common.lib.RecipeTypesAS.*;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: RegistryRecipeTypes
 * Created by HellFirePvP
 * Date: 13.08.2019 / 19:10
 */
public class RegistryRecipeTypes {

    private RegistryRecipeTypes() {}

    public static void init() {
        TYPE_WELL = new ResolvingRecipeType<>("well", WellLiquefaction.class, (recipe, context) -> recipe.matches(context.getInput()));
        TYPE_INFUSION = new ResolvingRecipeType<>("infusion", LiquidInfusion.class, (recipe, context) -> true);
        TYPE_ALTAR = new ResolvingRecipeType<>("simple_altar", SimpleAltarRecipe.class, (recipe, context) -> recipe.matches(context.getAltar(), context.ignoreStarlightRequirement()));
    }

    public static void initAltarEffects() {
        BUILTIN_ATTUNEMENT_SPARKLE = registerEffect(new BuiltInEffectAttunementSparkle());
        BUILTIN_CONSTELLATION_LINES = registerEffect(new BuiltInEffectConstellationLines());
        BUILTIN_DISCOVERY_CENTRAL_BEAM = registerEffect(new BuiltInEffectDiscoveryCentralBeam());
        BUILTIN_TRAIT_FOCUS_CIRCLE = registerEffect(new BuiltInEffectTraitFocusCircle());
        BUILTIN_TRAIT_RELAY_HIGHLIGHT = registerEffect(new BuiltInEffectTraitRelayHighlight());

        ALTAR_DEFAULT_LIGHTBEAMS = registerEffect(new EffectAltarDefaultLightbeams());
        ALTAR_DEFAULT_SPARKLE = registerEffect(new EffectAltarDefaultSparkle());
        ALTAR_FOCUS_SPARKLE = registerEffect(new EffectAltarFocusSparkle());
        ALTAR_RANDOM_SPARKLE = registerEffect(new EffectAltarRandomSparkle());
        FOCUS_DUST_SWIRL = registerEffect(new EffectFocusDustSwirl());
        FOCUS_EDGE = registerEffect(new EffectFocusEdge());
        GATEWAY_EDGE = registerEffect(new EffectGatewayEdge());
        LARGE_DUST_SWIRL = registerEffect(new EffectLargeDustSwirl());
        LIQUID_BURST = registerEffect(new EffectLiquidBurst());
        LUMINESCENCE_BURST = registerEffect(new EffectLuminescenceBurst());
        LUMINESCENCE_FLARE = registerEffect(new EffectLuminescenceFlare());
        PILLAR_LIGHTBEAMS = registerEffect(new EffectPillarLightbeams());
        PILLAR_SPARKLE = registerEffect(new EffectPillarSparkle());
        UPGRADE_ALTAR = registerEffect(new EffectUpgradeAltar());
        VORTEX_PLANE = registerEffect(new EffectVortexPlane());
    }

    private static <T extends AltarRecipeEffect> T registerEffect(T recipeEffect) {
        recipeEffect.setRegistryName(NameUtil.fromClass(recipeEffect, "Effect"));
        AstralSorcery.getProxy().getRegistryPrimer().register(recipeEffect);
        return recipeEffect;
    }

    private static <C extends IItemHandler, T extends IHandlerRecipe<C>, R extends RecipeCraftingContext<T, C>, S extends ResolvingRecipeType<C, T, R>> S register(S recipeType) {
        Registry.register(Registry.RECIPE_TYPE, recipeType.getRegistryName(), recipeType.getType());
        return recipeType;
    }

}