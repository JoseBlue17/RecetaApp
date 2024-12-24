import React, { useState, useEffect } from 'react';
import '@fontsource/kalam';
import axios from 'axios';
import ListaDeRecetas from './ListaDeRecetas';
import './App.css';

function App() {
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedDifficulty, setSelectedDifficulty] = useState('');
  const [recetas, setRecetas] = useState([]);
  const [error, setError] = useState(null);

  const dificultadesDisponibles = [
    'Fácil',
    'Moderada',
    'Difícil',
  ]; // Puedes reemplazar esta lista con una consulta dinámica al backend

  // Función para cargar todas las recetas
  const fetchAllRecetas = async () => {
    try {
      const response = await axios.get('http://localhost:8081/recetas');
      console.log("Todas las recetas recibidas:", response.data);
      setRecetas(response.data);
      setError(null);
    } catch (error) {
      console.error("Error al cargar las recetas:", error);
      setError("No se pudieron cargar las recetas");
    }
  };

  // Cargar todas las recetas al iniciar la aplicación
  useEffect(() => {
    fetchAllRecetas();
  }, []);

  // Filtrar recetas por dificultad
  useEffect(() => {
    const fetchFilteredRecetas = async () => {
      if (selectedDifficulty) {
        try {
          const response = await axios.get('http://localhost:8081/recetas/dificultad', {
            params: { dificultad: selectedDifficulty },
          });

          console.log("Recetas filtradas por dificultad seleccionada:", response.data);
          setRecetas(response.status === 204 ? [] : response.data);
          setError(null);
        } catch (error) {
          console.error("Error al filtrar por dificultad seleccionada:", error);
          setError("No se pudieron cargar las recetas");
          setRecetas([]);
        }
      }
    };

    fetchFilteredRecetas();
  }, [selectedDifficulty]);

  // Filtrar recetas por ingredientes
  useEffect(() => {
    const fetchRecetasByIngredientes = async () => {
      if (searchTerm) {
        try {
          // Asegurarse de que los ingredientes estén en el formato correcto
          const ingredientesList = searchTerm.split(',').map(ingrediente => ingrediente.trim()).join(',');
          const response = await axios.get('http://localhost:8081/recetas/ingredientes', {
            params: { ingredientes: ingredientesList },
          });

          console.log("Recetas filtradas por ingredientes:", response.data);
          setRecetas(response.status === 204 ? [] : response.data);
          setError(null);
        } catch (error) {
          console.error("Error al filtrar por ingredientes:", error);
          setError("No se pudieron cargar las recetas");
          setRecetas([]);
        }
      } else {
        fetchAllRecetas(); // Si el campo de búsqueda está vacío, mostrar todas las recetas
      }
    };

    fetchRecetasByIngredientes();
  }, [searchTerm]);

  // Limpiar filtros y mostrar todas las recetas
  const handleClearFilters = () => {
    setSearchTerm('');
    setSelectedDifficulty('');
    fetchAllRecetas(); // Volver a cargar todas las recetas
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Buscador de Recetas🍽️</h1>
        <div className="search-container">
          <input
            type="text"
            placeholder="Ingresa un ingrediente"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
          <select
            value={selectedDifficulty}
            onChange={(e) => setSelectedDifficulty(e.target.value)}
          >
            <option value="">Filtrar por dificultad</option>
            {dificultadesDisponibles.map((dif, index) => (
              <option key={index} value={dif}>
                {dif}
              </option>
            ))}
          </select>
          <button onClick={handleClearFilters} className="clear-button">
            ↩ Limpiar filtros
          </button>
        </div>
        {error && <p style={{ color: 'red' }}>{error}</p>}
      </header>
      <ListaDeRecetas recetas={recetas} />
    </div>
  );
}

export default App;
