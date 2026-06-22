# Massa Logistics

## Integrantes del grupo
- Joaquin Furze
- Facundo Alamo

## Alternativa elegida
Alternativa C: Centro Logístico de Distribución Avanzada

## Estructuras de datos utilizadas
- Diccionario — catálogo de productos por código.
- Conjunto — códigos de producto ya usados (evita duplicados).
- Cola con Prioridad (min-heap) — detección de stock crítico.
- Cola (FIFO) — pedidos por orden de llegada.
- Pila (LIFO) — historial de movimientos.
- Pila Enlazada — reversión de operaciones (deshacer).
- Cola Circular — flota de camiones para reparto.
- Lista Enlazada — pedidos activos.
- Árbol binario — jerarquía de categorías.
- Grafo ponderado no dirigido — zonas del depósito y rutas (camino más corto).

## Funcionalidades implementadas
- Gestión de productos: alta con control de código duplicado y búsqueda por código.
- Stock crítico: identificación del producto con menor stock.
- Pedidos: atención FIFO y listado de pedidos activos con altas y bajas.
- Movimientos: historial y reversión de la última operación.
- Reparto: flota de camiones en cola circular y asignación a ruta.
- Categorías: jerarquía en árbol binario con visualización.
- Rutas del depósito: grafo de zonas y cálculo del camino más corto.

## Link del repositorio
https://github.com/zefursito/Massa-Logistics

## Actividades realizadas por cada integrante

### Joaquin Furze
- Estructura inicial del proyecto y organización en paquetes.
- Clases base del modelo: Producto, Pedido, Movimiento y Camion.
- Implementación y corrección del Diccionario, la Cola Circular de camiones y la Lista Enlazada de pedidos.
- Gestores de inventario, pedidos y reparto (paquete service).
- Programa principal de demostración (Main).

### Facundo Alamo
- Interfaces del sistema: IMostrable, IColeccion, IMovimiento, IPriorizable.
- Implementación de la Cola, la Pila, la Pila Enlazada, el Conjunto de códigos y la Cola con Prioridad de stock crítico.
- Árbol de categorías y Grafo de rutas del depósito.
- Gestor de movimientos (paquete service).
- Casos de prueba de los TDA (PruebasTDA) y redacción del README.
