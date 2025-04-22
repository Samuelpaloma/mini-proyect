let menuGlobal = [];

document.addEventListener('DOMContentLoaded', () => {
  
});

function agregarProducto() {
  const name = document.getElementById('nombre').value.trim();
  const precio = parseFloat(document.getElementById('precio').value.trim());
  const imagen = document.getElementById('imagen').value.trim();

  if (!name || isNaN(precio)) {
    alert("Por favor completa todos los campos.");
    return;
  }

  const producto = { name, precio, imagen };

  fetch('http://localhost:8080/api/v1/menu/', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(producto)
  })
  .then(response => {
    if (!response.ok) throw new Error('No se pudo agregar el producto');
    return response.text(); // Devuelves un texto como "register OK"
  })
  .then(data => {
    alert('Producto agregado con éxito');
    getMenu();
  })
  .catch(error => {
    console.error('Error al agregar:', error);
    alert('Error al agregar el producto');
  });
}



function renderMenu(lista) {
  const contenedor = document.querySelector('.platos-container');
  contenedor.innerHTML = '';

  lista.forEach(plato => {
    const divPlato = document.createElement('div');
    divPlato.classList.add('plato');

    divPlato.innerHTML = `
      <img src="${plato.imagen || '/api/placeholder/300/200'}" alt="${plato.nombre}">
      <div class="plato-info">
        <h3>${plato.nombre}</h3>
        <p>$${parseFloat(plato.precio).toFixed(2)}</p>
      </div>
    `;

    contenedor.appendChild(divPlato);
  });
}

function getMenu() {
  fetch('http://localhost:8080/api/v1/menu/')
    .then(response => response.json())
    .then(data => {
      menuGlobal = data;
      renderMenu(menuGlobal);
    })
    .catch(error => {
      console.error('Error al obtener el menú:', error);
    });
}
