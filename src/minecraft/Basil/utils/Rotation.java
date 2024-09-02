package Basil.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class Rotation {
    static Minecraft mc = Minecraft.getMinecraft();

    public static float[] getRotationTo(Entity e) {
        // Calculate deltas
        double deltaX = e.posX - mc.thePlayer.posX;
        double deltaY = e.posY + e.getEyeHeight() - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double deltaZ = e.posZ - mc.thePlayer.posZ;

        // Calculate the horizontal distance to the target entity
        double dist = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

        // Calculate yaw and pitch
        float yaw = (float) Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0F;
        float pitch = (float) -Math.toDegrees(Math.atan(deltaY / dist));

        // Normalize yaw to the range [-180, 180]
        yaw = (yaw + 360) % 360;
        if (yaw > 180) {
            yaw -= 360;
        }

        return new float[]{yaw, pitch};
    }

    public static float getYawDistToEntity(EntityLivingBase entity) {
        float yaw = getRotationTo(entity)[0];
        float pYaw = mc.thePlayer.rotationYaw;
        float x = yaw - pYaw;
        x = (x + 540) % 360 - 180;  // Normalize yaw difference to [-180, 180]
        return x;
    }

    public static float getPitchDistToEntity(EntityLivingBase entity) {
        float pitch = getRotationTo(entity)[1];
        float pPitch = mc.thePlayer.rotationPitch;
        float x = pitch - pPitch;
        x = (x + 540) % 360 - 180;  // Normalize pitch difference to [-180, 180]
        return x;
    }

    public static float getYawDistToEntity(EntityLivingBase entity, float yaw) {
        float pYaw = mc.thePlayer.rotationYaw;
        float x = yaw - pYaw;
        x = (x + 540) % 360 - 180;  // Normalize yaw difference to [-180, 180]
        return x;
    }
}
