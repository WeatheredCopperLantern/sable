package dev.ryanhcode.sable.neoforge.platform;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.ryanhcode.sable.neoforge.compatibility.flywheel.FlywheelCompatNeoForge;
import dev.ryanhcode.sable.neoforge.mixinterface.sublevel_render.vanilla.ModelDataManagerExtension;
import dev.ryanhcode.sable.platform.SableSubLevelRenderPlatform;
import dev.ryanhcode.sable.sublevel.render.vanilla.SingleBlockSubLevelWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@ApiStatus.Internal
public class SableSubLevelRenderPlatformImpl implements SableSubLevelRenderPlatform {

    @Override
    public void removeCustomModelData(final ClientLevel level, final BlockPos pos) {
        ((ModelDataManagerExtension) level.getModelDataManager()).sable$removeModelData(pos);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public void tesselateBlock(final SingleBlockSubLevelWrapper level, final BakedModel bakedModel, final BlockState blockState, final BlockPos pos, final PoseStack poseStack, final VertexConsumer vertexConsumer, final RandomSource randomSource, final long seed, final int packedOverlay, final @Nullable RenderType renderType) {
        Minecraft.getInstance().getBlockRenderer().modelRenderer.tesselateWithoutAO(level, bakedModel, blockState, pos, poseStack, vertexConsumer, true, randomSource, seed, packedOverlay, level.getLevel().getModelData(pos), renderType);
    }

    @Override
    public List<RenderType> getRenderLayers(final SingleBlockSubLevelWrapper blockAndTintGetter, final BakedModel bakedModel, final BlockState blockState, final BlockPos pos, final RandomSource randomSource) {
        return bakedModel.getRenderTypes(blockState, randomSource, blockAndTintGetter.getModelData(pos)).asList();
    }

    @Override
    public void tryAddFlywheelVisual(final BlockEntity blockEntity) {
        if (FlywheelCompatNeoForge.FLYWHEEL_LOADED) {
            FlywheelCompatNeoForge.tryAddVisual(blockEntity);
        }
    }
}
