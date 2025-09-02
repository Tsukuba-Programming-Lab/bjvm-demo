# Node.jsの軽量イメージをベースにする
FROM node:20-alpine

# 作業ディレクトリを設定
WORKDIR /app

# 最初にpackage.jsonとロックファイルをコピー
COPY package*.json ./

# 依存関係をインストール
# CI=true とすることで、warningをerrorとして扱わず、インタラクティブなプロンプトを無効化
ENV CI=true
RUN npm install

# アプリケーションのソースコードをコピー
# （docker-composeでボリュームマウントするため、主にイメージ単体での利用を想定）
COPY . .

# Viteの開発サーバーが使用するポートを公開
EXPOSE 5173

# コンテナ起動時にViteの開発サーバーを実行
# --host オプションにより、コンテナの外部からアクセスできるようになる
CMD ["npm", "run", "dev", "--", "--host"]
