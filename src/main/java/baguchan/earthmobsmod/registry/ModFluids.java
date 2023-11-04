package baguchan.earthmobsmod.registry;

import baguchan.earthmobsmod.EarthMobsMod;
import baguchan.earthmobsmod.fluid.MudFluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

public class ModFluids {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, EarthMobsMod.MODID);

	public static final RegistryObject<FlowingFluid> MUD = FLUIDS.register("mud", () -> new MudFluid.Source());
	public static final RegistryObject<FlowingFluid> MUD_FLOW = FLUIDS.register("mud_flow", () -> new MudFluid.Flowing());

}
