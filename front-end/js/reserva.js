let reservasGlobal = [];

function agregarReserva() {
  // Obtener referencias a los elementos con los IDs que tienes en tu HTML
  const nombreInput = document.getElementById('nombre');
  const fechaInput = document.getElementById('fecha');
  const horaInput = document.getElementById('hora');
  const personasInput = document.getElementById('personas');
  const celularInput = document.getElementById('celular');
  const userInput = document.getElementById('user'); // ID del usuario
  const mesaInput = document.getElementById('mesa'); // ID de la mesa
  
  // Verificar que todos los elementos existen
  if (!nombreInput || !fechaInput || !horaInput || !personasInput || !celularInput || !userInput || !mesaInput) {
    console.error("No se encontraron todos los campos del formulario de reserva");
    alert("Error: No se encontraron todos los campos del formulario");
    return;
  }
  
  // Obtener los valores y validarlos
  const nombre = nombreInput.value.trim();
  const fecha = fechaInput.value; // Fecha en formato YYYY-MM-DD
  const hora = horaInput.value; // Hora en formato HH:mm
  const personas = parseInt(personasInput.value, 10); // Asegurar que sea un número entero
  const celular = parseInt(celularInput.value, 10); // Asegurar que sea un número entero
  const userId = parseInt(userInput.value, 10); // ID del usuario (debe ser un número)
  const mesaId = parseInt(mesaInput.value, 10); // ID de la mesa (debe ser un número)

  if (!nombre || !fecha || !hora || isNaN(personas) || isNaN(celular) || isNaN(userId) || isNaN(mesaId)) {
    alert("Por favor completa todos los campos obligatorios.");
    return;
  }

  // Combinar fecha y hora en un formato LocalDateTime compatible con Java (ISO 8601)
  const fechaHora = `${fecha}T${hora}`;

  // Crear objeto de reserva ajustado al DTO del backend
  const reserva = { 
    name: nombre,
    fecha: fechaHora, // LocalDateTime en formato ISO 8601
    hora: fechaHora, // Usamos la misma fecha y hora en este caso
    numeroPersonas: personas,
    numeroCelular: celular,
    user: { id: userId }, // Objeto User con ID
    mesa: { id: mesaId }, // Objeto Mesa con ID
  };

  // Realizar la solicitud fetch
  fetch('http://localhost:8080/api/v1/reservation/', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(reserva)
  })
  .then(response => {
    // Manejar errores HTTP
    if (!response.ok) {
      console.error('Error HTTP:', response.status, response.statusText);
      throw new Error('No se pudo realizar la reserva. Código de estado: ' + response.status);
    }
    return response.json();
  })
  .then(data => {
    if (data.success) { // Ajusta la validación según el formato de respuesta de tu backend
      alert(data.message || '¡Reserva realizada con éxito! Te contactaremos para confirmar.');
      document.getElementById('reserva-form').reset();
    } else {
      // Manejar errores específicos del backend
      console.error('Error en el servidor:', data);
      alert(data.message || 'Error al realizar la reserva. Por favor intenta nuevamente.');
    }
  })
  .catch(error => {
    console.error('Error al hacer la reserva:', error);
    alert('Error al realizar la reserva. Por favor intenta nuevamente.');
  });
}

document.addEventListener('DOMContentLoaded', () => {
  // Manejar el formulario de reservas para clientes
  const reservaForm = document.getElementById('reserva-form');
  if (reservaForm) {
    reservaForm.addEventListener('submit', function(e) {
      e.preventDefault();
      agregarReserva();
    });
  }
});