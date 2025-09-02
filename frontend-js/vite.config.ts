import { defineConfig, searchForWorkspaceRoot } from 'vite'
import wasm from "vite-plugin-wasm";

export default defineConfig({
    plugins: [wasm()],
    // To disable cacheing .wasm files in dev mode.
    optimizeDeps: {
        exclude: ['bjvm'],
    },
});