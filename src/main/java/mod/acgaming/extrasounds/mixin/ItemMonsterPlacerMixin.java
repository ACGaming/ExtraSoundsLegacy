package mod.acgaming.extrasounds.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import mod.acgaming.extrasounds.config.ESConfig;
import mod.acgaming.extrasounds.sound.ESSoundEvents;
import mod.acgaming.extrasounds.sound.ESSoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemMonsterPlacer.class)
public class ItemMonsterPlacerMixin
{
    @Inject(method = "onItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemMonsterPlacer;spawnCreature(Lnet/minecraft/world/World;Lnet/minecraft/util/ResourceLocation;DDD)Lnet/minecraft/entity/Entity;"))
    public void esUseSpawnEgg(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<EnumActionResult> cir)
    {
        if (ESConfig.soundToggles.esUseSpawnEggSound) ESSoundManager.playSoundWorld(player.getEntityWorld(), player, ESSoundEvents.use_spawn_egg, pos);
    }
}