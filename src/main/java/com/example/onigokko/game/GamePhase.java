package com.example.onigokko.game;

/**
 * ゲーム全体の進行状態を管理する。
 * WAITING RUNNING PAUSED の3状態を行き来する。
 */

public enum GamePhase {

    /** 待機状態。役職希望・設定変更が可能 */
    WAITING("待機中"),

    /** ゲーム中。タイマーが進行し、確保判定が有効となる。*/
    RUNNING("ゲーム中"),

    /** 一時停止中。タイマー、確保判定が停止する。*/
    PAUSED("一時停止中");

    private final String displayName;

    GamePhase(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
