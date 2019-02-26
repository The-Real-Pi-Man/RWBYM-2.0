package be.bluexin.rwbym.blocks;

import be.bluexin.rwbym.Init.RWBYItems;
import be.bluexin.rwbym.utility.RegUtil;
import be.bluexin.rwbym.weaponry.RWBYItem;
import be.bluexin.rwbym.weaponry.RWBYSword;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;


public class RWBYToolkit extends BlockBase {

    public RWBYToolkit(String name,
                     Material mat,
                     CreativeTabs tab,
                     float hardness,
                     float resistance,
                     String tool, int harvest) {
        super(name, mat, tab, hardness, resistance, tool, harvest);
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!playerIn.inventory.getCurrentItem().isEmpty() && playerIn.inventory.getCurrentItem().isItemDamaged()){
            playerIn.inventory.getCurrentItem().damageItem(-90000, playerIn);
            playerIn.addExperienceLevel(-5);
            worldIn.setBlockToAir(pos);
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public boolean isFullCube(IBlockState p_isFullCube_1_) {
        return false;
    }
    @Override
    public boolean doesSideBlockRendering(IBlockState p_doesSideBlockRendering_1_, IBlockAccess p_doesSideBlockRendering_2_, BlockPos p_doesSideBlockRendering_3_, EnumFacing p_doesSideBlockRendering_4_) {
        return false;
    }
    @Override
    public boolean isTranslucent(IBlockState state)
    {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}