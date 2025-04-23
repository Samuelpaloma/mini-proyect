let menuGlobal = [];

function buscarPorNombre() {
  const textoBusqueda = document.getElementById('busqueda-nombre').value.trim().toLowerCase();

  if (textoBusqueda === '') {
    getMenu();
    return;
  }

  fetch(`http://localhost:8080/api/v1/menu/filter/${textoBusqueda}`)
    .then(response => response.json())
    .then(data => {
      menuGlobal = Array.isArray(data) ? data : [];
      renderMenu(menuGlobal);
    })
    .catch(error => {
      console.error('Error al filtrar por nombre:', error);
      menuGlobal = [];
      renderMenu([]);
    });
}

function filtrarPlatos() {
  const precioMin = parseFloat(document.getElementById('precio-min').value) || 0;
  const precioMax = parseFloat(document.getElementById('precio-max').value) || Infinity;

  if (!Array.isArray(menuGlobal) || menuGlobal.length === 0) {
    renderMenu([]);
    return;
  }

  const listaFiltrada = menuGlobal.filter(plato => {
    const precio = parseFloat(plato.precio);
    return !isNaN(precio) && precio >= precioMin && precio <= precioMax;
  });

  renderMenu(listaFiltrada);
}

function agregarProducto() {
  const name = document.getElementById('nombre').value.trim();
  const precio = parseFloat(document.getElementById('precio').value.trim());
  const imagen = document.getElementById('imagen').value.trim();

  if (!name || isNaN(precio) || precio <= 0) {
    alert("Por favor completa todos los campos con valores válidos.");
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
    return response.text();
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
  if (!contenedor) {
    console.error("No se encontró el contenedor '.platos-container'");
    return;
  }

  contenedor.innerHTML = '';

  if (!Array.isArray(lista) || lista.length === 0) {
    const mensajeNoResultados = document.createElement('div');
    mensajeNoResultados.classList.add('sin-resultados');
    mensajeNoResultados.innerHTML = '<h3>No se encontraron productos que coincidan con la búsqueda</h3>';
    contenedor.appendChild(mensajeNoResultados);
    return;
  }

  lista.forEach(plato => {
    const divPlato = document.createElement('div');
    divPlato.classList.add('plato');

    const nombre = plato.name || "Sin nombre";
    const precio = parseFloat(plato.precio) || 0; 
    const imagen = plato.imagen;

    divPlato.innerHTML = `
      <img src="${imagen}" alt="${nombre}">
      <div class="plato-info">
          <h3>${nombre}</h3>
          <p>$${precio.toFixed(2)}</p>
          <div class="botones">
            <button class="btn-eliminar" data-id="${plato.id_menu}">Eliminar</button>
            <button class="btn-actualizar" data-id="${plato.id_menu}">Actualizar</button>
          </div>
      </div>
    `;

    contenedor.appendChild(divPlato);
  });

  const cardAgregar = document.createElement('div');
  cardAgregar.classList.add('plato');

  cardAgregar.innerHTML = ` 
    <h3>Agregar nuevo producto</h3>
    <input type="text" id="nombre" placeholder="Nombre del plato">
    <input type="number" id="precio" placeholder="Precio">
    <input type="text" id="imagen" placeholder="URL de la imagen">
    <button onclick="agregarProducto()">Agregar</button>
  `;

  contenedor.appendChild(cardAgregar);

  // Botón eliminar
  document.querySelectorAll('.btn-eliminar').forEach(btn => {
    btn.addEventListener('click', async (e) => {
      const id = e.target.dataset.id;
      const confirmacion = confirm(`¿Estás seguro de eliminar el plato con ID ${id}?`);
      if (!confirmacion) return;

      try {
        const res = await fetch(`http://localhost:8080/api/v1/menu/${id}`, {
          method: 'DELETE'
        });
        const data = await res.json();
        alert(data.message || "Eliminado correctamente");
        // Opcional: volver a cargar el menú
        location.reload(); 
      } catch (error) {
        console.error(error);
        alert("Error al eliminar el producto.");
      }
    });
  });

  // Botón actualizar
  document.querySelectorAll('.btn-actualizar').forEach(btn => {
    btn.addEventListener('click', async (e) => {
      const id = e.target.dataset.id;

      // Esto puede ser un modal o un prompt por ahora
      const nuevoNombre = prompt("Nuevo nombre del plato:");
      const nuevoPrecio = prompt("Nuevo precio del plato:");
      const nuevaImagen = prompt("Nueva URL de imagen:");

      if (!nuevoNombre || !nuevoPrecio || !nuevaImagen) {
        alert("Todos los campos son obligatorios.");
        return;
      }

      try {
        const res = await fetch(`http://localhost:8080/api/v1/menu/${id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            name: nuevoNombre,
            precio: parseFloat(nuevoPrecio),
            imagen: nuevaImagen
          })
        });

        const data = await res.json();
        alert(data.message || "Actualizado correctamente");
        location.reload(); // o vuelve a llamar a renderMenu con datos actualizados
      } catch (error) {
        console.error(error);
        alert("Error al actualizar el producto.");
      }
    });
  });

}



function getMenu() {
  fetch('http://localhost:8080/api/v1/menu/')
    .then(response => response.json())
    .then(data => {
      menuGlobal = Array.isArray(data) ? data : [];
      renderMenu(menuGlobal);
    })
    .catch(error => {
      renderMenu([]); 
    });
}

document.addEventListener('DOMContentLoaded', () => {
  getMenu();

  document.getElementById('precio-min').addEventListener('input', filtrarPlatos);
  document.getElementById('precio-max').addEventListener('input', filtrarPlatos);
  document.getElementById('busqueda-nombre').addEventListener('input', buscarPorNombre);

  document.getElementById('aplicar-filtros').addEventListener('click', () => {
    const textoBusqueda = document.getElementById('busqueda-nombre').value.trim();
    if (textoBusqueda !== '') {
      buscarPorNombre();
    } else {
      getMenu();
    }
  });
});