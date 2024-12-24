import React from 'react';

function ListaDeRecetas({ recetas }) {
  // Agregar este console.log para ver la estructura de los datos
  console.log("Datos de recetas recibidos:", recetas);

  return (
    <div className="lista-de-recetas">
      {recetas && recetas.length > 0 ? (
        <ul>
          {recetas.map((receta) => {
            // Agregar console.log para cada receta
            console.log("Receta individual:", receta);
            console.log("Tiempo de cocción:", receta.tiempoCoccion, typeof receta.tiempoCoccion);

            return (
              <li key={receta.id}>

               {console.log("URL de la imagen:", receta.imagenUrl)}
               <div>
                <h2>{receta.nombre}</h2>
                <p>{receta.descripcion}</p>
                <p>Dificultad: {receta.dificultad}</p>
                {receta.tiempoCoccion && (
                  <p>Tiempo de cocción: {receta.tiempoCoccion} minutos</p>
                )}
                </div>

                {receta.imagenUrl && (
                <div className="imagen-contenedor">
                <img src={receta.imagenUrl}
                alt={receta.nombre}
                className="receta-imagen"
                loading="lazy"              />
                </div>
                )}

              </li>
            );
          })}
        </ul>
      ) : (
        <p>No se encontraron recetas😢.</p>
      )}
    </div>
  );
}

export default ListaDeRecetas;