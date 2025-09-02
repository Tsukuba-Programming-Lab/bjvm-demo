# --- ステージ1: ビルド環境 ---
# GradleとJDKが含まれるイメージをベースにする
FROM gradle:8.5.0-jdk17 AS build

# 作業ディレクトリを設定
WORKDIR /home/gradle/src

# 最初にビルド定義ファイルのみをコピー
# これにより、依存関係が変更されない限り、レイヤーキャッシュが効く
COPY build.gradle.kts settings.gradle.kts ./
COPY src/main/resources/ ./src/main/resources/

# 依存関係をダウンロード
RUN gradle build --no-daemon --stacktrace -x test

# アプリケーションのソースコードをすべてコピー
COPY . .

# Gradleを使ってアプリケーションをビルド（テストはスキップ）
# --no-daemonオプションはCI/CD環境で推奨
RUN gradle build --no-daemon --stacktrace -x test

# --- ステージ2: 実行環境 ---
# より軽量なJREイメージをベースにする
FROM openjdk:17.0.2-slim

# 作業ディレクトリを設定
WORKDIR /app

# ステージ1でビルドされたJARファイルをコピー
COPY --from=build /home/gradle/src/build/libs/*.jar /app/application.jar

# コンテナがリッスンするポートを指定
EXPOSE 8080

# コンテナ起動時にアプリケーションを実行
ENTRYPOINT ["java", "-jar", "/app/application.jar"]

