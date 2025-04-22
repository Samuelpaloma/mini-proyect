document.addEventListener('DOMContentLoaded', function() {
    // ===== Navegación =====
    const navLinks = document.querySelectorAll('.nav-link');
    
    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            
            navLinks.forEach(l => l.classList.remove('active'));
            
            this.classList.add('active');
            
            const sectionId = this.getAttribute('data-section');
            
            document.querySelectorAll('section').forEach(section => {
                section.classList.remove('active');
            });
            
            document.getElementById(sectionId).classList.add('active');
            
            history.pushState(null, null, `#${sectionId}`);
        });
    });
    
    // Manejar la navegación por historial (botones atrás/adelante)
    window.addEventListener('popstate', function() {
        const hash = window.location.hash.substring(1) || 'inicio';
        
        // Actualizar navegación y secciones activas
        navLinks.forEach(link => {
            if (link.getAttribute('data-section') === hash) {
                link.classList.add('active');
            } else {
                link.classList.remove('active');
            }
        });
        
        document.querySelectorAll('section').forEach(section => {
            if (section.id === hash) {
                section.classList.add('active');
            } else {
                section.classList.remove('active');
            }
        });
    });
    
    // Manejar envío del formulario de reserva
    const reservaForm = document.getElementById('reserva-form');
    if (reservaForm) {
        reservaForm.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('¡Reserva realizada con éxito! Te contactaremos para confirmar.');
            this.reset();
        });
    }
    
    // Manejar la carga inicial según el hash en la URL
    const initialHash = window.location.hash.substring(1) || 'inicio';
    document.querySelectorAll('section').forEach(section => {
        section.classList.remove('active');
    });
    document.getElementById(initialHash).classList.add('active');
    
    navLinks.forEach(link => {
        if (link.getAttribute('data-section') === initialHash) {
            link.classList.add('active');
        } else {
            link.classList.remove('active');
        }
    });
    
    
});