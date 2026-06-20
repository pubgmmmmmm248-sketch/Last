package com.example.tntwarner;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.item.TNTEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.List;

public class HUDRenderer extends AbstractGui {
    private final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        // Рисуем только основной интерфейс
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;
        if (mc.level == null || mc.player == null) return;

        // Ищем TNT в радиусе 48 блоков
        List<TNTEntity> tnts = mc.level.getEntitiesOfClass(TNTEntity.class, 
                mc.player.getBoundingBox().inflate(48));

        if (tnts.isEmpty()) return;

        MatrixStack ms = event.getMatrixStack();
        int y = 10;

        for (TNTEntity tnt : tnts) {
            float seconds = (float) tnt.getFuse() / 20.0F;
            double dist = mc.player.distanceTo(tnt);
            
            String text = String.format("⚠ TNT: %.1fs (Дист: %.1fm)", seconds, dist);
            int color = seconds < 1.0f ? 0xFF5555 : 0xFFFFFF; // Краснеет перед взрывом

            mc.font.drawShadow(ms, text, 10, y, color);
            y += 12;
        }
    }
}
