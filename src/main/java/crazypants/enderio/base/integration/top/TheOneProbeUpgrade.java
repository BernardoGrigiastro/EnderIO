package crazypants.enderio.base.integration.top;

import javax.annotation.Nonnull;

import com.enderio.core.common.util.ItemUtil;
import com.enderio.core.common.util.NullHelper;

import crazypants.enderio.base.config.Config;
import crazypants.enderio.base.handler.darksteel.AbstractUpgrade;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.util.Prep;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry.ItemStackHolder;

public class TheOneProbeUpgrade extends AbstractUpgrade {

  private static final @Nonnull String UPGRADE_NAME = "top";
  public static final @Nonnull String PROBETAG = "theoneprobe";

  public static final @Nonnull TheOneProbeUpgrade INSTANCE = new TheOneProbeUpgrade();

  @ItemStackHolder("theoneprobe:probe")
  public static ItemStack probe = null;

  @Override
  public @Nonnull ItemStack getUpgradeItem() {
    return NullHelper.first(probe, Prep.getEmpty());
  }

  public TheOneProbeUpgrade() {
    super(UPGRADE_NAME, "enderio.darksteel.upgrade.top", Prep.getEmpty(), Config.darkSteelTOPCost);
  }

  @Override
  public boolean canAddToItem(@Nonnull ItemStack stack) {
    return isAvailable() && stack.getItem() == ModObject.itemDarkSteelHelmet.getItem() && !hasUpgrade(stack);
  }

  @Override
  public void writeToItem(@Nonnull ItemStack stack) {
    super.writeToItem(stack);
    ItemUtil.getOrCreateNBT(stack).setInteger(PROBETAG, 1);
  }

  public boolean isAvailable() {
    return probe != null && Prep.isValid(probe);
  }

}