package hdsxwt.better_minecart.screen;

import hdsxwt.better_minecart.block.entity.AssemblyTableBlockEntity;
import hdsxwt.better_minecart.item.BrakeModuleItem;
import hdsxwt.better_minecart.item.ChassisItem;
import hdsxwt.better_minecart.item.EngineModuleItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AssemblyTableScreenHandler extends ScreenHandler {
    private final AssemblyTableBlockEntity blockEntity;
    private final Inventory inventory;
    
    public AssemblyTableScreenHandler(int syncId, PlayerInventory playerInventory, AssemblyTableBlockEntity blockEntity) {
        super(ModScreenHandlers.ASSEMBLY_TABLE, syncId);
        this.blockEntity = blockEntity;
        this.inventory = new SimpleInventory(4);
        
        // 装配台槽位
        this.addSlot(new Slot(inventory, AssemblyTableBlockEntity.CHASSIS_SLOT, 26, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof ChassisItem;
            }
        });
        
        this.addSlot(new Slot(inventory, AssemblyTableBlockEntity.ENGINE_SLOT, 62, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof EngineModuleItem;
            }
        });
        
        this.addSlot(new Slot(inventory, AssemblyTableBlockEntity.BRAKE_SLOT, 98, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof BrakeModuleItem;
            }
        });
        
        this.addSlot(new Slot(inventory, AssemblyTableBlockEntity.OUTPUT_SLOT, 152, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        
        // 玩家物品栏
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        
        // 玩家快捷栏
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
    
    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        
        if (slot != null && slot.hasStack()) {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();
            
            if (slotIndex == AssemblyTableBlockEntity.OUTPUT_SLOT) {
                if (!this.insertItem(slotStack, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(slotStack, itemStack);
            } else if (slotIndex >= 4 && slotIndex < 40) {
                // 从玩家背包到装配台
                if (slotStack.getItem() instanceof ChassisItem) {
                    if (!this.insertItem(slotStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotStack.getItem() instanceof EngineModuleItem) {
                    if (!this.insertItem(slotStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotStack.getItem() instanceof BrakeModuleItem) {
                    if (!this.insertItem(slotStack, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= 4 && slotIndex < 31) {
                    if (!this.insertItem(slotStack, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotIndex >= 31 && slotIndex < 40 && !this.insertItem(slotStack, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(slotStack, 4, 40, false)) {
                return ItemStack.EMPTY;
            }
            
            if (slotStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        
        return itemStack;
    }
    
    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
    
    @Override
    public void onSlotClick(int slotIndex, int button, net.minecraft.screen.slot.SlotActionType actionType, PlayerEntity player) {
        if (slotIndex == AssemblyTableBlockEntity.OUTPUT_SLOT && 
            actionType == net.minecraft.screen.slot.SlotActionType.PICKUP) {
            
            if (!inventory.getStack(AssemblyTableBlockEntity.CHASSIS_SLOT).isEmpty() &&
                !inventory.getStack(AssemblyTableBlockEntity.ENGINE_SLOT).isEmpty() &&
                !inventory.getStack(AssemblyTableBlockEntity.BRAKE_SLOT).isEmpty() &&
                inventory.getStack(AssemblyTableBlockEntity.OUTPUT_SLOT).isEmpty()) {
                
                blockEntity.assembleCart();
            }
        }
        super.onSlotClick(slotIndex, button, actionType, player);
    }
}