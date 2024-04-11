package com.github.L_Ender.cataclysm.client.render;


import com.github.L_Ender.lionfishapi.client.model.tools.AdvancedModelPart;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector4f;

public class RenderUtils {
    public static void matrixStackFromCitadelModel(PoseStack matrixStack, AdvancedModelPart AdvancedModelPart) {
        AdvancedModelPart parent = AdvancedModelPart.getParent();
        if (parent != null) matrixStackFromCitadelModel(matrixStack, parent);
        AdvancedModelPart.translateRotate(matrixStack);
    }

    public static Vec3 matrixStackFromCitadelModel(Entity entity, float entityYaw, AdvancedModelPart modelRenderer) {
        PoseStack matrixStack = new PoseStack();
        matrixStack.translate(entity.getX(), entity.getY(), entity.getZ());
        matrixStack.mulPose((new Quaternionf()).rotationY((-entityYaw + 180) * ((float)Math.PI / 180F)));
        matrixStack.scale(-1, -1, 1);
        matrixStack.translate(0, -1.5f, 0);
        RenderUtils.matrixStackFromCitadelModel(matrixStack, modelRenderer);
        PoseStack.Pose matrixEntry = matrixStack.last();
        Matrix4f matrix4f = matrixEntry.pose();
        Vector4f vec = new Vector4f(0, 0, 0, 1);
        vec.mul(matrix4f);
        return new Vec3(vec.x(), vec.y(), vec.z());
    }
}