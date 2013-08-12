package crazypants.enderio.conduit.redstone;

import crazypants.enderio.ModObject;
import crazypants.enderio.conduit.*;
import crazypants.enderio.conduit.facade.ItemConduitFacade;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;

public class ItemRedstoneConduit extends AbstractItemConduit {

  private static ItemConduitSubtype[] subtypes = new ItemConduitSubtype[] {
    new ItemConduitSubtype(ModObject.itemRedstoneConduit.unlocalisedName, ModObject.itemRedstoneConduit.name, "enderio:itemRedstoneConduit"),
    new ItemConduitSubtype(ModObject.itemRedstoneConduit.unlocalisedName + "switch", "Conduit Switch", "enderio:itemRedstoneSwitch")
    
  };
  
  public static ItemRedstoneConduit create() {
    ItemRedstoneConduit result = new ItemRedstoneConduit();
    result.init(subtypes);
    return result;
  }

  protected ItemRedstoneConduit() {
    super(ModObject.itemRedstoneConduit);    
  }
  
  @Override
  public Class<? extends IConduit> getBaseConduitType() {
    return IRedstoneConduit.class;
  }

  @Override
  public IConduit createConduit(ItemStack stack) {
    if(stack.getItemDamage() == 1) {
      return new RedstoneSwitch();
    } else {
      return new RedstoneConduit();
    }
  }

}
