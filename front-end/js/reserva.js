let reservasGlobal = [];

function buscarReservaPorNombre() {
  const textoBusqueda = document.getElementById('busqueda-nombre-reserva').value.trim().toLowerCase();

  if (textoBusqueda === '') {
    getReservas();
    return;
  }

  fetch(`http://localhost:8080/api/v1/reservation/filter/${textoBusqueda}`)
    .then(response => response.json())
    .then(data => {
      reservasGlobal = Array.isArray(data) ? data : [];
      renderReservas(reservasGlobal);
    })
    .catch(error => {
      console.error('Error al filtrar reservas por nombre:', error);
      reservasGlobal = [];
      renderReservas([]);
    });
}

function filtrarReservasPorFecha() {
  const fechaMin = document.getElementById('fecha-min').value;
  const fechaMax = document.getElementById('fecha-max').value;

  if (!Array.isArray(reservasGlobal) || reservasGlobal.length === 0) {
    renderReservas([]);
    return;
  }

  const listaFiltrada = reservasGlobal.filter(reserva => {
    const fechaReserva = new Date(reserva.fecha);
    const minDate = fechaMin ? new Date(fechaMin) : new Date(0);
    const maxDate = fechaMax ? new Date(fechaMax) : new Date(8640000000000000);
    
    return fechaReserva >= minDate && fechaReserva <= maxDate;
  });

  renderReservas(listaFiltrada);
}

function agregarReserva() {
  const nombre = document.getElementById('nombre-reserva').value.trim();
  const email = document.getElementById('email-reserva').value.trim();
  const telefono = document.getElementById('telefono-reserva').value.trim();
  const fecha = document.getElementById('fecha-reserva').value;
  const hora = document.getElementById('hora-reserva').value;
  const personas = parseInt(document.getElementById('personas-reserva').value);
  const comentarios = document.getElementById('comentarios-reserva').value.trim();

  if (!nombre || !email || !telefono || !fecha || !hora || isNaN(personas)) {
    alert("Por favor completa todos los campos obligatorios.");
    return;
  }

  const reserva = { 
    nombre, 
    email, 
    telefono, 
    fecha, 
    hora, 
    personas, 
    comentarios,
    estado: 'pendiente' 
  };

  fetch('http://localhost:8080/api/v1/reservation/', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(reserva)
  })
  .then(response => {
    if (!response.ok) throw new Error('No se pudo realizar la reserva');
    return response.json();
  })
  .then(data => {
    alert(data.message || '¡Reserva realizada con éxito! Te contactaremos para confirmar.');
    document.getElementById('reserva-form').reset();
    getReservas();
  })
  .catch(error => {
    console.error('Error al hacer la reserva:', error);
    alert('Error al realizar la reserva. Por favor intenta nuevamente.');
  });
}

