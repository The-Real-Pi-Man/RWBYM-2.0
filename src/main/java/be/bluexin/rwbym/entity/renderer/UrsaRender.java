package be.bluexin.rwbym.entity.renderer;

import be.bluexin.rwbym.RWBYModels;
import be.bluexin.rwbym.entity.EntityUrsa;
import be.bluexin.rwbym.entity.ModelUrsa;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class UrsaRender extends RenderLivingBase<EntityUrsa> {

    public static Factory FACTORY = new Factory();

    public UrsaRender(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

    protected boolean canRenderName(EntityUrsa entity) {
        return false;
    }

    protected void preRenderCallback(EntityUrsa entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityUrsa entity) {
        return new ResourceLocation(RWBYModels.MODID, "textures/entity/ursa.png");
    }

    public static class Factory implements IRenderFactory<EntityUrsa> {

        @Override
        public Render<? super EntityUrsa> createRenderFor(RenderManager manager) {
            return new UrsaRender(manager, new ModelUrsa(), 0);
        }

    }
}
