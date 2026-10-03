package com.example.onigokko.game;

import net.minecraft.ChatFormatting;

/**
 * ゲーム開始後の確定役職。
 * 希望（RoleWish）とは別物。
 */

public enum PlayerRole {

    /** 鬼 逃走者を攻撃し確保する。*/
    ONI("ONI", ChatFormatting.RED),

    /** 逃走者 鬼から逃げる。 */
    RUNNER("逃走者", ChatFormatting.BLUE),

    /** 確保済み Spectatorとなってゲームには干渉できない。 */
    CAPTURED("確保済み", ChatFormatting.GRAY),

    /** 観戦者 最初からゲームに参加しない。 */
    OBSERVER("観戦者", ChatFormatting.GREEN);

    private final String displayName;
    private final ChatFormatting color;

    PlayerRole(String displayName, ChatFormatting color){
        this.displayName = displayName;
        this.color = color;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    public ChatFormatting getColor(){
        return this.color;
    }

    /** ゲームに参加しているか（観戦者ではないかの判断） */
    public boolean isParticipant(){
        return this != OBSERVER;
    }

    /** 捕まっていない逃走者か */
    public boolean isActiveRunner(){
        return this == RUNNER;
    }

}
