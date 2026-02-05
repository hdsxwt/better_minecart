package hdsxwt.better_minecart.block.entity;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.item.BrakeModuleItem;
import hdsxwt.better_minecart.item.ChassisItem;
import hdsxwt.better_minecart.item.EngineModuleItem;
import hdsxwt.better_minecart.item.ModItems;
import hdsxwt.better_minecart.screen.AssemblyTableScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class AssemblyTableBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {
    public static final int CHASSIS_SLOT = 0;
    public static final int ENGINE_SLOT = 1;
    public static final int BRAKE_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;
    private static final int INVENTORY_SIZE = 4;
    
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
    
    public AssemblyTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ASSEMBLY_TABLE, pos, state);
    }
    
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AssemblyTableScreenHandler(syncId, playerInventory, this);
    }
    
    @Override
    public Text getDisplayName() {
        return Text.translatable("block.better_minecart.assembly_table");
    }
    
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }
    
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        markDirty();
    }
    
    public void assembleCart() {
        if (!inventory.get(CHASSIS_SLOT).isEmpty() &&
            !inventory.get(ENGINE_SLOT).isEmpty() &&
            !inventory.get(BRAKE_SLOT).isEmpty() &&
            inventory.get(OUTPUT_SLOT).isEmpty()) {
            
            ItemStack chassis = inventory.get(CHASSIS_SLOT);
            ItemStack engineStack = inventory.get(ENGINE_SLOT);
            ItemStack brakeStack = inventory.get(BRAKE_SLOT);
            
            if (chassis.getItem() instanceof ChassisItem &&
                engineStack.getItem() instanceof EngineModuleItem &&
                brakeStack.getItem() instanceof BrakeModuleItem) {
                
                EngineModuleItem engine = (EngineModuleItem) engineStack.getItem();
                BrakeModuleItem brake = (BrakeModuleItem) brakeStack.getItem();
                
                ItemStack assembledCart = createAssembledCart(engine, brake);
                inventory.set(OUTPUT_SLOT, assembledCart);
                
                inventory.get(CHASSIS_SLOT).decrement(1);
                inventory.get(ENGINE_SLOT).decrement(1);
                inventory.get(BRAKE_SLOT).decrement(1);
                
                markDirty();
            }
        }
    }
    
    private ItemStack createAssembledCart(EngineModuleItem engine, BrakeModuleItem brake) {
        ItemStack cart = new ItemStack(ModItems.ACCELERATED_MINECART);
        NbtCompound nbt = cart.getOrCreateNbt();
        
        nbt.putInt("PowerValue", engine.getPowerValue());
        nbt.putInt("ResistanceValue", brake.getPowerValue());
        nbt.putInt("EngineTier", engine.getModuleTier());
        nbt.putInt("BrakeTier", brake.getModuleTier());
        nbt.putFloat("ControlFactor", brake.getControlFactor());
        
        String displayName = String.format("装配矿车 [动力:%d | 阻力:%d]", 
            engine.getPowerValue(), brake.getPowerValue());
        cart.setCustomName(Text.literal(displayName));
        
        return cart;
    }
    
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }
    
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }
}