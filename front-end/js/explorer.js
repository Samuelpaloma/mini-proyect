function registerExplorer() {
    return new Promise(async (resolve) => {
        // alert("hola");
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "id": 0,
            "name": document.getElementById("nombre").value,
            "nationality": document.getElementById("nacionalidad").value,
            "age": document.getElementById("edad").value,
            "reputation": document.getElementById("reputacion").value,
            "image_explorer": document.getElementById("imagen").value
        });

        let response = await fetch("http://localhost:8085/api/v1/explorer/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        getExplorer();

    });
}

function getExplorer(){
    return new Promise(async (resolve) => {
        var url="http://localhost:8085/api/v1/explorer/";
        var filtro=document.getElementById("nameFilter").value;
        if(filtro!=""){
            url+="filter/"+filtro;
        }

        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let response = await fetch(url, {
            method: "GET",
            headers: headersList
        });

        let data = await response.json();
        // innerHTML="" limpiar la lista
        var container=document.getElementById("listExplorer");
        container.innerHTML="";
        data.forEach(register => {
            // console.log(register);
            //se crea la columna
            var col=document.createElement("div");
            col.className="col-lg-3 col-md-4 col-sm-6 pb-1";
            //creación de la tarjeta
            var card=document.createElement("div");
            card.className="team-item bg-white mb-4";
            //contenedor de la imagen
            var containerImage=document.createElement("div");
            containerImage.className="team-img position-relative overflow-hidden";

            //Imagen
            var image=document.createElement("img");
            image.className="img-fluid w-100";
            image.src=register["imageExplorer"];

            //contenedor texto
            var containertText=document.createElement("div");
            containertText.className="text-center py-4";
            //titulo
            var title=document.createElement("h5");
            title.className="text-truncate";
            title.innerText=register["name"];

            //descripción
            var description=document.createElement("p");
            // title.className="text-truncate";
            description.innerHTML="Age:"+register["age"]+"<br> Reputation:"+register["reputation"];

            var btnEdit=document.createElement("button");
            btnEdit.className="btn btn-warning";
            btnEdit.innerText="Edit";
            
            var btnDelete=document.createElement("button");
            btnDelete.className="btn btn-danger";
            btnDelete.innerText="Delete";
            
            //se agrega al documento
            containerImage.appendChild(image);
            card.appendChild(containerImage);

            containertText.appendChild(title);
            containertText.appendChild(description);
            card.appendChild(containertText);
            card.appendChild(btnEdit);
            card.appendChild(btnDelete);

            col.appendChild(card);
            container.appendChild(col);
        });
        

    });
}