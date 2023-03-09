package mod.acgaming.extrasounds.sound.client;

import java.util.Map;

import com.cleanroommc.assetmover.AssetMoverAPI;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import mod.acgaming.extrasounds.ExtraSounds;

public class ESSoundEventsClient
{
    public static void getBackportedSounds()
    {
        Map<String, String> backportMap = new Object2ObjectOpenHashMap<>();

        // TYPING
        backportMap.put("assets/minecraft/sounds/block/bamboo/step1.ogg", "assets/extrasounds/sounds/block/bamboo/step1.ogg");
        backportMap.put("assets/minecraft/sounds/block/bamboo/step2.ogg", "assets/extrasounds/sounds/block/bamboo/step2.ogg");
        backportMap.put("assets/minecraft/sounds/block/bamboo/step3.ogg", "assets/extrasounds/sounds/block/bamboo/step3.ogg");
        backportMap.put("assets/minecraft/sounds/block/bamboo/step4.ogg", "assets/extrasounds/sounds/block/bamboo/step4.ogg");
        backportMap.put("assets/minecraft/sounds/block/bamboo/step5.ogg", "assets/extrasounds/sounds/block/bamboo/step5.ogg");
        backportMap.put("assets/minecraft/sounds/block/bamboo/step6.ogg", "assets/extrasounds/sounds/block/bamboo/step6.ogg");

        // PLANT CROP
        backportMap.put("assets/minecraft/sounds/item/plant/crop1.ogg", "assets/extrasounds/sounds/item/plant/crop1.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/crop2.ogg", "assets/extrasounds/sounds/item/plant/crop2.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/crop3.ogg", "assets/extrasounds/sounds/item/plant/crop3.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/crop4.ogg", "assets/extrasounds/sounds/item/plant/crop4.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/crop5.ogg", "assets/extrasounds/sounds/item/plant/crop5.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/crop6.ogg", "assets/extrasounds/sounds/item/plant/crop6.ogg");

        // PLANT NETHERWART
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart1.ogg", "assets/extrasounds/sounds/item/plant/netherwart1.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart2.ogg", "assets/extrasounds/sounds/item/plant/netherwart2.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart3.ogg", "assets/extrasounds/sounds/item/plant/netherwart3.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart4.ogg", "assets/extrasounds/sounds/item/plant/netherwart4.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart5.ogg", "assets/extrasounds/sounds/item/plant/netherwart5.ogg");
        backportMap.put("assets/minecraft/sounds/item/plant/netherwart6.ogg", "assets/extrasounds/sounds/item/plant/netherwart6.ogg");

        // BEACON
        backportMap.put("assets/minecraft/sounds/block/beacon/ambient.ogg", "assets/extrasounds/sounds/block/beacon/ambient.ogg");
        backportMap.put("assets/minecraft/sounds/block/beacon/activate.ogg", "assets/extrasounds/sounds/block/beacon/activate.ogg");
        backportMap.put("assets/minecraft/sounds/block/beacon/deactivate.ogg", "assets/extrasounds/sounds/block/beacon/deactivate.ogg");
        backportMap.put("assets/minecraft/sounds/block/beacon/power1.ogg", "assets/extrasounds/sounds/block/beacon/power1.ogg");
        backportMap.put("assets/minecraft/sounds/block/beacon/power2.ogg", "assets/extrasounds/sounds/block/beacon/power2.ogg");
        backportMap.put("assets/minecraft/sounds/block/beacon/power3.ogg", "assets/extrasounds/sounds/block/beacon/power3.ogg");

        // POTION EFFECTS
        backportMap.put("assets/minecraft/sounds/block/conduit/short1.ogg", "assets/extrasounds/sounds/block/conduit/short1.ogg");
        backportMap.put("assets/minecraft/sounds/block/conduit/short2.ogg", "assets/extrasounds/sounds/block/conduit/short2.ogg");
        backportMap.put("assets/minecraft/sounds/block/conduit/short3.ogg", "assets/extrasounds/sounds/block/conduit/short3.ogg");
        backportMap.put("assets/minecraft/sounds/block/conduit/short4.ogg", "assets/extrasounds/sounds/block/conduit/short4.ogg");
        backportMap.put("assets/minecraft/sounds/block/conduit/short5.ogg", "assets/extrasounds/sounds/block/conduit/short5.ogg");
        backportMap.put("assets/minecraft/sounds/block/conduit/short6.ogg", "assets/extrasounds/sounds/block/conduit/short6.ogg");
        backportMap.put("assets/minecraft/sounds/block/conduit/short7.ogg", "assets/extrasounds/sounds/block/conduit/short7.ogg");
        backportMap.put("assets/minecraft/sounds/block/conduit/short8.ogg", "assets/extrasounds/sounds/block/conduit/short8.ogg");
        backportMap.put("assets/minecraft/sounds/block/conduit/short9.ogg", "assets/extrasounds/sounds/block/conduit/short9.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed1.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed1.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed2.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed2.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed3.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed3.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed4.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed4.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed5.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed5.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed6.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed6.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed7.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed7.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed8.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed8.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed9.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed9.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed10.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed10.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed11.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed11.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed12.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed12.ogg");
        backportMap.put("assets/minecraft/sounds/enchant/soulspeed/soulspeed13.ogg", "assets/extrasounds/sounds/enchant/soulspeed/soulspeed13.ogg");

        // METAL
        backportMap.put("assets/minecraft/sounds/block/copper/step1.ogg", "assets/extrasounds/sounds/block/copper/step1.ogg");
        backportMap.put("assets/minecraft/sounds/block/copper/step2.ogg", "assets/extrasounds/sounds/block/copper/step2.ogg");
        backportMap.put("assets/minecraft/sounds/block/copper/step3.ogg", "assets/extrasounds/sounds/block/copper/step3.ogg");
        backportMap.put("assets/minecraft/sounds/block/copper/step4.ogg", "assets/extrasounds/sounds/block/copper/step4.ogg");
        backportMap.put("assets/minecraft/sounds/block/copper/step5.ogg", "assets/extrasounds/sounds/block/copper/step5.ogg");
        backportMap.put("assets/minecraft/sounds/block/copper/step6.ogg", "assets/extrasounds/sounds/block/copper/step6.ogg");

        // GEM
        backportMap.put("assets/minecraft/sounds/block/amethyst/place1.ogg", "assets/extrasounds/sounds/block/amethyst/place1.ogg");
        backportMap.put("assets/minecraft/sounds/block/amethyst/place2.ogg", "assets/extrasounds/sounds/block/amethyst/place2.ogg");
        backportMap.put("assets/minecraft/sounds/block/amethyst/place3.ogg", "assets/extrasounds/sounds/block/amethyst/place3.ogg");
        backportMap.put("assets/minecraft/sounds/block/amethyst/place4.ogg", "assets/extrasounds/sounds/block/amethyst/place4.ogg");

        // BONEMEAL
        backportMap.put("assets/minecraft/sounds/item/bonemeal/bonemeal1.ogg", "assets/extrasounds/sounds/item/bonemeal/bonemeal1.ogg");
        backportMap.put("assets/minecraft/sounds/item/bonemeal/bonemeal2.ogg", "assets/extrasounds/sounds/item/bonemeal/bonemeal2.ogg");
        backportMap.put("assets/minecraft/sounds/item/bonemeal/bonemeal3.ogg", "assets/extrasounds/sounds/item/bonemeal/bonemeal3.ogg");
        backportMap.put("assets/minecraft/sounds/item/bonemeal/bonemeal4.ogg", "assets/extrasounds/sounds/item/bonemeal/bonemeal4.ogg");
        backportMap.put("assets/minecraft/sounds/item/bonemeal/bonemeal5.ogg", "assets/extrasounds/sounds/item/bonemeal/bonemeal5.ogg");

        ExtraSounds.LOGGER.info("Downloading Minecraft 1.19.3 sounds, this could take a while...");
        AssetMoverAPI.fromMinecraft("1.19.3", backportMap);
        ExtraSounds.LOGGER.info("Downloading finished!");
    }
}