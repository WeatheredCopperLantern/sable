package dev.ryanhcode.sable.platform;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.ryanhcode.sable.sublevel.render.vanilla.SingleBlockSubLevelWrapper;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.ServiceLoader;

@ApiStatus.Internal
public interface SableSubLevelRenderPlatform {
    SableSubLevelRenderPlatform INSTANCE = ServiceLoader.load(SableSubLevelRenderPlatform.class).findFirst().orElseThrow(() -> new RuntimeException("Failed to find sable block render platform"));

    void removeCustomModelData(final ClientLevel level, final BlockPos pos);

    void tesselateBlock(
            SingleBlockSubLevelWrapper blockAndTintGetter,
            BakedModel bakedModel,
            BlockState blockState,
            BlockPos pos,
            PoseStack poseStack,
            VertexConsumer vertexConsumer,
            RandomSource randomSource,
            long seed,
            int packedOverlay,
            @Nullable RenderType renderType);

    List<RenderType> getRenderLayers(
            final SingleBlockSubLevelWrapper blockAndTintGetter,
            final BakedModel bakedModel,
            final BlockState blockState,
            final BlockPos pos,
            final RandomSource randomSource);

    void tryAddFlywheelVisual(BlockEntity blockEntity);
}
