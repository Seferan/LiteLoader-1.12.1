package com.mumfrey.liteloader.client.mixin;

import java.io.File;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mumfrey.liteloader.client.ClientProxy;

import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ScreenShotHelper;

@Mixin(ScreenShotHelper.class)
public abstract class MixinScreenShotHelper
{
    @Inject(
        method = "saveScreenshot(Ljava/io/File;Ljava/lang/String;IILnet/minecraft/client/shader/Framebuffer;)"
                + "Lnet/minecraft/util/text/ITextComponent;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/ScreenShotHelper;createScreenshot(IILnet/minecraft/client/shader/Framebuffer;)"
                    + "Ljava/awt/image/BufferedImage;",
            ordinal = 0
        ),
        cancellable = true
    )
    private static void onSaveScreenshot(File gameDir, String name, int width, int height, Framebuffer fbo, CallbackInfoReturnable<ITextComponent> ci)
    {
        ClientProxy.onSaveScreenshot(ci, gameDir, name, width, height, fbo);
    }
}
