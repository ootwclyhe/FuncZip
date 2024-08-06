package god.funczip.RendererSet;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import god.funczip.EventSet.Client.RendererRegister;
import god.funczip.Funczip;
import god.funczip.RendererSet.Models.FillBallModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;

import static net.minecraft.world.item.ItemDisplayContext.GUI;

public class FunczipItemRender extends BlockEntityWithoutLevelRenderer {

    private FillBallModel model = new FillBallModel(Minecraft.getInstance().getEntityModels().bakeLayer(RendererRegister.fillballlocation));
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

    public FunczipItemRender() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        blockEntityRenderDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();


    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        this.model = new FillBallModel(Minecraft.getInstance().getEntityModels().bakeLayer(RendererRegister.fillballlocation));
    }

    @Override
    public void renderByItem(ItemStack itemStack, ItemDisplayContext ctx, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        poseStack.pushPose();
        poseStack.rotateAround(Axis.XP.rotation(180F), 0F, 0.0F, 0F);
        poseStack.translate(0.5D, -2D, 0D);
        Material material = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(Funczip.MODID, "block/fillballblock"));
        VertexConsumer consumer = material.sprite()
                .wrap(ItemRenderer.getFoilBufferDirect(bufferSource, this.model.renderType(material.atlasLocation()), true, itemStack.hasFoil()));
        this.model.renderToBuffer(poseStack, consumer, combinedLight, combinedOverlay);
        poseStack.popPose();
        if (ctx.firstPerson() || ctx == GUI) {
            poseStack.pushPose();
            //z1.505
            //x0.495
            //1.6
            poseStack.translate(0.5075D, 1.16D, 1.5075D);
            poseStack.scale(0.3F, 0.3F, 0.3F);
            ItemContainerContents icc = itemStack.get(DataComponents.CONTAINER);
            ItemStack is;
            if (icc == ItemContainerContents.EMPTY) {
                is = new ItemStack(Items.BEDROCK);
            } else is = icc.getStackInSlot(0);
            Minecraft.getInstance().getItemRenderer().renderStatic(
                    is, ctx, combinedLight, combinedOverlay, poseStack, bufferSource, null, 0);
            poseStack.popPose();
            poseStack.pushPose();
            poseStack.translate(0.5D, 1.14D, 1.5D);
            poseStack.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getInstance().getItemRenderer().renderStatic(
                    new ItemStack(Items.GLASS), ctx, combinedLight, combinedOverlay, poseStack, bufferSource, null, 0);
            poseStack.popPose();
        } else {
            poseStack.pushPose();
            poseStack.translate(0.5D, 1.05D, 1.5D);
            poseStack.scale(0.5F, 0.5F, 0.5F);
            ItemContainerContents icc = itemStack.get(DataComponents.CONTAINER);
            ItemStack is;
            if (icc == ItemContainerContents.EMPTY) {
                is = new ItemStack(Items.BEDROCK);
            } else is = icc.getStackInSlot(0);
            Minecraft.getInstance().getItemRenderer().renderStatic(
                    is, ctx, combinedLight, combinedOverlay, poseStack, bufferSource, null, 0);
            poseStack.popPose();
            poseStack.pushPose();
            poseStack.translate(0.5D, 1.015D, 1.5D);
            poseStack.scale(0.7F, 0.7F, 0.7F);
            Minecraft.getInstance().getItemRenderer().renderStatic(
                    new ItemStack(Items.GLASS), ctx, combinedLight, combinedOverlay, poseStack, bufferSource, null, 0);
            poseStack.popPose();
        }
    }


}
