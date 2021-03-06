package be.bluexin.rwbym.capabilities.Ruby;

import be.bluexin.rwbym.Init.RWBYItems;
import be.bluexin.rwbym.RWBYModels;
import be.bluexin.rwbym.client.particle.RosePetal;
import be.bluexin.rwbym.entity.EntityBeowolf;
import be.bluexin.rwbym.entity.EntityMutantDeathStalker;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.Capability;

import java.util.Iterator;
import java.util.List;

public class Ruby implements IRuby {

	private int invisiblityTimer = 0;
	
	private int cooldown = 0;
	
	private double playerY = -1;
	
	private boolean active = false;
	
	// a level greater than 0 will signal that this is the active semblance
	private int level = 0;
	
	/**activates the semblance*/
	@Override
	public boolean onActivate(EntityPlayer player) {
		
		//System.out.println("" + this.level);

		if(this.level >3){
			this.level = 3;
		}

		switch(this.level) {
		case 1:
			if (this.cooldown > 0) {
				return false;
			}
			else {
				if (player.onGround){
					this.setInvisisbility(50);
					this.active = true;
				}
				return true;
			}
		case 2:
			if (this.cooldown <= 0) {
				this.active = false;
				return false;
			}
			if (this.cooldown >= 30) {
				this.active = true;
				return true;
			}
			return false;
			case 3:
				if (this.cooldown <= 0) {
					this.active = false;
					return false;
				}
				if (this.cooldown >= 30) {
					this.active = true;
					return true;
				}
				return false;
		default:
			return false;
		}
	}
	
	@Override
	public boolean deActivate(EntityPlayer player) {
		
		switch(level) {
		case 1:
			break;
		case 2:
			this.active = false;

		case 3:
		this.active = false;}
		return false;
	}

	/**sets the invisibility and the cooldown for level one*/
	@Override
	public void setInvisisbility(int time) {
		this.invisiblityTimer = time;
		this.cooldown = time * 3;
	}
	
	@Override
	public void setInvisisbilityTimer(int time) {
		this.invisiblityTimer = time;
	}

	@Override
	public void setCooldownTimer(int time) {
		this.cooldown = time;
	}

	/**currently not used*/
	@Override
	public void setPlayerY(double Y) {
		this.playerY = Y;
	}
	
	/**updates the main logic of the semblance*/
	@Override
	public void onUpdate(EntityPlayer player) {

		//System.out.println(cooldown);
		
		if (this.active) {
			
			player.fallDistance = 0;
			
			if (player.world.isRemote) {
				if (player.onGround || this.level > 1) {
					//System.out.println(this.cooldown);
					Vec3d look = player.getLookVec();
					
					//how much speed to keep
					double decay = 0.95D;
					//comment out this line to go back to normal
					look = look.scale(Math.max(Math.sqrt(player.motionX * player.motionX + player.motionY * player.motionY / 4 + player.motionZ * player.motionZ) * decay, 1));
					player.motionX = look.x;
					player.motionY = look.y * 2;
					player.motionZ = look.z;
					player.fallDistance = 0;


					if(this.level >2){
					AxisAlignedBB axisalignedbb = player.getEntityBoundingBox().grow(1,1,1);


					List<?> list1 = player.world.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

					if (!list1.isEmpty())
					{
						Iterator<?> iterator = list1.iterator();

						double y = Math.pow(player.motionY, 2);
						double x = Math.pow(player.motionX, 2);
						double z = Math.pow(player.motionZ, 2);

						double d3 = Math.sqrt(x+y+z);
						float f = (float)d3;

						while (iterator.hasNext())
						{
							EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
							entitylivingbase.attackEntityFrom(DamageSource.FLY_INTO_WALL, f*100);
						}
					}}




					for (int i = 0; i < (this.level > 1 ? 32 : 2); i++) {
						ItemStack is = player.getHeldItemMainhand();
						ItemStack is2 = player.getHeldItemOffhand();
						if(is.getItem() == RWBYItems.crescentfrost){
							RWBYModels.proxy.generateSummerpetals(player);
						}else if(is.getItem() == RWBYItems.crescentgunfrost){
							RWBYModels.proxy.generateSummerpetals(player);
						}else if(is2.getItem() == RWBYItems.crescentfrost){
							RWBYModels.proxy.generateSummerpetals(player);
						}else if(is2.getItem() == RWBYItems.crescentgunfrost){
							RWBYModels.proxy.generateSummerpetals(player);
						}else RWBYModels.proxy.generateRosepetals(player);
					}
				}
			}
		}

		switch(this.level) {
		case 1:
			if (this.invisiblityTimer > 0) {
				this.invisiblityTimer--;
			}
			if (this.invisiblityTimer < 1) {
				this.active = false;
			}
			if (this.cooldown > 0) {
				this.cooldown--;
			}
			break;
		case 2:
			if (!this.active && this.cooldown < 360) {
				this.cooldown++;
			}
			if (this.active) {
				this.cooldown -= 3;
			}
			if (this.cooldown < 1) {
				this.active = false;
			}
			break;
			case 3:
				if (!this.active && this.cooldown < 360) {
					this.cooldown++;
				}
				if (this.active) {
					this.cooldown -= 3;
				}
				if (this.cooldown < 1) {
					this.active = false;
				}
				break;
		}
	}
	
	@Override
	public int getInvisibilityTimer() {
		return this.invisiblityTimer;
	}
	
	@Override
	public int getCooldown() {
		return this.cooldown;
	}

	@Override
	public boolean getInvisibility() {
		switch(this.level) {
		case 1: 
			return false;
		case 2:
			return this.active ? true : false;
		default:
			return false;
		}
	}
	
	@Override
	public boolean getActiveStatus() {
		return this.active;
	}

	@Override
	public double getPlayerY() {
		return this.playerY;
	}
	
	@Override
	public int getLevel() {
		return this.level;
	}
	
	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("Itimer", invisiblityTimer);
		nbt.setInteger("cooldown", cooldown);
		nbt.setInteger("level", level);
		nbt.setBoolean("active", active);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.invisiblityTimer = nbt.getInteger("Itimer");
		this.cooldown = nbt.getInteger("cooldown");
		this.level = nbt.getInteger("level");
		this.active = nbt.getBoolean("active");
	}

	@Override
	public Capability getCapability() {
		return RubyProvider.RUBY_CAP;
	}
	
	@Override
	public String toString() {
		return "Ruby";
	}

	@Override
	public boolean isActive() {
		return this.active;
	}

	@Override
	public boolean isInvisible() {
		switch(this.level) {
		case 1: 
			return false;
		case 2:
			return this.active;
		default:
			return false;
		}
	}
	
}
