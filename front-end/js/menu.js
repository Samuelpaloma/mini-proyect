let menuGlobal = [];

document.addEventListener('DOMContentLoaded', () => {
  getMenu();
});

function registrarMenu() {
  return new Promise(async (resolve) => {
    const nombre = document.getElementById("titulo").value.trim();
    const precio = document.getElementById("temporadas").value.trim(); // Asegúrate que el id coincida con tu input

    if (!nombre || !precio) {
      alert("Por favor completa todos los campos.");
      return;
    }

    const headersList = {
      "Accept": "*/*",
      "User-Agent": "web",
      "Content-Type": "application/json"
    };

    const bodyContent = JSON.stringify({
      id: 0,
      nombre: nombre,
      precio: precio
    });

    try {
      const response = await fetch("http://localhost:8080/api/v1/menu", {
        method: "POST",
        body: bodyContent,
        headers: headersList
      });

      if (!response.ok) throw new Error("Error al registrar plato");
      console.log(await response.text());
      getMenu(); // Recarga el menú
    } catch (error) {
      console.error("Error al registrar el plato:", error);
    }
  });
}

function getMenu() {
  fetch('http://localhost:8080/api/v1/menu', {
    method: 'GET'
  })
    .then(response => response.json())
    .then(data => {
      menuGlobal = data;
      renderMenu(data);
    })
    .catch(error => console.error('Error al obtener el menú:', error));
}

function renderMenu(lista) {
  const contenedor = document.querySelector('.platos-container');
  contenedor.innerHTML = '';

  lista.forEach(plato => {
    const divPlato = document.createElement('div');
    divPlato.classList.add('plato');

    divPlato.innerHTML = `
      <img src="/api/placeholder/300/200" alt="${plato.nombre}">
      <div class="plato-info">
        <h3>${plato.nombre}</h3>
        <p>$${parseFloat(plato.precio).toFixed(2)}</p>
      </div>
    `;

    contenedor.appendChild(divPlato);
  });
}