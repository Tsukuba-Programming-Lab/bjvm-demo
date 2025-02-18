import launchBJVM from 'bjvm';
import './style.css';

try {
    const stdOut = document.getElementById("stdOut")!;
    const options = {
        stdErrHandler: (text: string) => {
            stdOut.innerHTML += `<span style="color: red;">${text}</span>`;
        },
        stdOutHandler: (text: string) => {
            stdOut.innerHTML += `<span>${text}</span>`;
        },
        externalLibraries: [
            await fetchAsUint8Array("java/lib/dom.zip"),
            await fetchAsUint8Array("java/lib/bjvm-webapi-bindings.jar"),
        ],
    };

    await launchBJVM(await fetchAsUint8Array("java/app.jar"), options);
} catch (e) {
    console.log(e);
}

async function fetchAsUint8Array(path: string): Promise<Uint8Array> {
    const data = await (await fetch(path)).blob();
    const arrayBuffer = await data.arrayBuffer();
    return new Uint8Array(arrayBuffer);
}