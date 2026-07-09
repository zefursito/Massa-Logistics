# Massa Logistics

## Integrantes del grupo
- Joaquin Furze
- Facundo Alamo

## Alternativa elegida
Alternativa C: Centro Logístico de Distribución Avanzada

## Estructuras de datos utilizadas
- **Diccionario** — catálogo de productos por código.
- **Conjunto** — códigos de producto ya usados (evita duplicados).
- **Cola con Prioridad (min-heap)** — detección de stock crítico.
- **Cola (FIFO)** — pedidos por orden de llegada.
- **Pila (LIFO)** — historial de movimientos.
- **Pila Enlazada** — reversión de operaciones (deshacer).
- **Cola Circular** — flota de camiones para reparto.
- **Lista Enlazada** — pedidos activos y **secuenciación de Hojas de Ruta de auditoría**.
- **Árbol binario** — jerarquía de categorías.
- **Grafo ponderado no dirigido** — zonas del depósito y rutas (cálculo de camino más corto mediante algoritmo de Dijkstra).

## Funcionalidades implementadas
- **Gestión de productos:** alta con control de código duplicado y búsqueda por código.
- **Stock crítico:** identificación del producto con menor stock.
- **Pedidos:** atención FIFO y listado de pedidos activos con altas y bajas.
- **Movimientos:** historial y reversión de la última operación.
- **Reparto:** flota de camiones en cola circular y asignación a ruta.
- **Categorías:** jerarquía en árbol binario con visualización.
- **Rutas del depósito:** grafo de zonas y cálculo del camino más corto.
- **Auditoría de Rutas y Checkpoints (Nueva mejora):** Sistema que transforma el cálculo matemático del grafo en una Hoja de Ruta estructurada en una `Lista Enlazada`. Permite realizar un control activo en tiempo real cruzando la posición reportada del vehículo con el nodo esperado, disparando alertas automáticas ante desvíos de la ruta óptima.

## Documentación de la Nueva Funcionalidad (Auditoría Dinámica)

### 1. Descripción clara de la nueva funcionalidad
Se implementó un **Sistema de Hojas de Ruta Dinámicas y Auditoría de Recorridos en Tiempo Real**. El módulo procesa activamente el camino mínimo arrojado por el algoritmo de Dijkstra en el grafo de rutas y genera un objeto estructurado en memoria que representa una secuencia ordenada de puntos de control (*checkpoints*). El sistema simula la captura de datos de sensores o terminales portátiles del depósito: a medida que el transporte avanza y reporta su posición actual, el software valida de forma indexada si cumple con la planificación o si se ha desviado de la ruta óptima.

### 2. Justificación de la mejora para el sistema
* **Control Activo sobre Información Pasiva:** Transforma la salida del camino mínimo (anteriormente un String estático) en datos operables por el sistema para monitorear el flujo físico pasillo por pasillo.
* **Eficiencia Operativa:** Garantiza que los operarios y choferes sigan estrictamente el trayecto dictaminado por el algoritmo, minimizando tiempos de traslado y consumo de recursos.
* **Mitigación de Riesgos:** El sistema rompe la pasividad informativa automatizando la detección de anomalías; ante cualquier desvío detectado por el cruce de información en memoria, dispara una alerta inmediata (`[ALERTA] DESVIO DETECTADO`).

### 3. Sinergia e Integración de los TDA con el programa
El **TDA Grafo Ponderado** (Matriz de Adyacencia) se encarga de la lógica matricial y el cálculo del camino mínimo. Al finalizar Dijkstra, las relaciones inversas del arreglo `previo[]` son invertidas cronológicamente mediante una estructura temporal y posteriormente inyectadas en los nodos del **TDA Lista Enlazada Dinámica**, conformando la Hoja de Ruta final. 

La capa lógica del programa (`Main`/Auditoría) consume de forma limpia esta lista mediante llamadas indexadas a su método `.obtener(i)`, logrando un **desacoplamiento perfecto** bajo el principio de única responsabilidad: el grafo calcula, la lista secuencia y el sistema audita.

## Link del repositorio
https://github.com/zefursito/Massa-Logistics

## Actividades realizadas por cada integrante

### Joaquin Furze
- Estructura inicial del proyecto y organización en paquetes.
- Clases base del modelo: Producto, Pedido, Movimiento y Camion.
- Implementación y corrección del Diccionario, la Cola Circular de camiones y la Lista Enlazada de pedidos.
- Gestores de inventario, pedidos y reparto (paquete service).
- Programa principal de demostración (Main) e **integración del módulo de auditoría estática en el Bloque 7**.

### Facundo Alamo
- Interfaces del sistema: IMostrable, IColeccion, IMovimiento, IPriorizable.
- Implementación de la Cola, la Pila, la Pila Enlazada, el Conjunto de códigos y la Cola con Prioridad de stock crítico.
- Árbol de categorías y Grafo de rutas del depósito.
- **Desarrollo del método de inversión y reconstrucción de caminos (`obtenerSecuenciaCamino`) en el TDA Grafo**.
- Gestor de movimientos (paquete service).
- Casos de prueba de los TDA (PruebasTDA) y redacción del README.