let reservasGlobal = [];

// Función para cargar las mesas disponibles
async function cargarMesasDisponibles() {
    const mesaSelect = document.getElementById("mesa");
    const botonReserva = document.querySelector("#reserva-form button");

    botonReserva.disabled = true;

    try {
        const response = await fetch("http://localhost:8080/api/v1/mesa/available");
        if (!response.ok) throw new Error("Error al cargar las mesas disponibles.");

        const mesas = await response.json();
        console.log("Mesas disponibles:", mesas);

        mesaSelect.innerHTML = '<option value="" disabled selected>Seleccione una mesa</option>';

        if (mesas.length === 0) {
            mesaSelect.innerHTML += `<option value="" disabled>No hay mesas disponibles</option>`;
            return;
        }

        mesas.forEach((mesa) => {
            const option = document.createElement("option");
            option.value = mesa.idMesa;
            option.textContent = `Mesa ${mesa.idMesa} - Capacidad: ${mesa.capacidad}`;
            mesaSelect.appendChild(option);
        });

        botonReserva.disabled = false;

    } catch (error) {
        console.error("Error al cargar las mesas disponibles:", error);
        alert("No se pudieron cargar las mesas disponibles.");
    }
}

// Función para registrar una nueva reserva
async function registrarReserva() {
    const nombre = document.getElementById("nombre").value.trim();
    const fecha = document.getElementById("fecha").value;
    const numeroPersonas = parseInt(document.getElementById("personas").value, 10);
    const numeroCelular = document.getElementById("celular").value.trim();
    const mesaValor = document.getElementById("mesa").value;

    console.log("Valor crudo del select mesa:", mesaValor);

    if (!nombre || !fecha || isNaN(numeroPersonas) || numeroPersonas <= 0 ||
        !numeroCelular || numeroCelular.length !== 10 || !mesaValor) {
        alert("Por favor, completa todos los campos correctamente.");
        return;
    }

    const mesaId = parseInt(mesaValor, 10);
    console.log("Mesa seleccionada (idMesa):", mesaId);

    const reserva = {
        name: nombre,
        fecha: new Date(fecha).toISOString(),
        numeroPersonas,
        numeroCelular,
        idMesa: mesaId
    };

    console.log("Datos enviados al backend:", reserva);

    try {
        const response = await fetch("http://localhost:8080/api/v1/reservation/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(reserva)
        });

        const data = await response.json();
        console.log("Respuesta del backend:", data);

        if (response.ok) {
            alert(data.message || "¡Reserva realizada con éxito!");
            document.getElementById("reserva-form").reset();
            cargarMesasDisponibles();
            obtenerReservas();
        } else {
            alert(data || "Hubo un problema al realizar la reserva.");
        }
    } catch (error) {
        console.error("Error al registrar la reserva:", error);
        alert("Error al conectar con el servidor.");
    }
}

// Función para obtener reservas
async function obtenerReservas() {
    try {
        const response = await fetch("http://localhost:8080/api/v1/reservation/");
        if (!response.ok) throw new Error("Error al obtener las reservas.");

        const reservas = await response.json();
        reservasGlobal = Array.isArray(reservas) ? reservas : [];
        renderReservas(reservasGlobal);
    } catch (error) {
        console.error("Error al obtener las reservas:", error);
        reservasGlobal = [];
        renderReservas([]);
    }
}

// Función para mostrar reservas
function renderReservas(lista) {
    const contenedor = document.querySelector(".reservas-container");
    if (!contenedor) {
        console.error("No se encontró el contenedor '.reservas-container'");
        return;
    }

    contenedor.innerHTML = "";

    if (!Array.isArray(lista) || lista.length === 0) {
        contenedor.innerHTML = "<h3>No hay reservas registradas.</h3>";
    } else {
        lista.forEach((reserva) => {
            const divReserva = document.createElement("div");
            divReserva.classList.add("reserva");
            divReserva.innerHTML = `
                <h3>${reserva.name}</h3>
                <p>Fecha y hora: ${reserva.fecha}</p>
                <p>Número de personas: ${reserva.numeroPersonas}</p>
                <p>Celular: ${reserva.numeroCelular}</p>
                <p>Mesa: ${reserva.mesa?.idMesa || "Sin asignar"}</p>
            `;
            contenedor.appendChild(divReserva);
        });
    }
}

// Al cargar la página
document.addEventListener("DOMContentLoaded", () => {
    cargarMesasDisponibles();
    obtenerReservas();

    const reservaForm = document.getElementById("reserva-form");
    if (reservaForm) {
        reservaForm.addEventListener("submit", (event) => {
            event.preventDefault();
            registrarReserva();
        });
    }
});
