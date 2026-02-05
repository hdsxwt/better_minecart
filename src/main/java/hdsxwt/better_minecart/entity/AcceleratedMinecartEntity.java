package hdsxwt.better_minecart.entity;

import hdsxwt.better_minecart.item.BrakeModuleItem;
import hdsxwt.better_minecart.item.EngineModuleItem;
import hdsxwt.better_minecart.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AcceleratedMinecartEntity extends AbstractMinecartEntity {
    private int powerValue = 35;
    private int resistanceValue = 40;
    private int engineTier = 1;
    private int brakeTier = 1;
    private float controlFactor = 1.0f;
    
    private float currentSpeed = 0f;
    private boolean forwardPressed = false;
    private boolean backwardPressed = false;
    
    public AcceleratedMinecartEntity(EntityType<?> type, World world) {
        super(type, world);
    }
    
    public AcceleratedMinecartEntity(World world, double x, double y, double z, ItemStack stack) {
        super(ModEntities.ACCELERATED_MINECART, world, x, y, z);
        loadPropertiesFromItem(stack);
    }
    
    private void loadPropertiesFromItem(ItemStack stack) {
        if (stack.hasNbt()) {
            NbtCompound nbt = stack.getNbt();
            this.powerValue = nbt.getInt("PowerValue");
            this.resistanceValue = nbt.getInt("ResistanceValue");
            this.engineTier = nbt.getInt("EngineTier");
            this.brakeTier = nbt.getInt("BrakeTier");
            if (nbt.contains("ControlFactor", NbtCompound.FLOAT_TYPE)) {
                this.controlFactor = nbt.getFloat("ControlFactor");
            }
        }
    }
    
    @Override
    public void tick() {
        super.tick();
        
        if (this.getWorld().isClient() && this.hasPassengers()) {
            updateControls();
            applyMovement();
        }
    }
    
    private void updateControls() {
        PlayerEntity player = (PlayerEntity) this.getFirstPassenger();
        if (player != null) {
            forwardPressed = player.input.pressingForward;
            backwardPressed = player.input.pressingBack;
        }
    }
    
    private void applyMovement() {
        float maxSpeed = calculateMaxSpeed();
        float acceleration = calculateAcceleration();
        float deceleration = calculateDeceleration();
        
        if (forwardPressed && !backwardPressed) {
            currentSpeed = Math.min(currentSpeed + acceleration, maxSpeed);
        } else if (backwardPressed && !forwardPressed) {
            currentSpeed = Math.max(currentSpeed - acceleration, -maxSpeed * 0.7f);
        } else {
            if (currentSpeed > 0) {
                currentSpeed = Math.max(currentSpeed - deceleration, 0);
            } else if (currentSpeed < 0) {
                currentSpeed = Math.min(currentSpeed + deceleration, 0);
            }
        }
        
        if (Math.abs(currentSpeed) > 0.01f) {
            Vec3d velocity = this.getVelocity();
            Vec3d forward = this.getRotationVector();
            
            this.setVelocity(
                forward.x * currentSpeed,
                velocity.y,
                forward.z * currentSpeed
            );
        }
    }
    
    private float calculateMaxSpeed() {
        float baseSpeed = 0.4f;
        float powerBonus = (powerValue / 100.0f) * 1.6f;
        return Math.min(baseSpeed + powerBonus, 2.5f);
    }
    
    private float calculateAcceleration() {
        float baseAccel = 0.04f;
        float tierBonus = engineTier * 0.02f;
        return baseAccel + tierBonus;
    }
    
    private float calculateDeceleration() {
        float baseDecel = 0.02f;
        float resistanceBonus = (resistanceValue / 100.0f) * 0.12f;
        return (baseDecel + resistanceBonus) * controlFactor;
    }
    
    @Override
    public float getMaxSpeed() {
        return calculateMaxSpeed();
    }
    
    @Override
    protected double getMaxSpeedFactor() {
        return 2.0 + (engineTier * 0.3);
    }
    
    @Override
    public Type getMinecartType() {
        return Type.RIDEABLE;
    }
    
    // NBT 数据持久化
    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("PowerValue", powerValue);
        nbt.putInt("ResistanceValue", resistanceValue);
        nbt.putInt("EngineTier", engineTier);
        nbt.putInt("BrakeTier", brakeTier);
        nbt.putFloat("ControlFactor", controlFactor);
    }
    
    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.powerValue = nbt.getInt("PowerValue");
        this.resistanceValue = nbt.getInt("ResistanceValue");
        this.engineTier = nbt.getInt("EngineTier");
        this.brakeTier = nbt.getInt("BrakeTier");
        this.controlFactor = nbt.getFloat("ControlFactor");
    }
    
    @Override
    public ItemStack getPickBlockStack() {
        ItemStack stack = new ItemStack(ModItems.ACCELERATED_MINECART);
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt("PowerValue", powerValue);
        nbt.putInt("ResistanceValue", resistanceValue);
        nbt.putInt("EngineTier", engineTier);
        nbt.putInt("BrakeTier", brakeTier);
        nbt.putFloat("ControlFactor", controlFactor);
        
        // 设置显示名称
        String displayName = String.format("矿车 [P:%d/R:%d]", powerValue, resistanceValue);
        stack.setCustomName(net.minecraft.text.Text.literal(displayName));
        
        return stack;
    }
}