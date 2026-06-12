# Massa Logistics

## Integrantes
- Joaquin Furze
- Facundo Alamo

## Alternativa elegida
Alternativa C: Centro Logístico de Distribución Avanzada

---

## Estructuras de datos utilizadas

**Diccionario**
Guarda cada producto asociado a su código. Permite buscarlo, agregarlo o eliminarlo rápidamente por código.

**Conjunto**
Lleva registro de los códigos ya usados para evitar cargar dos veces el mismo producto.

**Cola con Prioridad**
Ordena los productos según su stock. El que tiene menos cantidad queda primero, así se puede detectar rápido cuál necesita reposición.

**Cola**
Maneja los pedidos en orden de llegada. El primero en entrar es el primero en ser atendido.

**Pila**
Guarda el historial de movimientos del depósito. Siempre se ve el último movimiento registrado primero.

**Pila Enlazada**
Igual que la pila pero con nodos enlazados. Se usa para revertir operaciones, sacando la última acción realizada.

**Cola Circular**
Maneja los camiones disponibles para reparto. Cuando un camión sale de la cola, el lugar queda disponible para el siguiente que entre, sin desperdiciar espacio.

**Lista Enlazada**
Guarda los pedidos activos uno tras otro. Se puede agregar o eliminar pedidos en cualquier posición sin necesidad de un tamaño fijo.

**Árbol Binario**
Organiza las categorías del depósito en jerarquía. Por ejemplo, Electrónica contiene Computadoras y Celulares. Se muestra visualmente con ramas igual al comando tree de Linux.

**Grafo**
Representa las zonas del depósito y los caminos entre ellas con sus distancias. Permite saber si dos zonas están conectadas directamente y calcular el camino más corto entre cualquier par de zonas.

---

## Funcionalidades implementadas — Segunda etapa

**Cola Circular de Camiones**
Se registran los camiones disponibles. Cuando uno sale a hacer un reparto, su lugar en la cola queda libre para el próximo que llegue. 

**Lista Enlazada de Pedidos**
Se listan todos los pedidos activos. Se pueden agregar nuevos pedidos y eliminar los que ya fueron entregados sin que afecte al resto de la lista.

**Árbol de Categorías**
Se organiza el catálogo en categorías y subcategorías. Cada categoría puede tener varias subcategorías debajo. La visualización muestra la jerarquía completa con ramas, igual que una carpeta de archivos.

**Grafo de Rutas del Depósito**
Se definen las zonas del depósito y los pasillos que las conectan con su distancia. Dado un origen y un destino, el sistema calcula automáticamente el camino más corto entre ellos.

---

## Repositorio
https://github.com/zefursito/Massa-Logistics

---

## Actividades por integrante

### Joaquin Furze
- Estructura inicial del proyecto.
- Clases base: Producto, Pedido y Movimiento.
- Implementación y corrección del Diccionario.
- Ajustes y cambios generales en el código.
- Segunda etapa: Cola Circular de Camiones y Lista Enlazada de Pedidos.

### Facundo Alamo
- Interfaces del sistema: IMostrable, IColeccion e IMovimiento.
- Cola de Pedidos, Conjunto de códigos y Cola con Prioridad de stock crítico.
- Limpieza general del código.
- Segunda etapa: Árbol de Categorías y Grafo de Rutas del Depósito.
