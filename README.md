# P4 — Board Game (ADS) · Java OOP + Interfaces + Excepciones

Implementación de la **Práctica 4** (Análisis y Diseño de Software): un juego por turnos sobre un tablero cuadrado donde dos jugadores compiten colocando fichas con reglas de vecindad, sobrescritura, fichas especiales **EN** (*enhancer*) y fichas **WA** (*wall*) que cambian de posición.

> Este repositorio está pensado como proyecto académico: el foco está en **diseño orientado a objetos**, **interfaces**, **herencia/polimorfismo** y **manejo de excepciones**.

---

## Descripción corta

Juego de tablero en Java con **interfaces**, **jerarquía de fichas** y **excepciones**: dos jugadores colocan tokens por turnos respetando vecindad y reglas de sobrescritura; incluye *enhancers* simétricos y *walls* que se reubican periódicamente.

---

## 📁 Estructura del proyecto

```
p4/
├── ForbiddenToken/
│ └── exceptions/
│ ├── ForbiddenToken.java
│ ├── Noneighborhoodcell.java
│ ├── Nooverwrittringcell.java
│ └── OutofBoardMargins.java
│
├── InvalidGame/
│ └── exceptions/
│ ├── InvalidGame.java
│ ├── MinimunSize.java
│ └── WallTokensNumber.java
│
├── game/
│ ├── BoardGame.java
│ ├── Cell.java
│ ├── Game.java
│ ├── GameLoader.java
│ ├── IBoard.java
│ ├── ICell.java
│ ├── IToken.java
│ ├── Player.java
│ └── tokens/
│ ├── EnhancerToken.java
│ ├── FixedToken.java
│ ├── GameToken.java
│ ├── MultiplierToken.java
│ ├── NormalToken.java
│ ├── PlayerToken.java
│ └── WallToken.java
│
└── Testers/
 ├── CellAndBoardTest.java
 ├── GameMain.java
 ├── GameMainFromFile.java
 └── TokensAndPlayersTest.java
```

### `game/` (núcleo)

- **`Game`**: bucle de juego por turnos (entrada por teclado), puntuación, condición de fin y cambio periódico de murallas. 
- **`BoardGame`**: implementación del tablero y reglas principales (vecindad, simetría, colocación de tokens, movimiento de murallas, lógica de enhancer). 
- **`Cell`**: celda con coordenadas y token; decide si otra celda es vecina. 
- **Interfaces**: 
 - `IBoard` define la API del tablero. 
 - `ICell` define la API de una celda. 
 - `IToken` define el contrato mínimo de una ficha. 
- **`GameLoader`**: carga parámetros básicos del juego desde fichero (size, turns, wallTokens). 

### `game/tokens/` (jerarquía de fichas)

Aquí vive la jerarquía de fichas del juego (jugador / no-jugador, sobrescribible o no, valor para puntuación, etc.). 
El enunciado fija los *strings* y reglas de impresión: **WA**, **EN**, **F**, **N**, **M**, y los guiones `--` para vacío. 

> Nota: en este README se describe lo que se deduce del uso desde `Game`/`BoardGame` y del enunciado, aunque las clases concretas de tokens están en `game/tokens/`.

### `ForbiddenToken/exceptions/` (errores al colocar fichas)

Excepciones “operacionales”: el token **no se puede colocar** en una coordenada por violar alguna regla.

- **`ForbiddenToken`**: excepción base, compone el mensaje `"Error. the token ... can not be placed..."`. 
- **`OutofBoardMargins`**: coordenadas fuera del tablero. 
- **`Nooverwrittringcell`**: la celda tiene token no sobrescribible. 
- **`Noneighborhoodcell`**: la celda no es vecina a la última ficha colocada por ese jugador. 

### `InvalidGame/exceptions/` (errores al inicializar)

Excepciones “de configuración”: el juego no se puede crear con esos parámetros.

- **`MinimunSize`**: tamaño mínimo 5. 
- **`WallTokensNumber`**: demasiadas murallas por jugador respecto al tamaño del tablero. 

---

## 🎮 Reglas del juego (implementación real)

### Inicialización del tablero (`BoardGame`)

- Tablero cuadrado de lado `size`.
- Se colocan dos fichas **normales** iniciales:
 - Jugador 1 en `(0,0)`
 - Jugador 2 en `(size-1, size-1)` 
- Se colocan `nWallPairs` **pares** de murallas (*WA*) en posiciones **simétricas** (siempre dos murallas por “par”). Se guarda la referencia de esas parejas para poder reubicarlas después. 
- Se colocan 2 fichas **enhancer** en posiciones simétricas (si es posible). 

### Vecindad (regla clave)

Un jugador sólo puede colocar una ficha en una celda que sea vecina (8-direcciones) de **su última ficha jugada**.

- La vecindad se calcula en `Cell.isNeighbor(ICell c)` (incluye diagonales). 
- La validación en el tablero se hace en `BoardGame.addPlayerToken(...)`, que:
 1) calcula vecinas `getNeighbors(row, column)` 
 2) comprueba si alguna vecina contiene el `getlasttoken()` del jugador 
 3) si no, lanza `Noneighborhoodcell`. 

### Sobrescritura

- `BoardGame.addToken(...)` impide colocar fichas fuera del tablero y en celdas con token no sobrescribible. 
- `BoardGame.addPlayerToken(...)` aplica la misma idea, pero además exige la regla de vecindad. 

### Enhancer (EN)

Cuando un jugador **coloca sobre una celda que contiene `EN`**:

1) se elimina el `EN` de esa celda 
2) se ejecuta un efecto aleatorio que coloca fichas normales del jugador 
3) el `EN` reaparece en una **celda aleatoria vacía** 

Efectos implementados en `enhancerExecution(row, column, jugador)`:

