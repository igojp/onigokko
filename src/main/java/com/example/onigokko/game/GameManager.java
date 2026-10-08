package com.example.onigokko.game;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Game全体の状態を管理するクラス（脳）
 * サーバーにつき1つだけ存在する（シングルトン）
 */

public class GameManager {

    // 設定の上限・下限
    public static final int MIN_LIMIT_MINUTES = 10;
    public static final int MAX_LIMIT_MINUTES = 60;
    public static final int LIMIT_STEP_MINUTES = 5;
    public static final int MIN_PLAYERS = 3;

    // シングルトン
    private static final GameManager INSTANCE = new GameManager();

    public static GameManager get(){
        return INSTANCE;
    }

    private GameManager() {

    }

    // 状態

    /** 現在の進行状態 */
    private GamePhase phase = GamePhase.WAITING;

    /** 制限時間（分） */
    private int limitMinutes = 15;

    /** 鬼の人数 */
    private int oniCount = 1;

    /** Player UUID -> 希望役職（開始前） */
    private final Map<UUID, RoleWish> wishes = new HashMap<>();

    /** Player UUID -> 確定役職（開始後） */
    private final Map<UUID, PlayerRole> roles = new HashMap<>();

    // 状態の問い合わせ

    public GamePhase getPhase(){
        return this.phase;
    }


    /** 待機中（設定変更や役職選択が可能）か */
    public boolean isWaiting(){
        return this.phase == GamePhase.WAITING;
    }

    /** タイマーが進行し、確保判定が有効な状態か*/
    public boolean isRunning(){
        return this.phase == GamePhase.RUNNING;
    }

    /** ゲームが始まっている（RUNNING or PAUSED）か */
    public boolean isGameActive(){
        return this.phase != GamePhase.WAITING;
    }

    // 希望役職

    public RoleWish getWish(ServerPlayer player) {
        return this.wishes.getOrDefault(player.getUUID(), RoleWish.RUNNER);
    }

    public void setWish(ServerPlayer player, RoleWish wish) {

        this.wishes.put(player.getUUID(), wish);
    }

    /**
     * 希望役職をひとつ進める（鬼→逃走者→観戦者→鬼...）
     * @return 変更後の希望役職
     */
    public RoleWish cycleWish(ServerPlayer player) {
        RoleWish next = getWish(player).next();
        setWish(player, next);
        return next;
    }

    // 確定役職

    public PlayerRole getRole(ServerPlayer player) {
        return this.roles.get(player.getUUID());
    }

    public void setRole(ServerPlayer player, PlayerRole role) {
        this.roles.put(player.getUUID(), role);
    }

    // 設定変更

    public int getLimitMinutes() {
        return this.limitMinutes;
    }

    /**
     * 制限時間を設定する。
     * @return を設定できたらtrue, 不正値の場合は　false（設定は変更されない）
     */
    public boolean setLimitMinutes(int minutes){
        if (minutes < MIN_LIMIT_MINUTES || minutes > MAX_LIMIT_MINUTES) {
            return false;
        }
        if (minutes % LIMIT_STEP_MINUTES != 0) {
            return false;
        }
        this.limitMinutes = minutes;
        return true;
    }

    public int getOniCount() {
        return this.oniCount;
    }

    /**
     * 鬼の人数を設定。
     * 参加者の 1/2 未満でなければならない。
     * @return 設定できたらtrue
     */
    public boolean setOniCount(int count, int participantCount) {
        if (count < 1) {
            return false;
        }
        if (count * 2 >= participantCount) {
            return false;
        }
        this.oniCount = count;
        return true;
    }

    // 参加者数の計算
    /** 観戦希望を除いた参加予定人数 */
    public int countParticipants(MinecraftServer server) {
        int count = 0;
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (getWish(player) != RoleWish.OBSERVER) {
                count++;
            }
        }
        return count;
    }

    /** ある役職のプレイヤー数をカウント */
    public int countRole(MinecraftServer server, PlayerRole role) {
        int count = 0;
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            if (getRole(player) == role) {
                count++;
            }
        }
        return count;
    }
}
