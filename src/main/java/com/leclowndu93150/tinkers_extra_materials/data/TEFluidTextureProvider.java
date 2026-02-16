package com.leclowndu93150.tinkers_extra_materials.data;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.registry.TEFluids;
import net.minecraft.data.PackOutput;
import slimeknights.mantle.fluid.texture.AbstractFluidTextureProvider;
import slimeknights.mantle.fluid.texture.FluidTexture;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.TConstruct;

public class TEFluidTextureProvider extends AbstractFluidTextureProvider {

    public TEFluidTextureProvider(PackOutput packOutput) {
        super(packOutput, TinkersExtraMaterials.MODID);
    }

    @Override
    public void addTextures() {
        tintedMolten(TEFluids.MOLTEN_UNOBTAINIUM).color(0xFF100F16);
        tintedMolten(TEFluids.MOLTEN_MYTHICAN).color(0xFF71A976);
        tintedMolten(TEFluids.MOLTEN_STAR_PLATINUM).color(0xFF4F306C);
        tintedMolten(TEFluids.MOLTEN_BELL_METAL).color(0xFF876D48);
    }

    private FluidTexture.Builder tintedMolten(FlowingFluidObject<?> fluid) {
        return texture(fluid).textures(TConstruct.getResource("fluid/molten/"), false, false);
    }

    @Override
    public String getName() {
        return "Tinkers' Extra Materials - Fluid Textures";
    }
}
