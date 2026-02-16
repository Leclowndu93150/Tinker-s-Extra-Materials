package com.leclowndu93150.tinkers_extra_materials.data.material;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.registry.TEBlocks;
import com.leclowndu93150.tinkers_extra_materials.registry.TEFluids;
import com.leclowndu93150.tinkers_extra_materials.registry.TEItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.recipe.data.ItemNameIngredient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import slimeknights.mantle.recipe.data.IRecipeHelper;
import slimeknights.mantle.recipe.helper.FluidOutput;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.mantle.recipe.ingredient.FluidIngredient;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.material.MaterialFluidRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MaterialMeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.function.Consumer;

public class TEMaterialRecipeProvider extends RecipeProvider implements IConditionBuilder, IRecipeHelper, IMaterialRecipeHelper {

    public TEMaterialRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getModId() {
        return TinkersExtraMaterials.MODID;
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        addCraftingRecipes(consumer);
        addCastingCraftRecipes(consumer);
        addMaterialRecipes(consumer);
        addSmelteryRecipes(consumer);
        addFluidRecipes(consumer);
        addAlloyRecipes(consumer);
        addEvoliteRecipes(consumer);
    }

    private void addCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TEBlocks.UNOBTAINIUM_BLOCK.get())
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .define('#', TEItems.UNOBTAINIUM_INGOT.get())
            .unlockedBy("has_ingot", has(TEItems.UNOBTAINIUM_INGOT.get()))
            .save(consumer, new ResourceLocation(TinkersExtraMaterials.MODID, "unobtainium_block_from_ingots"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TEItems.UNOBTAINIUM_INGOT.get(), 9)
            .requires(TEBlocks.UNOBTAINIUM_BLOCK_ITEM.get())
            .unlockedBy("has_block", has(TEBlocks.UNOBTAINIUM_BLOCK_ITEM.get()))
            .save(consumer, new ResourceLocation(TinkersExtraMaterials.MODID, "unobtainium_ingot_from_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TEItems.UNOBTAINIUM_INGOT.get())
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .define('#', TEItems.UNOBTAINIUM_NUGGET.get())
            .unlockedBy("has_nugget", has(TEItems.UNOBTAINIUM_NUGGET.get()))
            .save(consumer, new ResourceLocation(TinkersExtraMaterials.MODID, "unobtainium_ingot_from_nuggets"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TEItems.UNOBTAINIUM_NUGGET.get(), 9)
            .requires(TEItems.UNOBTAINIUM_INGOT.get())
            .unlockedBy("has_ingot", has(TEItems.UNOBTAINIUM_INGOT.get()))
            .save(consumer, new ResourceLocation(TinkersExtraMaterials.MODID, "unobtainium_nugget_from_ingot"));

        metalCraftingRecipes(consumer, "mythican", TEItems.MYTHICAN_INGOT, TEItems.MYTHICAN_NUGGET, TEBlocks.MYTHICAN_BLOCK_ITEM);
        metalCraftingRecipes(consumer, "star_platinum", TEItems.STAR_PLATINUM_INGOT, TEItems.STAR_PLATINUM_NUGGET, TEBlocks.STAR_PLATINUM_BLOCK_ITEM);
        metalCraftingRecipes(consumer, "bell_metal", TEItems.BELL_METAL_INGOT, TEItems.BELL_METAL_NUGGET, TEBlocks.BELL_METAL_BLOCK_ITEM);
    }

    private void metalCraftingRecipes(Consumer<FinishedRecipe> consumer, String name,
                                       RegistryObject<Item> ingot, RegistryObject<Item> nugget, RegistryObject<Item> blockItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockItem.get())
            .pattern("###").pattern("###").pattern("###")
            .define('#', ingot.get())
            .unlockedBy("has_ingot", has(ingot.get()))
            .save(consumer, new ResourceLocation(TinkersExtraMaterials.MODID, name + "_block_from_ingots"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot.get(), 9)
            .requires(blockItem.get())
            .unlockedBy("has_block", has(blockItem.get()))
            .save(consumer, new ResourceLocation(TinkersExtraMaterials.MODID, name + "_ingot_from_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ingot.get())
            .pattern("###").pattern("###").pattern("###")
            .define('#', nugget.get())
            .unlockedBy("has_nugget", has(nugget.get()))
            .save(consumer, new ResourceLocation(TinkersExtraMaterials.MODID, name + "_ingot_from_nuggets"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, nugget.get(), 9)
            .requires(ingot.get())
            .unlockedBy("has_ingot", has(ingot.get()))
            .save(consumer, new ResourceLocation(TinkersExtraMaterials.MODID, name + "_nugget_from_ingot"));
    }

    private void addCastingCraftRecipes(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.tableRecipe(TEItems.RUNICAN.get())
            .setFluid(tcFluidTag("molten_diamond"), FluidValues.GEM)
            .setCoolingTime(800, FluidValues.GEM)
            .setCast(Ingredient.of(Items.CLAY_BALL), true)
            .save(consumer, location("smeltery/casting/runican"));

        ItemCastingRecipeBuilder.tableRecipe(TEItems.PILLAGUM.get())
            .setFluid(tcFluidTag("molten_emerald"), FluidValues.GEM * 2)
            .setCoolingTime(800, FluidValues.GEM * 2)
            .setCast(Ingredient.of(Items.LAPIS_LAZULI), true)
            .save(consumer, location("smeltery/casting/pillagum"));

        ItemCastingRecipeBuilder.tableRecipe(TEItems.MATERIAL_000.get())
            .setFluid(tcFluidTag("molten_debris"), FluidValues.INGOT)
            .setCoolingTime(800, FluidValues.INGOT)
            .setCast(Ingredient.of(Items.ENDER_EYE), true)
            .save(consumer, location("smeltery/casting/material_000"));
    }

    private void addMaterialRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";
        materialRecipe(consumer, TEMaterialIds.UNOBTAINIUM, Ingredient.of(TEItems.UNOBTAINIUM_INGOT.get()), 1, 1, folder + "unobtainium/ingot");
        materialRecipe(consumer, TEMaterialIds.UNOBTAINIUM, Ingredient.of(TEItems.UNOBTAINIUM_NUGGET.get()), 1, 9, folder + "unobtainium/nugget");
        materialRecipe(consumer, TEMaterialIds.UNOBTAINIUM, Ingredient.of(TEBlocks.UNOBTAINIUM_BLOCK_ITEM.get()), 9, 1, folder + "unobtainium/block");

        materialRecipe(consumer, TEMaterialIds.MYTHICAN, Ingredient.of(TEItems.MYTHICAN_INGOT.get()), 1, 1, folder + "mythican/ingot");
        materialRecipe(consumer, TEMaterialIds.MYTHICAN, Ingredient.of(TEItems.MYTHICAN_NUGGET.get()), 1, 9, folder + "mythican/nugget");
        materialRecipe(consumer, TEMaterialIds.MYTHICAN, Ingredient.of(TEBlocks.MYTHICAN_BLOCK_ITEM.get()), 9, 1, folder + "mythican/block");

        materialRecipe(consumer, TEMaterialIds.STAR_PLATINUM, Ingredient.of(TEItems.STAR_PLATINUM_INGOT.get()), 1, 1, folder + "star_platinum/ingot");
        materialRecipe(consumer, TEMaterialIds.STAR_PLATINUM, Ingredient.of(TEItems.STAR_PLATINUM_NUGGET.get()), 1, 9, folder + "star_platinum/nugget");
        materialRecipe(consumer, TEMaterialIds.STAR_PLATINUM, Ingredient.of(TEBlocks.STAR_PLATINUM_BLOCK_ITEM.get()), 9, 1, folder + "star_platinum/block");

        materialRecipe(consumer, TEMaterialIds.BELL_METAL, Ingredient.of(TEItems.BELL_METAL_INGOT.get()), 1, 1, folder + "bell_metal/ingot");
        materialRecipe(consumer, TEMaterialIds.BELL_METAL, Ingredient.of(TEItems.BELL_METAL_NUGGET.get()), 1, 9, folder + "bell_metal/nugget");
        materialRecipe(consumer, TEMaterialIds.BELL_METAL, Ingredient.of(TEBlocks.BELL_METAL_BLOCK_ITEM.get()), 9, 1, folder + "bell_metal/block");

        materialRecipe(consumer, TEMaterialIds.RUNICAN, Ingredient.of(TEItems.RUNICAN.get()), 1, 1, folder + "runican");
        materialRecipe(consumer, TEMaterialIds.PILLAGUM, Ingredient.of(TEItems.PILLAGUM.get()), 1, 1, folder + "pillagum");
        materialRecipe(consumer, TEMaterialIds.MATERIAL_000, Ingredient.of(TEItems.MATERIAL_000.get()), 1, 1, folder + "material_000");
    }

    private void addSmelteryRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/parts/";
        TagKey<Fluid> moltenUnobtainium = forgeFluidTag("molten_unobtainium");

        MaterialFluidRecipeBuilder.material(TEMaterialIds.UNOBTAINIUM)
            .setFluid(moltenUnobtainium, FluidValues.INGOT)
            .setTemperature(1300)
            .save(consumer, location(folder + "casting/unobtainium"));
        MaterialMeltingRecipeBuilder.material(TEMaterialIds.UNOBTAINIUM, 1300,
                FluidOutput.fromTag(moltenUnobtainium, FluidValues.INGOT))
            .save(consumer, location(folder + "melting/unobtainium"));

        TagKey<Fluid> moltenMythican = forgeFluidTag("molten_mythican");
        MaterialFluidRecipeBuilder.material(TEMaterialIds.MYTHICAN)
            .setFluid(moltenMythican, FluidValues.INGOT)
            .setTemperature(1500)
            .save(consumer, location(folder + "casting/mythican"));
        MaterialMeltingRecipeBuilder.material(TEMaterialIds.MYTHICAN, 1500,
                FluidOutput.fromTag(moltenMythican, FluidValues.INGOT))
            .save(consumer, location(folder + "melting/mythican"));

        TagKey<Fluid> moltenStarPlatinum = forgeFluidTag("molten_star_platinum");
        MaterialFluidRecipeBuilder.material(TEMaterialIds.STAR_PLATINUM)
            .setFluid(moltenStarPlatinum, FluidValues.INGOT)
            .setTemperature(1500)
            .save(consumer, location(folder + "casting/star_platinum"));
        MaterialMeltingRecipeBuilder.material(TEMaterialIds.STAR_PLATINUM, 1500,
                FluidOutput.fromTag(moltenStarPlatinum, FluidValues.INGOT))
            .save(consumer, location(folder + "melting/star_platinum"));

        TagKey<Fluid> moltenBellMetal = forgeFluidTag("molten_bell_metal");
        MaterialFluidRecipeBuilder.material(TEMaterialIds.BELL_METAL)
            .setFluid(moltenBellMetal, FluidValues.INGOT)
            .setTemperature(1000)
            .save(consumer, location(folder + "casting/bell_metal"));
        MaterialMeltingRecipeBuilder.material(TEMaterialIds.BELL_METAL, 1000,
                FluidOutput.fromTag(moltenBellMetal, FluidValues.INGOT))
            .save(consumer, location(folder + "melting/bell_metal"));
    }

    private void addFluidRecipes(Consumer<FinishedRecipe> consumer) {
        String meltingFolder = "smeltery/melting/metal/unobtainium/";
        String castingFolder = "smeltery/casting/metal/unobtainium/";
        int temperature = 1300;

        MeltingRecipeBuilder.melting(
                Ingredient.of(TEItems.UNOBTAINIUM_INGOT.get()),
                TEFluids.MOLTEN_UNOBTAINIUM.result(FluidValues.INGOT),
                temperature, 1.0f)
            .save(consumer, location(meltingFolder + "ingot"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(TEItems.UNOBTAINIUM_NUGGET.get()),
                TEFluids.MOLTEN_UNOBTAINIUM.result(FluidValues.NUGGET),
                temperature, 1 / 3f)
            .save(consumer, location(meltingFolder + "nugget"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(TEBlocks.UNOBTAINIUM_BLOCK_ITEM.get()),
                TEFluids.MOLTEN_UNOBTAINIUM.result(FluidValues.METAL_BLOCK),
                temperature, 3.0f)
            .save(consumer, location(meltingFolder + "block"));

        ItemCastingRecipeBuilder.tableRecipe(TEItems.UNOBTAINIUM_INGOT.get())
            .setFluidAndTime(TEFluids.MOLTEN_UNOBTAINIUM, FluidValues.INGOT)
            .setCast(TinkerSmeltery.ingotCast.getMultiUseTag(), false)
            .save(consumer, location(castingFolder + "ingot_gold_cast"));

        ItemCastingRecipeBuilder.tableRecipe(TEItems.UNOBTAINIUM_INGOT.get())
            .setFluidAndTime(TEFluids.MOLTEN_UNOBTAINIUM, FluidValues.INGOT)
            .setCast(TinkerSmeltery.ingotCast.getSingleUseTag(), true)
            .save(consumer, location(castingFolder + "ingot_sand_cast"));

        ItemCastingRecipeBuilder.tableRecipe(TEItems.UNOBTAINIUM_NUGGET.get())
            .setFluidAndTime(TEFluids.MOLTEN_UNOBTAINIUM, FluidValues.NUGGET)
            .setCast(TinkerSmeltery.nuggetCast.getMultiUseTag(), false)
            .save(consumer, location(castingFolder + "nugget_gold_cast"));

        ItemCastingRecipeBuilder.tableRecipe(TEItems.UNOBTAINIUM_NUGGET.get())
            .setFluidAndTime(TEFluids.MOLTEN_UNOBTAINIUM, FluidValues.NUGGET)
            .setCast(TinkerSmeltery.nuggetCast.getSingleUseTag(), true)
            .save(consumer, location(castingFolder + "nugget_sand_cast"));

        ItemCastingRecipeBuilder.basinRecipe(TEBlocks.UNOBTAINIUM_BLOCK_ITEM.get())
            .setFluidAndTime(TEFluids.MOLTEN_UNOBTAINIUM, FluidValues.METAL_BLOCK)
            .save(consumer, location(castingFolder + "block"));

        TagKey<Fluid> moltenNetherite = forgeFluidTag("molten_netherite");
        ItemCastingRecipeBuilder.tableRecipe(TEItems.UNOBTAINIUM_INGOT.get())
            .setFluid(moltenNetherite, FluidValues.INGOT * 3)
            .setCoolingTime(1300, FluidValues.INGOT * 3)
            .setCast(Ingredient.of(Items.ECHO_SHARD), true)
            .save(consumer, location("smeltery/casting/unobtainium_from_netherite"));

        addMetalFluidRecipes(consumer, "mythican", TEItems.MYTHICAN_INGOT, TEItems.MYTHICAN_NUGGET,
            TEBlocks.MYTHICAN_BLOCK_ITEM, TEFluids.MOLTEN_MYTHICAN, 1500);

        addMetalFluidRecipes(consumer, "star_platinum", TEItems.STAR_PLATINUM_INGOT, TEItems.STAR_PLATINUM_NUGGET,
            TEBlocks.STAR_PLATINUM_BLOCK_ITEM, TEFluids.MOLTEN_STAR_PLATINUM, 1500);

        addMetalFluidRecipes(consumer, "bell_metal", TEItems.BELL_METAL_INGOT, TEItems.BELL_METAL_NUGGET,
            TEBlocks.BELL_METAL_BLOCK_ITEM, TEFluids.MOLTEN_BELL_METAL, 1000);
    }

    private void addMetalFluidRecipes(Consumer<FinishedRecipe> consumer, String name,
                                       RegistryObject<Item> ingot, RegistryObject<Item> nugget,
                                       RegistryObject<Item> blockItem,
                                       slimeknights.mantle.registration.object.FlowingFluidObject<?> fluid,
                                       int temperature) {
        String meltingFolder = "smeltery/melting/metal/" + name + "/";
        String castingFolder = "smeltery/casting/metal/" + name + "/";

        MeltingRecipeBuilder.melting(Ingredient.of(ingot.get()), fluid.result(FluidValues.INGOT), temperature, 1.0f)
            .save(consumer, location(meltingFolder + "ingot"));
        MeltingRecipeBuilder.melting(Ingredient.of(nugget.get()), fluid.result(FluidValues.NUGGET), temperature, 1 / 3f)
            .save(consumer, location(meltingFolder + "nugget"));
        MeltingRecipeBuilder.melting(Ingredient.of(blockItem.get()), fluid.result(FluidValues.METAL_BLOCK), temperature, 3.0f)
            .save(consumer, location(meltingFolder + "block"));

        ItemCastingRecipeBuilder.tableRecipe(ingot.get())
            .setFluidAndTime(fluid, FluidValues.INGOT)
            .setCast(TinkerSmeltery.ingotCast.getMultiUseTag(), false)
            .save(consumer, location(castingFolder + "ingot_gold_cast"));
        ItemCastingRecipeBuilder.tableRecipe(ingot.get())
            .setFluidAndTime(fluid, FluidValues.INGOT)
            .setCast(TinkerSmeltery.ingotCast.getSingleUseTag(), true)
            .save(consumer, location(castingFolder + "ingot_sand_cast"));
        ItemCastingRecipeBuilder.tableRecipe(nugget.get())
            .setFluidAndTime(fluid, FluidValues.NUGGET)
            .setCast(TinkerSmeltery.nuggetCast.getMultiUseTag(), false)
            .save(consumer, location(castingFolder + "nugget_gold_cast"));
        ItemCastingRecipeBuilder.tableRecipe(nugget.get())
            .setFluidAndTime(fluid, FluidValues.NUGGET)
            .setCast(TinkerSmeltery.nuggetCast.getSingleUseTag(), true)
            .save(consumer, location(castingFolder + "nugget_sand_cast"));
        ItemCastingRecipeBuilder.basinRecipe(blockItem.get())
            .setFluidAndTime(fluid, FluidValues.METAL_BLOCK)
            .save(consumer, location(castingFolder + "block"));
    }

    private void addAlloyRecipes(Consumer<FinishedRecipe> consumer) {
        AlloyRecipeBuilder.alloy(TEFluids.MOLTEN_MYTHICAN, FluidValues.INGOT * 3)
            .addInput(FluidIngredient.of(forgeFluidTag("molten_pewter"), FluidValues.INGOT))
            .addInput(FluidIngredient.of(forgeFluidTag("molten_bronze"), FluidValues.INGOT))
            .addInput(FluidIngredient.of(forgeFluidTag("molten_rose_gold"), FluidValues.INGOT))
            .save(consumer, location("smeltery/alloying/molten_mythican"));

        AlloyRecipeBuilder.alloy(TEFluids.MOLTEN_STAR_PLATINUM, FluidValues.INGOT * 5)
            .addInput(FluidIngredient.of(forgeFluidTag("molten_platinum"), FluidValues.INGOT * 2))
            .addInput(FluidIngredient.of(tcFluidTag("molten_amethyst"), FluidValues.GEM * 2))
            .addInput(FluidIngredient.of(tcFluidTag("molten_debris"), FluidValues.INGOT))
            .save(consumer, location("smeltery/alloying/molten_star_platinum"));

        AlloyRecipeBuilder.alloy(TEFluids.MOLTEN_BELL_METAL, FluidValues.INGOT * 2)
            .addInput(FluidIngredient.of(forgeFluidTag("molten_tin"), FluidValues.INGOT))
            .addInput(FluidIngredient.of(forgeFluidTag("molten_nickel"), FluidValues.INGOT))
            .save(consumer, location("smeltery/alloying/molten_bell_metal"));
    }

    private void addEvoliteRecipes(Consumer<FinishedRecipe> consumer) {
        Consumer<FinishedRecipe> cbConsumer = withCondition(consumer, new ModLoadedCondition("cobblemon"));
        String folder = "tools/materials/";

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TEItems.EVOLITE.get())
            .requires(cobblemonIngredient("fire_stone"))
            .requires(cobblemonIngredient("water_stone"))
            .requires(cobblemonIngredient("thunder_stone"))
            .requires(cobblemonIngredient("leaf_stone"))
            .requires(cobblemonIngredient("moon_stone"))
            .requires(cobblemonIngredient("sun_stone"))
            .requires(cobblemonIngredient("shiny_stone"))
            .requires(cobblemonIngredient("dusk_stone"))
            .requires(cobblemonIngredient("dawn_stone"))
            .unlockedBy("has_fire_stone", has(Items.EMERALD))
            .save(cbConsumer, location("evolite_from_stones"));

        materialRecipe(cbConsumer, TEMaterialIds.EVOLITE, Ingredient.of(TEItems.EVOLITE.get()), 1, 1, folder + "evolite");

        saveRawShapelessRecipe(cbConsumer, location("rare_candy_from_evolite"), "cobblemon:rare_candy", 1,
            Ingredient.of(TEItems.EVOLITE.get()), Ingredient.of(Items.EMERALD));
    }

    private Ingredient cobblemonIngredient(String name) {
        return ItemNameIngredient.from(new ResourceLocation("cobblemon", name));
    }

    private void saveRawShapelessRecipe(Consumer<FinishedRecipe> consumer, ResourceLocation id, String resultItem, int count, Ingredient... ingredients) {
        consumer.accept(new FinishedRecipe() {
            @Override
            public void serializeRecipeData(JsonObject json) {
                JsonArray arr = new JsonArray();
                for (Ingredient ing : ingredients) {
                    arr.add(ing.toJson());
                }
                json.add("ingredients", arr);
                JsonObject result = new JsonObject();
                result.addProperty("item", resultItem);
                result.addProperty("count", count);
                json.add("result", result);
            }
            @Override public ResourceLocation getId() { return id; }
            @Override public RecipeSerializer<?> getType() { return RecipeSerializer.SHAPELESS_RECIPE; }
            @Override public JsonObject serializeAdvancement() { return null; }
            @Override public ResourceLocation getAdvancementId() { return null; }
        });
    }

    private TagKey<Fluid> forgeFluidTag(String path) {
        return TagKey.create(Registries.FLUID, new ResourceLocation("forge", path));
    }

    private TagKey<Fluid> tcFluidTag(String path) {
        return TagKey.create(Registries.FLUID, new ResourceLocation("tconstruct", path));
    }

    private TagKey<Item> forgeItemTag(String path) {
        return TagKey.create(Registries.ITEM, new ResourceLocation("forge", path));
    }

    private void tagMeltingCasting(Consumer<FinishedRecipe> consumer, MaterialId material, TagKey<Fluid> fluidTag, int temperature, String folder) {
        tagMeltingCasting(consumer, material, fluidTag, temperature, material.getPath(), folder);
    }

    private void tagMeltingCasting(Consumer<FinishedRecipe> consumer, MaterialId material, TagKey<Fluid> fluidTag, int temperature, String ingotName, String folder) {
        Consumer<FinishedRecipe> wrapped = withCondition(consumer, tagCondition("ingots/" + ingotName));
        MaterialFluidRecipeBuilder.material(material)
            .setFluid(fluidTag, FluidValues.INGOT)
            .setTemperature(temperature)
            .save(wrapped, location(folder + "casting/" + material.getPath()));
        MaterialMeltingRecipeBuilder.material(material, temperature, FluidOutput.fromTag(fluidTag, FluidValues.INGOT))
            .save(wrapped, location(folder + "melting/" + material.getPath()));
    }

    private void gemMeltingCasting(Consumer<FinishedRecipe> consumer, MaterialId material, TagKey<Fluid> fluidTag, int temperature, String gemName, String folder) {
        Consumer<FinishedRecipe> wrapped = withCondition(consumer, tagCondition("gems/" + gemName));
        MaterialFluidRecipeBuilder.material(material)
            .setFluid(fluidTag, FluidValues.GEM)
            .setTemperature(temperature)
            .save(wrapped, location(folder + "casting/" + material.getPath()));
        MaterialMeltingRecipeBuilder.material(material, temperature, FluidOutput.fromTag(fluidTag, FluidValues.GEM))
            .save(wrapped, location(folder + "melting/" + material.getPath()));
    }
}
