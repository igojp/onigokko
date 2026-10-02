package com.example.onigokko.game;

import net.minecraft.ChatFormatting;

/**
 * ゲーム開始前にプレイヤーが杖で選ぶ「希望役職」。
 * 最終的な役職はゲーム開始時に決定させる。
 */

public enum RoleWish {

    /** 列挙定数*/
    ONI("鬼", ChatFormatting.RED, "oni"),
    RUNNER("逃走者", ChatFormatting.BLUE, "nigeru"),
    OBSERVER("観戦者", ChatFormatting.GREEN, "observer");


    /** フィールド*/
    private final String displayName;
    private final ChatFormatting color;
    private final String teamName;


    /** コンストラクタ */
    RoleWish(String displayName, ChatFormatting color, String teamName){
        this.displayName = displayName;
        this.color = color;
        this.teamName = teamName;
    }


    /** メソッド*/
    public String getDisplayName() {
        return this.displayName;
    }

    public ChatFormatting getColor(){
        return this.color;
    }

    public String getTeamName(){
        return this.teamName;
    }

    /**
     * 希望役職の切り替え。
     * 鬼 → 逃走者 → 観戦者 → 鬼 ... と循環する。
     */
    public RoleWish next(){
        RoleWish[] all = values();
        return all[(this.ordinal() + 1) % all.length];
    }
}
