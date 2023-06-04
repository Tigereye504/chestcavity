package net.tigereye.chestcavity.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.TextContent;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.tigereye.chestcavity.ChestCavity;
import net.tigereye.chestcavity.chestcavities.instance.ChestCavityInstance;
import net.tigereye.chestcavity.interfaces.CCStatusEffectInstance;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.registration.CCEnchantments;
import net.tigereye.chestcavity.registration.CCOrganScores;
import net.tigereye.chestcavity.registration.CCStatusEffects;

import java.util.*;

public class OrganUtil {

    public static void displayOrganQuality(Map<Identifier,Float> organQualityMap, List<Text> tooltip){
        organQualityMap.forEach((organ,score) -> {
            String tier;
            if(organ.equals(CCOrganScores.HYDROALLERGENIC)){
                if(score >= 2){
                    tier = "quality.chestcavity.severely";
                }
                else{
                    tier = "";
                }
            }
            else {
                if (score >= 1.5f) {
                    tier = "quality.chestcavity.supernatural";
                } else if (score >= 1.25) {
                    tier = "quality.chestcavity.exceptional";
                } else if (score >= 1) {
                    tier = "quality.chestcavity.good";
                } else if (score >= .75f) {
                    tier = "quality.chestcavity.average";
                } else if (score >= .5f) {
                    tier = "quality.chestcavity.poor";
                } else if (score >= 0) {
                    tier = "quality.chestcavity.pathetic";
                } else if (score >= -.25f) {
                    tier = "quality.chestcavity.slightly_reduces";
                } else if (score >= -.5f) {
                    tier = "quality.chestcavity.reduces";
                } else if (score >= -.75f) {
                    tier = "quality.chestcavity.greatly_reduces";
                } else {
                    tier = "quality.chestcavity.greatly_reduces";
                }
            }
            Text text = Text.translatable("organscore." + organ.getNamespace() + "." + organ.getPath(), Text.translatable(tier));
            tooltip.add(text);
        });
    }

    @Environment(EnvType.CLIENT)
    public static void displayCompatibility(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        NbtCompound tag = itemStack.getNbt();
        String textString;
        boolean uuidMatch = false;
        int compatLevel = 0;
        PlayerEntity serverPlayer = null;
        net.minecraft.server.MinecraftServer server = world.getServer();
        if(server == null) {
            server = MinecraftClient.getInstance().getServer();
        }
        if(server != null) {
            serverPlayer = server.getPlayerManager().getPlayer(MinecraftClient.getInstance().player.getEntityName());
            if(serverPlayer instanceof ChestCavityEntity ccPlayer){
                UUID ccID = ccPlayer.getChestCavityInstance().compatibility_id;
                //tooltip.add(Text.literal("ServerPlayerCC: "+ccID));
                compatLevel = ChestCavityUtil.getCompatibilityLevel(ccPlayer.getChestCavityInstance(),itemStack);
            }
        }
        else{
            compatLevel = -1;
        }

        if(EnchantmentHelper.getLevel(CCEnchantments.MALPRACTICE,itemStack) > 0){
            textString = "Unsafe to use";
        }
        else if (tag != null && tag.contains(ChestCavity.COMPATIBILITY_TAG.toString())
                && EnchantmentHelper.getLevel(CCEnchantments.O_NEGATIVE,itemStack) <= 0) {
            tag = tag.getCompound(ChestCavity.COMPATIBILITY_TAG.toString());
            String name = tag.getString("name");
            //tooltip.add(Text.literal("OrganOwnerCC: "+tag.getUuid("owner")));
            textString = "Only Compatible With: "+name;
        }
        else{
            textString = "Safe to Use";
        }

        MutableText text = MutableText.of(TextContent.EMPTY);
        if(compatLevel > 0) {
            text.formatted(Formatting.GREEN);
        }
        else if(compatLevel == 0){
            text.formatted(Formatting.RED);
        }
        else{
            text.formatted(Formatting.YELLOW);
        }
        text.append(textString);
        tooltip.add(text);
    }

