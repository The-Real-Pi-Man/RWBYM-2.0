package be.bluexin.rwbym.entity.renderer;

import be.bluexin.rwbym.RWBYModels;
import be.bluexin.rwbym.entity.EntityBeowolf;
import be.bluexin.rwbym.entity.ModelBeowolf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BeowolfRender extends RenderLivingBase<EntityBeowolf> {

    public static Factory FACTORY = new Factory();

    public BeowolfRender(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

    protected boolean canRenderName(EntityBeowolf entity) {
        return false;
    }

    protected void preRenderCallback(EntityBeowolf entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
        GlStateManager.translate(0, 0.3, 0);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBeowolf entity) {
        return new ResourceLocation(RWBYModels.MODID, "textures/entity/beowolf.png");
    }

    public static class Factory implements IRenderFactory<EntityBeowolf> {

        @Override
        public Render<? super EntityBeowolf> createRenderFor(RenderManager manager) {
            return new BeowolfRender(manager, new ModelBeowolf(), 0);
        }

    }
}
