package com.leclowndu93150.tinkers_extra_materials.registry;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.fluids.block.BurningLiquidBlock;

public class TEFluids {

    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(TinkersExtraMaterials.MODID);

    public static FlowingFluidObject<ForgeFlowingFluid> MOLTEN_UNOBTAINIUM;
    public static FlowingFluidObject<ForgeFlowingFluid> MOLTEN_MYTHICAN;
    public static FlowingFluidObject<ForgeFlowingFluid> MOLTEN_STAR_PLATINUM;

    public static void register(IEventBus modEventBus) {
        MOLTEN_UNOBTAINIUM = FLUIDS.register("molten_unobtainium")
            .type(hotBuilder("molten_unobtainium").temperature(1300).lightLevel(10))
            .block(BurningLiquidBlock.createBurning(MapColor.COLOR_BLACK, 10, 10, 7.0F))
            .bucket()
            .commonTag()
            .flowing();

        MOLTEN_MYTHICAN = FLUIDS.register("molten_mythican")
            .type(hotBuilder("molten_mythican").temperature(1500).lightLevel(12))
            .block(BurningLiquidBlock.createBurning(MapColor.COLOR_GREEN, 12, 10, 7.0F))
            .bucket()
            .commonTag()
            .flowing();

        MOLTEN_STAR_PLATINUM = FLUIDS.register("molten_star_platinum")
            .type(hotBuilder("molten_star_platinum").temperature(1500).lightLevel(12))
            .block(BurningLiquidBlock.createBurning(MapColor.COLOR_PURPLE, 12, 10, 7.0F))
            .bucket()
            .commonTag()
            .flowing();

        FLUIDS.register(modEventBus);
    }

    private static FluidType.Properties hotBuilder(String name) {
        return FluidType.Properties.create()
            .density(2000)
            .viscosity(10000)
            .temperature(1000)
            .descriptionId("fluid." + TinkersExtraMaterials.MODID + "." + name)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
            .motionScale(0.0023333333333333335)
            .canSwim(false)
            .canDrown(false)
            .pathType(BlockPathTypes.LAVA)
            .adjacentPathType(null);
    }
}