function renderReservas(lista) {
  const contenedor = document.querySelector('.reservas-container');
  if (!contenedor) {
    console.error("No se encontró el contenedor '.reservas-container'");
    return;
  }

  contenedor.innerHTML = '';

  if (!Array.isArray(lista) || lista.length === 0) {
    const mensajeNoResultados = document.createElement('div');
    mensajeNoResultados.classList.add('sin-resultados');
    mensajeNoResultados.innerHTML = '<h3>No hay reservas. Realiza una nueva para comenzar.</h3>';
    contenedor.appendChild(mensajeNoResultados);
  } else {
    lista.forEach(reserva => {
      const divReserva = document.createElement('div');
      divReserva.classList.add('reserva-item');

      const fechaFormateada = new Date(reserva.fecha).toLocaleDateString();
      
      let estadoClase = '';
      switch(reserva.estado) {
        case 'confirmada': estadoClase = 'estado-confirmada'; break;
        case 'cancelada': estadoClase = 'estado-cancelada'; break;
        default: estadoClase = 'estado-pendiente';
      }

      divReserva.innerHTML = `
        <div class="reserva-info">
            <h3>${reserva.nombre}</h3>
            <p><strong>Fecha:</strong> ${fechaFormateada} a las ${reserva.hora}</p>
            <p><strong>Contacto:</strong> ${reserva.email} / ${reserva.telefono}</p>
            <p><strong>Personas:</strong> ${reserva.personas}</p>
            <p><strong>Estado:</strong> <span class="${estadoClase}">${reserva.estado}</span></p>
            ${reserva.comentarios ? `<p><strong>Comentarios:</strong> ${reserva.comentarios}</p>` : ''}
            <div class="botones">
              <button class="btn-eliminar" data-id="${reserva.id}">Eliminar</button>
              <button class="btn-actualizar" data-id="${reserva.id}">Actualizar</button>
            </div>
        </div>
      `;

      contenedor.appendChild(divReserva);
    });
  }

  // Agregar Event Listeners para los botones
  document.querySelectorAll('.btn-eliminar').forEach(btn => {
    btn.addEventListener('click', async (e) => {
      const id = e.target.dataset.id;
      const confirmacion = confirm(`¿Estás seguro de eliminar la reserva con ID ${id}?`);
      if (!confirmacion) return;

      try {
        const res = await fetch(`http://localhost:8080/api/v1/reservation/${id}`, {
          method: 'DELETE'
        });
        const data = await res.json();
        alert(data.message || "Eliminado correctamente");
        getReservas();
      } catch (error) {
        console.error(error);
        alert("Error al eliminar la reserva.");
      }
    });
  });

  document.querySelectorAll('.btn-actualizar').forEach(btn => {
    btn.addEventListener('click', async (e) => {
      const id = e.target.dataset.id;
      const reservaActual = reservasGlobal.find(r => r.id == id);
      
      if (!reservaActual) {
        alert("No se pudo encontrar la información de la reserva.");
        return;
      }

      // Esto podría mejorarse con un modal o un formulario dedicado
      const nuevoNombre = prompt("Nombre:", reservaActual.nombre);
      const nuevoEmail = prompt("Email:", reservaActual.email);
      const nuevoTelefono = prompt("Teléfono:", reservaActual.telefono);
      const nuevaFecha = prompt("Fecha (YYYY-MM-DD):", reservaActual.fecha.split('T')[0]);
      const nuevaHora = prompt("Hora (HH:MM):", reservaActual.hora);
      const nuevasPersonas = prompt("Número de personas:", reservaActual.personas);
      const nuevosComentarios = prompt("Comentarios:", reservaActual.comentarios || "");

      if (!nuevoNombre || !nuevoEmail || !nuevoTelefono || !nuevaFecha || !nuevaHora || !nuevasPersonas) {
        alert("Todos los campos obligatorios deben completarse.");
        return;
      }

      try {
        const res = await fetch(`http://localhost:8080/api/v1/reservation/${id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            nombre: nuevoNombre,
            email: nuevoEmail,
            telefono: nuevoTelefono,
            fecha: nuevaFecha,
            hora: nuevaHora,
            personas: parseInt(nuevasPersonas),
            comentarios: nuevosComentarios,
            estado: reservaActual.estado || 'pendiente'
          })
        });

        const data = await res.json();
        alert(data.message || "Actualizado correctamente");
        getReservas();
      } catch (error) {
        console.error(error);
        alert("Error al actualizar la reserva.");
      }
    });
  });
}

function getReservas() {
  fetch('http://localhost:8080/api/v1/reservation/')
    .then(response => response.json())
    .then(data => {
      reservasGlobal = Array.isArray(data) ? data : [];
      renderReservas(reservasGlobal);
    })
    .catch(error => {
      console.error('Error al obtener reservas:', error);
      renderReservas([]);
    });
}

document.addEventListener('DOMContentLoaded', () => {
  // Inicializar lista de reservas si estamos en la página de administración
  const reservasContainer = document.querySelector('.reservas-container');
  if (reservasContainer) {
    getReservas();
    
    // Manejadores para filtros si existen
    const busquedaNombre = document.getElementById('busqueda-nombre-reserva');
    if (busquedaNombre) {
      busquedaNombre.addEventListener('input', buscarReservaPorNombre);
    }
    
    const fechaMin = document.getElementById('fecha-min');
    const fechaMax = document.getElementById('fecha-max');
    if (fechaMin && fechaMax) {
      fechaMin.addEventListener('change', filtrarReservasPorFecha);
      fechaMax.addEventListener('change', filtrarReservasPorFecha);
    }
  }

  // Manejar el formulario de reservas para clientes
  const reservaForm = document.getElementById('reserva-form');
  if (reservaForm) {
    reservaForm.addEventListener('submit', function(e) {
      e.preventDefault();
      agregarReserva();
    });
  }
});