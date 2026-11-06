const formulario = document.getElementById('formulario');
const campoPregunta = document.getElementById('pregunta');
const conversacion = document.getElementById('conversacion');
const botonEnviar = document.getElementById('enviar');

function agregarMensaje(texto, tipo) {
    const elemento = document.createElement('div');
    elemento.className = `mensaje ${tipo}`;
    elemento.textContent = texto;
    conversacion.appendChild(elemento);
    conversacion.scrollTop = conversacion.scrollHeight;
}

formulario.addEventListener('submit', async (evento) => {
    evento.preventDefault();
    const pregunta = campoPregunta.value.trim();
    if (!pregunta) return;

    agregarMensaje(pregunta, 'usuario');
    campoPregunta.value = '';
    botonEnviar.disabled = true;
    botonEnviar.textContent = '...';

    try {
        const respuesta = await fetch('/api/chat', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ pregunta })
        });
        if (!respuesta.ok) {
            const detalle = await respuesta.text();
            throw new Error(detalle || `Error HTTP ${respuesta.status}`);
        }
        const datos = await respuesta.json();
        agregarMensaje(datos.respuesta || 'No se recibió contenido.', 'asistente');
    } catch (error) {
        agregarMensaje(`No fue posible obtener una respuesta: ${error.message}`, 'asistente');
    } finally {
        botonEnviar.disabled = false;
        botonEnviar.textContent = 'Enviar';
        campoPregunta.focus();
    }
});
