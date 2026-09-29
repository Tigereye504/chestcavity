package net.tigereye.chestcavity.registration;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

import com.mojang.brigadier.context.CommandContext;


import net.tigereye.chestcavity.interfaces.ChestCavityEntity;
import net.tigereye.chestcavity.util.ChestCavityUtil;

import java.util.Optional;

public class CCCommands {

    public static void register() {
        net.minecraftforge.event.RegisterCommandsEvent.BUS.addListener(event -> {
            var dispatcher=event.getDispatcher();
            dispatcher.register(Commands.literal("chestcavity")
                .then(Commands.literal("getscores")
                    .executes(CCCommands::getScoresNoArgs)
                    .then(Commands.argument("entity", EntityArgument.entity())
                        .executes(CCCommands::getScores)))
            );
            dispatcher.register(Commands.literal("chestcavity")
                .then(Commands.literal("resetChestCavity").requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .executes(CCCommands::resetChestCavityNoArgs)
                    .then(Commands.argument("entity", EntityArgument.entity())
                        .executes(CCCommands::resetChestCavity)))
            );
        });

    }

    private static int getScoresNoArgs(CommandContext<CommandSourceStack> context) {
        Entity entity;
        try {
            entity = context.getSource().getEntityOrException();
        }
        catch(Exception e){
            context.getSource().sendFailure(Component.literal("getScores failed to get entity"));
            return -1;
        }
        Optional<ChestCavityEntity> optional = ChestCavityEntity.of(entity);
        if(optional.isPresent()){
            ChestCavityUtil.outputOrganScoresString((string) -> {
                context.getSource().sendSuccess(() -> Component.literal(string),false);
            },optional.get().getChestCavityInstance());
            return 1;
        }
        return 0;
    }

    private static int getScores(CommandContext<CommandSourceStack> context) {
        Entity entity;
        try {
            entity = EntityArgument.getEntity(context, "entity");
        }
        catch(Exception e){
            context.getSource().sendFailure(Component.literal("getScores failed to get entity"));
            return -1;
        }
        Optional<ChestCavityEntity> optional = ChestCavityEntity.of(entity);
        if(optional.isPresent()){
            ChestCavityUtil.outputOrganScoresString((string) -> {
                context.getSource().sendSuccess(() -> Component.literal(string),false);
            },optional.get().getChestCavityInstance());
            return 1;
        }
        return 0;
    }

    private static int resetChestCavityNoArgs(CommandContext<CommandSourceStack> context) {
        Entity entity;
        try {
            entity = context.getSource().getEntityOrException();
        }
        catch(Exception e){
            context.getSource().sendFailure(Component.literal("resetChestCavity failed to get entity"));
            return -1;
        }
        Optional<ChestCavityEntity> optional = ChestCavityEntity.of(entity);
        if(optional.isPresent()){
            ChestCavityUtil.generateChestCavityIfOpened(optional.get().getChestCavityInstance());
            return 1;
        }
        return 0;
    }

    private static int resetChestCavity(CommandContext<CommandSourceStack> context) {
        Entity entity;
        try {
            entity = EntityArgument.getEntity(context, "entity");
        }
        catch(Exception e){
            context.getSource().sendFailure(Component.literal("getChestCavity failed to get entity"));
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