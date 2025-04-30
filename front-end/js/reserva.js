let reservasGlobal = [];

// Función para cargar las mesas disponibles
async function cargarMesasDisponibles() {
    const mesaSelect = document.getElementById("mesa");

    try {
        const response = await fetch("http://localhost:8080/api/v1/reservation/available-mesas");
        if (!response.ok) {
            throw new Error("Error al cargar las mesas disponibles.");
        }

        const mesas = await response.json();
        console.log("Mesas disponibles:", mesas);

        mesaSelect.innerHTML = '<option value="" disabled selected>Seleccione una mesa</option>';

        if (mesas.length === 0) {
            const option = document.createElement("option");
            option.value = "";
            option.disabled = true;
            option.textContent = "No hay mesas disponibles";
            mesaSelect.appendChild(option);
            return;
        }

        mesas.forEach((mesa) => {
            const option = document.createElement("option");
            option.value = mesa.idMesa;
            option.textContent = `Mesa ${mesa.idMesa} - Capacidad: ${mesa.capacidad}`;
            mesaSelect.appendChild(option);
        });
    } catch (error) {
        console.error("Error al cargar las mesas disponibles:", error);
        alert("No se pudieron cargar las mesas disponibles.");
    }
}

// Función para registrar una nueva reserva
async function registrarReserva() {
    const nombre = document.getElementById("nombre").value.trim();
    const fecha = document.getElementById("fecha").value; // Ahora incluye fecha y hora
    const numeroPersonas = parseInt(document.getElementById("personas").value, 10);
    const numeroCelular = document.getElementById("celular").value.trim();
    const mesaId = parseInt(document.getElementById("mesa").value, 10);

    if (!nombre || !fecha || isNaN(numeroPersonas) || !numeroCelular || isNaN(mesaId)) {
        alert("Por favor, completa todos los campos correctamente.");
        return;
    }

    const reserva = {
        name: nombre,
        fecha: fecha, // Enviar fecha y hora en formato ISO 8601
        numeroPersonas: numeroPersonas,
        numeroCelular: numeroCelular,
        idMesa: mesaId
    };

    try {
        const response = await fetch("http://localhost:8080/api/v1/reservation/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(reserva)
        });

        if (!response.ok) {
            throw new Error("Error al registrar la reserva.");
        }

        const data = await response.json();
        alert(data.message || "¡Reserva realizada con éxito!");
        document.getElementById("reserva-form").reset();
        cargarMesasDisponibles(); // Actualizar las mesas disponibles
        obtenerReservas(); // Actualizar la lista de reservas
    } catch (error) {
        console.error("Error al registrar la reserva:", error);
        alert("Hubo un problema al realizar la reserva.");
    }
}

// Función para obtener todas las reservas
async function obtenerReservas() {
    try {
        const response = await fetch("http://localhost:8080/api/v1/reservation/");
        if (!response.ok) {
            throw new Error("Error al obtener las reservas.");
        }

        const reservas = await response.json();
        reservasGlobal = Array.isArray(reservas) ? reservas : [];
        renderReservas(reservasGlobal);
    } catch (error) {
        console.error("Error al obtener las reservas:", error);
        reservasGlobal = [];
        renderReservas([]);
    }
}

// Función para renderizar las reservas en la página
function renderReservas(lista) {
    const contenedor = document.querySelector(".reservas-container");
    if (!contenedor) {
        console.error("No se encontró el contenedor '.reservas-container'");
        return;
    }

    contenedor.innerHTML = "";

    if (!Array.isArray(lista) || lista.length === 0) {
        const mensajeNoResultados = document.createElement("div");
        mensajeNoResultados.classList.add("sin-resultados");
        mensajeNoResultados.innerHTML = "<h3>No hay reservas registradas.</h3>";
        contenedor.appendChild(mensajeNoResultados);
    } else {
        lista.forEach((reserva) => {
            const divReserva = document.createElement("div");
            divReserva.classList.add("reserva");

            divReserva.innerHTML = `
                <h3>${reserva.name}</h3>
                <p>Fecha y hora: ${reserva.fecha}</p>
                <p>Número de personas: ${reserva.numeroPersonas}</p>
                <p>Celular: ${reserva.numeroCelular}</p>
                <p>Mesa: ${reserva.mesa.idMesa}</p>
            `;

            contenedor.appendChild(divReserva);
        });
    }
}

// Inicializar eventos y cargar datos al cargar la página
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