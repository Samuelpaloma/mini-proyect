let menuGlobal = [];

function buscarPorNombre() {
  const textoBusqueda = document.getElementById('busqueda-nombre').value.trim();
  
  if (textoBusqueda === '') {
    filtrarPlatos();
    return;
  }
  
  fetch(`http://localhost:8080/api/v1/menu/filter/${textoBusqueda}`)
    .then(response => response.json())
    .then(data => {
      console.log("DATOS RECIBIDOS:", data);
      console.log("TIPO DE DATOS:", typeof data);
      console.log("ES ARRAY?", Array.isArray(data));
      if (Array.isArray(data)) {
        console.log("LONGITUD DEL ARRAY:", data.length);
        if (data.length > 0) {
          console.log("PRIMER ELEMENTO:", data[0]);
          console.log("PROPIEDADES DEL PRIMER ELEMENTO:", Object.keys(data[0]));
        }
      }
      
      menuGlobal = data;
      filtrarPlatos();
    })
    .catch(error => {
      console.error('Error al filtrar por nombre:', error);
      filtrarPlatos();
    });
}

function filtrarPlatos() {
  console.log("Ejecutando filtrarPlatos con menuGlobal:", menuGlobal);
  
  const precioMin = parseFloat(document.getElementById('precio-min').value) || 0;
  const precioMax = parseFloat(document.getElementById('precio-max').value) || Infinity;

  // Verificamos si menuGlobal es un array y tiene elementos
  if (!Array.isArray(menuGlobal) || menuGlobal.length === 0) {
    console.log("menuGlobal no es un array o está vacío");
    renderMenu([]);
    return;
  }

  const listaFiltrada = menuGlobal.filter(plato => {
    // Comprobamos que plato sea un objeto
    if (!plato || typeof plato !== 'object') {
      console.log("Elemento no válido en menuGlobal:", plato);
      return false;
    }
    
    // Intentamos obtener el precio, probando diferentes propiedades
    let precio;
    if ('precio' in plato) {
      precio = parseFloat(plato.precio);
    } else if ('price' in plato) {
      precio = parseFloat(plato.price);
    } else {
      console.log("No se encontró propiedad de precio en:", plato);
      return false;
    }
    
    // Verificamos que el precio es un número y está en el rango
    if (isNaN(precio)) {
      console.log("Precio no es un número:", precio);
      return false;
    }
    
    return precio >= precioMin && precio <= precioMax;
  });

  console.log("Lista filtrada final:", listaFiltrada);
  renderMenu(listaFiltrada);
}

document.addEventListener('DOMContentLoaded', () => {
  getMenu();

  // Los filtros de precio siguen usando filtrarPlatos
  document.getElementById('precio-min').addEventListener('input', filtrarPlatos);
  document.getElementById('precio-max').addEventListener('input', filtrarPlatos);
  
  // La búsqueda por nombre ahora usa buscarPorNombre
  document.getElementById('busqueda-nombre').addEventListener('input', buscarPorNombre);
  
  // El botón de aplicar filtros ahora podría considerar ambos tipos de filtrado
  document.getElementById('aplicar-filtros').addEventListener('click', () => {
    const textoBusqueda = document.getElementById('busqueda-nombre').value.trim();
    if (textoBusqueda !== '') {
      buscarPorNombre();
    } else {
      filtrarPlatos();
    }
  });
});

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
  console.log("Renderizando lista:", lista);
  
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
  } else {
    lista.forEach(plato => {
      const divPlato = document.createElement('div');
      divPlato.classList.add('plato');
      
      // Intentamos obtener el nombre, probando diferentes propiedades
      const nombre = plato.name || plato.nombre || "Sin nombre";
      
      // Intentamos obtener el precio, probando diferentes propiedades
      const precio = parseFloat(plato.precio || plato.price || 0);
      
      // Intentamos obtener la imagen, probando diferentes propiedades
      const imagen = plato.imagen || plato.image || '/api/placeholder/300/200';

      divPlato.innerHTML = `
        <img src="${imagen}" alt="${nombre}">
        <div class="plato-info">
          <h3>${nombre}</h3>
          <p>$${precio.toFixed(2)}</p>
        </div>
      `;

      contenedor.appendChild(divPlato);
    });
  }

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
      renderMenu([]);
    });
}