    public static void explode(LivingEntity entity, float explosionYield) {
        if (!entity.world.isClient) {
            Explosion.DestructionType destructionType = entity.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            entity.world.createExplosion(null, entity.getX(), entity.getY(), entity.getZ(), (float)Math.sqrt(explosionYield), destructionType);
            spawnEffectsCloud(entity);
        }

    }

    public static List<StatusEffectInstance> getStatusEffects(ItemStack organ){
        NbtCompound tag = organ.getOrCreateNbt();
        NbtList NbtList;
        if (!tag.contains("CustomPotionEffects", 9)) {
            return new ArrayList<>();
        }
        else{
            NbtList = tag.getList("CustomPotionEffects",10);
            List<StatusEffectInstance> list = new ArrayList<>();
            for(int i = 0; i < NbtList.size(); ++i) {
                NbtCompound NbtCompound = NbtList.getCompound(i);
                StatusEffectInstance statusEffectInstance = StatusEffectInstance.fromNbt(NbtCompound);
                if (statusEffectInstance != null) {
                    list.add(statusEffectInstance);
                }
            }
            return list;
        }
    }

    public static void milkSilk(LivingEntity entity){
        if(!entity.hasStatusEffect(CCStatusEffects.SILK_COOLDOWN)){
            ChestCavityEntity.of(entity).ifPresent(cce -> {
                if(cce.getChestCavityInstance().opened){
                    ChestCavityInstance cc = cce.getChestCavityInstance();
                    float silk = cc.getOrganScore(CCOrganScores.SILK);
                    if(silk > 0){
                        if(spinWeb(entity,cc,silk)) {
                            entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.SILK_COOLDOWN, ChestCavity.config.SILK_COOLDOWN,0,false,false,true));
                        }
                    }
                }
            });
        }
    }

    public static void queueDragonBombs(LivingEntity entity, ChestCavityInstance cc, int bombs){
        if(entity instanceof PlayerEntity){
            ((PlayerEntity)entity).addExhaustion(bombs*.6f);
        }
        for(int i = 0; i < bombs;i++){
            cc.projectileQueue.add(OrganUtil::spawnDragonBomb);
        }
        entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.DRAGON_BOMB_COOLDOWN, ChestCavity.config.DRAGON_BOMB_COOLDOWN, 0, false, false, true));
    }

    public static void queueForcefulSpit(LivingEntity entity, ChestCavityInstance cc, int projectiles){
        if(entity instanceof PlayerEntity){
            ((PlayerEntity)entity).addExhaustion(projectiles*.1f);
        }
        for(int i = 0; i < projectiles;i++){
            cc.projectileQueue.add(OrganUtil::spawnSpit);
        }
        entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.FORCEFUL_SPIT_COOLDOWN, ChestCavity.config.FORCEFUL_SPIT_COOLDOWN, 0, false, false, true));
    }

    public static void queueGhastlyFireballs(LivingEntity entity, ChestCavityInstance cc, int ghastly){
        if(entity instanceof PlayerEntity){
            ((PlayerEntity)entity).addExhaustion(ghastly*.3f);
        }
        for(int i = 0; i < ghastly;i++){
            cc.projectileQueue.add(OrganUtil::spawnGhastlyFireball);
        }
        entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.GHASTLY_COOLDOWN, ChestCavity.config.GHASTLY_COOLDOWN, 0, false, false, true));
    }

    public static void queuePyromancyFireballs(LivingEntity entity, ChestCavityInstance cc, int pyromancy){
        if(entity instanceof PlayerEntity){
            ((PlayerEntity)entity).addExhaustion(pyromancy*.1f);
        }
        for(int i = 0; i < pyromancy;i++){
            cc.projectileQueue.add(OrganUtil::spawnPyromancyFireball);
        }
        entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.PYROMANCY_COOLDOWN, ChestCavity.config.PYROMANCY_COOLDOWN, 0, false, false, true));
    }

    public static void queueShulkerBullets(LivingEntity entity, ChestCavityInstance cc, int shulkerBullets){
        if(entity instanceof PlayerEntity){
            ((PlayerEntity)entity).addExhaustion(shulkerBullets*.3f);
        }
        for(int i = 0; i < shulkerBullets;i++){
            cc.projectileQueue.add(OrganUtil::spawnShulkerBullet);
        }
        entity.addStatusEffect(new StatusEffectInstance(CCStatusEffects.SHULKER_BULLET_COOLDOWN, ChestCavity.config.SHULKER_BULLET_COOLDOWN, 0, false, false, true));
    }

    public static void setStatusEffects(ItemStack organ, ItemStack potion){
        List<StatusEffectInstance> potionList = PotionUtil.getPotionEffects(potion);
        List<StatusEffectInstance> list = new ArrayList<>();
        for (StatusEffectInstance effect : potionList) {
            StatusEffectInstance effectCopy = new StatusEffectInstance(effect);
            ((CCStatusEffectInstance) effectCopy).CC_setDuration(Math.max(1,effectCopy.getDuration()/4));
            list.add(effectCopy);
        }
        setStatusEffects(organ,list);
    }

    public static void setStatusEffects(ItemStack organ, List<StatusEffectInstance> list){
        NbtCompound tag = organ.getOrCreateNbt();
        NbtList NbtList = new NbtList();

        for(int i = 0; i < list.size(); ++i) {
            StatusEffectInstance effect = list.get(i);
            if (effect != null) {
                NbtCompound NbtCompound = new NbtCompound();
                NbtList.add(effect.writeNbt(NbtCompound));
            }
        }
        tag.put("CustomPotionEffects",NbtList);
    }

    public static void shearSilk(LivingEntity entity){
        ChestCavityEntity.of(entity).ifPresent(cce -> {
            if(cce.getChestCavityInstance().opened){
                float silk = cce.getChestCavityInstance().getOrganScore(CCOrganScores.SILK);

                if(silk > 0){
                    if(silk >= 2){
                        ItemStack stack = new ItemStack(Items.COBWEB,((int)silk)/2);
                        ItemEntity itemEntity = new ItemEntity(entity.world, entity.getX(), entity.getY(), entity.getZ(), stack);
                        entity.world.spawnEntity(itemEntity);
                    }
                    if(silk % 2 >= 1){
                        ItemStack stack = new ItemStack(Items.STRING);
                        ItemEntity itemEntity = new ItemEntity(entity.world, entity.getX(), entity.getY(), entity.getZ(), stack);
                        entity.world.spawnEntity(itemEntity);
                    }
                }
            }
        });
    }

    public static void spawnEffectsCloud(LivingEntity entity) {
        Collection<StatusEffectInstance> collection = entity.getStatusEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(entity.world, entity.getX(), entity.getY(), entity.getZ());
            areaEffectCloudEntity.setRadius(2.5F);
            areaEffectCloudEntity.setRadiusOnUse(-0.5F);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
            Iterator<StatusEffectInstance> var3 = collection.iterator();

            while(var3.hasNext()) {
                StatusEffectInstance statusEffectInstance = var3.next();
                areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
            }

            entity.world.spawnEntity(areaEffectCloudEntity);
        }

    }

    public static void spawnSilk(LivingEntity entity){
        entity.dropItem(Items.STRING);
    }

    public static void spawnSpit(LivingEntity entity){
        Vec3d entityFacing = entity.getRotationVector().normalize();

        LlamaEntity fakeLlama = new LlamaEntity(EntityType.LLAMA,entity.world);
        fakeLlama.setPos(entity.getX(),entity.getY(),entity.getZ());
        fakeLlama.setPitch(entity.getPitch());
        fakeLlama.setYaw(entity.getYaw());
        fakeLlama.bodyYaw = entity.bodyYaw;
        LlamaSpitEntity llamaSpitEntity = new LlamaSpitEntity(entity.world, fakeLlama);
        llamaSpitEntity.setOwner(entity);
        llamaSpitEntity.setVelocity(entityFacing.x*2,entityFacing.y*2,entityFacing.z*2);
        entity.world.spawnEntity(llamaSpitEntity);
        entityFacing = entityFacing.multiply(-.1D);
        entity.addVelocity(entityFacing.x,entityFacing.y,entityFacing.z);
    }

    public static void spawnDragonBomb(LivingEntity entity){
        Vec3d entityFacing = entity.getRotationVector().normalize();
        DragonFireballEntity fireballEntity = new DragonFireballEntity(entity.world, entity, entityFacing.x, entityFacing.y, entityFacing.z);
        fireballEntity.updatePosition(fireballEntity.getX(), entity.getBodyY(0.5D) + 0.3D, fireballEntity.getZ());
        entity.world.spawnEntity(fireballEntity);
        entityFacing = entityFacing.multiply(-0.2D);
        entity.addVelocity(entityFacing.x,entityFacing.y,entityFacing.z);
    }

    public static void spawnDragonBreath(LivingEntity entity){
        Optional<ChestCavityEntity> optional = ChestCavityEntity.of(entity);
        if(optional.isEmpty()){
            return;
        }
        ChestCavityEntity cce = optional.get();
        ChestCavityInstance cc= cce.getChestCavityInstance();
        float breath = cc.getOrganScore(CCOrganScores.DRAGON_BREATH);
        double range = Math.sqrt(breath/2)*5;
        HitResult result = entity.raycast(range, 0, false);
        Vec3d pos = result.getPos();
        double x = pos.x;
        double y = pos.y;
        double z = pos.z;
        BlockPos.Mutable mutable = new BlockPos.Mutable(x,y,z);
        while(entity.world.isAir(mutable)) {
            --y;
            if (y < 0.0D) {
                return;
            }

            mutable.set(x,y,z);
        }
        y = (MathHelper.floor(y) + 1);
        AreaEffectCloudEntity breathEntity = new AreaEffectCloudEntity(entity.world, x, y, z);
        breathEntity.setOwner(entity);
        breathEntity.setRadius((float)Math.max(range/2,Math.min(range, MathUtil.horizontalDistanceTo(breathEntity,entity))));
        breathEntity.setDuration(200);
        breathEntity.setParticleType(ParticleTypes.DRAGON_BREATH);
        breathEntity.addEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE));
        entity.world.spawnEntity(breathEntity);

    }

    public static void spawnGhastlyFireball(LivingEntity entity){
        Vec3d entityFacing = entity.getRotationVector().normalize();
        FireballEntity fireballEntity = new FireballEntity(entity.world, entity, entityFacing.x, entityFacing.y, entityFacing.z, 1);
        fireballEntity.updatePosition(fireballEntity.getX(), entity.getBodyY(0.5D) + 0.3D, fireballEntity.getZ());
        entity.world.spawnEntity(fireballEntity);
        entityFacing = entityFacing.multiply(-.8D);
        entity.addVelocity(entityFacing.x,entityFacing.y,entityFacing.z);
    }

    public static void spawnPyromancyFireball(LivingEntity entity){
        Vec3d entityFacing = entity.getRotationVector().normalize();
        SmallFireballEntity smallFireballEntity = new SmallFireballEntity(entity.world, entity, entityFacing.x + entity.getRandom().nextGaussian() * .1, entityFacing.y, entityFacing.z + entity.getRandom().nextGaussian() * .1);
        smallFireballEntity.updatePosition(smallFireballEntity.getX(), entity.getBodyY(0.5D) + 0.3D, smallFireballEntity.getZ());
        entity.world.spawnEntity(smallFireballEntity);
        entityFacing = entityFacing.multiply(-.2D);
        entity.addVelocity(entityFacing.x,entityFacing.y,entityFacing.z);
    }

    public static void spawnShulkerBullet(LivingEntity entity){
        //Vec3d entityFacing = entity.getRotationVector().normalize();
        TargetPredicate targetPredicate = TargetPredicate.createAttackable();
        targetPredicate.setBaseMaxDistance(ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE*2);
        LivingEntity target = entity.world.getClosestEntity(LivingEntity.class,
                targetPredicate, entity, entity.getX(), entity.getY(),entity.getZ(),
                new Box(entity.getX()-ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE,entity.getY()-ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE,entity.getZ()-ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE,
                        entity.getX()+ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE,entity.getY()+ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE,entity.getZ()+ChestCavity.config.SHULKER_BULLET_TARGETING_RANGE));
        if(target == null){
            return;
        }
        ShulkerBulletEntity shulkerBulletEntity = new ShulkerBulletEntity(entity.world,entity,target, Direction.Axis.Y);
        shulkerBulletEntity.updatePosition(shulkerBulletEntity.getX(), entity.getBodyY(0.5D) + 0.3D, shulkerBulletEntity.getZ());
        entity.world.spawnEntity(shulkerBulletEntity);
        //entityFacing = entityFacing.multiply(-.4D);
        //entity.addVelocity(entityFacing.x,entityFacing.y,entityFacing.z);
    }

    public static boolean spinWeb(LivingEntity entity, ChestCavityInstance cc, float silkScore){
        int hungerCost = 0;
        PlayerEntity player = null;
        if(entity instanceof PlayerEntity){
            player = (PlayerEntity)entity;
            if(player.getHungerManager().getFoodLevel() < 6){
                return false;
            }
        }

        if(silkScore >= 2) {
            BlockPos pos = entity.getBlockPos().offset(entity.getHorizontalFacing().getOpposite());
            if(entity.getEntityWorld().getBlockState(pos).isAir()){
                if(silkScore >= 3) {
                    hungerCost = 16;
                    silkScore -= 3;
                    entity.getEntityWorld().setBlockState(pos, Blocks.WHITE_WOOL.getDefaultState(), 2);
                }
                else{
                    hungerCost = 8;
                    silkScore -= 2;
                    entity.getEntityWorld().setBlockState(pos, Blocks.COBWEB.getDefaultState(), 2);
                }
            }
        }
        while(silkScore >= 1) {
            silkScore--;
            hungerCost += 4;
            cc.projectileQueue.add(OrganUtil::spawnSilk);
        }
        if(player != null){
            player.getHungerManager().addExhaustion(hungerCost);
        }
        return hungerCost > 0;
    }

    public static boolean teleportRandomly(LivingEntity entity, float range) {
        if (!entity.world.isClient() && entity.isAlive()) {
            for(int i = 0; i < ChestCavity.config.MAX_TELEPORT_ATTEMPTS; i++) {
                double d = entity.getX() + ((entity.getRandom().nextDouble() - 0.5D) * range);
                double e = Math.max(1, entity.getY() + ((entity.getRandom().nextDouble() - 0.5D) * range));
                double f = entity.getZ() + ((entity.getRandom().nextDouble() - 0.5D) * range);
                if(teleportTo(entity, d, e, f)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean teleportTo(LivingEntity entity, double x, double y, double z) {
        if(entity.hasVehicle()){
            entity.stopRiding();
        }
        BlockPos.Mutable targetPos = new BlockPos.Mutable(x, y, z);
        BlockState blockState = entity.world.getBlockState(targetPos);
        while(targetPos.getY() > 0 && !(blockState.getMaterial().blocksMovement()
                || blockState.getMaterial().isLiquid()))
        {
            targetPos.move(Direction.DOWN);
            blockState = entity.world.getBlockState(targetPos);
        }
        if(targetPos.getY() <= 0){
            return false;
        }

        targetPos.move(Direction.UP);
        blockState = entity.world.getBlockState(targetPos);
        BlockState blockState2 = entity.world.getBlockState(targetPos.up());
        while(blockState.getMaterial().blocksMovement()
                || blockState.getMaterial().isLiquid()
                || blockState2.getMaterial().blocksMovement()
                || blockState2.getMaterial().isLiquid()){
            targetPos.move(Direction.UP);
            blockState = entity.world.getBlockState(targetPos);
            blockState2 = entity.world.getBlockState(targetPos.up());
        }

        if(entity.world.getDimension().hasCeiling() && targetPos.getY() >= entity.world.getHeight()){
            return false;
        }
        entity.teleport(x, targetPos.getY()+.1, z);
        if (!entity.isSilent()) {
            entity.world.playSound(null, entity.prevX, entity.prevY, entity.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, entity.getSoundCategory(), 1.0F, 1.0F);
            entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
        }

        return true;
    }
}
