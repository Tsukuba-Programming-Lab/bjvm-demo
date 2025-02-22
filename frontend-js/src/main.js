import launchBJVM from 'bjvm';
import './style.css';

const fetchBinary = async (path) =>  {
    return new Uint8Array(await (await fetch(path)).arrayBuffer());
}

try {
    (async () => {
        const stdOut = document.getElementById("stdOut");
        const options = {
            stdErrHandler: (text) => {
                stdOut.innerHTML += `<span style="color: red;">${text}</span>`;
            },
            stdOutHandler: (text) => {
                stdOut.innerHTML += `<span>${text}</span>`;
            },
            externalLibraries: [
                await fetchBinary("java/lib/dom.zip"),
                await fetchBinary("java/lib/bjvm-webapi-bindings.jar"),
            ],
        };

        await launchBJVM(await fetchBinary("java/app.jar"), options);
    })();
} catch (e) {
    console.log(e);
}