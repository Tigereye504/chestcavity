package net.tigereye.chestcavity.registration;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.Optional;

public class CCCommands {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("chestcavity")
                .then(CommandManager.literal("getscores")
                    .executes(CCCommands::getScoresNoArgs)
                    .then(CommandManager.argument("entity", EntityArgumentType.entity())
                        .executes(CCCommands::getScores)))
            );
            dispatcher.register(CommandManager.literal("chestcavity")
                .then(CommandManager.literal("resetChestCavity").requires(source -> source.hasPermissionLevel(2))
                    .executes(CCCommands::resetChestCavityNoArgs)
                    .then(CommandManager.argument("entity", EntityArgumentType.entity())
                        .executes(CCCommands::resetChestCavity)))
            );
        });

    }

    private static int getScoresNoArgs(CommandContext<ServerCommandSource> context) {
        Entity entity;
        try {
            entity = context.getSource().getEntityOrThrow();
        }
        catch(Exception e){
            context.getSource().sendError(new LiteralText("getScores failed to get entity"));
            return -1;
        }
        Optional<ChestCavityEntity> optional = ChestCavityEntity.of(entity);
        if(optional.isPresent()){
            ChestCavityUtil.outputOrganScoresString((string) -> {
                context.getSource().sendFeedback(new LiteralText(string),false);
            },optional.get().getChestCavityInstance());
            return 1;
        }
        return 0;
    }

    private static int getScores(CommandContext<ServerCommandSource> context) {
        Entity entity;
        try {
            entity = EntityArgumentType.getEntity(context, "entity");
        }
        catch(Exception e){
            context.getSource().sendError(new LiteralText("getScores failed to get entity"));
            return -1;
        }
        Optional<ChestCavityEntity> optional = ChestCavityEntity.of(entity);
        if(optional.isPresent()){
            ChestCavityUtil.outputOrganScoresString((string) -> {
                context.getSource().sendFeedback(new LiteralText(string),false);
            },optional.get().getChestCavityInstance());
            return 1;
        }
        return 0;
    }

    private static int resetChestCavityNoArgs(CommandContext<ServerCommandSource> context) {
        Entity entity;
        try {
            entity = context.getSource().getEntityOrThrow();
        }
        catch(Exception e){
            context.getSource().sendError(new LiteralText("resetChestCavity failed to get entity"));
            return -1;
        }
        Optional<ChestCavityEntity> optional = ChestCavityEntity.of(entity);
        if(optional.isPresent()){
            ChestCavityUtil.generateChestCavityIfOpened(optional.get().getChestCavityInstance());
            return 1;
        }
        return 0;
    }

    private static int resetChestCavity(CommandContext<ServerCommandSource> context) {
        Entity entity;
        try {
            entity = EntityArgumentType.getEntity(context, "entity");
        }
        catch(Exception e){
            context.getSource().sendError(new LiteralText("getChestCavity failed to get entity"));
            return -1;
        }
        Optional<ChestCavityEntity> optional = ChestCavityEntity.of(entity);
        if(optional.isPresent()){
            ChestCavityUtil.generateChestCavityIfOpened(optional.get().getChestCavityInstance());
            return 1;
        }
        return 0;
    }

}