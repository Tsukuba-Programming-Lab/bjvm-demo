import { defineConfig, searchForWorkspaceRoot } from 'vite'
import wasm from "vite-plugin-wasm";

export default defineConfig({
    server: {
        fs: {
            allow: [
                // search up for workspace root
                searchForWorkspaceRoot(process.cwd()),
                // your custom rules
                '/Users/itsuru/RustroverProjects/bjvm/bjvm-wasm',
            ],
        },
    },
    plugins: [wasm()],
    // To disable cacheing .wasm files in dev mode.
    optimizeDeps: {
        exclude: ['bjvm'],
    },
});