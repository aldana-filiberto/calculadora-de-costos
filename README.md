# 🧮 Calculadora de Costos

Este proyecto es una aplicación de escritorio que permite calcular el costo total de un producto en base a los ingredientes utilizados y sus respectivas cantidades. Ideal para emprendimientos gastronómicos, panaderías, pastelerías o cualquier actividad que requiera estimar el precio de venta en función del costo de producción.

## 🚀 Tecnologías y herramientas utilizadas

- **Java 17** (Programación orientada a objetos)
- **Java Swing** (Interfaz gráfica)
- **Manejo de archivos**
- **Eclipse IDE** (estructura de proyecto)
- **POO** (Clases como `Ingrediente`, `Receta`)
- **Eventos e interfaces gráficas**
- **Validaciones y control de entradas del usuario**
- **Diseño simple y amigable**
- **Empaquetado con `jpackage` para Windows**
- **WiX Toolset**

## 🎯 Funcionalidades principales

- Alta de ingredientes con nombre, cantidad y costo unitario.
- Creación de recetas con múltiples ingredientes.
- Cálculo automático del costo total de una receta.
- Interfaz gráfica simple para cargar y visualizar datos.
- Cálculo del precio de venta sugerido basado en margen de ganancia.

## 📂 Estructura del proyecto

```text
calculadora-de-costos/
├── dist/                      
│   └── costos.jar
├── iconos/                   
│   └── pastel2.ico
├──src/
│   ├── calculadora/
│   │   ├── Ingrediente.java       
│   │   └── Receta.java            
│   └── interfaz/
│   |    └── CalculadoraInterface.java  
├── README.md                 
├── .gitignore
├── build/        
    └── CalculadoraDeCostos-1.0.0.msi
```

## 🖥 Instalador para Windows

Podés descargar el instalador `.msi` desde la sección de releases:

✅ No requiere instalación de Java  
🖥 Ejecutable para Windows (.exe)  
📥 [Descargar ejecutable](https://github.com/aldana-filiberto/calculadora-de-costos/releases/tag/v1.0.0)

1. Descargar el `.msi`
2. Hacer doble clic
3. ¡Listo!


## 🔧 Cosas por mejorar
- 💾 Persistencia de datos: actualmente los ingredientes y recetas no se guardan entre sesiones. Implementar almacenamiento en archivos o base de datos (por ejemplo, SQLite) mejoraría la utilidad práctica.
- 🎨 Diseño de interfaz: aunque funcional, la interfaz Swing podría beneficiarse de un rediseño más moderno, o incluso migrar a frameworks como JavaFX.
- 📊 Parámetros personalizables: permitir que el usuario defina márgenes de ganancia y monedas para distintos escenarios.
- 📦 Exportación de resultados: agregar la opción de exportar el costo y receta final en formato PDF o CSV.
- 🧪 Pruebas unitarias: incluir pruebas con JUnit para asegurar la integridad de los cálculos y facilitar mantenimiento futuro.
- 🧰 Escalabilidad: modularizar más la lógica para permitir futuras expansiones (por ejemplo, gestión de stock, múltiples productos).

---
🧠 Autoría
- Proyecto personal desarrollado para facilitar el control de costos y márgenes en la elaboración de productos a pequeña escala. Ideal como herramienta de apoyo para emprendedores o uso académico.





