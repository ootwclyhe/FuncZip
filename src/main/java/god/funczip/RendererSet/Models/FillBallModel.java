package god.funczip.RendererSet.Models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import god.funczip.Funczip;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class FillBallModel extends Model {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Funczip.MODID, "fillballblock"), "main");
    private final ModelPart group2;
    private final ModelPart group4;
    private final ModelPart group;
    private final ModelPart bone;

    public FillBallModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.group2 = root.getChild("group2");
        this.group4 = root.getChild("group4");
        this.group = root.getChild("group");
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition group2 = partdefinition.addOrReplaceChild("group2", CubeListBuilder.create().texOffs(46, 16).addBox(4.6841F, -7.9021F, -0.2415F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.1841F, 4.8076F, -1.5F));

        PartDefinition cube_r1 = group2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 14).addBox(0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.1841F, -0.5F, 5.5341F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r2 = group2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(9, 6).addBox(0.0F, -1.5F, 5.0F, 2.0F, 4.5F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.1841F, -2.5668F, 0.31F, -0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r3 = group2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(9, 13).mirror().addBox(-0.5F, -3.0F, -0.5F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.6841F, -5.4273F, -2.0093F, -0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r4 = group2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(35, 12).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6841F, -1.2705F, -2.4122F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r5 = group2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(16, 13).addBox(-2.0F, -2.0F, 0.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.1841F, 1.6924F, -2.65F, -0.3927F, 0.0F, 0.0F));

        PartDefinition group4 = partdefinition.addOrReplaceChild("group4", CubeListBuilder.create().texOffs(10, 3).addBox(-8.5F, 6.0F, 7.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-9.0F, -12.0F, 7.0F, 2.0F, 18.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 14).mirror().addBox(-8.5F, 12.0F, 7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(16, 5).addBox(-9.0F, 7.0F, 7.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

        PartDefinition group = partdefinition.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(52, 13).addBox(-9.5F, 1.5F, 6.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(25, 4).addBox(-9.5F, -17.0F, 6.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

        return LayerDefinition.create(meshdefinition, 64, 20);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        group2.render(poseStack, buffer, packedLight, packedOverlay, color);
        group4.render(poseStack, buffer, packedLight, packedOverlay, color);
        group.render(poseStack, buffer, packedLight, packedOverlay, color);
        bone.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}