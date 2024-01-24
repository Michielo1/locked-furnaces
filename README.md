# locked-furnaces
Simply adds the ability to restrict furnances from smelting anything.

This is a Work-In-Progress plugin.
Todo:
- Add stripped wood & stripped logs
- Add `Allow_bypass` functionality from config


# Configuration
The configuration is pretty straight-forward. When starting the server with this plugin enabled, you will get the follow config.yml:
```
##################
# LockedFurnaces #
##################

# Do you need help setting up the config? Check the wiki at: [SOON]

# In all furnaces, which items should be unable to be smolten unless unlocked?
# Please note you can use also use special notations to for example lock all logs :)
Default_locked: ["example", "%example"]


Allow_bypass: True
```

In `Default_locked` you can add as many items from the list down below that will be blocked by default. As we for example have many types of logs, you can also add "%log" which will block any items from the list below with the word `log` in it.


# Items that can be locked or unlocked
I used [this](https://minecraft.fandom.com/wiki/Smelting) source to get all these items.

| Category | Item |
| --- | --- |
| Food | Potato |
| Food | Kelp |
| Food | Beef |
| Food | Porkchop |
| Food | Mutton |
| Food | Chicken |
| Food | Rabbit |
| Food | Cod |
| Food | Salmon |
| Ores | Raw_iron |
| Ores | Raw_copper |
| Ores | Raw_gold |
| Ores | Nether_gold_ore |
| Ores | Ancient_debris |
| Ores | Redstone_ore |
| Ores | Coal_ore |
| Ores | Lapis_lazuli |
| Ores | Diamond_ore |
| ores | Nether_quartz_ore |
| Ores | Iron_ore |
| Ores | Copper_ore |
| Ores | Gold_ore |
| Iron | Iron_shovel |
| Iron | Iron_pickaxe |
| Iron | Iron_axe |
| Iron | Iron_hoe |
| Iron | Iron_sword |
| Iron | Chainmail_helmet |
| Iron | Chainmail_chestplate |
| Iron | Chainmail_leggings |
| Iron | Chainmail_boots |
| Iron | Iron_helmet |
| Iron | Iron_chestplate |
| Iron | Iron_leggings |
| Iron | Iron_boots |
| Iron | Iron_horse_armor |
| Gold | Golden_shovel |
| Gold | Golden_pickaxe |
| Gold | Golden_golden_axe |
| Gold | Golden_hoe |
| Gold | Golden_sword |
| Gold | Golden_helmet |
| Gold | Golden_chestplate |
| Gold | Golden_leggings |
| Gold | Golden_boots |
| Gold | Golden_horse_armor |
| Stone | Cobblestone |
| Stone | Stone |
| Stone | Stone_bricks |
| Stone | Cobbled_deepslate |
| Stone | Deepslate_bricks |
| Stone | Deepslate_tiles |
| Stone | Sandstone |
| Stone | Red_sandstone |
| Stone | Nether_brick |
| Stone | Basalt |
| Stone | Polished_blackstone_bricks |
| Blocks | Quartz_block |
| Blocks | Terracotta |
| Blocks | **color**_terracotta |
| Blocks | Sand |
| Blocks | Red_sand |
| Blocks | Wet_sponge |
| Blocks | Clay |
| Blocks | Cactus |
| Blocks | Netherrack |
| Items | Chorus_fruit |
| Items | Sea_pickle |
| Items | Clay_ball |
| Wood | **type**_log |
| Wood | **type**_wood |