- **Caso 0**: rellena hacia abajo la **columna** desde la fila `row` con `NormalToken` hasta chocar con un token no sobrescribible o el borde. 
- **Caso 1**: rellena hacia la derecha la **fila** desde la columna `column` con `NormalToken`. 
- **Caso “vecinas”**: en el código existe un `else` que pretende llenar celdas vecinas, pero ver **Notas / detalles a revisar** (hay un bug en el random). 

### Murallas (WA) y reubicación periódica

En `Game.play()` se cuenta un acumulador (`murallas`) y **cada 3 “turnos”** se reubican las parejas de murallas:

- `Game.play()` llama a `tablero.changeWallTokensPosition()` cuando `murallas == 3`. 
- `BoardGame.changeWallTokensPosition()` mueve cada pareja a nuevas posiciones simétricas, dejando las antiguas en `null`. 

---

## 🧠 Bucle de juego (`Game.play()`)

`Game.play()` controla:

- número máximo de turnos
- turno de jugador 1 y jugador 2 por cada “turno global”
- entrada por teclado en formato `f,c` o comando `END` para terminar
- “pasar turno” si un jugador no tiene movimientos
- final automático si **ninguno de los dos** puede jugar 

### Puntuación

En la implementación, cada jugador empieza con `1.0` y se incrementa con `getValor()` de la última ficha jugada por ese jugador. 

### Generación de fichas por turno (probabilidades)

`Game.createToken(Player p)` crea al azar una ficha para el jugador:

- `MultiplierToken` si el random cae en el primer ~10% 
- `FixedToken` en el siguiente ~20% 
- `NormalToken` el resto (~70%) 

---

---

## 🧩 Jerarquía de fichas (`game/tokens/`)

Las fichas del juego implementan la interfaz **`IToken`** y siguen una jerarquía clara basada en **herencia y polimorfismo**:

```
IToken
 └── GameToken
 ├── PlayerToken
 │ ├── NormalToken
 │ ├── FixedToken
 │ └── MultiplierToken
 ├── EnhancerToken
 └── WallToken
```

### 🔹 `GameToken` (clase base)
Clase abstracta común a todas las fichas:
- Define comportamiento y propiedades comunes (por ejemplo, valor en puntuación, si es sobrescribible, y representación en texto).
- Todas las fichas del tablero derivan directa o indirectamente de esta clase.

### 🔹 `PlayerToken` (fichas de jugador)
Clase abstracta para fichas que **pertenecen a un jugador**. 
Añade la asociación con un `Player` y heredan de aquí:

- **`NormalToken` (N)** 
 - Valor típico: 1 
 - Se puede sobrescribir. 
 - Es la ficha más común (probabilidad más alta en `Game.createToken()`).

- **`FixedToken` (F)** 
 - Valor típico: 1 
 - **No se puede sobrescribir**. 
 - Sirve para bloquear posiciones del rival.

- **`MultiplierToken` (M)** 
 - Valor típico: 3 
 - Se puede sobrescribir. 
 - Aporta más puntuación que una ficha normal.

Estas tres se generan aleatoriamente en cada turno según las probabilidades definidas en `Game.createToken(...)`.

### 🔹 `EnhancerToken` (EN)
- No pertenece a ningún jugador.
- Actúa como **ficha especial**:
 - Si un jugador coloca una ficha sobre una celda con `EN`, se activa un **efecto especial**:
 - Relleno de fila, columna o vecindad con fichas normales del jugador (según el random).
 - Después del efecto, el `EN` **se recoloca en una celda vacía aleatoria** del tablero.
- Se colocan inicialmente **en posiciones simétricas** del tablero.

### 🔹 `WallToken` (WA)
- No pertenece a ningún jugador.
- **No se puede sobrescribir**.
- Representa una **muralla** que bloquea el tablero.
- Las murallas se colocan inicialmente en **pares simétricos**.
- Cada cierto número de turnos, el juego **reubica todas las murallas** en nuevas posiciones simétricas (`BoardGame.changeWallTokensPosition()`).

### 🖨️ Representación en el tablero

Según el enunciado y el uso en el código:

- `"--"` : celda vacía 
- `"N"` : `NormalToken` 
- `"F"` : `FixedToken` 
- `"M"` : `MultiplierToken` 
- `"EN"` : `EnhancerToken` 
- `"WA"` : `WallToken` 

Esto se usa tanto para mostrar el tablero por consola como para depuración.

---

## ▶️ Cómo ejecutar

### Opción A — Modo interactivo (teclado)

El enunciado propone un `GameMain` donde pides:
- tamaño del tablero
- número máximo de turnos
- número de murallas por jugador 

Ejemplo (esperado por enunciado):

```
Indicate the size of the board
5
Indicate the maximum turns
2
Indicate the number of wall tokens per user
1
Starting turn 1
...
```

### Opción B — Cargar desde fichero

`GameLoader.load(path)` lee 3 líneas (`size`, `turns`, `wallTokens`) y construye el `Game`. 

Ejemplo de fichero:

```
10
20
2
```

---

## ✅ Diseño (qué se está evaluando)

Este proyecto es “de ADS”, así que lo relevante es:

- **Interfaces** (`IBoard`, `ICell`, `IToken`) para desacoplar implementación de contrato. 
- **Excepciones específicas** para separar:
 - fallos de jugada (ForbiddenToken + derivadas) 
 - fallos de configuración (InvalidGame + derivadas) 
- **Herencia/polimorfismo** en la familia de fichas (`PlayerToken` vs tokens “globales” como EN/WA).


---

## Licencia

Para un proyecto académico, **MIT** suele ser una opción simple y suficiente.

---

## Autoría

- Carlos Riveira 
- Santiago de Prada 
(autores según cabeceras de los ficheros fuente)
