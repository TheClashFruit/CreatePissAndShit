package me.theclashfruit.pissnshit.blocks.toilet;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.fluid.CombinedTankWrapper;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.LangBuilder;
import com.simibubi.create.foundation.utility.VecHelper;
import com.simibubi.create.infrastructure.config.AllConfigs;
import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import io.github.fabricators_of_create.porting_lib.util.FluidTextUtil;
import io.github.fabricators_of_create.porting_lib.util.FluidUnit;
import me.theclashfruit.pissnshit.registry.Blocks;
import me.theclashfruit.pissnshit.registry.Fluids;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SidedStorageBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

import static com.simibubi.create.content.kinetics.BlockStressValues.getCapacity;

public class MechanicalToiletBlockEntity extends SmartBlockEntity implements IHaveGoggleInformation, SidedStorageBlockEntity {
    private SmartFluidTankBehaviour tank;

    public MechanicalToiletBlockEntity(BlockPos pos, BlockState state) {
        super(Blocks.MECHANICAL_TOILET_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        tank = new SmartFluidTankBehaviour(SmartFluidTankBehaviour.OUTPUT, this, 1, FluidConstants.BUCKET, true)
            .forbidInsertion();

        behaviours.add(tank);
    }

    @Nullable
    @Override
    public Storage<FluidVariant> getFluidStorage(@Nullable Direction face) {
        return tank.getCapability();
    }

    @Override
    public void tick() {
        super.tick();

        if (world.isClient) return;
    }

    @Override
    public boolean addToGoggleTooltip(List<Text> tooltip, boolean isPlayerSneaking) {
        Lang.translate("gui.goggles.basin_contents")
            .forGoggles(tooltip);

        boolean isEmpty = true;

        FluidUnit unit = AllConfigs.client().fluidUnitType.get();
        LangBuilder unitSuffix = Lang.translate(unit.getTranslationKey());
        boolean simplify = AllConfigs.client().simplifyFluidUnit.get();

        for (SmartFluidTankBehaviour.TankSegment tank : tank.getTanks()) {
            FluidStack fluidStack = tank.getTank().getFluid();

            if (fluidStack.isEmpty())
                continue;

            Lang.text("")
                .add(Lang.fluidName(fluidStack)
                    .add(Lang.text(" "))
                    .style(Formatting.GRAY)
                    .add(Lang.text(FluidTextUtil.getUnicodeMillibuckets(fluidStack.getAmount(), unit, simplify))
                        .add(unitSuffix)
                        .style(Formatting.BLUE)))
                .forGoggles(tooltip, 1);

            isEmpty = false;
        }

        if (isEmpty)
            tooltip.remove(0);

        return true;
    }

    public void addPiss(int amount) {
        for (SmartFluidTankBehaviour.TankSegment tank : tank.getTanks()) {
            tank.getTank().setFluid(new FluidStack(FluidVariant.of(Fluids.STILL_PISS), tank.getTank().getFluid().getAmount() + amount));
        }
    }
}
