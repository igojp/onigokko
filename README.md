# 基本鬼ごっこMOD

Minecraft 1.20.1 / Forge 47.x 用の鬼ごっこMODです。

## 遊び方

1. 「役職選択の杖」を右クリックして、鬼 / 逃走者 / 観戦者 の希望を選ぶ。
2. GMが「魔法の時計」または `/oni time` で制限時間を設定
3. GMが `/oni setoni <人数>` で鬼の人数を設定
4. `/oni start` でゲームを開始。
5. 鬼が逃走者を殴ると確保。全員確保で鬼が勝利、時間切れで逃走者の勝利。

## コマンド(OP限定)

| コマンド | 内容 |
|---|---|
| `/oni start` | ゲーム開始 |
| `/oni stop` | 一時停止・再開 |
| `/oni reset` | ゲームを中断してリセット |
| `/oni time <分>` | 制限時間（10〜60分、5分刻み） |
| `/oni setoni <人数>` | 鬼の人数（参加者の半分未満） |
| `/oni status` | 現在の状態を表示 |

## 動作環境

- Minecraft Java Edition 1.20.1
- Forge 47.x
- 最低3人（観戦者を除く）

## ビルド方法

JDK17が必要です。
    
    ./gradlew build
`build/libs/`にjarが生成されます。