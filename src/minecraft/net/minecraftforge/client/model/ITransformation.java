package net.minecraftforge.client.model;

import org.lwjgl.util.vector.Matrix;

import net.minecraft.util.EnumFacing;

public interface ITransformation
{
    Matrix getMatrix();

    EnumFacing rotate(EnumFacing var1);

    int rotate(EnumFacing var1, int var2);
}
